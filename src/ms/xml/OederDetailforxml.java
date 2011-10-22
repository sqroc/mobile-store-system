/**
 * 订单信息
 * 调用方法  地址栏后面加from（第几条）to(到第几条)uid(用户ID)
 * XML中的节点解释：
 * num  订单号
 */
package ms.xml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ms.model.Cart;
import ms.model.Item;
import ms.service.CartManager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class OederDetailforxml
 */
public class OederDetailforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OederDetailforxml() {
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
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String uid = request.getParameter("uid");
		int from2 = Integer.parseInt(from);
		int to2 = Integer.parseInt(to);
		int uid2 = Integer.parseInt(uid);
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		CartManager cartManager = (CartManager) ctx.getBean("cartManager");
		List<Cart> carts = null;
		try {
			carts = cartManager.getCartsByUid(uid2, from2, to2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document d = DocumentHelper.createDocument();
		// 创建根节点
		Element elements = d.addElement("orders");
		for (Cart cart : carts) {
			Element element = elements.addElement("order");
			element.addElement("num").addText(cart.getNum());
			element.addElement("date").addText(cart.getDate().toString());
			element.addElement("state").addText(cart.getState());
			element.addElement("allprice").addText(
				String.valueOf(cart.getAllprice()));
		}
		d.setXMLEncoding("utf-8");
		writer.print(d.asXML());
	}

}
