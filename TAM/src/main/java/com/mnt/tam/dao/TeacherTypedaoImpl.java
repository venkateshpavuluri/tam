package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.TeacherType;
import com.mnt.tam.daoSupport.TamDaoSupport;

@Repository("teacherTypeDao")
public class TeacherTypedaoImpl extends TamDaoSupport implements TeacherTypeDao {
    private static Logger logger = Logger.getLogger(TeacherTypedaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Long l = 0l;
    Iterator<Object[]> iterator = null;

    public String saveTeacherTypeDetails(TeacherType object) {
	// TODO Auto-generated method stub
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

    public List<Object[]> searchTeacherTypeDetails() {
	// TODO Auto-generated method stub
	try {
	    hql = "select s.teacherTypeId,s.teacherType from TeacherType s order by s.teacherType";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> basicSearchForTeacherType(String dbField,
	    String operations, String basicSearchId) {
	// TODO Auto-generated method stub
	try {
	    hql = "select s.teacherTypeId,s.teacherType from TeacherType s where s."
		    + dbField + "" + operations + "?";

	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<TeacherType> editTeacherTypeDetails(int TeacherTypeId) {
	// TODO Auto-generated method stub
	List<TeacherType> listOfTeacherTypes = null;
	try {
	    hql = "from com.mnt.tam.bean.TeacherType s where s.teacherTypeId="
		    + TeacherTypeId;
	    listOfTeacherTypes = getHibernateTemplate().find(hql);
	    logger.info("list of TeacherType are =="
		    + listOfTeacherTypes.size());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfTeacherTypes;
    }

    public String updateTeacherTypeDetails(TeacherType TeacherType) {
	// TODO Auto-generated method stub
	try {
	    getHibernateTemplate().update(TeacherType);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteTeacherTypeDetails(int id) {
	// TODO Auto-generated method stub
	try {
	    TeacherType TeacherType = new TeacherType();
	    TeacherType.setTeacherTypeId(id);
	    getHibernateTemplate().delete(TeacherType);
	    msg = "S";

	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String teacherType, String teacherTypeId) {
	try {
	    if (teacherTypeId != null) {
		String sql = "select count(*) from TeacherType t where  t.teacherType='"
			+ teacherType
			+ "' and t.teacherTypeId!='"
			+ teacherTypeId + "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from TeacherType st where  st.teacherType='"
			+ teacherType + "'";
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
