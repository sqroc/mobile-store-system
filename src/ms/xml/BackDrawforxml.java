/**
 * 退货
 * 调用方法  地址栏后面加num（订单号）
 * 
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

import ms.model.BackDetail;
import ms.model.BackDraw;
import ms.model.Cart;
import ms.model.OrderItem;
import ms.model.User;
import ms.service.BackDetailManager;
import ms.service.BackDrawManager;
import ms.service.CartManager;
import ms.service.OrderItemManager;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class BackDrawforxml
 */
public class BackDrawforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BackDrawforxml() {
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
		BackDrawManager backDrawManager = (BackDrawManager) ctx
				.getBean("backDrawManager");
		CartManager CartManager = (CartManager) ctx.getBean("cartManager");
		Cart cart = CartManager.loadByNum(num);
		User user = cart.getUser();
		BackDraw backDraw = new BackDraw();
		backDraw.setCode(num);
		backDraw.setUpdateTime(new Date());
		backDraw.setTotalM(cart.getAllprice());
		backDraw.setState("未处理");
		backDraw.setUser(user);
		backDrawManager.save(backDraw);
		BackDetailManager backDetailManager = (BackDetailManager) ctx
				.getBean("backDetailManager");
		OrderItemManager orderItemManager = (OrderItemManager) ctx
				.getBean("orderItemManager");
		List<OrderItem> orderItems = orderItemManager.getOrderItembyCart(cart);
		for (int i = 0; i < orderItems.size(); i++) {
			BackDetail backDetail = new BackDetail();
			backDetail.setBackDraw(backDraw);
			backDetail.setCode(num);
			backDetail.setItem(orderItems.get(i).getItem());
			backDetail.setPrice(orderItems.get(i).getPrice());
			backDetail.setQuantity(orderItems.get(i).getNumber());
			backDetailManager.save(backDetail);
		}

	}

}
