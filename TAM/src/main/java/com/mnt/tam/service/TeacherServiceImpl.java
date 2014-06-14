/**
 * 
 */
package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnt.tam.bean.Country;
import com.mnt.tam.bean.Teacher;
import com.mnt.tam.bean.TeacherSubject;
import com.mnt.tam.bean.TeacherType;
import com.mnt.tam.dao.TeacherDao;

/**
 * @author devi
 *
 */



@Service("teacherService")

public class TeacherServiceImpl implements TeacherService{
	@Autowired
	TeacherDao teachDao;
	private static Logger logger=Logger.getLogger(TeacherServiceImpl.class);
	String msg=null;
    Long l=0l;
	
	
	public Long checkTeacherCount(String fname, String tid) {
		try {
		    l = teachDao.checkTeacherCount(fname, tid);
		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
		return l;
	}
	
	
	public String saveTeacherDetails(Teacher teacher) {
		// TODO Auto-generated method stub
		try
		{
			
			msg=teachDao.saveTeacherDetails(teacher);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
			
		}
		return msg;
	}
	public List<Teacher> searchTeacherDetails(Teacher teacher) {
		// TODO Auto-generated method stub
		List<Object[]> listOfObjects=null;
		List<Teacher> listOfTeachers=null;
		try
		{
		
			
			String dbField = teacher.getXmlLabel();
			String operation = teacher.getOperations();
			String basicSearchId = teacher.getBasicSearchId();

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
			
			if(teacher.getBasicSearchId().equals(""))
			{
			 listOfObjects=teachDao.searchTeacherDetails();
			}
			else
			{
				listOfObjects=teachDao.basicSearchForTeacher(dbField, operation, basicSearchId);
			}
			listOfTeachers=new ArrayList<Teacher>();
			Iterator<Object[]> iterator=listOfObjects.iterator();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				Teacher teacherList=new Teacher();
				teacherList.setfName((String) objects[0]);
				teacherList.setlName((String) objects[1]);
				teacherList.setMidName((String) objects[2]);
				teacherList.setDob((String) objects[3]);
				teacherList.setGender((String) objects[4]);
				teacherList.setAddress((String) objects[5]);
				teacherList.setCity((String) objects[6]);
				teacherList.setState((String) objects[7]);
				Country c=((Country) objects[8]);
				teacherList.setCountry(c.getCountry());
				teacherList.setZip((String) objects[9]);
				teacherList.setEmail((String) objects[10]);
				teacherList.setPhoneNo((String) objects[11]);
				teacherList.setMobileNo((String) objects[12]);
				TeacherType teachType=(TeacherType) objects[13];
				teacherList.setTeacherType(teachType.getTeacherType());
				teacherList.setTeacherId((Integer) objects[14]);
			   listOfTeachers.add(teacherList);
		
			}
			
	
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			
		}
		return listOfTeachers;
	}
	 @Transactional(readOnly = true)
	public Teacher editTeacherDetails(int teacherId) {
		// TODO Auto-generated method stub
		List<Teacher> listOfTeachers=null;
		Set<Teacher> teach=new HashSet<Teacher>();
		Teacher teacher=null;
		 Set<TeacherSubject> teacherSubject=null;
		    TeacherSubject teachSubject=null;
		try
		{
			listOfTeachers=teachDao.editTeacherDetails(teacherId);
			if (listOfTeachers != null) {
				Iterator<Teacher> iterator = listOfTeachers.iterator();
				
				while (iterator.hasNext()) {
					teacher= (Teacher) iterator.next();
				}
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			
		}
		return teacher;
	}
	public String updateTeacherDetails(Teacher teacher) {
		// TODO Auto-generated method stub
		try
		{
			msg=teachDao.updateTeacherDetails(teacher);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			
		}
		return msg;
	}
	public String deleteTeacherDetails(int id) {
		// TODO Auto-generated method stub
		try
		{
			msg=teachDao.deleteTeacherDetails(id);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getMessage());
			
		}
		return msg;
	}


}
