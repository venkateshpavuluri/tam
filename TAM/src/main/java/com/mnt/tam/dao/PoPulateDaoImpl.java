/**
 * Copyright MNTSOFT 
 */
package com.mnt.tam.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.daoSupport.TamDaoSupport;

/**
 * @author pvenkateswarlu
 *@version 1.0 09-10-2013
 */
@Repository("populateDao")
public class PoPulateDaoImpl extends TamDaoSupport implements PoPulateDao {


	public List<Object[]> poPulate(String sql) {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		try
		{
			list=getHibernateTemplate().find(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

}
