package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.Privilege;

public interface PrivilegeDao {
    public String savePrivilegeDetails(Privilege object);

    public List<Object[]> searchPrivilegeDetails();

    public List<Object[]> basicSearchForPrivilege(String dbField,
	    String operations, String basicSearchId);

    public List<Privilege> editPrivilegeDetails(int PrivilegeId);

    public String updatePrivilegeDetails(Privilege Privilege);

    public String deletePrivilegeDetails(int id);
    
    public Long duplicateCheck(String privilege, String privilegeId);
}
