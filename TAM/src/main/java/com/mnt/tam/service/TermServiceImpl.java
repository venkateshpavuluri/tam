package com.mnt.tam.service;


import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.TermBean;
import com.mnt.tam.dao.TermDao;
@Service("tService")
public class TermServiceImpl implements TermService{
	@Autowired
	TermDao sDao;
	List<Object[]> objects=null;
	String msg=null;
	List<Object[]> list = null;
	private static Logger logger=Logger.getLogger(TermServiceImpl.class);
	Long l=0l;

	public Long checkTermCout(String term, String tid) {
		try {
		    l =sDao.checkTermCout(term, tid);
		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
		return l;
		
	}
	

	public String saveTermDetails(Object object){
		msg=sDao.saveTermDetails(object);
		
	return msg;
}
	public List<Object[]> searchTermDetails(Object object) {
		try {
			TermBean tbean=(TermBean) object;
							
				int id = tbean.getTerm_Id();
				String dbField = tbean.getXmlLabel();
				String operation = tbean.getOperations();
				String basicSearchId = tbean.getBasicSearchId();

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
				

				if (basicSearchId == "") {
					list = sDao.searchTermDetails();

				} else {
					list = sDao.termBasicSearch(dbField,operation,basicSearchId);	
				}

						
				

			} catch (Exception e) {
				e.printStackTrace();
			}
				
		return list;
	}
	
	
	public TermBean editTermDetails(int termId) {
		// TODO Auto-generated method stub
		List<TermBean> termList=null;
		TermBean term=null;
		try
		{
			termList=sDao.editTermDetails(termId);
			if (termList != null) {
				Iterator<TermBean> iterator = termList.iterator();
				while (iterator.hasNext()) {
					term= (TermBean) iterator.next();
					term.setEditMsg("Edit");
				}
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return term;
	}
	public String updateTermDetails(TermBean term) {
		// TODO Auto-generated method stub
		try
		{
			msg=sDao.updateTermDetails(term);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	
	public String deleteTermDetails(int id)
	{
		try {
			msg = sDao.deleteTermDetails(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}


