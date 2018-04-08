package utils;

import java.io.IOException;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * 使用DBCP连接MySQL数据库
 * @author KuKuXiaoShuaiBin
 *
 */
public class DBCPUtil {

	private static Properties props = new Properties();

	static {

		// 加载配置文件

		try {

			props.load(DBCPUtil.class.getClassLoader()

					.getResourceAsStream("dbcp.properties"));

		} catch (IOException e) {

			throw new RuntimeException("配置文件加载失败");

		}

	}

	/**
	 * 
	 * 获得数据源
	 *
	 * 
	 * 
	 * @return
	 * 
	 */

	public static DataSource getDataSource() {

		try {

			return BasicDataSourceFactory.createDataSource(props);

		} catch (Exception e) {

			throw new RuntimeException("获得数据源失败");

		}

	}

	/**
	 * 
	 * 获取连接
	 *
	 * 
	 * 
	 * @return
	 * 
	 */

	public static Connection getConnection() {

		try {

			return getDataSource().getConnection();

		} catch (SQLException e) {

			throw new RuntimeException("连接数据库失败");

		}

	}

	/**
	 * 
	 * 关闭连接
	 *
	 * 
	 * 
	 * @param conn
	 * 
	 * @param prep
	 * 
	 * @param rs
	 * 
	 */

	public static void close(Connection conn, PreparedStatement prep,

			ResultSet rs) {

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

		conn = DBCPUtil.getConnection();

		System.out.println(conn);

		DBCPUtil.close(conn, null, null);

	}

}