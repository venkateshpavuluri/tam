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

import com.mnt.tam.bean.TermBean;
import com.mnt.tam.bean.TermTest;
import com.mnt.tam.dao.TermTestDao;

/**
 * @author devi
 *
 */
@Service("termTestService")
public class TermTestServiceImpl implements TermTestService{
	private static Logger logger=Logger.getLogger(TermTestServiceImpl.class);
	private String msg=null;
	@Autowired
	private TermTestDao tTestDao;
	Long l=0l;
		
	
	public Long checkTermTestDup(String ttestName, String ttid) {
		try {
		    l = tTestDao.checkTermTestDup(ttestName, ttid);
		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
		return l;
	    }

	
		public String saveTermTestDetails(TermTest termtest) {
			// TODO Auto-generated method stub
			try
			{
				msg=tTestDao.saveTermTestDetails(termtest);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
		public List<TermTest> searchTermTestDetails(TermTest termtest) {
			// TODO Auto-generated method stub
			List<Object[]> listOfObjects=null;
			List<TermTest> listOftest=null;
			try
			{
			
				
				String dbField = termtest.getXmlLabel();
				String operation = termtest.getOperations();
				String basicSearchId = termtest.getBasicSearchId();

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
				
				if(termtest.getBasicSearchId().equals(""))
				{
				 listOfObjects=tTestDao.searchTermTestDetails();
				}
				else
				{
					listOfObjects=tTestDao.basicSearchForTermTest(dbField, operation, basicSearchId);
				}
				listOftest=new ArrayList<TermTest>();
				Iterator<Object[]> iterator=listOfObjects.iterator();
				while(iterator.hasNext())
				{
					Object[] objects=(Object[])iterator.next();
					TermTest termTestList= new TermTest();
					termTestList.setTermtest((String) objects[0]);
					termTestList.setTermTestCode((String) objects[1]);
					TermBean term=(TermBean)objects[2];
					termTestList.setTerm(term.getTerm());
					termTestList.setTermtest_Id((Integer) objects[3]);
				
				listOftest.add(termTestList);
				}
				
		
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return listOftest;
		}
		public TermTest editTermTestDetails(int termTestId) {
			// TODO Auto-generated method stub
			List<TermTest> listOfTermTest=null;
			TermTest termTest=null;
			try
			{
				listOfTermTest=tTestDao.editTermTestDetails(termTestId);
				if (listOfTermTest != null) {
					Iterator<TermTest> iterator = listOfTermTest.iterator();
					while (iterator.hasNext()) {
						termTest= (TermTest) iterator.next();
						termTest.setEditMsg("Edit");
					}
				}
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return termTest;
		}
		public String updateTermTestDetails(TermTest ttest) {
			// TODO Auto-generated method stub
			try
			{
				msg=tTestDao.updateTermTestDetails(ttest);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}
		public String deleteTermTestDetails(int id) {
			// TODO Auto-generated method stub
			try
			{
				msg=tTestDao.deleteTermTestDetails(id);				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return msg;
		}


}
