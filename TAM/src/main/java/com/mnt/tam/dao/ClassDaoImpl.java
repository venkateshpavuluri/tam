package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.Classes;
import com.mnt.tam.daoSupport.TamDaoSupport;

@Repository("classDao")
public class ClassDaoImpl extends TamDaoSupport implements ClassDao {
    private static Logger logger = Logger.getLogger(ClassDaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Iterator<Object[]> iterator = null;
    Long l = 0l;

    public String saveClassDetails(Classes Class) {
	try {
	    Serializable id = getHibernateTemplate().save(Class);
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

    public List<Object[]> searchClassDetails() {
	try {
	    hql = "select s.classId,s.className,s.school from com.mnt.tam.bean.Classes s";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> basicSearchForClass(String dbField,
	    String operations, String basicSearchId) {
	try {
	    hql = "select s.classId,s.className,s.school from com.mnt.tam.bean.Classes s where s."
		    + dbField + "" + operations + "?";

	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Classes> editClassDetails(int ClassId) {
	List<Classes> listOfClasss = null;
	try {
	    hql = "from com.mnt.tam.bean.Classes s where s.classId=" + ClassId;
	    listOfClasss = getHibernateTemplate().find(hql);
	    logger.info("list of Classare ==" + listOfClasss.size());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfClasss;
    }

    public String updateClassDetails(Classes Class) {
	try {
	    getHibernateTemplate().update(Class);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteClassDetails(int id) {
	try {
	    Classes Class = new Classes();
	    Class.setClassId(id);
	    getHibernateTemplate().delete(Class);
	    msg = "S";

	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String className, String classId) {
	try {
	    if (classId != null) {
		String sql = "select count(*) from Classes st where  st.className='"
			+ className + "' and st.classId!='" + classId + "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from Classes st where  st.className='"
			+ className + "'";
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
