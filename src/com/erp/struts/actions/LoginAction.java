package com.erp.struts.actions;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.erp.hibernate.beans.User;
import com.erp.hibernate.dao.UserDAO;
import com.erp.struts.forms.LoginForm;

public class LoginAction extends DispatchAction{
	private UserDAO service;

	@Override
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		PrintWriter out=response.getWriter();
		User user=service.Valid(userName,password);
		if(user!=null){
			out.print(userName);
			HttpSession session=request.getSession(true);
			session.setAttribute("userName",userName);
			ActionForward forward=new ActionForward();
			forward=mapping.findForward("home");
			return null;
		}else{
			out.print("Error");
			return null;
		}
	}

	public UserDAO getService(){
		return service;
	}

	public void setService(UserDAO service){
		this.service=service;
	}
}
