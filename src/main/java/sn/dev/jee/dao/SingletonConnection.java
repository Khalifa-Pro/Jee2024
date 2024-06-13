package sn.dev.jee.dao;

import java.awt.Point;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import sn.dev.jee.entity.Produit;

public class SingletonConnection {
	
	public static SessionFactory sf;
	
	/**
	 * Creation d'une session de connexion
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		
		if (sf == null) {
			
			Configuration configuration = new Configuration();
			
			Properties properties = new Properties();
			
			properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
			properties.put(Environment.URL,"jdbc:mysql://localhost:3306/jee_db");
			properties.put(Environment.USER,"root");
			properties.put(Environment.PASS,"");
			properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL8Dialect");
			properties.put(Environment.HBM2DDL_AUTO,"update");
			properties.put(Environment.SHOW_SQL,true);
			
			configuration.setProperties(properties);
			configuration.addAnnotatedClass(Produit.class);
			
			ServiceRegistry sr = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			
			sf = configuration.buildSessionFactory(sr);

		}
		return sf;
	}

}
