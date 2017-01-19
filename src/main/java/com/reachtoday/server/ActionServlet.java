package com.reachtoday.server;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionServlet {
	Map responseObject = new HashMap();
	public void init(ServletConfig config);
	public String performAction(HttpServletRequest request,HttpServletResponse response) throws Exception;
	public void destroy();

}
