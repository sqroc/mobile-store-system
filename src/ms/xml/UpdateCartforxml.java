/**
 * 更新购物车中的商品信息
 * 调用方法  地址栏后面加uid（用户ID）itemid（商品名，多个商品名用逗号隔开）number（数量用逗号隔开）
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

import ms.model.Cartinfo;
import ms.service.CartinfoManager;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class UpdateCartforxml
 */
public class UpdateCartforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartforxml() {
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
		String itemid = request.getParameter("itemid");
		String number = request.getParameter("number");
		String []item_id = itemid.split(",");
		String []nums = number.split(",");
		
		int uid2 = Integer.parseInt(uid);
		
		CartinfoManager cartinfoManager = (CartinfoManager) ctx.getBean("cartinfoManager");
		List<Cartinfo> cartinfos = null;
		try {
			cartinfos = cartinfoManager.loadbyuid(uid2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<item_id.length;i++){
			for(Cartinfo cartinfo : cartinfos){
				if(cartinfo.getItem().getItem_id() == Integer.parseInt(item_id[i])){
					cartinfo.setNumber(Integer.parseInt(nums[i]));
					try {
						cartinfoManager.modify(cartinfo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}

}
