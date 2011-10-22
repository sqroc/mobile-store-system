/**
 * 提交订单
 * 调用方法  地址栏后面加uid（用户ID）
 * XML中的节点解释：
 * allprice 总价格
 * num  订单号
 * state 订单状态
 * date 下订单日期
 * realname 真实姓名
 */
package ms.xml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ms.model.Cart;
import ms.model.Cartinfo;
import ms.model.Item;
import ms.model.OrderItem;
import ms.model.User;
import ms.service.CartManager;
import ms.service.CartinfoManager;
import ms.service.ItemManager;
import ms.service.OrderItemManager;
import ms.service.UserManager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class Orderforxml
 */
public class Orderforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orderforxml() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		String uid = request.getParameter("uid");
		int uid2 = Integer.parseInt(uid);
		CartinfoManager cartinfoManager = (CartinfoManager) ctx
				.getBean("cartinfoManager");
		UserManager userManager = (UserManager) ctx
				.getBean("userManager");
		User user = null;
		try {
			user = userManager.loadById(uid2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Cartinfo> cartinfos = null;
		double allprice = 0.0;
		try {
			cartinfos = cartinfoManager.loadbyuid(uid2);
			for (Cartinfo cartinfo : cartinfos) {
				allprice += cartinfo.getAllprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CartManager cartManager = (CartManager) ctx
				.getBean("cartManager");
		Cart cart = new Cart();
		cart.setAllprice(allprice);
		cart.setDate(new Date());
		cart.setUser(user);
		cart.setState("未付款");
		Date date = new Date();
	    long time = date.getTime();
	    String num = time+uid+"";
	    cart.setNum(num);
		try {
			cartManager.add(cart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OrderItemManager orderItemManager = (OrderItemManager) ctx
				.getBean("orderItemManager");
		ItemManager itemManager = (ItemManager) ctx
				.getBean("itemManager");
		Cart cart2 = null;
		cart2 = cartManager.loadByNum(num);
		for (Cartinfo cartinfo : cartinfos) {
			Item item = cartinfo.getItem();
			int number = cartinfo.getNumber();
			number = item.getStore_num() - number;
			item.setStore_num(number);
			try {
				itemManager.modify(item);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OrderItem oi = new OrderItem();
			oi.setCart(cart2);
			oi.setItem(cartinfo.getItem());
			oi.setNumber(cartinfo.getNumber());
			oi.setPrice(cartinfo.getAllprice());
			try {
				orderItemManager.add(oi);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cartinfo.setIsorder(1);
			try {
				cartinfoManager.modify(cartinfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Document d = DocumentHelper.createDocument();
		// 创建根节点
		Element elements = d.addElement("carts");
		
			Element element = elements.addElement("cart");
			element.addElement("allprice").addText(
					String.valueOf(cart2.getAllprice()));
			element.addElement("num").addText(cart2.getNum());
			element.addElement("state").addText(
					cart2.getState());
			element.addElement("date").addText(
					cart2.getDate()+"");
			element.addElement("realname").addText(
					cart2.getUser().getRealname());
		d.setXMLEncoding("utf-8");
		writer.print(d.asXML());
		
		
	}

}
