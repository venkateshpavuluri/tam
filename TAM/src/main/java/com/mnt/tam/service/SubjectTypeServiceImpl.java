package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mnt.tam.bean.SubjectType;
import com.mnt.tam.dao.SubjectTypeDao;

@Service("subjectTypeService")
public class SubjectTypeServiceImpl implements SubjectTypeService {

    private static Logger logger = Logger
	    .getLogger(SubjectTypeServiceImpl.class);
    private String msg = null;
    @Autowired
    private SubjectTypeDao subjectTypeDao;
    Long l=0l;

    public String saveSubjectTypeDetails(SubjectType subjectType) {
	try {
	    msg = subjectTypeDao.saveSubjectTypeDetails(subjectType);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<SubjectType> searchSubjectTypeDetails(SubjectType subjectType) {
	List<Object[]> listOfObjects = null;
	List<SubjectType> listOfSubTypes = null;
	try {

	    String dbField = subjectType.getXmlLabel();
	    String operation = subjectType.getOperations();
	    String basicSearchId = subjectType.getBasicSearchId();

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
	    if (subjectType.getBasicSearchId().equals("")) {
		listOfObjects = subjectTypeDao.searchSubjectTypeDetails();
	    } else {
		listOfObjects = subjectTypeDao.basicSearchForSubjectType(dbField,
			operation, basicSearchId);
	    }
	    listOfSubTypes = new ArrayList<SubjectType>();
	    Iterator<Object[]> iterator = listOfObjects.iterator();
	    while (iterator.hasNext()) {
		Object[] objects = (Object[]) iterator.next();
		SubjectType listSubType = new SubjectType();
		listSubType.setSubjectType((String) objects[1]);
		listSubType.setSubjectTypeId((Integer) objects[0]);
		listOfSubTypes.add(listSubType);
	    }

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfSubTypes;
    }

    public SubjectType editSubjectTypeDetails(int subjectTypeId) {
	List<SubjectType> listOfSubjectTypes = null;
	SubjectType type = null;
	try {
	    listOfSubjectTypes = subjectTypeDao.editSubjectTypeDetails(subjectTypeId);
	    if (listOfSubjectTypes != null) {
		Iterator<SubjectType> iterator = listOfSubjectTypes.iterator();
		while (iterator.hasNext()) {
		    type = (SubjectType) iterator.next();
		    type.setEditMsg("Edit");
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return type;
    }

    public String updateSubjectTypeDetails(SubjectType subjectType) {
	try {
	    msg = subjectTypeDao.updateSubjectTypeDetails(subjectType);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteSubjectTypeDetails(int id) {
	try {
	    msg = subjectTypeDao.deleteSubjectTypeDetails(id);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateCheck(String subjectType, String subjectId) {
	 try{
	     l = subjectTypeDao.duplicateCheck(subjectType, subjectId);
	 }catch(Exception e){
	     logger.error(e.getMessage());
	 }
	 return l;
    }
}
