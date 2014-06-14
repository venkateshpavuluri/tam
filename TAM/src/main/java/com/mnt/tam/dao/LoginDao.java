/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;

/**
 * @author yogi
 *
 */
public interface LoginDao {
    public List<Object[]> getCredentials(String username,String password);
}
