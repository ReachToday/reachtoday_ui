package com.reachtoday.server;

import java.sql.Connection;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseConnection {

	DataSource ds = null;
	Connection con = null;

	public DatabaseConnection(String connectionString) throws Exception{
		createConnection(connectionString);
	}
	protected void createConnection(String connectionString)throws Exception{
		if ( connectionString == null || connectionString == "" ){
			throw new SQLClientInfoException(); // default is used, must have to use custom exception for this case
		}
	    Context initCtx = new InitialContext();
	    ds = (DataSource)initCtx.lookup(connectionString);
	}


	  /** Returns the Connection object for the DataSource
	   **/
	  public Connection getConnection() throws SQLException{
		try{
			if( con == null ) con = ds.getConnection();
		}catch(SQLException e){
			throw e;
		}
	    return con;
	  }

	  public void destroy()throws Exception{
		  con.close();
	  }

	@Override
	protected void finalize() throws Throwable {
		destroy();
		super.finalize();
	}

}
