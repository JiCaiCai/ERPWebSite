package com.erp.struts.actions;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.erp.hibernate.beans.Stock;
import com.erp.hibernate.dao.LevelDAO;
import com.erp.hibernate.dao.MaterialDAO;
import com.erp.hibernate.dao.StockDAO;
import com.erp.util.ColumnNameMapper;

public class MaterialAction extends DispatchAction{

	public ActionForward GetColumnName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(ColumnNameMapper.Material);
		return null;
	}

	public ActionForward Query(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out=response.getWriter();
		MaterialDAO service=new MaterialDAO();
		List list=service.Query();
		if(list!=null&&list.size()>0)
			Action.GenerateResponseXML(list,out);
		return null;
	}

	public ActionForward Insert(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	public ActionForward Delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		String ID=request.getParameter("id");
		MaterialDAO service=new MaterialDAO();
		boolean isSuccess=service.Delete(ID);
		if(isSuccess)
			out.print("success");
		else
			out.print("fail");
		return null;
	}

	public ActionForward Update(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		String ID=request.getParameter("ID");
		String name=request.getParameter("name");
		String specification=request.getParameter("routeID");
		String routeID=request.getParameter("routeID");
		MaterialDAO service=new MaterialDAO();
		boolean isSuccess=service.Update(ID,name,specification,routeID);
		if(isSuccess)
			out.print("success");
		else
			out.print("fail");
		return null;
	}

}
