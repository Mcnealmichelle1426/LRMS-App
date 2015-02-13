package org.vetech.lrms.core.system;

import org.hibernate.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alex on 2/3/15.
 */
public class CRUD {
	SessionFactory sessionFactory = null;

	public CRUD(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean saveOrUpdate(Object object) {
		boolean status = false;

		Object newObject = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(object);
			transaction.commit();

			status = true;
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}

			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	public Object save(Object object) {
		Object newObject = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			newObject = session.save(object);
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}

			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return newObject;
	}

	public boolean delete(Object object) {
		boolean status = false;

		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(object);
			transaction.commit();

			status = true;
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}

			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	public List findByPrimaryKey(Object object, String queryString) {
		List result = null;

		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery(queryString);
			query.setParameter("pk", object);
			result = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}

			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return result;
	}

	public List<Object> getObject(String queryString, int startIndex, int maxRecords) {
		List result = null;

		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery(queryString);
			query.setFirstResult(startIndex).setMaxResults(maxRecords);

			result = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}

			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return result;
	}

	public List<Object> getObjectLazyLoad(String queryString) {
		List result = null;

		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery(queryString);
			result = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}

			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (session != null) {
				//session.close();
			}
		}

		return result;
	}

	public List<Object> getObjectLazyLoad(String queryString, int startIndex, int maxRecords) {
		List result = null;

		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery(queryString);
			query.setFirstResult(startIndex).setMaxResults(maxRecords);
			result = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}

			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (session != null) {
				//session.close();
			}
		}

		return result;
	}

	public List getObject(String queryString)
	{
		List result = null;

		Session session = null;
		Transaction transaction = null;

		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery(queryString);
			result = query.list();
			transaction.commit();
		}
		catch(HibernateException ex)
		{
			if(transaction != null)
			{
				transaction.rollback();
			}

			Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}

		return result;
	}
}
