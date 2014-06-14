package com.mnt.tam.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mnt.tam.daoSupport.TamDaoSupport;

@Repository("daoImpl")
public class LoginDaoImpl extends TamDaoSupport implements LoginDao {

    public List<Object[]> getCredentials(String username, String password) {
	List<Object[]> list = null;
	if (password != null) {
	    String hql = "select h.userId,h.userName,h.password from Users h where h.userName='"
		    + username + "' and h.password='" + password + "'";
	    list = getHibernateTemplate().find(hql);
	} else {
	    String hql = "select h.userId,h.userName,h.password from Users h where h.userName='"
		    + username + "'";
	    list = getHibernateTemplate().find(hql);
	}
	return list;
    }

}
