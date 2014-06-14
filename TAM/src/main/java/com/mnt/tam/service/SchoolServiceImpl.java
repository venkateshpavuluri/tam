package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.Country;
import com.mnt.tam.bean.School;
import com.mnt.tam.dao.SchoolDao;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService {
    private static Logger logger = Logger.getLogger(SchoolServiceImpl.class);
    private String msg = null;
    Long l = 0l;
    @Autowired
    private SchoolDao SchoolDao;

    public String saveSchoolDetails(School School) {
	// TODO Auto-generated method stub
	try {
	    msg = SchoolDao.saveSchoolDetails(School);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<School> searchSchoolDetails(School School) {
	// TODO Auto-generated method stub
	List<Object[]> listOfObjects = null;
	List<School> schoolList = null;
	try {

	    String dbField = School.getXmlLabel();
	    String operation = School.getOperations();
	    String basicSearchId = School.getBasicSearchId();

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

	    // sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.school,s.admissionNo from com.mnt.tam.bean.School s ";
	    if (School.getBasicSearchId().equals("")) {
		listOfObjects = SchoolDao.searchSchoolDetails();
	    } else {
		listOfObjects = SchoolDao.basicSearchForSchool(dbField,
			operation, basicSearchId);
	    }
	    schoolList = new ArrayList<School>();
	    Iterator<Object[]> iterator = listOfObjects.iterator();
	    School schoolSearch = null;
	    while (iterator.hasNext()) {
		Object[] obj = (Object[]) iterator.next();
		schoolSearch = new School();
		schoolSearch.setSchoolId((Integer) obj[0]);
		schoolSearch.setSchoolName((String) obj[1]);
		schoolSearch.setAddress((String) obj[2]);
		schoolSearch.setCity((String) obj[3]);
		schoolSearch.setState((String) obj[4]);
		schoolSearch.setBranchCode((String)obj[6]);
		Country c = (Country) obj[5];
		schoolSearch.setCountry(c.getCountry());

		schoolList.add(schoolSearch);
	    }

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	logger.error("the list size is:" + schoolList.size());
	return schoolList;
    }

    public School editSchoolDetails(int SchoolId) {
	// TODO Auto-generated method stub
	List<School> listOfSchools = null;
	School school = null;
	try {
	    listOfSchools = SchoolDao.editSchoolDetails(SchoolId);
	    if (listOfSchools != null) {
		Iterator<School> iterator = listOfSchools.iterator();
		while (iterator.hasNext()) {
		    school = (School) iterator.next();
		    school.setEditMsg("Edit");
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return school;
    }

    public String updateSchoolDetails(School School) {
	// TODO Auto-generated method stub
	try {
	    msg = SchoolDao.updateSchoolDetails(School);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteSchoolDetails(int id) {
	// TODO Auto-generated method stub
	try {
	    msg = SchoolDao.deleteSchoolDetails(id);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateCheck(String school, String schoolId,String branchCode) {
	try {
	    l = SchoolDao.duplicateCheck(school, schoolId,branchCode);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return l;
    }

}
