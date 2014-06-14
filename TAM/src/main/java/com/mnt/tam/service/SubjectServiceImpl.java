package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.Classes;
import com.mnt.tam.bean.SubjectBean;
import com.mnt.tam.bean.SubjectType;
import com.mnt.tam.dao.SubjectDao;
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
    private static Logger logger = Logger.getLogger(SubjectServiceImpl.class);
    private String msg = null;
    @Autowired
    private SubjectDao subjectDao;
    Long l =0l;

    public String saveSubjectDetails(SubjectBean subject) {
	try {
	    msg = subjectDao.saveSubjectDetails(subject);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<SubjectBean> searchSubjectDetails(SubjectBean subject) {
	List<Object[]> listOfObjects = null;
	List<SubjectBean> listOfSubjects = null;
	try {

	    String dbField = subject.getXmlLabel();
	    String operation = subject.getOperations();
	    String basicSearchId = subject.getBasicSearchId();

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

	    if (subject.getBasicSearchId().equals("")) {
		listOfObjects = subjectDao.searchSubjectDetails();
	    } else {
		listOfObjects = subjectDao.basicSearchForSubject(dbField,
			operation, basicSearchId);
	    }
	    listOfSubjects = new ArrayList<SubjectBean>();
	    Iterator<Object[]> iterator = listOfObjects.iterator();
	    while (iterator.hasNext()) {
		// select s.subjectId, s.subjectTypeId, s.subject,
		// s.parentSubjectId,s.maxMarks from
		// com.mnt.tam.bean.SubjectBean s
		Object[] objects = (Object[]) iterator.next();
		SubjectBean listSubject = new SubjectBean();
		listSubject.setSubjectId((Integer) objects[0]);
		listSubject.setSubjectTypeId((String) objects[1]);
		listSubject.setSubject((String) objects[2]);
		listSubject.setParentSubjectId((String) objects[3]);
		listSubject.setMaxMarks((Integer) objects[4]);
		SubjectType subjectType = (SubjectType) objects[5];
		listSubject.setSubjectTypeName(subjectType.getSubjectType());
		Classes classes=(Classes)objects[6];
		listSubject.setClassName(classes.getClassName());
		/*
		 * listSubject.setStudentId((Integer)objects[14]);
		 * listStudent.setAdmissionNo((String)objects[15]);
		 */
		listOfSubjects.add(listSubject);
	    }

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfSubjects;
    }

    public SubjectBean editSubjectDetails(int subejctId) {
	List<SubjectBean> listOfSubjects = null;
	SubjectBean subjectBean = null;
	try {
	    listOfSubjects = subjectDao.editSubjectDetails(subejctId);
	    if (listOfSubjects != null) {
		Iterator<SubjectBean> iterator = listOfSubjects.iterator();
		while (iterator.hasNext()) {
		    subjectBean = (SubjectBean) iterator.next();
		    subjectBean.setEditMsg("Edit");
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return subjectBean;
    }

    public String updateSubjectDetails(SubjectBean subject) {
	try {
	    msg = subjectDao.updateSubjectDetails(subject);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteSubjectDetails(int id) {
	try {
	    msg = subjectDao.deleteSubjectDetails(id);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateChek(String subject, String subjectId) {
	try{
	    l= subjectDao.duplicateCheck(subject, subjectId);
	}catch(Exception e){
	    e.printStackTrace();
	    logger.error(e.getMessage());
	    }
	return l;
    }

}
