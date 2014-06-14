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

import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.StudentClass;
import com.mnt.tam.daoSupport.TamDaoSupport;

/**
 * @author venkateshp
 * 
 */
@Repository("studentClassDao")
public class StudentClassDaoImpl extends TamDaoSupport implements
	StudentClassDao {
    private static Logger logger = Logger.getLogger(StudentClassDaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Long l = 0l;
    Iterator<Object[]> iterator = null;
    
    public String saveStudentClassDetails(StudentClass studentClass) {
	try {
	    Serializable id = getHibernateTemplate().save(studentClass);
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

    public List<Object[]> searchStudentClassDetails() {
	try {
	    hql = "select s.studentClassId,s.section,s.student,s.classStudent,s.year from com.mnt.tam.bean.StudentClass s";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}

	return listOfObjects;
    }

    public List<Object[]> basicSearchForStudentClass(String dbField,
	    String operations, String basicSearchId) {
	try {
	    hql = "select s.studentClassId,s.section,s.student,s.classStudent,s.year from com.mnt.tam.bean.StudentClass s where s."
		    + dbField + "" + operations + "?";

	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> editStudentClassDetails(int studentClassId) {
	try {
	    hql = "select s.studentClassId,s.sectionId,s.classId,s.studentId,s.year from com.mnt.tam.bean.StudentClass  s where s.studentClassId="
		    + studentClassId;
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfObjects;
    }

    public String updateStudentClassDetails(StudentClass studentClass) {
	try {
	    getHibernateTemplate().update(studentClass);
	    msg = "S";

	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public String deleteStudentClassDetails(int id) {
	try {
	    StudentClass studentClass = new StudentClass();
	    studentClass.setStudentClassId(id);
	    getHibernateTemplate().delete(studentClass);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateCheck(String studentClass, String studentClassId) {
	try {
	    if (studentClassId != null) {
		String sql = "select count(*) from StudentClass s where  s.studentClass='"
			+ studentClass + "' and s.studentClassId!='" + studentClassId + "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();

		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from StudentClass s where  s.studentClass='"
			+ studentClass + "'";
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
