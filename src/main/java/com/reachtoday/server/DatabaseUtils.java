package com.reachtoday.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 * Database utility class which will be accessed from the application for the various database related activities.
 * This class utilize the  </b>Apache Common DBUtils api http://commons.apache.org/proper/commons-dbutils </b>
 * @author Arvind
 *
 *
 */
public class DatabaseUtils {
	DatabaseConnection dbc = null;



	public DatabaseUtils(String connectionString) throws Exception {
		dbc = new DatabaseConnection("jdbc/DSTest");
	}
	/**
	 * generate List<Objects[]> from the query result. We can also pass parameters for the query, while pasing param we need to follow prepared statement formats .
	 * eg  <i> new DatabaseUtils.executeQueryToList("SELECT * FROM patient WHERE name=?", h, "John Doe"); </i>
	 * for more details check http://commons.apache.org/proper/commons-dbutils/examples.html
	 * @param qry query which needs to be executed; <i>mandatory</i>
	 * @param param Parameters which is required to
	 * @return
	 * @throws SQLException
	 */
	public List executeQueryToList(String qry, Object... param) throws SQLException{
		ResultSetHandler<List> rstHandler = new ResultSetHandler<List>() {
		    private RowProcessor convert = BasicRowProcessor.instance();
			@Override
			public List handle(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				  List results = new ArrayList();

			        while (rs.next()) {
			            results.add(this.convert.toMap(rs));
			        }

			        return results;
			}
		};

		//if param are provided then it will be automatically assigned to the query by means of prepared statement internally
		if (param==null){
			// Execute the query and get the results back from the handler 		// quries which not have params to pass during execution
			return new QueryRunner().query(dbc.getConnection(),qry, rstHandler);
		}else{
			// Execute the query and get the results back from the handler 		// params are passed as like prepared statement
			return new QueryRunner().query(dbc.getConnection(),qry, rstHandler, param);
		}
	}

	/**
	 * Execute update query and return number of rows updated. We can also pass parameters for the query, while pasing param we need to follow prepared statement formats .
	 * eg  <i> new DatabaseUtils.update( "UPDATE patient SET height=? WHERE name=?",2.05, "Jane Tarzan" ); </i>
	 * for more details check http://commons.apache.org/proper/commons-dbutils/examples.html
	 * @param qry query which needs to be executed; <i>mandatory</i>
	 * @param param Parameters which is required to
	 * @return
	 * @throws SQLException
	 */
	public int executeUpdate(String qry, Object... param) throws SQLException{
		return executeInsertorUpdate(qry, param);
	}

	/**
	 * Execute insert query and return number of rows updated. We can also pass parameters for the query, while pasing param we need to follow prepared statement formats .
	 * eg  <i> new DatabaseUtils.update( "INSERT INTO Person (name,height) VALUES (?,?)", "Jane Tarzan", 1.82 ); </i>
	 * for more details check http://commons.apache.org/proper/commons-dbutils/examples.html
	 * @param qry query which needs to be executed; <i>mandatory</i>
	 * @param param Parameters which is required to
	 * @return
	 * @throws SQLException
	 */
	public int executeInsert(String qry, Object... param) throws SQLException{
		return executeInsertorUpdate(qry, param);
	}

	private int executeInsertorUpdate(String qry, Object... param) throws SQLException{
		return new QueryRunner().update(dbc.getConnection(), qry, param);
	}

	public void destroy()throws Exception{
		dbc.destroy();
	}


	protected void finalize() throws Throwable {
		destroy();
		super.finalize();
	}
}
