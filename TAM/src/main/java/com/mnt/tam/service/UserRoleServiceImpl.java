package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.Roles;
import com.mnt.tam.bean.UserRoles;
import com.mnt.tam.bean.Users;
import com.mnt.tam.dao.UserRolesDao;
@Service("UserRolesService")
public class UserRoleServiceImpl implements UserRoleService{
	private static Logger logger=Logger.getLogger(UserRoleServiceImpl.class);
	private String msg=null;
	@Autowired
	private UserRolesDao UserRolesDao;
	Long l=0l;
	public Long roleDup(String roleName, String userName, String id) {
		
		try {
		    l = UserRolesDao.checkDup(roleName, userName, id);
		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
		return l;
	    }


		public String saveUserRolesDetails(UserRoles UserRoles) {
			// TODO Auto-generated method stub
			try
			{
				msg=UserRolesDao.saveUserRolesDetails(UserRoles);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
		public List<UserRoles> searchUserRolesDetails(UserRoles UserRoles) {
			// TODO Auto-generated method stub
			List<Object[]> listOfObjects=null;
			List<UserRoles> UserRolesList=null;
			try
			{
			
				
				String dbField = UserRoles.getXmlLabel();
				String operation = UserRoles.getOperations();
				String basicSearchId = UserRoles.getBasicSearchId();

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
				
			//sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.UserRoles,s.admissionNo from com.mnt.tam.bean.UserRoles s ";
				if(UserRoles.getBasicSearchId().equals(""))
				{
				 listOfObjects=UserRolesDao.searchUserRolesDetails();
				}
				else
				{
					listOfObjects=UserRolesDao.basicSearchForUserRoles(dbField, operation, basicSearchId);
				}
				UserRolesList=new ArrayList<UserRoles>();
				Iterator<Object[]> iterator=listOfObjects.iterator();
				UserRoles UserRolesSearch = null;
				while (iterator.hasNext()) {
					Object[] obj = (Object[]) iterator.next();
					UserRolesSearch = new UserRoles();
					UserRolesSearch.setUserRoleId((Integer) obj[0]);
					Users  user=((Users)obj[1]);
					UserRolesSearch.setUserName(user.getUserName());
					Roles roles=((Roles)obj[2]);
					UserRolesSearch.setRoleName(roles.getRole_Name());
					//UserRolesSearch.setUserRoles((String) obj[1]);
					
					UserRolesList.add(UserRolesSearch);
				}
				
				
		
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			logger.error("the list size is:"+UserRolesList.size());
			return UserRolesList;
		}
		public UserRoles editUserRolesDetails(int UserRolesId) {
			// TODO Auto-generated method stub
			List<UserRoles> listOfUserRoless=null;
			UserRoles UserRoles=null;
			try
			{
				listOfUserRoless=UserRolesDao.editUserRolesDetails(UserRolesId);
				if (listOfUserRoless != null) {
					Iterator<UserRoles> iterator = listOfUserRoless.iterator();
					while (iterator.hasNext()) {
						UserRoles= (UserRoles) iterator.next();
						UserRoles.setEditMsg("Edit");
					}
				}
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return UserRoles;
		}
		public String updateUserRolesDetails(UserRoles UserRoles) {
			// TODO Auto-generated method stub
			try
			{
				msg=UserRolesDao.updateUserRolesDetails(UserRoles);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
		public String deleteUserRolesDetails(int id) {
			// TODO Auto-generated method stub
			try
			{
				msg=UserRolesDao.deleteUserRolesDetails(id);
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
}
