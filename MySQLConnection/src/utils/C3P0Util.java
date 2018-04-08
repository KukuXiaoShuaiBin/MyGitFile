package utils;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 使用C3P0连接池连接MySQL数据库
 * @author KuKuXiaoShuaiBin
 *
 */
public class C3P0Util {

	/**
	 * 
	 * 获得一个数据源
	 * 
	 */

	private static DataSource dataSource = new ComboPooledDataSource();

	public static DataSource getDataSources() {

		return dataSource;

	}

	/**
	 * 
	 * 获取连接
	 * 
	 */

	public static Connection getConnection() {

		try {

			return dataSource.getConnection();

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

		Connection conn = null;

		conn = C3P0Util.getConnection();

		System.out.println(conn);

		C3P0Util.close(conn, null, null);

	}

}
