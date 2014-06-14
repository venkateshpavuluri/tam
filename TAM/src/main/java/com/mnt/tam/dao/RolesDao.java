/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.Roles;


/**
 * @author devi
 *
 */
public interface RolesDao {
	public String saveRoleDetails(Object object);
	public List<Object[]> roleBasicSearch(String label, String operator, String searchName);
	public List<Object[]> searchRoleDetails();
	public List<Roles> editRoleDetails(int roleId);
	public String deleteRoleDetails(int id);
	public String updateRoleDetails(Roles role);
	public Long checkDup(String roleName, String Id);
	

}
