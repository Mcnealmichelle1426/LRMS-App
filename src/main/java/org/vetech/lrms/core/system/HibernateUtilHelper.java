package org.vetech.lrms.core.system;

import org.hibernate.SessionFactory;

/**
 * Created by alex on 2/3/15.
 */
public class HibernateUtilHelper
{
	public HibernateUtilHelper()
	{

	}

	public SessionFactory getMerchandisingHU()
	{
		return CoreLRMSHibernateUtil.getSessionFactory();
	}
}

