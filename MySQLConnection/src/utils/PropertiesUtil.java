package utils;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Properties;

/**
 * 使用配置文件db.properties的方式连接MySQL数据库
 * @author KuKuXiaoShuaiBin
 *
 */
public class PropertiesUtil {

	private static Properties prop;

	static {

		prop = new Properties();

		try {
			// 加载配置文件
			prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(

					"db.properties"));
			// 加载驱动
			Class.forName(prop.getProperty("driver"));

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

	/**
	 * 获得连接
	 * @param conn
	 * @return
	 */
	public static Connection getConnection(Connection conn) {

		if (conn == null) {

			try {
				// 获得连接
				conn = DriverManager.getConnection(prop.getProperty("url"),

						prop.getProperty("username"), prop.getProperty("password"));

			} catch (SQLException e) {

				throw new RuntimeException(e);

			}

		}

		return conn;

	}

	/**
	 * 关闭连接
	 * @param conn
	 * @param prep
	 * @param rs
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
	 * 测试连接
	 * @param args
	 */
	public static void main(String[] args) {

		Connection conn = null;

		conn = PropertiesUtil.getConnection(conn);

		System.out.println(conn);

		PropertiesUtil.close(conn, null, null);

	}

}