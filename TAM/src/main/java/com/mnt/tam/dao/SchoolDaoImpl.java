package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.School;
import com.mnt.tam.daoSupport.TamDaoSupport;

@Repository("SchoolDao")
public class SchoolDaoImpl extends TamDaoSupport implements SchoolDao {
    private static Logger logger = Logger.getLogger(SchoolDaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Long l = 0l;
    Iterator<Object[]> iterator = null;

    public String saveSchoolDetails(School school) {
	// TODO Auto-generated method stub
	try {
	    Serializable id = getHibernateTemplate().save(school);
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

    public List<Object[]> searchSchoolDetails() {
	// TODO Auto-generated method stub
	try {
	    hql = "select s.schoolId,s.schoolName,s.address,s.city,s.state,s.countries,s.branchCode from School s order by s.schoolName";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> basicSearchForSchool(String dbField,
	    String operations, String basicSearchId) {
	// TODO Auto-generated method stub
	try {
	    hql = "select s.schoolId,s.schoolName,s.address,s.city,s.state,s.countries,s.branchCode from School s where s."
		    + dbField + "" + operations + "?";

	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<School> editSchoolDetails(int SchoolId) {
	// TODO Auto-generated method stub
	List<School> listOfSchools = null;
	try {
	    hql = "from com.mnt.tam.bean.School s where s.schoolId=" + SchoolId;
	    listOfSchools = getHibernateTemplate().find(hql);
	    logger.info("list of School are ==" + listOfSchools.size());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfSchools;
    }

    public String updateSchoolDetails(School School) {
	// TODO Auto-generated method stub
	try {
	    getHibernateTemplate().update(School);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteSchoolDetails(int id) {
	// TODO Auto-generated method stub
	try {
	    School school = new School();
	    school.setSchoolId(id);
	    getHibernateTemplate().delete(school);
	    msg = "S";

	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String school, String schoolId,String branchCode) {
	try {
	    if (schoolId!= null) {
		String sql = "select count(*) from School s where  s.schoolName='"
			+ school + "' and s.schoolId!='" + schoolId + "' and s.branchCode='"+branchCode+"'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from School s where  s.schoolName='"
			+ school + "' and s.branchCode='"+branchCode+"'";
		listOfObjects = getHibernateTemplate().find(sql);
		logger.debug("count iss==="+listOfObjects.get(0)+"sql iss=="+sql);
		
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
    public static void main(String[] args) {
		String s1="hai";
		String s2="hello";
		s1=s1.concat(s2);
		String s3="haihello";
		String s4="haihello";
		String j=new String("haihello");
		System.out.println(s4.equals(j));
	}
}
