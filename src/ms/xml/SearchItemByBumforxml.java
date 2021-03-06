/**
 * 通过批准文号搜索商品
 * 调用方法  地址栏后面加pnum    （字段为product_num）
 * XML中的节点解释：
 * 见ms.model中的bean的属性解释
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
import ms.model.ItemProvider;
import ms.service.ItemManager;
import ms.service.ItemProviderManager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class SearchItemByBumforxml
 */
public class SearchItemByBumforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItemByBumforxml() {
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
		String pnum = request.getParameter("pnum");
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();  
		ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		ItemManager itemManager = (ItemManager) ctx.getBean("itemManager");
		ItemProviderManager itemProviderManager = (ItemProviderManager) ctx.getBean("itemProviderManager");
		Item item = null;
		ItemProvider itemProvider = null;
		try {
			item = itemManager.loadByPnum(pnum);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		if(item !=null){
			Document d = DocumentHelper.createDocument();
			// 创建根节点
			Element elements = d.addElement("items");
				itemProvider = itemProviderManager.loadbyItemid(item.getItem_id());
				Element element = elements.addElement("item");
				element.addElement("item_id").addText(
						String.valueOf(item.getItem_id()));
				element.addElement("item_name").addText(item.getItem_name());
				element.addElement("img_url").addText(item.getImg_url());
				element.addElement("category").addText(
						item.getCategory().getName());
				element.addElement("standard").addText(
						item.getStandard());
				element.addElement("product_num").addText(
						item.getProduct_num());
				element.addElement("price").addText(
					String.valueOf(itemProvider.getPrice()));

			d.setXMLEncoding("utf-8");
			writer.print(d.asXML());
		}else{
			writer.print("0");
		}
		
	}

}
