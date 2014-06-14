package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.TermBean;
import com.mnt.tam.bean.TermTest;
import com.mnt.tam.daoSupport.TamDaoSupport;
@Repository("sDao")
public class TermDaoImpl extends TamDaoSupport implements TermDao{
	String msg=null;
	List<Object[]> objects=null;
	private List<Object[]> listOfObjects=null;
	public Long checkTermCout(String term, String tid) {
		
		Iterator<Object[]> iterator = null;
		List<Object> list = null;
		Long l = 0l;
		 try {
			    if (tid != null) {
				String sql = "select count(*) from TermBean st where  st.term='"
					+ term
					+ "' and st.term_Id!='"
					+ tid
					+ "'";
				listOfObjects = getHibernateTemplate().find(sql);
				iterator = listOfObjects.iterator();

				while (iterator.hasNext()) {
				    Object object = (Object) iterator.next();
				    l = (Long) object;

				}
			    } else {
				String sql = "select count(*) from TermBean st where  st.term='"
					+ term + "'";
				listOfObjects = getHibernateTemplate().find(sql);
				iterator = listOfObjects.iterator();

				while (iterator.hasNext()) {
				    Object object = (Object) iterator.next();
				    l = (Long) object;

				}
			    }

			} catch (Exception e) {
			    e.printStackTrace();
			}
			return l;
	}
	
	public String saveTermDetails(Object object){
		try
		{
						
			TermBean tbean=(TermBean)object;
			tbean.setTerm_Id(1);
		   Serializable id= getHibernateTemplate().save(tbean);
		  
			msg="S";
			
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
		}
		return msg;
	}
	public List<Object[]> searchTermDetails() {
		try {
			
			
			String hql = "select t.term_Id,t.term from com.mnt.tam.bean.TermBean t ";
			objects = getHibernateTemplate().find(hql);
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}
	public List<Object[]> termBasicSearch(String label, String operator, String searchName) {
		try {
		
						
			String hql = "select t.term_Id,t.term from com.mnt.tam.bean.TermBean t where t."
					+ label + "" + operator + " ? " ;
			objects = getHibernateTemplate().find(hql,searchName);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}
	
	public List<TermBean> editTermDetails(int termId) {
		// TODO Auto-generated method stub
		List<TermBean> termList=null;
		try
		{
			String hql="from com.mnt.tam.bean.TermBean t where t.term_Id="+termId;
			termList=getHibernateTemplate().find(hql);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return termList;
	}
	public String updateTermDetails(TermBean term) {
		// TODO Auto-generated method stub
		try
		{
			getHibernateTemplate().update(term);
			msg="S";
		}
		catch(Exception e )
		{
			msg="F";
			e.printStackTrace();
		}
		return msg;
	}
	
	
	
	public String deleteTermDetails(int id) {
		
		try {
			TermBean deleteTerm = getHibernateTemplate().get(
					TermBean.class, id);
			
			getHibernateTemplate().delete(deleteTerm);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "S";
	}
	
		

	
}
