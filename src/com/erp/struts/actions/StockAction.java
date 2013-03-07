package com.erp.struts.actions;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.VariableElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.erp.hibernate.beans.Stock;
import com.erp.hibernate.dao.StockDAO;

public class StockAction extends DispatchAction{
	
	public ActionForward GetColumnName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		out.print("materialID;name;stockNum");
		return null;
	}

	public ActionForward Query(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out=response.getWriter();
		StockDAO service=new StockDAO();
		List<Stock> stocks=service.Query();
		if(stocks!=null&&stocks.size()>0)
			GenerateResponseXML(stocks,out);
		return null;
	}
	
	private void GenerateResponseXML(List list,PrintWriter out){
		out.print("<?xml version='1.0' encoding='utf-8'?>");
		out.print("<table>");
		Iterator it=list.iterator();
		while(it.hasNext()){
			out.print("<row>");
			Object[] objects=(Object[])it.next();
			for(int i=0;i<objects.length;i++)
				out.print("<cell>"+objects[i]+"</cell>");
			out.print("</row>");
		}
		out.print("</table>");
	}
	
	public ActionForward Insert(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		String materialID=request.getParameter("materialID");
		int stockNum=Integer.parseInt(request.getParameter("stockNum"));
		StockDAO service=new StockDAO();
		Stock stock=new Stock(materialID,stockNum);
		boolean isSuccess=service.Insert(stock);
		if(isSuccess)
			out.print("success");
		else 
			out.print("fail");
		return null;
	}
	
	public ActionForward Delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		StockDAO service=new StockDAO();
		boolean isSuccess=service.Delete(id);
		if(isSuccess)
			out.print("success");
		else
			out.print("fail");
		return null;
	}
	
	public ActionForward Update(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		String materialID=request.getParameter("materialID");
		int stockNum=Integer.parseInt(request.getParameter("stockNum"));
		StockDAO service=new StockDAO();
		Stock stock=new Stock(materialID,stockNum);
		boolean isSuccess=service.Update(stock);
		if(isSuccess)
			out.print("success");
		else 
			out.print("fail");
		return null;
	}

}
