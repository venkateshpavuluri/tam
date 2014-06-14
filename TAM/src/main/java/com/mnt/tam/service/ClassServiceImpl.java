package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.School;
import com.mnt.tam.bean.Classes;
import com.mnt.tam.dao.ClassDao;
@Service("classService")
public class ClassServiceImpl implements ClassService{
	private static Logger logger=Logger.getLogger(ClassServiceImpl.class);
	private String msg=null;
	@Autowired
	private ClassDao classDao;
	Long l =0l;
	public String saveClassesDetails(Classes Classes) {
			try
			{
				msg=classDao.saveClassDetails(Classes);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
	public List<Classes> searchClassesDetails(Classes Classes) {
			List<Object[]> listOfObjects=null;
			List<Classes> listOfClasss=null;
			try
			{
			
				
				String dbField = Classes.getXmlLabel();
				String operation = Classes.getOperations();
				String basicSearchId = Classes.getBasicSearchId();

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
				
			//sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.school,s.admissionNo from com.mnt.tam.bean.Class s ";
				if(Classes.getBasicSearchId().equals(""))
				{
				 listOfObjects=classDao.searchClassDetails();
				}
				else
				{
					listOfObjects=classDao.basicSearchForClass(dbField, operation, basicSearchId);
				}
				listOfClasss=new ArrayList<Classes>();
				Iterator<Object[]> iterator=listOfObjects.iterator();
				while(iterator.hasNext())
				{
					Object[] objects=(Object[])iterator.next();
					Classes listClass=new Classes();
					listClass.setClassId((Integer)objects[0]);
					listClass.setClassName((String)objects[1]);
					School s=(School)objects[2];
					listClass.setSchoolName(s.getSchoolName());
					
				listOfClasss.add(listClass);
				}
				
		
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return listOfClasss;
		}
	public Classes editClassesDetails(int ClassesId) {
			List<Classes> listOfClasss=null;
			Classes Class=null;
			try
			{
				listOfClasss=classDao.editClassDetails(ClassesId);
				if (listOfClasss != null) {
					Iterator<Classes> iterator = listOfClasss.iterator();
					while (iterator.hasNext()) {
						Class= (Classes) iterator.next();
						Class.setEditMsg("Edit");
					}
				}
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return Class;
		}
	public String updateClassesDetails(Classes Classes) {
			try
			{
				msg=classDao.updateClassDetails(Classes);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
	public String deleteClassesDetails(int id) {
			try
			{
				msg=classDao.deleteClassDetails(id);
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
	public Long duplicateCheck(String className, String classId) {
	    try{
		l = classDao.duplicateCheck(className, classId);
	    }catch(Exception e){
		e.printStackTrace();
	    }
	    return l;
	}


}
