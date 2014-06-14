/**
 * 
 */
package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.Users;
import com.mnt.tam.daoSupport.TamDaoSupport;

/**
 * @author devi
 *
 */
@Repository("uDao")
public class UsersDaoImpl extends TamDaoSupport implements UsersDao{
	private static Logger logger=Logger.getLogger(UsersDaoImpl.class);
	private String msg=null;
	private List<Object[]> listOfObjects=null;
	private String hql=null;
	
	
	public Long checkUserDup(String userName, String uid) {

		Iterator<Object[]> iterator = null;
		List<Object> list = null;
		Long l = 0l;
		try {
		    if (uid != null) {
			String sql = "select count(*) from Users st where  st.userName='"
				+ userName
				+ "' and st.userId!='"
				+ uid
				+ "'";
			listOfObjects = getHibernateTemplate().find(sql);
			iterator = listOfObjects.iterator();

			while (iterator.hasNext()) {
			    Object object = (Object) iterator.next();
			    l = (Long) object;

			}
		    } else {
			String sql = "select count(*) from Users st where  st.userName='"
				+ userName + "'";
			listOfObjects = getHibernateTemplate().find(sql);
			iterator = listOfObjects.iterator();

			while (iterator.hasNext()) {
			    Object object = (Object) iterator.next();
			    l = (Long) object;

			}
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		}
		return l;
	}
	
		public String saveUsersDetails(Users user) {
			// TODO Auto-generated method stub
			try
			{
				Serializable id=getHibernateTemplate().save(user);
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
		public List<Object[]> searchUsersDetails() {
			// TODO Auto-generated method stub
			try
			{
				hql="select u.userId,u.userName,u.password,u.isActive,u.validFrom,u.validTo from com.mnt.tam.bean.Users u ";
				listOfObjects=getHibernateTemplate().find(hql);
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			return listOfObjects;
		}
		public List<Object[]> basicSearchForUsers(String dbField,
				String operations, String basicSearchId) {
			// TODO Auto-generated method stub
			try
			{
				 hql = "select u.userId,u.userName,u.password,u.isActive,u.validFrom,u.validTo from com.mnt.tam.bean.Users u where u."
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
		public List<Users> editUsersDetails(int userId) {
			// TODO Auto-generated method stub
			List<Users> listOfUsers=null;
			try
			{
				hql="from com.mnt.tam.bean.Users s where s.userId="+userId;
				listOfUsers=getHibernateTemplate().find(hql);
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return listOfUsers;
		}
		public String updateUsersDetails(Users user) {
			// TODO Auto-generated method stub
			try
			{
				getHibernateTemplate().update(user);
				msg="S";
			}
			catch(Exception e )
			{
				msg="F";
				e.printStackTrace();
			}
			return msg;
		}
		public String deleteUsersDetails(int id) {
			// TODO Auto-generated method stub
			try
			{
				Users user=new Users();
				user.setUserId(id);
				getHibernateTemplate().delete(user);
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
