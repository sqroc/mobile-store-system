/**
 * 购物车中各个商品信息
 * 调用方法  地址栏后面加uid（用户ID）
 * XML中的节点解释：
 * allprice 价格
 * item_name  商品名称
 * number 商品数量
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
import ms.model.Item;
import ms.service.CartinfoManager;
import ms.service.ItemManager;
import ms.service.ItemProviderManager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class Caerinfoforxml
 */
public class Caerinfoforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Caerinfoforxml() {
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
		String uid = request.getParameter("uid");
		int uid2 = Integer.parseInt(uid);
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();  
		ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		CartinfoManager cartinfoManager = (CartinfoManager) ctx.getBean("cartinfoManager");
		List<Cartinfo> cartinfos = null;
		try {
			cartinfos = cartinfoManager.loadbyuid(uid2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document d = DocumentHelper.createDocument();
		// 创建根节点
		Element elements = d.addElement("cartinfos");
		for (Cartinfo cartinfo : cartinfos) {
			Element element = elements.addElement("cartinfo");
			element.addElement("allprice").addText(
					String.valueOf(cartinfo.getAllprice()));
			element.addElement("item_name").addText(cartinfo.getItem().getItem_name());
			element.addElement("item_id").addText(String.valueOf(cartinfo.getItem().getItem_id()));
			element.addElement("number").addText(
					String.valueOf(cartinfo.getNumber()));
		}
		d.setXMLEncoding("utf-8");
		writer.print(d.asXML());
	}

}
