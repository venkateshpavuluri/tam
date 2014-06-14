/**
 * 
 */
package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.Roles;
import com.mnt.tam.bean.TermBean;
import com.mnt.tam.dao.RolesDao;

/**
 * @author devi
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	RolesDao roleDao;
	List<Object[]> objects=null;
	String msg=null;
	List<Object[]> list = null;
	Long l=0l;
	private static Logger logger=Logger.getLogger(RoleServiceImpl.class);
		
	public Long roleDup(String roleName, String id) {
		
			try {
			    l = roleDao.checkDup(roleName, id);
			} catch (Exception e) {
			    logger.error(e.getMessage());
			}
			return l;
		    }
	
	
	public String saveRoleDetails(Object object){
		msg=roleDao.saveRoleDetails(object);
		
	return msg;
}
	public List<Object[]> searchRoleDetails(Object object) {
		try {
			Roles role=(Roles)object;
							
				int id = role.getRole_Id();
				String dbField = role.getXmlLabel();
				String operation = role.getOperations();
				String basicSearchId = role.getBasicSearchId();

				if (operation.equals("_%")) {
					operation = " like ";
					basicSearchId = basicSearchId + "%";

				} else if (operation.equals("%_")) {
					operation = " like ";
					basicSearchId = "%" + basicSearchId;

				} else if (operation.equals("%_%")) {
					operation = " like ";
					basicSearchId = "%" + basicSearchId + "%";

				}
				

				if (basicSearchId == "") {
					list = roleDao.searchRoleDetails();

				} else {
					list = roleDao.roleBasicSearch(dbField, operation, basicSearchId);
				}

								
				

			} catch (Exception e) {
				e.printStackTrace();
			}
				
		return list;
	}
	
	
	public Roles editRoleDetails(int roleId) {
		// TODO Auto-generated method stub
		List<Roles> roleList=null;
		Roles role=null;
		try
		{
			roleList=roleDao.editRoleDetails(roleId);
			if (roleList != null) {
				Iterator<Roles> iterator = roleList.iterator();
				while (iterator.hasNext()) {
					role= (Roles) iterator.next();
					role.setEditMsg("Edit");
				}
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return role;
	}
	public String updateRoleDetails(Roles role) {
		// TODO Auto-generated method stub
		try
		{
			msg=roleDao.updateRoleDetails(role);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	
	public String deleteRoleDetails(int id)
	{
		try {
			msg = roleDao.deleteRoleDetails(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

}
