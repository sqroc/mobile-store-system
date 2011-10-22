package ms.util;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ms.dao.BackDetailDao;
import ms.dao.BackDrawDao;
import ms.dao.DrawDetailDao;
import ms.dao.DrawItemDao;
import ms.dao.PorderDao;
import ms.dao.PorderDetailDao;
import ms.model.BackDetail;
import ms.model.BackDraw;
import ms.model.DrawDetail;
import ms.model.DrawItem;
import ms.model.Porder;
import ms.model.PorderDetail;
import ms.model.Store;
import ms.service.PorderDetailManager;
import ms.service.impl.PorderDetailManagerImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
public class PrintWord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServletContext context;
	ApplicationContext ctx;
    public PrintWord() {
        super();
       
    }
    
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 context = request.getSession().getServletContext();
	     ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
            String typeString = request.getParameter("type");
            String idString = request.getParameter("id");
            String pfileName = request.getParameter("pfileName");
        
            
            if(typeString.equalsIgnoreCase("linghuo"))
            {
            	printDrawItem(typeString,idString,pfileName,request,response);
            	
            }
            else if(typeString.equalsIgnoreCase("jinhuo"))
            {
            	printPorder(typeString,idString,pfileName,request,response);
            	
            }
            else if(typeString.equalsIgnoreCase("tuihuo"))
            {
            	printBackDraw(typeString,idString,pfileName,request,response);
            }
            
            
	}
	
	
	public void printPorder(String type,String id,String pfileName,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		
		response.setContentType("application/doc ; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		PorderDao porderDao = (PorderDao) ctx.getBean("PorderDao");
	    Porder tempPorder =  porderDao.loadByNum(id);
	    
	    
	    if(tempPorder!=null)
	    {
	    	out.println("进货单单据 :  ");
	    	out.println("");
        	out.println("");
	    	out.println("单据编号:  "+tempPorder.getCode());
	    	out.println("操作员姓名:  "+tempPorder.getOperator());
	    	out.println("经办人姓名:  "+tempPorder.getExeman());
	    	out.println("交货日期:  "+tempPorder.getRevDate());
	    	out.println("备注:  "+tempPorder.getMemo());
	    	out.println("金额:  "+tempPorder.getAmount());
	    	out.println("单据日期     "+tempPorder.getBillDate());
	    	out.println("");
	    	out.println("");
	    	

	    	
	    	
	     List<PorderDetail> porderDetails = null ;
	     
	     
	     PorderDetailDao porderDetailDao = (PorderDetailDao) ctx.getBean("PorderDetailDao");
	     
	     
	     
	     
	    // PorderDetailManager porderDetailManager = new PorderDetailManagerImpl();
	     
	     System.out.println(id);
	     
		 porderDetails = porderDetailDao.getPorderDetailsByNum(id);
				 
		if(porderDetails!=null)
		{
			out.println("详细货品信息");
			for(int i=0 ;i<porderDetails.size();i++)
			{
				out.println("货品:        "+(i+1));
				out.println("");
				out.println("货品名        "+porderDetails.get(i).getItem().getItem_name());
				out.println("数量        "+porderDetails.get(i).getQuantity());
				out.println("总价格        "+porderDetails.get(i).getPrice());
			}
	    
	    }
	  // String filename = pfileName+".doc";
		
		String filename = "jin.doc";
		response.setHeader("Content-Disposition", "attachment; filename="+filename);    
	    }
	}
	public void printBackDraw(String type,String id,String pfileName,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		
		
		response.setContentType("application/doc ; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		BackDrawDao backDrawDao = (BackDrawDao) ctx.getBean("BackDrawDao");
		BackDraw backDraw  = backDrawDao.loadByNum(id);
	  
        if(backDraw!=null)
	    {
	    	out.println("退货单单据    " );
	    	out.println("");
	    	out.println("");
	    	out.println("单据编号:  "+backDraw.getCode());
	    	out.println("操作员姓名:  "+backDraw.getOperator());
	    	out.println("退货人姓名:  "+backDraw.getBackMan());
	    	out.println("仓库ID:  "+backDraw.getStore().getStoreid());
	    	out.println("总价值:  "+backDraw.getTotalM());
	    	out.println("备注:  "+backDraw.getMemo());
	    	out.println("单据日期:  "+backDraw.getBillDate());
	    	out.println("");
	    	out.println("");
	    	
	     BackDetailDao backDetailDao = (BackDetailDao) ctx.getBean("BackDetailDao");
	    List<BackDetail> backDetails = null ;
	    backDetails = backDetailDao.getBackDetailsByNum(id);
		
		if(backDetails!=null)
		{
			out.println("详细货品信息");
		
			for(int i=0 ;i<backDetails.size();i++)
			{
				out.println("货品        "+(i+1));
				out.println("货品名        "+backDetails.get(i).getItem().getItem_name());
				out.println("数量        "+backDetails.get(i).getQuantity());
				out.println("单笔总价格        "+backDetails.get(i).getPrice());
		     }

			

	    }
	    		
		
		//String filename = pfileName+".doc";
		String filename = "tui.doc";
		response.setHeader("Content-Disposition", "attachment; filename="+filename);    
	
	}
	
	}
	
	
	public void printDrawItem(String type,String id,String pfileName,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		
		
		response.setContentType("application/doc ; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		DrawItemDao drawItemDao = (DrawItemDao) ctx.getBean("DrawItemDao");
	    DrawItem drawItem  = drawItemDao.loadByNum(id);
	  
        if(drawItem!=null)
	    {
	    	out.println("领货单单据");
	    	out.println("");
	    	out.println("");
	    	out.println("单据编号:  "+drawItem.getCode());
	    	out.println("操作员姓名:  "+drawItem.getOperator());
	    	out.println("领料人姓名:  "+drawItem.getDrawMan());
	    	//out.println("仓库ID:  "+drawItem.getStore().getStoreid());
	    	out.println("备注:  "+drawItem.getMemo());
	    	out.println("单据日期:  "+drawItem.getBillDate());
	    	out.println("");
	    	out.println("");
	    	
	 
	    DrawDetailDao drawDetailDao = (DrawDetailDao) ctx.getBean("DrawDetailDao");
	    List<DrawDetail> drawDetais = null ;
		drawDetais = drawDetailDao.getDrawDetailsByNum(id);
		
		if(drawDetais!=null)
		{
			out.println("详细货品信息");
		
			for(int i=0 ;i<drawDetais.size();i++)
			{
				out.println("货品        "+(i+1));
				out.println("货品名        "+drawDetais.get(i).getItem().getItem_name());
				out.println("数量        "+drawDetais.get(i).getQuantity());
				out.println("总价格        "+drawDetais.get(i).getAprice());
		     }

			

	    }
	    		
		
		//String filename = pfileName+".doc";
		String filename = "ling.doc";
		response.setHeader("Content-Disposition", "attachment; filename="+filename);    
	
	}
	
	}
}
