package com.mnt.tam.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.Classes;
import com.mnt.tam.bean.Section;
import com.mnt.tam.dao.SectionDao;

@Service("SectionService")
public class SectionServiceImpl implements SectionService {
    private static Logger logger = Logger.getLogger(SectionServiceImpl.class);
    private String msg = null;
    @Autowired
    private SectionDao sectionDao;
    Long l = 0l;

    public String saveSectionDetails(Section Section) {
	try {
	    msg = sectionDao.saveSectionDetails(Section);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<Section> searchSectionDetails(Section Section) {
	List<Object[]> listOfObjects = null;
	List<Section> SectionList = null;
	try {

	    String dbField = Section.getXmlLabel();
	    String operation = Section.getOperations();
	    String basicSearchId = Section.getBasicSearchId();

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

	    // sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.Section,s.admissionNo from com.mnt.tam.bean.Section s ";
	    if (Section.getBasicSearchId().equals("")) {
		listOfObjects = sectionDao.searchSectionDetails(Section);
	    } else {
		listOfObjects = sectionDao.basicSearchForSection(dbField,
			operation, basicSearchId);
	    }
	    SectionList = new ArrayList<Section>();
	    Iterator<Object[]> iterator = listOfObjects.iterator();
	    Section SectionSearch = null;
	    while (iterator.hasNext()) {
		Object[] obj = (Object[]) iterator.next();
		SectionSearch = new Section();
		SectionSearch.setSectionId((Integer) obj[0]);
		SectionSearch.setSection((String) obj[1]);

		Classes cs = (Classes) obj[2];
		SectionSearch.setClassName(cs.getClassName());
		SectionList.add(SectionSearch);
	    }

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	logger.error("the list size is:" + SectionList.size());
	return SectionList;
    }

    public Section editSectionDetails(int SectionId) {
	List<Section> listOfSections = null;
	Section Section = null;
	try {
	    listOfSections = sectionDao.editSectionDetails(SectionId);
	    if (listOfSections != null) {
		Iterator<Section> iterator = listOfSections.iterator();
		while (iterator.hasNext()) {
		    Section = (Section) iterator.next();
		    Section.setEditMsg("Edit");
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return Section;
    }

    public String updateSectionDetails(Section Section) {
	try {
	    msg = sectionDao.updateSectionDetails(Section);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteSectionDetails(int id) {
	try {
	    msg = sectionDao.deleteSectionDetails(id);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public Long duplicateCheck(String section, String sectionId) {
	try {
	    l = sectionDao.duplicateCheck(section, sectionId);

	} catch (Exception r) {
	    r.printStackTrace();
	}
	return l;
    }

}
