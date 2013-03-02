package com.erp.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.erp.struts.forms.LoginForm;

public class LoginAction extends DispatchAction{

	@Override
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		LoginForm loginForm=(LoginForm)form;
		ActionForward forward=new ActionForward();
		forward=mapping.findForward("Home");
		HttpSession session=request.getSession(true);
		session.setAttribute("userName",loginForm.getUserName());
		return forward;
	}
}
