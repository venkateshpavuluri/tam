package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.SubjectBean;
import com.mnt.tam.daoSupport.TamDaoSupport;
@Repository("subjectDao")
public class SubjectDaoImpl extends TamDaoSupport implements SubjectDao {

    private static Logger logger = Logger.getLogger(SubjectDaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Long l = 0l;
    Iterator<Object[]> iterator = null;

    public String saveSubjectDetails(SubjectBean subjectBean) {
	try {
	
	    Serializable id = getHibernateTemplate().save(subjectBean);
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

    public List<Object[]> searchSubjectDetails() {
	try {
	    hql = "select s.subjectId, s.subjectTypeId, s.subject, s.parentSubjectId,s.maxMarks,s.subjectType,s.classes from com.mnt.tam.bean.SubjectBean s order by subject ";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> basicSearchForSubject(String dbField,
	    String operations, String basicSearchId) {
	try {
	    hql = "select s.subjectId, s.subjectTypeId, s.subject, s.parentSubjectId,s.maxMarks, s.subjectType,s.classes from com.mnt.tam.bean.SubjectBean s  where s."
		    + dbField + "" + operations + "? order by subject";

	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<SubjectBean> editSubjectDetails(int subjectId) {
	List<SubjectBean> listOfSubjects = null;
	try {
	    hql = "from com.mnt.tam.bean.SubjectBean s where s.subjectId="
		    + subjectId;
	    listOfSubjects = getHibernateTemplate().find(hql);
	    logger.info("list of subjects are ==" + listOfSubjects.size());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfSubjects;
    }

    public String updateSubjectDetails(SubjectBean subject) {
	try {
	    getHibernateTemplate().update(subject);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteSubjectDetails(int id) {
	try {
	    SubjectBean subject = new SubjectBean();
	    subject.setSubjectId(id);
	    getHibernateTemplate().delete(subject);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String subject, String subjectId) {
	try {
	    if (subjectId != null) {
		String sql = "select count(*) from SubjectBean s where  Upper(s.subject)='"
			+ subject.toUpperCase() + "' and s.subjectId!='" + subjectId + "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();

		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from SubjectBean s where  Upper(s.subject)='"
			+ subject.toUpperCase() + "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return l;
    }

}
