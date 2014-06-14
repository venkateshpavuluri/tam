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

import com.mnt.tam.bean.Classes;
import com.mnt.tam.bean.School;
import com.mnt.tam.bean.Section;
import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.StudentClass;
import com.mnt.tam.dao.StudentClassDao;

/**
 * @author venkateshp
 * 
 */
@Service("studentClassService")
public class StudentClassServiceImpl implements StudentClassService {
    private static Logger logger = Logger
	    .getLogger(StudentClassServiceImpl.class);
    private String msg = null;
    @Autowired
    private StudentClassDao studentClassDao;
    Long l = 0l;

    public String saveStudentClassDetails(StudentClass studentClass) {
	// TODO Auto-generated method stub
	try {
	    msg = studentClassDao.saveStudentClassDetails(studentClass);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public List<StudentClass> searchStudentClassDetails(
	    StudentClass studentClass) {
	// TODO Auto-generated method stub
	List<Object[]> listOfObjects = null;
	List<StudentClass> listOfStudents = null;
	try {

	    String dbField = studentClass.getXmlLabel();
	    String operation = studentClass.getOperations();
	    String basicSearchId = studentClass.getBasicSearchId();

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
	    if (studentClass.getBasicSearchId().equals("")) {
		listOfObjects = studentClassDao.searchStudentClassDetails();
	    } else {
		// hql="select s.studentClassId,s.studentClass,s.section,s.student,s.classStudent from com.mnt.tam.bean.StudentClass s";
		listOfObjects = studentClassDao.basicSearchForStudentClass(
			dbField, operation, basicSearchId);
	    }
	    if (listOfObjects != null) {
		listOfStudents = new ArrayList<StudentClass>();

		Iterator<Object[]> iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object[] objects = (Object[]) iterator.next();
		    StudentClass searchStudents = new StudentClass();
		    searchStudents.setStudentClassId((Integer) objects[0]);
		    Section section = (Section) objects[1];
		    searchStudents.setSectionName(section.getSection());
		    Student student = (Student) objects[2];
		    searchStudents.setStudentName(student.getfName());
		    Classes classes = (Classes) objects[3];
		    searchStudents.setClassName(classes.getClassName());
		    searchStudents.setYear((String)objects[4]);
		    listOfStudents.add(searchStudents);
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfStudents;
    }

    public StudentClass editStudentClassDetails(int studentClassId) {
	// TODO Auto-generated method stub
	StudentClass studentClass = null;
	List<Object[]> listOfObjects = null;
	StudentClass studentClassInfo = null;
	try {
	    // hql="select s.studentClassId,s.studentClass,s.sectionId,s.classId,s.studentId from com.mnt.tam.bean.StudentClass  s where s.studentClassId="+studentClassId;
	    listOfObjects = studentClassDao
		    .editStudentClassDetails(studentClassId);
	    if (listOfObjects != null) {
		Iterator<Object[]> iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object[] objects = (Object[]) iterator.next();
		    studentClassInfo = new StudentClass();
		    studentClassInfo.setStudentClassId((Integer) objects[0]);
		  
		    studentClassInfo.setSectionId((String) objects[1]);
		    studentClassInfo.setClassId((String) objects[2]);
		    studentClassInfo.setStudentId((String) objects[3]);
		    studentClassInfo.setYear((String) objects[4]);
		    studentClassInfo.setEditMsg("Edit");

		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return studentClassInfo;
    }

    public String updateStudentClasssDetails(StudentClass studentClass) {
	// TODO Auto-generated method stub
	try {
	    msg = studentClassDao.updateStudentClassDetails(studentClass);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public String deleteStudentClassDetails(int id) {
	// TODO Auto-generated method stub
	try {
	    msg = studentClassDao.deleteStudentClassDetails(id);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateCheck(String studentClass, String studentId) {
	try {
	    l = studentClassDao.duplicateCheck(studentClass, studentId);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return l;
    }
}
