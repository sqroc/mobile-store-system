/**
 * 提交放入购物车中的商品信息
 * 调用方法  地址栏后面加uid（用户ID）itemid（商品名）number（数量）price（总价格=单价*数量）
 */
package ms.xml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ms.model.Cartinfo;
import ms.model.Item;
import ms.service.CartinfoManager;
import ms.service.ItemManager;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class Cartforxml
 */
public class Cartforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cartforxml() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		String uid = request.getParameter("uid");
		String itemid = request.getParameter("itemid");
		String number = request.getParameter("number");
		String price = request.getParameter("price");
		int uid2 = Integer.parseInt(uid);
		int itemid2 = Integer.parseInt(itemid);
		int number2 = Integer.parseInt(number);
		double price2 = Double.parseDouble(price);
		Cartinfo cartinfo = new Cartinfo();
		cartinfo.setUid(uid2);
		cartinfo.setNumber(number2);
		ItemManager itemManager = (ItemManager) ctx.getBean("itemManager");
		Item item = null;
		try {
			item = itemManager.loadById(itemid2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cartinfo.setItem(item);
		cartinfo.setAllprice(price2);
		cartinfo.setIsorder(0);
		CartinfoManager cartinfoManager = (CartinfoManager) ctx
				.getBean("cartinfoManager");
		try {
			cartinfoManager.add(cartinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
