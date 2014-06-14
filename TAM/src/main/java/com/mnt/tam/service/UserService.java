/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.Users;



/**
 * @author devi
 *
 */
public interface UserService {
	public String saveUsersDetails(Users user);
	public List<Users> searchUsersDetails(Users user);
	public Users editUsersDetails(int userId);
	public String updateUsersDetails(Users user); 
	public String deleteUsersDetails(int id);
    public Long checkUserDup(String userName, String uid);
    
}
