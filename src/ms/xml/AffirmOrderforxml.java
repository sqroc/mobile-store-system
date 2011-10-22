/**
 * 确认收获 
 * 调用方法  地址栏后面加num（订单号）
 * 返回信息为1表示付款成功
 */
package ms.xml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ms.model.Cart;
import ms.service.CartManager;
import ms.service.UserManager;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class AffirmOrderforxml
 */
public class AffirmOrderforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AffirmOrderforxml() {
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
		String num = request.getParameter("num");

		CartManager cartManager = (CartManager) ctx.getBean("cartManager");
		Cart cart = null;
		cart = cartManager.loadByNum(num);
		if(cart.getState().equals("已发货")){
			cart.setState("确认收货");
			try {
				cartManager.modify(cart);
			} catch (Exception e) {
				e.printStackTrace();
			}
			writer.print("1");
		}else{
			writer.print("-1");
		}
		
		
	}

}
