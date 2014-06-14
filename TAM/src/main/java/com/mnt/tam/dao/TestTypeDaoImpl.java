/**
 * 
 */
package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import com.mnt.tam.dao.TestTypeDao;
import com.mnt.tam.daoSupport.TamDaoSupport;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.mnt.tam.bean.TestType;

/**
 * @author tparvathi
 * 
 */
@Repository("testTypeDao")
public class TestTypeDaoImpl extends TamDaoSupport implements TestTypeDao {
    private static Logger logger = Logger.getLogger(TestTypeDaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Iterator<Object[]> iterator = null;
    Long l = 0l;

    public String saveTestTypeDetails(TestType object) {
	try {
	    Serializable id = getHibernateTemplate().save(object);
	    logger.info("id is==" + id);
	    if (id != null) {
		msg = "S";
	    } else {
		msg = "F";
	    }
	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<Object[]> searchTestTypeDetails() {
	try {
	    hql = "select s.testTypeId,s.testType from TestType s order by s.testType";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> basicSearchForTestType(String dbField,
	    String operations, String basicSearchId) {
	try {
	    hql = "select s.testTypeId,s.testType from TestType s where s."
		    + dbField + "" + operations + "?";

	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<TestType> editTestTypeDetails(int TestTypeId) {
	List<TestType> listOfTestTypes = null;
	try {
	    hql = "from com.mnt.tam.bean.TestType s where s.testTypeId="
		    + TestTypeId;
	    listOfTestTypes = getHibernateTemplate().find(hql);
	    logger.info("list of TestType are ==" + listOfTestTypes.size());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfTestTypes;
    }

    public String updateTestTypeDetails(TestType TestType) {
	try {
	    getHibernateTemplate().update(TestType);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteTestTypeDetails(int id) {
	try {
	    TestType TestType = new TestType();
	    TestType.setTestTypeId(id);
	    getHibernateTemplate().delete(TestType);
	    msg = "S";

	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String testType, String testTypeId) {
	try {
	    if (testTypeId != null) {
		String sql = "select count(*) from TestType st where  st.testType='"
			+ testType
			+ "' and st.testTypeId!='"
			+ testTypeId
			+ "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from TestType st where  st.testType='"
			+ testType + "'";
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
