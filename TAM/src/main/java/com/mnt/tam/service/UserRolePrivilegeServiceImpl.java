package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.Privilege;
import com.mnt.tam.bean.Roles;
import com.mnt.tam.bean.UserRolePrivilege;
import com.mnt.tam.bean.Users;
import com.mnt.tam.dao.UserRolePrivilegeDao;
@Service("userRolePrivilegeService")
public class UserRolePrivilegeServiceImpl implements UserRolePrivilegeService {
	private static Logger logger=Logger.getLogger(UserRolePrivilegeServiceImpl.class);
	private String msg=null;
	@Autowired
	private UserRolePrivilegeDao UserRolePrivilegeDao;

		public String saveUserRolePrivilegeDetails(UserRolePrivilege UserRolePrivilege) {
			// TODO Auto-generated method stub
			try
			{
				msg=UserRolePrivilegeDao.saveUserRolePrivilegeDetails(UserRolePrivilege);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
		public List<UserRolePrivilege> searchUserRolePrivilegeDetails(UserRolePrivilege UserRolePrivilege) {
			// TODO Auto-generated method stub
			List<Object[]> listOfObjects=null;
			List<UserRolePrivilege> UserRolePrivilegeList=null;
			try
			{
			
				
				String dbField = UserRolePrivilege.getXmlLabel();
				String operation = UserRolePrivilege.getOperations();
				String basicSearchId = UserRolePrivilege.getBasicSearchId();

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
				
			//sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.UserRolePrivilege,s.admissionNo from com.mnt.tam.bean.UserRolePrivilege s ";
				if(UserRolePrivilege.getBasicSearchId().equals(""))
				{
				 listOfObjects=UserRolePrivilegeDao.searchUserRolePrivilegeDetails();
				}
				else
				{
					listOfObjects=UserRolePrivilegeDao.basicSearchForUserRolePrivilege(dbField, operation, basicSearchId);
				}
				UserRolePrivilegeList=new ArrayList<UserRolePrivilege>();
				Iterator<Object[]> iterator=listOfObjects.iterator();
				UserRolePrivilege UserRolePrivilegeSearch = null;
				while (iterator.hasNext()) {
					Object[] obj = (Object[]) iterator.next();
					UserRolePrivilegeSearch = new UserRolePrivilege();
					UserRolePrivilegeSearch.setUserRolePrivilegeId((Integer)obj[0]);
					Users  user=((Users)obj[1]);
					UserRolePrivilegeSearch.setUserName(user.getUserName());
					Roles roles=((Roles)obj[2]);
					UserRolePrivilegeSearch.setRoleName(roles.getRole_Name());
					Privilege privilege=((Privilege)obj[3]);
					UserRolePrivilegeSearch.setPrivilegeName(privilege.getPrivilege());
					
					
					UserRolePrivilegeList.add(UserRolePrivilegeSearch);
				}
		
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			logger.error("the list size is:"+UserRolePrivilegeList.size());
			return UserRolePrivilegeList;
		}
		public UserRolePrivilege editUserRolePrivilegeDetails(int UserRolePrivilegeId) {
			// TODO Auto-generated method stub
			List<UserRolePrivilege> listOfUserRolePrivileges=null;
			UserRolePrivilege UserRolePrivilege=null;
			try
			{
				listOfUserRolePrivileges=UserRolePrivilegeDao.editUserRolePrivilegeDetails(UserRolePrivilegeId);
				if (listOfUserRolePrivileges != null) {
					Iterator<UserRolePrivilege> iterator = listOfUserRolePrivileges.iterator();
					while (iterator.hasNext()) {
						UserRolePrivilege= (UserRolePrivilege) iterator.next();
						UserRolePrivilege.setEditMsg("Edit");
					}
				}
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return UserRolePrivilege;
		}
		public String updateUserRolePrivilegeDetails(UserRolePrivilege UserRolePrivilege) {
			// TODO Auto-generated method stub
			try
			{
				msg=UserRolePrivilegeDao.updateUserRolePrivilegeDetails(UserRolePrivilege);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
		public String deleteUserRolePrivilegeDetails(int id) {
			// TODO Auto-generated method stub
			try
			{
				msg=UserRolePrivilegeDao.deleteUserRolePrivilegeDetails(id);
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
}
