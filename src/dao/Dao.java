package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
	static DataSource ds;

	static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/managementScore");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public Connection getConnection() throws Exception {
		return ds.getConnection();
	}
}