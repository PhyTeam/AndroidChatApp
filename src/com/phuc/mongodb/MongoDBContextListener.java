package com.phuc.mongodb;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mongodb.MongoClient;

public class MongoDBContextListener implements ServletContextListener
{
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		MongoClient mongoClient = (MongoClient)arg0.getServletContext().getAttribute("MONGODB_CLIENT");
		mongoClient.close();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		try {
			ServletContext servletContext = sc.getServletContext();
			MongoClient mongoClient = new MongoClient(
					servletContext.getInitParameter("MONGODB_HOST"),
					Integer.parseInt(servletContext.getInitParameter("PORT"))
					);
			System.out.println("DB: MongoClient has been created.");
			servletContext.setAttribute("MONGODB_CLIENT", mongoClient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("DB: Some thing wrong");
			e.printStackTrace();
		}
	}

}
