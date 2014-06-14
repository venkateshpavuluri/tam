package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.TeacherType;
import com.mnt.tam.dao.TeacherTypeDao;

@Service("TeacherTypeService")
public class TeacherTypeServiceImpl implements TeacherTypeService {
    private static Logger logger = Logger
	    .getLogger(TeacherTypeServiceImpl.class);
    private String msg = null;
    @Autowired
    private TeacherTypeDao teacherTypeDao;
    Long l = 0l;

    public String saveTeacherTypeDetails(TeacherType TeacherType) {
	try {
	    msg = teacherTypeDao.saveTeacherTypeDetails(TeacherType);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<TeacherType> searchTeacherTypeDetails(TeacherType TeacherType) {
	List<Object[]> listOfObjects = null;
	List<TeacherType> TeacherTypeList = null;
	try {

	    String dbField = TeacherType.getXmlLabel();
	    String operation = TeacherType.getOperations();
	    String basicSearchId = TeacherType.getBasicSearchId();

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

	    // sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.TeacherType,s.admissionNo from com.mnt.tam.bean.TeacherType s ";
	    if (TeacherType.getBasicSearchId().equals("")) {
		listOfObjects = teacherTypeDao.searchTeacherTypeDetails();
	    } else {
		listOfObjects = teacherTypeDao.basicSearchForTeacherType(
			dbField, operation, basicSearchId);
	    }
	    TeacherTypeList = new ArrayList<TeacherType>();
	    Iterator<Object[]> iterator = listOfObjects.iterator();
	    TeacherType TeacherTypeSearch = null;
	    while (iterator.hasNext()) {
		Object[] obj = (Object[]) iterator.next();
		TeacherTypeSearch = new TeacherType();
		TeacherTypeSearch.setTeacherTypeId((Integer) obj[0]);
		TeacherTypeSearch.setTeacherType((String) obj[1]);

		TeacherTypeList.add(TeacherTypeSearch);
	    }

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	logger.error("the list size is:" + TeacherTypeList.size());
	return TeacherTypeList;
    }

    public TeacherType editTeacherTypeDetails(int TeacherTypeId) {
	List<TeacherType> listOfTeacherTypes = null;
	TeacherType TeacherType = null;
	try {
	    listOfTeacherTypes = teacherTypeDao
		    .editTeacherTypeDetails(TeacherTypeId);
	    if (listOfTeacherTypes != null) {
		Iterator<TeacherType> iterator = listOfTeacherTypes.iterator();
		while (iterator.hasNext()) {
		    TeacherType = (TeacherType) iterator.next();
		    TeacherType.setEditMsg("Edit");
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return TeacherType;
    }

    public String updateTeacherTypeDetails(TeacherType TeacherType) {
	try {
	    msg = teacherTypeDao.updateTeacherTypeDetails(TeacherType);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteTeacherTypeDetails(int id) {
	try {
	    msg = teacherTypeDao.deleteTeacherTypeDetails(id);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateCheck(String teacherType, String teacherTypeId) {
	try {
	    l = teacherTypeDao.duplicateCheck(teacherType, teacherTypeId);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return l;
    }
}
