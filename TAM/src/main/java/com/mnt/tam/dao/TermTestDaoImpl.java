/**
 * 
 */
package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


import com.mnt.tam.bean.TermBean;
import com.mnt.tam.bean.TermTest;
import com.mnt.tam.daoSupport.TamDaoSupport;

/**
 * @author devi
 *
 */
@Repository("tTestDao")
public class TermTestDaoImpl extends TamDaoSupport implements TermTestDao{
	private static Logger logger=Logger.getLogger(TermTestDaoImpl.class);
	private String msg=null;
	private List<Object[]> listOfObjects=null;
	private String hql=null;
	
	public Long checkTermTestDup(String ttestName, String ttid) {

		Iterator<Object[]> iterator = null;
		List<Object> list = null;
		Long l = 0l;
		 try {
			    if (ttid != null) {
				String sql = "select count(*) from TermTest st where  st.termtest='"
					+ ttestName
					+ "' and st.termtest_Id!='"
					+ ttid
					+ "'";
				listOfObjects = getHibernateTemplate().find(sql);
				iterator = listOfObjects.iterator();

				while (iterator.hasNext()) {
				    Object object = (Object) iterator.next();
				    l = (Long) object;

				}
			    } else {
				String sql = "select count(*) from TermTest st where  st.termtest='"
					+ ttestName + "'";
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
	
		public String saveTermTestDetails(TermTest temtest) {
			// TODO Auto-generated method stub
			try
			{
				Serializable id=getHibernateTemplate().save(temtest);
				
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
				e.printStackTrace();
			}
			return msg;
		}
		public List<Object[]> searchTermTestDetails() {
			// TODO Auto-generated method stub
			try
			{
				hql="select t.termtest,t.termTestCode,t.termBean,t.termtest_Id from com.mnt.tam.bean.TermTest t ";
				listOfObjects=getHibernateTemplate().find(hql);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			return listOfObjects;
		}
		public List<Object[]> basicSearchForTermTest(String dbField,
				String operations, String basicSearchId) {
			// TODO Auto-generated method stub
			try
			{
				 hql = "select t.termtest,t.termTestCode,t.termBean,t.termtest_Id from com.mnt.tam.bean.TermTest t where t."
						+ dbField + "" + operations + "?";

				Object[] parameters = { basicSearchId };
				listOfObjects=getHibernateTemplate().find(hql,parameters);
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			return listOfObjects;
		}
		public List<TermTest> editTermTestDetails(int termTestId) {
			// TODO Auto-generated method stub
			List<TermTest> listOfTermtests=null;
			try
			{
				hql="from com.mnt.tam.bean.TermTest t where t.termtest_Id="+termTestId;
				listOfTermtests=getHibernateTemplate().find(hql);
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return listOfTermtests;
		}
		public String updateTermTestDetails(TermTest termtest) {
			// TODO Auto-generated method stub
			try
			{
				getHibernateTemplate().update(termtest);
				msg="S";
			}
			catch(Exception e )
			{
				msg="F";
				e.printStackTrace();
			}
			return msg;
		}
		public String deleteTermTestDetails(int id) {
			// TODO Auto-generated method stub
			try
			{
				TermTest termtest=new TermTest();
				termtest.setTermtest_Id(id);
				termtest.setTermBean(new TermBean());
				getHibernateTemplate().delete(termtest);
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
