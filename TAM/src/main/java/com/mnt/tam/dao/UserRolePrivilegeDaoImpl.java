package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.UserRolePrivilege;
import com.mnt.tam.daoSupport.TamDaoSupport;
@Repository("UserRolePrivilegeDao")
public class UserRolePrivilegeDaoImpl extends TamDaoSupport implements UserRolePrivilegeDao {
	private static Logger logger=Logger.getLogger(UserRoleDaoImpl.class);
	private String msg=null;
	private List<Object[]> listOfObjects=null;
	private String hql=null;
		public String saveUserRolePrivilegeDetails(UserRolePrivilege object) {
			// TODO Auto-generated method stub
			try
			{
				Serializable id=getHibernateTemplate().save(object);
				logger.info("id is=="+id);
				if(id!=null)
				{
					msg="S";
				}
				else
				{
					msg="F";
				}
			}
			catch(Exception e)
			{
				msg="F";
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
		public List<Object[]> searchUserRolePrivilegeDetails() {
			// TODO Auto-generated method stub
			try
			{
				hql="select s.userRolePrivilegeId,s.users,s.roles,s.privilege from UserRolePrivilege s";
				listOfObjects=getHibernateTemplate().find(hql);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			return listOfObjects;
		}
		public List<Object[]> basicSearchForUserRolePrivilege(String dbField,
				String operations, String basicSearchId) {
			// TODO Auto-generated method stub
			try
			{
				 hql = "select s.userRolePrivilegeId,s.users,s.roles,s.privilege from UserRolePrivilege s where s."
						+ dbField + "" + operations + "?";

				Object[] parameters = { basicSearchId };
				listOfObjects=getHibernateTemplate().find(hql,parameters);
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			return listOfObjects;
		}
		public List<UserRolePrivilege> editUserRolePrivilegeDetails(int UserRolePrivilegeId) {
			// TODO Auto-generated method stub
			List<UserRolePrivilege> listOfUserRolePrivileges=null;
			try
			{
				hql="from com.mnt.tam.bean.UserRolePrivilege s where s.userRolePrivilegeId="+UserRolePrivilegeId;
				listOfUserRolePrivileges=getHibernateTemplate().find(hql);
				logger.info("list of UserRolePrivilege are =="+listOfUserRolePrivileges.size());
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return listOfUserRolePrivileges;
		}
		public String updateUserRolePrivilegeDetails(UserRolePrivilege UserRolePrivilege) {
			// TODO Auto-generated method stub
			try
			{
				getHibernateTemplate().update(UserRolePrivilege);
				msg="S";
			}
			catch(Exception e )
			{
				msg="F";
				e.printStackTrace();
			}
			return msg;
		}
		public String deleteUserRolePrivilegeDetails(int id) {
			// TODO Auto-generated method stub
			try
			{
				UserRolePrivilege UserRolePrivilege=new UserRolePrivilege();
				UserRolePrivilege.setUserRolePrivilegeId(id);
				getHibernateTemplate().delete(UserRolePrivilege);
				msg="S";
				
			}
			catch(Exception e)
			{
				msg="F";
				logger.error(e.getMessage());
			}
			return msg;
		}
}
