package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.Privilege;
import com.mnt.tam.daoSupport.TamDaoSupport;

@Repository("PrivilegeDao")
public class PrivilegeDaoImpl extends TamDaoSupport implements PrivilegeDao {
    private static Logger logger = Logger.getLogger(PrivilegeDaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Long l = 0l;
    Iterator<Object[]> iterator = null;

    public String savePrivilegeDetails(Privilege object) {
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

    public List<Object[]> searchPrivilegeDetails() {
	// TODO Auto-generated method stub
	try {
	    hql = "select s.privilegeId,s.privilege from Privilege s order by s.privilege";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> basicSearchForPrivilege(String dbField,
	    String operations, String basicSearchId) {
	// TODO Auto-generated method stub
	try {
	    hql = "select s.privilegeId,s.privilege from Privilege s where s."
		    + dbField + "" + operations + "?";

	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Privilege> editPrivilegeDetails(int PrivilegeId) {
	// TODO Auto-generated method stub
	List<Privilege> listOfPrivileges = null;
	try {
	    hql = "from com.mnt.tam.bean.Privilege s where s.privilegeId="
		    + PrivilegeId;
	    listOfPrivileges = getHibernateTemplate().find(hql);
	    logger.info("list of Privilege are ==" + listOfPrivileges.size());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfPrivileges;
    }

    public String updatePrivilegeDetails(Privilege Privilege) {
	// TODO Auto-generated method stub
	try {
	    getHibernateTemplate().update(Privilege);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    e.printStackTrace();
	}
	return msg;
    }

    public String deletePrivilegeDetails(int id) {
	// TODO Auto-generated method stub
	try {
	    Privilege Privilege = new Privilege();
	    Privilege.setPrivilegeId(id);
	    getHibernateTemplate().delete(Privilege);
	    msg = "S";

	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String privilege, String privilegeId) {
	try {
	    if (privilegeId != null) {
		String sql = "select count(*) from Privilege st where  st.privilege='"
			+ privilege
			+ "' and st.privilegeId!='"
			+ privilegeId
			+ "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from Privilege st where  st.privilege='"
			+ privilege + "'";
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
