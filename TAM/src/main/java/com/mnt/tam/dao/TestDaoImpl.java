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

import com.mnt.tam.bean.Test;
import com.mnt.tam.daoSupport.TamDaoSupport;

/**
 * @author venkateshp
 * 
 */
@Repository("testDao")
public class TestDaoImpl extends TamDaoSupport implements TestDao {
    private static Logger logger = Logger.getLogger(TestDaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Long l = 0l;
    Iterator<Object[]> iterator = null;

    public String saveTestDetails(Test test) {
	try {
	    Serializable id = getHibernateTemplate().save(test);
	    if (id != null) {
		msg = "S";
	    } else {
		msg = "F";
	    }
	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public List<Object[]> searchTestDetails() {
	try {
	    hql = "select t.testId,t.test,t.testType,t.termTest,t.subjectBean from com.mnt.tam.bean.Test t";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());

	}
	return listOfObjects;
    }

    public List<Object[]> basicSearchForTest(String dbField, String operations,
	    String basicSearchId) {
	try {
	
		
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> editTestDetails(int testId) {
	try {
	    hql = "select t.testId,t.test,t.testTypeId,t.subjectId,t.termTestId from com.mnt.tam.bean.Test t where t.testId="
		    + testId;
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public String updateTestDetails(Test test) {
	try {
	    getHibernateTemplate().update(test);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public String deleteTestDetails(int id) {
	try {
	    Test test = new Test();
	    test.setTestId(id);
	    getHibernateTemplate().delete(test);
	    msg = "S";

	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String testName, String testId) {
	try {
	    if (testId != null) {
		String sql = "select count(*) from Test st where  st.test='"
			+ testName + "' and st.testId!='" + testId + "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();

		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from Test st where  st.test='"
			+ testName + "'";
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
}
