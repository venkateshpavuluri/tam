package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.Section;
import com.mnt.tam.daoSupport.TamDaoSupport;

@Repository("sectionDao")
public class SectionDaoImpl extends TamDaoSupport implements SectionDao {
    private static Logger logger = Logger.getLogger(SectionDaoImpl.class);
    private String msg = null;
    private List<Object[]> listOfObjects = null;
    private String hql = null;
    Long l = 0l;
    Iterator<Object[]> iterator = null;

    public String saveSectionDetails(Section Section) {
	try {
	    Serializable id = getHibernateTemplate().save(Section);
	    logger.info("id is==" + id);
	    if (id != null) {
		msg = "S";
	    } else {
		msg = "F";
	    }
	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<Object[]> searchSectionDetails(Section Section) {
	try {
	    hql = "select s.sectionId,s.section,s.classes from Section s order by s.section";
	    listOfObjects = getHibernateTemplate().find(hql);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Object[]> basicSearchForSection(String dbField,
	    String operations, String basicSearchId) {
	try {
	    hql = "select s.sectionId,s.section,s.classes from Section s where s."
		    + dbField + "" + operations + "?";

	    Object[] parameters = { basicSearchId };
	    listOfObjects = getHibernateTemplate().find(hql, parameters);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return listOfObjects;
    }

    public List<Section> editSectionDetails(int SectionId) {
	List<Section> listOfSections = null;
	try {
	    hql = "from com.mnt.tam.bean.Section s where s.sectionId="
		    + SectionId;
	    listOfSections = getHibernateTemplate().find(hql);
	    logger.info("list of Section are ==" + listOfSections.size());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfSections;
    }

    public String updateSectionDetails(Section Section) {
	try {
	    getHibernateTemplate().update(Section);
	    msg = "S";
	} catch (Exception e) {
	    msg = "F";
	    e.printStackTrace();
	}
	return msg;
    }

    public String deleteSectionDetails(int id) {
	try {
	    Section Section = new Section();
	    Section.setSectionId(id);
	    getHibernateTemplate().delete(Section);
	    msg = "S";

	} catch (Exception e) {
	    msg = "F";
	    logger.error(e.getMessage());
	}
	return msg;
    }

    public Long duplicateCheck(String section, String sectionId) {
	try {
	    if (sectionId != null) {
		String sql = "select count(*) from Section s where  s.section='"
			+ section + "' and s.sectionId!='" + sectionId + "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();

		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    } else {
		String sql = "select count(*) from Section s where  s.section='"
			+ section + "'";
		listOfObjects = getHibernateTemplate().find(sql);
		iterator = listOfObjects.iterator();
		while (iterator.hasNext()) {
		    Object object = (Object) iterator.next();
		    l = (Long) object;
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return l;
    }
}
