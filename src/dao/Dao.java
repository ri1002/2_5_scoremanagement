package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
	static DataSource ds;

    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/managementScore");
        } catch (Exception e) {
            e.printStackTrace(); // デバッグ用にログ出力（後でLoggerに変更もOK）
        }
    }

	public Connection getConnection() throws Exception {
		return ds.getConnection();
	}
}