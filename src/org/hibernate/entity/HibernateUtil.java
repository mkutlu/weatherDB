package org.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.pojo.*;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;
	static {
		try {
			Configuration config = getConfiguration();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			config.setSessionFactoryObserver(new SessionFactoryObserver() {
				private static final long serialVersionUID = 1L;

				@Override
				public void sessionFactoryCreated(SessionFactory factory) {
				}

				@Override
				public void sessionFactoryClosed(SessionFactory factory) {
					StandardServiceRegistryBuilder.destroy(serviceRegistry);
				}
			});
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}

	private static Configuration getConfiguration() {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(City.class);
		
		cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/weather");
		cfg.setProperty("hibernate.connection.username", "postgres");
		cfg.setProperty("hibernate.connection.password", "1");
		cfg.setProperty("hibernate.connection.pool_size","20");
		cfg.setProperty("hibernate.show_sql", "true");
		cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		cfg.setProperty("hibernate.hbm2ddl.auto", "update");
		cfg.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
		cfg.setProperty("hibernate.current_session_context_class", "thread");
		return cfg;
	}
}