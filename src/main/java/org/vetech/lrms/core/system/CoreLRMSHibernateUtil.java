package org.vetech.lrms.core.system;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by alex on 2/3/15.
 */
public class CoreLRMSHibernateUtil {
	private static final SessionFactory sessionFactory;

	static
	{
		try
		{
			sessionFactory = new AnnotationConfiguration().configure("/org/vetech/lrms/core/hibernate/hibernate.cfg.xml").buildSessionFactory();
		}
		catch (HibernateException ex)
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
