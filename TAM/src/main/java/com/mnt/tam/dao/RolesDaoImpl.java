/**
 * 
 */
package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.Roles;
import com.mnt.tam.daoSupport.TamDaoSupport;


/**
 * @author devi
 *
 */
@Repository("roleDao")
public class RolesDaoImpl extends TamDaoSupport implements RolesDao{
	String msg=null;
	List<Object[]> objects=null;
	private List<Object[]> listOfObjects=null;
	Iterator<Object[]> iterator = null;
	Long l = 0l;
	
	public Long checkDup(String roleName, String roleId) {

		 try {
			    if (roleId != null) {
				String sql = "select count(*) from Roles st where  st.role_Name='"
					+ roleName
					+ "' and st.role_Id!='"
					+ roleId
					+ "'";
				listOfObjects = getHibernateTemplate().find(sql);
				iterator = listOfObjects.iterator();

				while (iterator.hasNext()) {
				    Object object = (Object) iterator.next();
				    l = (Long) object;

				}
			    } else {
				String sql = "select count(*) from Roles st where  st.role_Name='"
					+ roleName + "'";
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
	
	public String saveRoleDetails(Object object){
		try
		{
						
		Roles role=(Roles)object;
		role.setRole_Id(1);
		   Serializable id= getHibernateTemplate().save(role);
		  
			msg="S";
			
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
		}
		return msg;
	}
	public List<Object[]> searchRoleDetails() {
		try {
			
			
			String hql = "select r.role_Id, r.role_Name from com.mnt.tam.bean.Roles r ";
			objects = getHibernateTemplate().find(hql);
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}
	public List<Object[]> roleBasicSearch(String label, String operator, String searchName) {
		try {
		
						
			String hql = "select r.role_Id, r.role_Name from com.mnt.tam.bean.Roles r  where r."
					+ label + "" + operator + " ? " ;
			objects = getHibernateTemplate().find(hql,searchName);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}
	
	public List<Roles> editRoleDetails(int roleId) {
		// TODO Auto-generated method stub
		List<Roles> roleList=null;
		try
		{
			String hql="from com.mnt.tam.bean.Roles r where r.role_Id="+roleId;
			roleList=getHibernateTemplate().find(hql);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return roleList;
	}
	public String updateRoleDetails(Roles role) {
		// TODO Auto-generated method stub
		try
		{
			getHibernateTemplate().update(role);
			msg="S";
		}
		catch(Exception e )
		{
			msg="F";
			e.printStackTrace();
		}
		return msg;
	}
	
	
	
	public String deleteRoleDetails(int id) {
		
		try {
			Roles delRole = getHibernateTemplate().get(
					Roles.class, id);
			
			getHibernateTemplate().delete(delRole);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "S";
	}
	
		

}
