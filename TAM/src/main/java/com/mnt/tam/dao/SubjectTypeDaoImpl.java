package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.SubjectType;
import com.mnt.tam.daoSupport.TamDaoSupport;

@Repository("subjectTypeDao")
public class SubjectTypeDaoImpl extends TamDaoSupport implements SubjectTypeDao {

    private static Logger logger = Logger.getLogger(SubjectTypeDao.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Long l = 0l;
    Iterator<Object[]> iterator = null;

    public String saveSubjectTypeDetails(SubjectType subjectType) {
	// TODO Auto-generated method stub
	try {
	    Serializable id = getHibernateTemplate().save(subjectType);
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

    public List<Object[]> searchSubjectTypeDetails() {
	// TODO Auto-generated method stub
	try {
	    hql = "select s.subjectTypeId,s.subjectType from com.mnt.tam.bean.SubjectType s ";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> basicSearchForSubjectType(String dbField,
	    String operations, String basicSearchId) {
	// TODO Auto-generated method stub
	try {
	    hql = "select s.subjectTypeId,s.subjectType from com.mnt.tam.bean.SubjectType s where s."
		    + dbField + "" + operations + "?";

	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<SubjectType> editSubjectTypeDetails(int subjectTypeId) {
	// TODO Auto-generated method stub
	List<SubjectType> listOfSubjectType = null;
	try {
	    hql = "from com.mnt.tam.bean.SubjectType s where s.subjectTypeId="
		    + subjectTypeId;
	    listOfSubjectType = getHibernateTemplate().find(hql);
	    logger.info("list of subjet types are =="
		    + listOfSubjectType.size());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfSubjectType;
    }

    public String updateSubjectTypeDetails(SubjectType subjectType) {
	// TODO Auto-generated method stub
	try {
	    getHibernateTemplate().update(subjectType);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteSubjectTypeDetails(int id) {
	// TODO Auto-generated method stub
	try {
	    SubjectType type = new SubjectType();
	    type.setSubjectTypeId(id);
	    getHibernateTemplate().delete(type);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String subjectType, String subjectId) {
	try {
	    if (subjectId != null) {
		String sql = "select count(*) from SubjectType st where  st.subjectType='"
			+ subjectType
			+ "' and st.subjectTypeId!='"
			+ subjectId
			+ "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();

		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;

		}
	    } else {
		String sql = "select count(*) from SubjectType st where  st.subjectType='"
			+ subjectType + "'";
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
