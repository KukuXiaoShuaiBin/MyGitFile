package utils;

import java.io.InputStreamReader;

import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 使用MyBatis连接MySQL数据库
 * @author KuKuXiaoShuaiBin
 *
 */
public class MyBatisUtil {

	private static SqlSession session = null;

	public static SqlSession getSession() {

		try {
			session = new SqlSessionFactoryBuilder()
					.build(new InputStreamReader(MyBatisUtil.class.getClassLoader().getResourceAsStream("mybatis-config.xml"))).openSession();
		} catch (Exception e) {

			throw new RuntimeException(e);

		}

		return session;

	}

	public static void close() {

		if (session != null) {

			session.close();

		}

	}

	public static void main(String[] args) {

		SqlSession session = null;

		session = MyBatisUtil.getSession();

		System.out.println(session.getConnection());

		MyBatisUtil.close();

	}

}