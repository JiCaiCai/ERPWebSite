package com.erp.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class LogoutAction extends DispatchAction{
	@Override
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		forward=mapping.findForward("Home");
		HttpSession session=request.getSession(false);
		if(session!=null){
			session.removeAttribute("userName");
		}
		return forward;
	}
}
