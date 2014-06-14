/**
 * 
 */
package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.mnt.tam.bean.Student;
import com.mnt.tam.daoSupport.TamDaoSupport;

/**
 * @author venkateshp
 *
 */
@Repository("studentDao")
public class StudentDaoImpl extends TamDaoSupport implements StudentDao {
private static Logger logger=Logger.getLogger(StudentDaoImpl.class);
private String msg=null;
private List<Object[]> listOfObjects=null;
private String hql=null;
Long l = 0l;
Iterator<Object[]> iterator = null;
	public String saveStudentDetails(Student student) {
		try
		{
			Serializable id=getHibernateTemplate().save(student);
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
	public List<Object[]> searchStudentDetails() {
		try
		{
			hql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.countries,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.school,s.studentId,s.admissionNo from com.mnt.tam.bean.Student s ";
			listOfObjects=getHibernateTemplate().find(hql);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return listOfObjects;
	}
	public List<Object[]> basicSearchForStudent(String dbField,
			String operations, String basicSearchId) {
		try
		{
			 hql = "select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.countries,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.school,s.studentId,s.admissionNo from com.mnt.tam.bean.Student s where s."
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
	public List<Student> editStudentDetails(int studentId) {
		List<Student> listOfStudents=null;
		try
		{
			hql="from com.mnt.tam.bean.Student s where s.studentId="+studentId;
			listOfStudents=getHibernateTemplate().find(hql);
			logger.info("list of student are =="+listOfStudents.size());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return listOfStudents;
	}
	public String updateStudentDetails(Student student) {
		try
		{
			getHibernateTemplate().update(student);
			msg="S";
		}
		catch(Exception e )
		{
			msg="F";
			e.printStackTrace();
		}
		return msg;
	}
	public String deleteStudentDetails(int id) {
		try
		{
			Student student=new Student();
			student.setStudentId(id);
			getHibernateTemplate().delete(student);
			msg="S";
			
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
		}
		return msg;
	}
	public Long duplicateCheck(String name, String studentId) {
	    try {
		    if (studentId != null) {
			String sql = "select count(*) from Student st where  st.fName='"
				+ name
				+ "' and st.studentId!='"
				+ studentId
				+ "'";
			listOfObjects = getHibernateTemplate().find(sql);
			iterator = listOfObjects.iterator();

			while (iterator.hasNext()) {
			    Object object = (Object) iterator.next();
			    l = (Long) object;

			}
		    } else {
			String sql = "select count(*) from Student st where  st.fName='"
				+ name + "'";
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
}
