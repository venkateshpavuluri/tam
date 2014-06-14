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
import com.mnt.tam.bean.Country;
import com.mnt.tam.bean.School;
import com.mnt.tam.bean.Student;
import com.mnt.tam.dao.StudentDao;

/**
 * @author venkateshp
 * 
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    private static Logger logger = Logger.getLogger(StudentServiceImpl.class);
    private String msg = null;
    Long l = 0l;
    @Autowired
    private StudentDao studentDao;

    public String saveStudentDetails(Student student) {
	try {
	    msg = studentDao.saveStudentDetails(student);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<Student> searchStudentDetails(Student student) {
	List<Object[]> listOfObjects = null;
	List<Student> listOfStudents = null;
	try {

	    String dbField = student.getXmlLabel();
	    String operation = student.getOperations();
	    String basicSearchId = student.getBasicSearchId();

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

	    // sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.school,s.admissionNo from com.mnt.tam.bean.Student s ";
	    if (student.getBasicSearchId().equals("")) {
		listOfObjects = studentDao.searchStudentDetails();
	    } else {
		listOfObjects = studentDao.basicSearchForStudent(dbField,
			operation, basicSearchId);
	    }
	    listOfStudents = new ArrayList<Student>();
	    Iterator<Object[]> iterator = listOfObjects.iterator();
	    while (iterator.hasNext()) {
		Object[] objects = (Object[]) iterator.next();
		Student listStudent = new Student();
		listStudent.setfName((String) objects[0]);
		listStudent.setlName((String) objects[1]);
		listStudent.setMidName((String) objects[2]);
		listStudent.setDob((String) objects[3]);
		listStudent.setCity((String) objects[4]);
		listStudent.setState((String) objects[5]);
		Country country = (Country) objects[6];
		listStudent.setCountry(country.getCountry());
		listStudent.setFatehrName((String) objects[7]);
		listStudent.setmName((String) objects[8]);
		listStudent.setfOccupation((String) objects[9]);
		listStudent.setmOccupation((String) objects[10]);
		listStudent.setMobileNo((String) objects[11]);
		listStudent.setPhoneNo((String) objects[12]);
		School school = (School) objects[13];
		listStudent.setSchoolName(school.getSchoolName());
		listStudent.setStudentId((Integer) objects[14]);
		listStudent.setAdmissionNo((String) objects[15]);
		listOfStudents.add(listStudent);
	    }

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfStudents;
    }

    public Student editStudentDetails(int studentId) {
	List<Student> listOfStudents = null;
	Student student = null;
	try {
	    listOfStudents = studentDao.editStudentDetails(studentId);
	    if (listOfStudents != null) {
		Iterator<Student> iterator = listOfStudents.iterator();
		while (iterator.hasNext()) {
		    student = (Student) iterator.next();
		    student.setEditMsg("Edit");
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return student;
    }

    public String updateStudentDetails(Student student) {
	try {
	    msg = studentDao.updateStudentDetails(student);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteStudentDetails(int id) {
	try {
	    msg = studentDao.deleteStudentDetails(id);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateCheck(String name, String studentId) {
	try {
	    l = studentDao.duplicateCheck(name, studentId);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return l;
    }
}
