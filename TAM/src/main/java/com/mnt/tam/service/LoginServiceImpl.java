/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.dao.LoginDaoImpl;

/**
 * @author yogi
 * 
 */
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDaoImpl daoImpl;
    private List<Object[]> list = null;

    /*
     * public LoginDaoImpl getDaoImpl() { return daoImpl; }
     * 
     * public void setDaoImpl(LoginDaoImpl daoImpl) { this.daoImpl = daoImpl; }
     */

    public List<Object[]> getCredentials(String username, String password) {
	try {
	    list = daoImpl.getCredentials(username, password);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return list;
    }
}
