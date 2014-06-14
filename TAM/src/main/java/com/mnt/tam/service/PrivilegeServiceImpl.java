package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.Privilege;
import com.mnt.tam.dao.PrivilegeDao;

@Service("PrivilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {
    private static Logger logger = Logger.getLogger(PrivilegeServiceImpl.class);
    private String msg = null;
    @Autowired
    private PrivilegeDao PrivilegeDao;
    Long l = 0l;

    public String savePrivilegeDetails(Privilege Privilege) {
	// TODO Auto-generated method stub
	try {
	    msg = PrivilegeDao.savePrivilegeDetails(Privilege);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<Privilege> searchPrivilegeDetails(Privilege Privilege) {
	// TODO Auto-generated method stub
	List<Object[]> listOfObjects = null;
	List<Privilege> PrivilegeList = null;
	try {

	    String dbField = Privilege.getXmlLabel();
	    String operation = Privilege.getOperations();
	    String basicSearchId = Privilege.getBasicSearchId();

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

	    // sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.Privilege,s.admissionNo from com.mnt.tam.bean.Privilege s ";
	    if (Privilege.getBasicSearchId().equals("")) {
		listOfObjects = PrivilegeDao.searchPrivilegeDetails();
	    } else {
		listOfObjects = PrivilegeDao.basicSearchForPrivilege(dbField,
			operation, basicSearchId);
	    }
	    PrivilegeList = new ArrayList<Privilege>();
	    Iterator<Object[]> iterator = listOfObjects.iterator();
	    Privilege PrivilegeSearch = null;
	    while (iterator.hasNext()) {
		Object[] obj = (Object[]) iterator.next();
		PrivilegeSearch = new Privilege();
		PrivilegeSearch.setPrivilegeId((Integer) obj[0]);
		PrivilegeSearch.setPrivilege((String) obj[1]);

		PrivilegeList.add(PrivilegeSearch);
	    }

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	logger.error("the list size is:" + PrivilegeList.size());
	return PrivilegeList;
    }

    public Privilege editPrivilegeDetails(int PrivilegeId) {
	// TODO Auto-generated method stub
	List<Privilege> listOfPrivileges = null;
	Privilege Privilege = null;
	try {
	    listOfPrivileges = PrivilegeDao.editPrivilegeDetails(PrivilegeId);
	    if (listOfPrivileges != null) {
		Iterator<Privilege> iterator = listOfPrivileges.iterator();
		while (iterator.hasNext()) {
		    Privilege = (Privilege) iterator.next();
		    Privilege.setEditMsg("Edit");
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return Privilege;
    }

    public String updatePrivilegeDetails(Privilege Privilege) {
	// TODO Auto-generated method stub
	try {
	    msg = PrivilegeDao.updatePrivilegeDetails(Privilege);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public String deletePrivilegeDetails(int id) {
	// TODO Auto-generated method stub
	try {
	    msg = PrivilegeDao.deletePrivilegeDetails(id);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateCheck(String privilege, String privilegeId) {
	try {
	    l = PrivilegeDao.duplicateCheck(privilege, privilegeId);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return l;
    }
}
