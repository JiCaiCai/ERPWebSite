package com.erp.struts.actions;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class Action{
	static void GenerateResponseXML(List list,PrintWriter out){
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
}
