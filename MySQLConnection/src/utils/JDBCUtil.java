package utils;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

/**
 * 使用原生JDBC连接MySQL数据库
 * @author KuKuXiaoShuaiBin
 *
 */
public class JDBCUtil {

	static {

		/**
		 * 
		 * 1. 加载驱动
		 * 
		 */

		try {

			Class.forName("com.mysql.jdbc.Driver"); // 利用反射加载驱动

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 2.建立连接
	 * 
	 */

	public static Connection getConnection() {

		try {
			//这里可以修改要访问数据库的IP及库名
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gumpdb?characterEncoding=utf-8", "root",
					"root");

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	/**
	 * 
	 * 关闭连接
	 * 
	 * @param conn
	 * 
	 * @param prep
	 * 
	 * @param rs
	 * 
	 */

	public static void close(Connection conn, PreparedStatement prep, ResultSet rs) {

		if (rs != null) {

			try {

				rs.close();

			} catch (SQLException e) {

				e.printStackTrace();

			} finally {

				rs = null;

			}

		}

		if (prep != null) {

			try {

				prep.close();

			} catch (SQLException e) {

				e.printStackTrace();

			} finally {

				prep = null;

			}

		}

		if (conn != null) {

			try {

				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();

			} finally {

				conn = null;

			}

		}

	}

	/**
	 * 
	 * 测试数据库连通性
	 * 
	 */

	public static void main(String[] args) {

		Connection conn = JDBCUtil.getConnection();

		System.out.println(conn);

		JDBCUtil.close(conn, null, null);

	}

}