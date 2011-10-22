/**
 * 用户信息
 * 调用方法  地址栏后面加name（用户名）
 * XML中的节点解释：
 * 见ms.model中的bean的属性解释
 */
package ms.xml;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class UserInfoxml
 */
public class UserInfoxml extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoxml() {
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
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();  
		ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		UserManager userManager = (UserManager) ctx.getBean("userManager");
		User user = null;
		try {
			user = userManager.loadByName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document d = DocumentHelper.createDocument();
		// 创建根节点
		Element elements = d.addElement("users");
	
			Element element = elements.addElement("user");
			element.addElement("uid").addText(
					String.valueOf(user.getUid()));
			element.addElement("realname").addText(user.getRealname());
			element.addElement("address").addText(
					user.getAddress());
			element.addElement("phone").addText(
					user.getPhone());
			element.addElement("money").addText(
					user.getMoney()+"");
	
		d.setXMLEncoding("utf-8");
		writer.print(d.asXML());
		
	}

}
