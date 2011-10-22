/**
 * 用户登录
 * 调用方法  地址栏后面加name（用户名）password（密码）
 * 返回信息解释：
 * 0登录失败，1登录成功
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

import ms.model.Item;
import ms.model.User;
import ms.service.UserManager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class Userforxml
 */
public class Userforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userforxml() {
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
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();  
		ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		UserManager userManager = (UserManager) ctx.getBean("userManager");
		boolean ok = false;
		try {
			ok = userManager.check(name, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ok == false){
			writer.print("-1");
		}else{
			int uid=0;
			try {
				uid = userManager.loadByName(name).getUid();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.print(uid);
		}
		
	}

}
