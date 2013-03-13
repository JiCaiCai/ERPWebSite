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
import com.erp.hibernate.beans.Level;
import com.erp.hibernate.dao.LevelDAO;
import com.erp.util.ColumnNameMapper;

public class LevelAction extends DispatchAction{
	
	private LevelDAO service;

	public ActionForward GetColumnName(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(ColumnNameMapper.Level);
		return null;
	}

	public ActionForward Query(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out=response.getWriter();
		List list=service.Query();
		if(list!=null&&list.size()>0)
			Action.GenerateResponseXML(list,out);
		return null;
	}

	public ActionForward Insert(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		String code=request.getParameter("code");
		String level=request.getParameter("level");
		Level lev=new Level();
		lev.setCode(code);
		lev.setLevel(level);
		boolean isSuccess=service.Insert(lev);
		if(isSuccess)
			out.print("success");
		else
			out.print("fail");
		return null;
	}

	public ActionForward Delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		String code=request.getParameter("id");
		boolean isSuccess=service.Delete(code);
		if(isSuccess)
			out.print("success");
		else
			out.print("fail");
		return null;
	}

	public ActionForward Update(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		String code=request.getParameter("code");
		String level=request.getParameter("level");
		boolean isSuccess=service.Update(code,level);
		if(isSuccess)
			out.print("success");
		else
			out.print("fail");
		return null;
	}

	public LevelDAO getService(){
		return service;
	}

	public void setService(LevelDAO service){
		this.service=service;
	}
}
