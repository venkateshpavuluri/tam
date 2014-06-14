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


import com.mnt.tam.service.DateConversionService;
import com.mnt.tam.bean.Users;
import com.mnt.tam.dao.UsersDao;

/**
 * @author devi
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	private static Logger logger=Logger.getLogger(UserServiceImpl.class);
	@Autowired
	DateConversionService dateService;

	private String msg=null;
	@Autowired
	UsersDao uDao;
      Long l=0l;		
	public Long checkUserDup(String userName, String uid) {
		try{
		     l = uDao.checkUserDup(userName, uid);
		 }catch(Exception e){
		     logger.error(e.getMessage());
		 }
		 return l;
	}
	public String saveUsersDetails(Users user) {
		// TODO Auto-generated method stub
		try
		{
			msg=uDao.saveUsersDetails(user);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	public List<Users> searchUsersDetails(Users user) {
		// TODO Auto-generated method stub
		List<Object[]> listOfObjects=null;
		List<Users> listOfUsers=null;
		try
		{
		
			
			String dbField = user.getXmlLabel();
			String operation = user.getOperations();
			String basicSearchId = user.getBasicSearchId();

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
			
			if(user.getBasicSearchId().equals(""))
			{
			 listOfObjects=uDao.searchUsersDetails();
			}
			else
			{
				listOfObjects=uDao.basicSearchForUsers(dbField, operation, basicSearchId);
			}
			listOfUsers=new ArrayList<Users>();
			Iterator<Object[]> iterator=listOfObjects.iterator();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				Users userlist=new Users();
				userlist.setUserId((Integer)objects[0]);
				userlist.setUserName((String)objects[1]);
				userlist.setPassword((String)objects[2]);
				userlist.setIsActive((Boolean)objects[3]);
				userlist.setValidFrom(dateService.dateFormat(dateService.dateParse(((String)objects[4]), "se"), "se"));
				userlist.setValidTo(dateService.dateFormat(dateService.dateParse(((String)objects[5]), "se"), "se"));
				listOfUsers.add(userlist);
			
			}
			
	      
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return listOfUsers;
	}
	public Users editUsersDetails(int userId) {
		// TODO Auto-generated method stub
		List<Users> listOfUsers=null;
		Users user=null;
		try
		{
			
			listOfUsers=uDao.editUsersDetails(userId);
			/*user.setValidFrom(dateService.dateFormat(dateService.dateParse((user.getValidFrom()), "se"), "se"));
			user.setValidTo(dateService.dateFormat(dateService.dateParse((user.getValidTo()), "se"), "se"));
			*/if (listOfUsers != null) {
				Iterator<Users> iterator = listOfUsers.iterator();
				while (iterator.hasNext()) {
					user= (Users) iterator.next();
					
					user.setEditMsg("Edit");
				}
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	public String updateUsersDetails(Users user) {
		// TODO Auto-generated method stub
		try
		{
			msg=uDao.updateUsersDetails(user);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	public String deleteUsersDetails(int id) {
		// TODO Auto-generated method stub
		try
		{
			msg=uDao.deleteUsersDetails(id);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}


}
