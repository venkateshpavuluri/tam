package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.Privilege;

public interface PrivilegeService {
	public String savePrivilegeDetails(Privilege Privilege);
	public List<Privilege> searchPrivilegeDetails(Privilege Privilege);
	public Privilege editPrivilegeDetails(int PrivilegeId);
	public String updatePrivilegeDetails(Privilege Privilege); 
	public String deletePrivilegeDetails(int id);
	public Long duplicateCheck(String privilege, String privilegeId);
}
