package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 使用Hibernate连接MySQL数据库
 * @author KuKuXiaoShuaiBin
 *
 */
public class HibernateUtil {

	private static Configuration configuration;

	private static SessionFactory sessionFactory;

	private static Session session;

	static {
		// 创建配置
		configuration = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		// 创建会话工厂对象
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	// 3.得到session

	public static Session getSession() {

		session = sessionFactory.openSession();

		return session;

	}

	// 4.关闭session

	public static void close() {

		if (session != null) {

			session.close();

		}

	}

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();

		System.out.println(session);

		HibernateUtil.close();

	}

}