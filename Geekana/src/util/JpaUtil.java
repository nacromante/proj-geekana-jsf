package util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;





public class JpaUtil {
	private static EntityManagerFactory emf;
	private String driverJpa;
	private String urlJpa;
	private String userJpa;
	private String passwordJpa;
	private String hbm2ddl_auto;
	private String show_sql;
	private String format_sql;
	private String provider_class;
	private String c3p0_min_size;
	private String c3p0_max_size;
	private String c3p0_timeout;
	private String c3p0_idle_test_period;
	private String testConnectionOnCheckout;
	private String c3p0_Debug_DEBUG;

	public void initProperties() {

//		PropertiesLoader props = new PropertiesLoader();
		
		driverJpa = PropertiesLoader.propertiesLoader().getProperty("driverJpa");
		urlJpa = PropertiesLoader.propertiesLoader().getProperty("urlJpa");
		userJpa = PropertiesLoader.propertiesLoader().getProperty("userJpa");
		passwordJpa = PropertiesLoader.propertiesLoader().getProperty("passwordJpa");
		hbm2ddl_auto = PropertiesLoader.propertiesLoader().getProperty("hbm2ddl.auto");
		show_sql = PropertiesLoader.propertiesLoader().getProperty("show_sql");
		format_sql = PropertiesLoader.propertiesLoader().getProperty("format_sql");
		provider_class = PropertiesLoader.propertiesLoader().getProperty("provider_class");
		c3p0_min_size = PropertiesLoader.propertiesLoader().getProperty("c3p0.min_size");
		c3p0_max_size = PropertiesLoader.propertiesLoader().getProperty("c3p0.max_size");
		c3p0_timeout = PropertiesLoader.propertiesLoader().getProperty("c3p0.timeout");
		c3p0_idle_test_period = PropertiesLoader.propertiesLoader().getProperty("c3p0.idle_test_period");
		testConnectionOnCheckout = PropertiesLoader.propertiesLoader().getProperty("testConnectionOnCheckout");
		c3p0_Debug_DEBUG = PropertiesLoader.propertiesLoader().getProperty("c3p0.Debug.DEBUG");

	}
		 
		//Criando meu entityManagerFactory
	public EntityManagerFactory createEntityManagerFactory() {

		initProperties();
		if (emf == null) {
//			logger.info("Appling properties to persistence....");
			Map<String, Object> configOverrides = new HashMap<String, Object>();
			
			configOverrides.put("javax.persistence.jdbc.driver", driverJpa);
			configOverrides.put("javax.persistence.jdbc.url", urlJpa);
			configOverrides.put("javax.persistence.jdbc.user", userJpa);
			configOverrides.put("javax.persistence.jdbc.password", passwordJpa);
			configOverrides.put("hibernate.hbm2ddl.auto", hbm2ddl_auto);
			configOverrides.put("hibernate.show_sql", show_sql);
			configOverrides.put("hibernate.format_sql", format_sql);
			configOverrides.put("hibernate.connection.provider_class", provider_class);
			configOverrides.put("hibernate.c3p0.min_size", c3p0_min_size);
			configOverrides.put("hibernate.c3p0.max_size", c3p0_max_size);
			configOverrides.put("hibernate.c3p0.timeout", c3p0_timeout);
			configOverrides.put("hibernate.c3p0.idle_test_period", c3p0_idle_test_period);
			configOverrides.put("testConnectionOnCheckout", testConnectionOnCheckout);
			configOverrides.put("com.mchange.v2.c3p0.Debug.DEBUG", c3p0_Debug_DEBUG);

//			logger.info("Creating Entity Manager Factory....");
			return emf = Persistence.createEntityManagerFactory("teste",configOverrides);
		} else {

			return emf;
		}
	}
	
	public EntityManager getEntityManager() {
		createEntityManagerFactory();
		return emf.createEntityManager();
	}


}
