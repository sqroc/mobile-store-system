/**
 * 根据分类ID搜索出商品
 * 调用方法  地址栏后面加cid（分类ID）from（第几条）to(到第几条)
 * XML中的节点解释：
 * category 分类
 * item_name  中文名称
 * standard 规格
 * product_num  产品编号(批准文号)
 * price  售价
 * 
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
 * Servlet implementation class ItemByCategoryforxml
 */
public class ItemByCategoryforxml extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemByCategoryforxml() {
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
		String cid = request.getParameter("cid");
		int from2 = Integer.parseInt(from);
		int to2 = Integer.parseInt(to);
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();  
		ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		ItemManager itemManager = (ItemManager) ctx.getBean("itemManager");
		ItemProviderManager itemProviderManager = (ItemProviderManager) ctx.getBean("itemProviderManager");
		List<Item> items = null;
		ItemProvider itemProvider = null;
		try {
			items = itemManager.getItems(cid,from2,to2,3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document d = DocumentHelper.createDocument();
		// 创建根节点
		Element elements = d.addElement("items");
		for (Item item : items) {
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
		}
		d.setXMLEncoding("utf-8");
		writer.print(d.asXML());
	}

}
