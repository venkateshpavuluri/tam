/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;


import com.mnt.tam.bean.Users;

/**
 * @author devi
 *
 */
public interface UsersDao {
	public String saveUsersDetails(Users object);
	public List<Object[]> searchUsersDetails(); 
	public List<Object[]> basicSearchForUsers(String dbField,String operations,String basicSearchId);
    public List<Users> editUsersDetails(int studentId);
    public String updateUsersDetails(Users user);
	public String deleteUsersDetails(int id);
    public Long checkUserDup(String userName, String uid);
}
