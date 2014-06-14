package com.mnt.tam.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.UserRoles;
import com.mnt.tam.daoSupport.TamDaoSupport;

@Repository("UserRolesDao")
public class UserRoleDaoImpl extends TamDaoSupport implements UserRolesDao {
    private static Logger logger = Logger.getLogger(UserRoleDaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Iterator<Object[]> iterator = null;
    Long l = 0l;
    @Autowired
    JndiObjectFactoryBean factoryBean;
    public Long checkDup(String roleName, String userName, String roleId) {
	try {
	    if (roleId != null) {
		String sql = "select count(*) from UserRoles st where  st.roleId='"
			+ roleName
			+ "' and st.userId='"
			+ userName
			+ "' and st.userRoleId!='" + roleId + "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();
		
		
		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from UserRoles st where  st.roleId='"
			+ roleName + "'and st.userId='" + userName + "'";
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
    public String saveUserRolesDetails(UserRoles object) {
	try {
		InitialContext context=new InitialContext();
		DataSource dataSource=(DataSource)context.lookup("java:comp/env/jdbc/jdbcPool");
		Connection connection=dataSource.getConnection();
		System.out.println("connection isss=="+connection);
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
    public List<Object[]> searchUserRolesDetails() {
	try {
	    hql = "select s.userRoleId,s.users,s.roles from UserRoles s";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }
    public List<Object[]> basicSearchForUserRoles(String dbField,
	    String operations, String basicSearchId) {
	try {
	    hql = "select s.userRoleId,s.users,s.roles from UserRoles s where s."
		    + dbField + "" + operations + "?";
	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }
    public List<UserRoles> editUserRolesDetails(int UserRolesId) {
	List<UserRoles> listOfUserRoless = null;
	try {
	    hql = "from com.mnt.tam.bean.UserRoles s where s.userRoleId="
		    + UserRolesId;
	    listOfUserRoless = getHibernateTemplate().find(hql);
	    logger.info("list of UserRoles are ==" + listOfUserRoless.size());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfUserRoless;
    }
    public String updateUserRolesDetails(UserRoles UserRoles) {
	try {
	    getHibernateTemplate().update(UserRoles);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";

	    e.printStackTrace();
	}
	return msg;
    }
    public String deleteUserRolesDetails(int id) {
	try {
	    UserRoles UserRoles = new UserRoles();
	    UserRoles.setUserRoleId(id);
	    getHibernateTemplate().delete(UserRoles);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }
    public static void main(String[] args) {
		Integer i=Integer.valueOf("100",23);
		System.out.println(-3.0/0);
	}

}
