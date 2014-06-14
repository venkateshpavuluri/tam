package com.mnt.tam.dao;

import java.util.List;
import com.mnt.tam.bean.UserRoles;

public interface UserRolesDao {
    public String saveUserRolesDetails(UserRoles object);

    public List<Object[]> searchUserRolesDetails();

    public List<Object[]> basicSearchForUserRoles(String dbField,
	    String operations, String basicSearchId);

    public List<UserRoles> editUserRolesDetails(int UserRolesId);

    public String updateUserRolesDetails(UserRoles UserRoles);

    public String deleteUserRolesDetails(int id);

    public Long checkDup(String roleName, String userName, String roleId);
}
