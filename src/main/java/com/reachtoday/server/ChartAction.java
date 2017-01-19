package com.reachtoday.server;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reachtoday.server.ActionServlet;
import com.reachtoday.server.DatabaseUtils;


public class ChartAction implements ActionServlet {
    @Override
    public void init(ServletConfig config) {
        // TODO Auto-generated method stub

    }

    @Override
    public String performAction(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	DatabaseUtils dbUtils = new DatabaseUtils("");
    	responseObject.clear();
    	//List resourceData = new ArrayList<>();
    	String qry="select * from testdbsample";
    	//resourceData.add(dbUtils.executeQueryToList(qry));
    	responseObject.put("testPojo",dbUtils.executeQueryToList(qry));
    	 System.out.println(responseObject);
		return null;
      }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
    
  
  

}

