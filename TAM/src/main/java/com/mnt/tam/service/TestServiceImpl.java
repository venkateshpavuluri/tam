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

import com.mnt.tam.bean.SubjectBean;
import com.mnt.tam.bean.TermTest;
import com.mnt.tam.bean.Test;
import com.mnt.tam.bean.TestType;
import com.mnt.tam.dao.TestDao;

/**
 * @author venkateshp
 * 
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    private static Logger logger = Logger.getLogger(TestServiceImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Long l = 0l;
    @Autowired
    private TestDao testDao;

    public String saveTestDetails(Test test) {
	// TODO Auto-generated method stub
	try {
	    msg = testDao.saveTestDetails(test);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<Test> searchTestDetails(Test test) {
	// TODO Auto-generated method stub
	List<Object[]> listOfObjects = null;
	List<Test> listOfTests = null;
	try {
	    String dbField = test.getXmlLabel();
	    String operation = test.getOperations();
	    String basicSearchId = test.getBasicSearchId();
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
	    if (test.getBasicSearchId().equals("")) {
		listOfObjects = testDao.searchTestDetails();
	    } else {
		// hql="select t.testId,t.test,t.testType,t.termTest,t.subjectBean from com.mnt.tam.bean.Test t";
		listOfObjects = testDao.basicSearchForTest(dbField, operation,
			basicSearchId);
	    }
	    if (listOfObjects != null) {
		listOfTests = new ArrayList<Test>();
		Iterator<Object[]> iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object[] objects = (Object[]) iterator.next();
		    Test testSearch = new Test();
		    testSearch.setTestId((Integer) objects[0]);
		    testSearch.setTest((String) objects[1]);
		    TestType testType = (TestType) objects[2];
		    testSearch.setTestTypeName(testType.getTestType());
		    SubjectBean subjectBean = (SubjectBean) objects[4];
		    testSearch.setSubjectName(subjectBean.getSubject());
		    TermTest termTest = (TermTest) objects[3];
		    testSearch.setTermTestName(termTest.getTerm());
		    listOfTests.add(testSearch);
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfTests;
    }

    public Test editTestDetails(int testId) {
	// TODO Auto-generated method stub
	Test test = null;
	try {
	    listOfObjects = testDao.editTestDetails(testId);
	    if (listOfObjects != null) {
		// hql="select t.testId,t.test,t.testTypeId,t.subjectId,t.termTestId from com.mnt.tam.bean.Test t wher t.testId";
		Iterator<Object[]> iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object[] objects = (Object[]) iterator.next();
		    test = new Test();
		    test.setTestId((Integer) objects[0]);
		    test.setTest((String) objects[1]);
		    test.setTestTypeId((String) objects[2]);
		    test.setSubjectId((String) objects[3]);
		    test.setTermTestId((String) objects[4]);
		    test.setEditMsg("Edit");

		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return test;
    }

    public String updateTestDetails(Test test) {
	// TODO Auto-generated method stub
	try {
	    msg = testDao.updateTestDetails(test);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public String deleteTestDetails(int id) {
	// TODO Auto-generated method stub
	try {
	    msg = testDao.deleteTestDetails(id);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String testName, String testId) {
	try {
	    l = testDao.duplicateCheck(testName, testId);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error(e.getMessage());
	}
	return l;
    }
}
