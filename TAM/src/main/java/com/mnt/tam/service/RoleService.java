/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.Roles;


/**
 * @author devi
 *
 */
public interface RoleService {
	public String saveRoleDetails(Object object);
	public List<Object[]> searchRoleDetails(Object object);
	public Roles editRoleDetails(int roleId);
	public String updateRoleDetails(Roles role); 
	public String deleteRoleDetails(int id);
	public Long roleDup(String roleName, String id);
}
