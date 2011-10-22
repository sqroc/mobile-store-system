/**
 * 付款
 * 调用方法  地址栏后面加uid（用户ID）num（订单号）
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
import ms.model.User;
import ms.service.CartManager;
import ms.service.UserManager;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class PayOrderforxml
 */
public class PayOrderforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayOrderforxml() {
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
		String num = request.getParameter("num");
		
		CartManager cartManager = (CartManager) ctx
				.getBean("cartManager");
		UserManager userManager = (UserManager) ctx
				.getBean("userManager");
		Cart cart = null;
		cart = cartManager.loadByNum(num);
		cart.setState("已付款，未发货");
		try {
			cartManager.modify(cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		double price = cart.getAllprice();
		User user = null;
		try {
			user = userManager.loadById(uid2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setMoney(user.getMoney()-price);
		try {
			userManager.modify(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.print("1");
		
	}

}
