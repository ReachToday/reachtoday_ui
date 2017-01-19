package com.reachtoday.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reachtoday.server.DatabaseUtils;

public class MainAction extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
	  System.out.println("in mainacrion file");
	  response.addHeader("Access-Control-Allow-Origin", "*");
	  response.addHeader("Access-Control-Allow-Methods", "POST, GET");
	  
	  HttpSession session = request.getSession(true);
	  PrintWriter out = response.getWriter();

	  String requestMode=request.getParameter("mode");
	  
	  if(requestMode.equals("1")){
		  
      /*String practiceName = "USA Medical Group";
      String username = "Brandy";
      out.println('[');
      out.println("  {");
      out.print("    \"practicename\": \"");
      out.print(practiceName);
      out.println("\",");
      out.print("    \"username\": \"");
      out.print(username);
      out.println("\"");
      out.println("  }");
      out.println(']');
      out.flush();*/
		  try{
		  DatabaseUtils dbUtils = new DatabaseUtils("");
//	    	responseObject.clear();
	    	//List resourceData = new ArrayList<>();
	    	String qry="select * from testdbsample";
	    	//resourceData.add(dbUtils.executeQueryToList(qry));
	    	List test=dbUtils.executeQueryToList(qry);
	    	out.println(""+test.get(0));
	    	
		  }catch(Exception e)
		  {
			  String practiceName = "In Exception"+e;
		      String username = "Brandy";
		      out.println('[');
		      out.println("  {");
		      out.print("    \"practicename\": \"");
		      out.print(practiceName);
		      out.println("\",");
		      out.print("    \"username\": \"");
		      out.print(username);
		      out.println("\"");
		      out.println("  }");
		      out.println(']');
		      out.flush();
		  }
  }
	  else if (requestMode=="2"){
		  
		  String menu1 = "Home";
	      int menuid1 = 1;
	      
	      String menu2 = "Schedule";
	      int menuid2 = 2;
	      
	      String menu3 = "Chart";
	      int menuid3 = 3;
	      
	      String menu4 = "Billing";
	      int menuid4 = 4;
	      
	      
	      out.println('[');
	      
	      out.println("  {");
	      out.print("    \"menuname\": \"");
	      out.print(menu1);
	      out.println("\",");
	      out.print("    \"menuid\": ");
	      out.print(menuid1);
	      out.println("  },");
	      
	      out.println("  {");
	      out.print("    \"menuname\": \"");
	      out.print(menu2);
	      out.println("\",");
	      out.print("    \"menuid\": ");
	      out.print(menuid2);
	      out.println("  },");
	      
	      out.println("  {");
	      out.print("    \"menuname\": \"");
	      out.print(menu3);
	      out.println("\",");
	      out.print("    \"menuid\": ");
	      out.print(menuid3);
	      out.println("  },");
	      
	      out.println("  {");
	      out.print("    \"menuname\": \"");
	      out.print(menu4);
	      out.println("\",");
	      out.print("    \"menuid\": ");
	      out.print(menuid4);
	      out.println("  },");
	      
	      
	      out.println(']');
	      out.flush();
		  
	  }
  }
}

