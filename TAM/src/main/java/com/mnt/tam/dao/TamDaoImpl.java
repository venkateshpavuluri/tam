/**
 * 
 */
package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author venkateshp
 *
 */
public class TamDaoImpl extends HibernateDaoSupport implements TamDao {
	String msg=null;
	private static Logger logger=Logger.getLogger(TamDaoImpl.class);
	public String saveDetails(Object object) {
		// TODO Auto-generated method stub
		try
		{
			Serializable id=getHibernateTemplate().save(object);
			if(id!=null)
			{
				msg="S";
			}
			else
			{
				msg="F";
			}
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
		}
		return msg;
	}

	public List<Object[]> searchDetails(String sql) {
		// TODO Auto-generated method stub
		List<Object[]>  objects=null;
		try
		{
			objects=getHibernateTemplate().find(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return objects;
	}

	public String updateDetails(Object object) {
		// TODO Auto-generated method stub
		try
		{
			getHibernateTemplate().update(object);
			msg="S";
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
		}
		return msg;
	}

	public String deleteDetails(Object object) {
		// TODO Auto-generated method stub
		try
		{
			getHibernateTemplate().delete(object);
			msg="S";
			
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
		}
		return msg;
	}

	public String deleteAllDetails(List<Object> objects) {
		// TODO Auto-generated method stub
		try
		{
			getHibernateTemplate().deleteAll(objects);
			msg="S";
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
		}
		return msg;
	}

	public Long duplicateCheck(String sql) {
		// TODO Auto-generated method stub
		Long val=null;
		try
		{
			List<String> list=getHibernateTemplate().find(sql);
			val=Long.parseLong(list.get(0));
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return val;
	}

	public String saveAllDetails(List<Object> object) {
		// TODO Auto-generated method stub
		try
		{
			getHibernateTemplate().saveOrUpdateAll(object);
			msg="S";
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
		}
		return msg;
}

}
