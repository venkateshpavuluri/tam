/**
 * 
 */
package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnt.tam.bean.Roles;
import com.mnt.tam.bean.Teacher;
import com.mnt.tam.bean.TeacherSubject;
import com.mnt.tam.daoSupport.TamDaoSupport;

/**
 * @author devi
 *
 */
@Repository("teachDao")
@Transactional

public class TeacherDaoImpl extends TamDaoSupport  implements TeacherDao{

	private static Logger logger=Logger.getLogger(TeacherDaoImpl.class);
	private String msg=null;
	private List<Object[]> listOfObjects=null;
	private String hql=null;
	
	public Long checkTeacherCount(String fname, String tid) {

		
		List<Object> list = null;
		Long l = 0l;
		Iterator<Object[]> iterator = null;
		
		 try {
			    if (tid != null) {
				String sql = "select count(*) from Teacher st where  st.fName='"
					+ fname
					+ "' and st.teacherId!='"
					+ tid
					+ "'";
				listOfObjects = getHibernateTemplate().find(sql);
				iterator = listOfObjects.iterator();

				while (iterator.hasNext()) {
				    Object object = (Object) iterator.next();
				    l = (Long) object;

				}
			    } else {
				String sql = "select count(*) from Teacher st where  st.fName='"
					+ fname + "'";
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
		public String saveTeacherDetails(Teacher teach) {
		
			// TODO Auto-generated method stub
			try
			{
				teach.setGender(teach.getGender());
				Serializable id=getHibernateTemplate().save(teach);
				Set<TeacherSubject> teacherSubject=null;
			    TeacherSubject teachSubject=null;
				List<TeacherSubject> teachSubLine=teach.getTeachers();
				teacherSubject = new HashSet<TeacherSubject>();
				for(TeacherSubject t:teachSubLine){
					teachSubject = new TeacherSubject();
					teachSubject.setClassId(t.getClassId());
					teachSubject.setTeacherId((Integer)id);
					teachSubject.setSubjectId(t.getSubjectId());
					teacherSubject.add(teachSubject);
				}
				getHibernateTemplate().saveOrUpdateAll(teacherSubject);
				
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
		public List<Object[]> searchTeacherDetails() {
			// TODO Auto-generated method stub
			try
			{
				hql="select t.fName,t.lName,t.midName,t.dob,t.gender,t.address,t.city,t.state,t.countries,t.zip,t.email,t.phoneNo,t.mobileNo,t.teacherTypeBean,t.teacherId from com.mnt.tam.bean.Teacher t ";
				listOfObjects=getHibernateTemplate().find(hql);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			return listOfObjects;
		}
		public List<Object[]> basicSearchForTeacher(String dbField,
				String operations, String basicSearchId) {
			// TODO Auto-generated method stub
			try
			{
				 hql = "select t.fName,t.lName,t.midName,t.dob,t.gender,t.address,t.city,t.state,t.countries,t.zip,t.email,t.phoneNo,t.mobileNo,t.teacherTypeBean,t.teacherId from com.mnt.tam.bean.Teacher t where t."
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
		 @Transactional(readOnly = true)
		public List<Teacher> editTeacherDetails(int teacherId) {
			// TODO Auto-generated method stub
			List<Teacher> listOfTeachers=null;
			try
			{
				hql="from com.mnt.tam.bean.Teacher t where t.teacherId="+teacherId;
				listOfTeachers=getHibernateTemplate().find(hql);
			
	
			for(Teacher ts:listOfTeachers){
				getHibernateTemplate().initialize(ts.getTeachers());	
				
			}
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
				
			}
			return listOfTeachers;
		}
		public String updateTeacherDetails(Teacher teacher) {
			// TODO Auto-generated method stub
			try
			{ 	
				
			
				Set<Teacher> teach=new HashSet<Teacher>();
				Set<TeacherSubject> teacherSubject=null;
				TeacherSubject teachSub=null;
				List<TeacherSubject> teachSubLine=teacher.getTeachers();
				teacherSubject = new HashSet<TeacherSubject>();
				for(TeacherSubject t:teachSubLine){
					teachSub = new TeacherSubject();
					teachSub.setClassId(t.getClassId());
					teachSub.setTeacherId(t.getTeacherId());
					teachSub.setSubjectId(t.getSubjectId());
					teachSub.setTeacherSubjectId(t.getTeacherSubjectId());
					teacherSubject.add(teachSub);
					
				}
				getHibernateTemplate().update(teacher);
				//getHibernateTemplate().saveOrUpdate(teacherSubject);
				msg="S";
			}
			catch(Exception e )
			{
				msg="F";
				e.printStackTrace();
			}
			return msg;
		}
		public String deleteTeacherDetails(int id) {
			// TODO Auto-generated method stub
			try
			{
				
				Teacher teacher=(Teacher)getHibernateTemplate().get(Teacher.class, id);
			
				Set<TeacherSubject> teacherSubjects=teacher.getTeacherSubjects();
				
				getHibernateTemplate().deleteAll(teacherSubjects);
				//getHibernateTemplate().clear();
					Teacher teachers=new Teacher();
					teachers.setTeacherId(id);
					getHibernateTemplate().delete(teachers);
					
				msg="S";
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				msg="F";
				
			//logger.error(e.getMessage());
			}
			return msg;
		}


}
