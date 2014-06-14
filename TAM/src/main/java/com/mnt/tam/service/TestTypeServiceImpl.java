package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mnt.tam.bean.TestType;
import com.mnt.tam.dao.TestTypeDao;


@Service("testTypeService")
public class TestTypeServiceImpl implements TestTypeService {
    private static Logger logger = Logger.getLogger(TestTypeServiceImpl.class);
    private String msg = null;
    @Autowired
    private TestTypeDao testTypeDao;
    Long l = 0l;

    public String saveTestTypeDetails(TestType TestType) {
	try {
	    msg = testTypeDao.saveTestTypeDetails(TestType);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<TestType> searchTestTypeDetails(TestType TestType) {
	List<Object[]> listOfObjects = null;
	List<TestType> TestTypeList = null;
	try {

	    String dbField = TestType.getXmlLabel();
	    String operation = TestType.getOperations();
	    String basicSearchId = TestType.getBasicSearchId();

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

	    // sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.TestType,s.admissionNo from com.mnt.tam.bean.TestType s ";
	    if (TestType.getBasicSearchId().equals("")) {
		listOfObjects = testTypeDao.searchTestTypeDetails();
	    } else {
		listOfObjects = testTypeDao.basicSearchForTestType(dbField,
			operation, basicSearchId);
	    }
	    TestTypeList = new ArrayList<TestType>();
	    Iterator<Object[]> iterator = listOfObjects.iterator();
	    TestType TestTypeSearch = null;
	    while (iterator.hasNext()) {
		Object[] obj = (Object[]) iterator.next();
		TestTypeSearch = new TestType();
		TestTypeSearch.setTestTypeId((Integer) obj[0]);
		TestTypeSearch.setTestType((String) obj[1]);

		TestTypeList.add(TestTypeSearch);
	    }

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	logger.error("the list size is:" + TestTypeList.size());
	return TestTypeList;
    }

    public TestType editTestTypeDetails(int TestTypeId) {
	List<TestType> listOfTestTypes = null;
	TestType TestType = null;
	try {
	    listOfTestTypes = testTypeDao.editTestTypeDetails(TestTypeId);
	    if (listOfTestTypes != null) {
		Iterator<TestType> iterator = listOfTestTypes.iterator();
		while (iterator.hasNext()) {
		    TestType = (TestType) iterator.next();
		    TestType.setEditMsg("Edit");
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return TestType;
    }

    public String updateTestTypeDetails(TestType TestType) {
	try {
	    msg = testTypeDao.updateTestTypeDetails(TestType);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteTestTypeDetails(int id) {
	try {
	    msg = testTypeDao.deleteTestTypeDetails(id);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateCheck(String testType, String testTypeId) {
	try {
	    l = testTypeDao.duplicateCheck(testType, testTypeId);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return l;
    }
}
