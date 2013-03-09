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
import com.erp.util.ColumnNameMapper;

public class StockAction extends DispatchAction{

	public ActionForward GetColumnName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(ColumnNameMapper.Stock);
		return null;
	}

	public ActionForward Query(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out=response.getWriter();
		StockDAO service=new StockDAO();
		List list=service.Query();
		if(list!=null&&list.size()>0)
			Action.GenerateResponseXML(list,out);
		return null;
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
