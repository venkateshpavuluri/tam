package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.UserRoles;

public interface UserRoleService {
	public String saveUserRolesDetails(UserRoles UserRoles);
	public List<UserRoles> searchUserRolesDetails(UserRoles UserRoles);
	public UserRoles editUserRolesDetails(int UserRolesId);
	public String updateUserRolesDetails(UserRoles UserRoles); 
	public String deleteUserRolesDetails(int id);
	public Long roleDup(String roleName,String userName, String id);
}
