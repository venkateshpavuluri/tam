/**
 * 
 */
package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.transaction.Transaction;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.json.simple.JSONArray;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.ChildSubjectsJson;
import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.TestResult;
import com.mnt.tam.daoSupport.TamDaoSupport;

/**
 * @author venkateshp
 *
 */
@Repository("testResultDao")
public class TestResultDaoImpl extends TamDaoSupport implements TestResultDao{
	private static Logger logger=Logger.getLogger(TestResultDaoImpl.class);
	private String msg=null;
	private List<Object[]> listOfObjects=null;
	private String hql=null;
	public String saveTestResultDetails(List<TestResult> listOfTests) {
		// TODO Auto-generated method stub
		try
		{
			getHibernateTemplate().saveOrUpdateAll(listOfTests);
			msg="S";
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	public List<Object[]> searchTestResultDetails() {
		// TODO Auto-generated method stub
		try
		{
			//hql="select t.testResultId,t.tests,t.classes,t.subjectBean,(select t.parentSubject from com.mnt.tam.bean.TestResult t where t.parentSubjectId!=NULL and t.parentSubjectId=NULL ) from com.mnt.tam.bean.TestResult t";
			listOfObjects=getHibernateTemplate().findByNamedQuery("getTestResultDetails");
			logger.info("in basic search size is=="+listOfObjects.size());		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return listOfObjects;
	}
	public List<Object[]> basicSearchForTestResult(String dbField,
			String operations, String basicSearchId) {
		// TODO Auto-generated method stub
		try
		{
		    listOfObjects=getHibernateTemplate().findByNamedQueryAndNamedParam("basicSearchForTestResult",new String[]{"dbField","operation","id"}, new Object[]{dbField,operations,basicSearchId});
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return listOfObjects;
	}
	public List<Object[]> editTestResultDetails(int testId,int classId,int subjectId) {
		// TODO Auto-generated method stub
		try
		{
			hql="select count(*) from  com.mnt.tam.bean.TestResult t where t.parentSubjectId="+subjectId;
			List<Long> count=getHibernateTemplate().find(hql);
			logger.debug("count iss=="+count.get(0));
			if(count.get(0)!=0)
			{
			hql="select t.testResultId,t.marks,t.tests,t.student,t.parentSubjectId,t.classId,t.subjectBean from com.mnt.tam.bean.TestResult t where t.testId="+testId+" and t.classId="+classId+" and t.parentSubjectId="+subjectId;
		listOfObjects=getHibernateTemplate().find(hql);
			}
			else
			{
				hql="select t.testResultId,t.marks,t.tests,t.student,t.subjectId,t.classId,t.subjectBean from com.mnt.tam.bean.TestResult t where t.testId="+testId+" and t.classId="+classId+" and t.subjectId="+subjectId;
				listOfObjects=getHibernateTemplate().find(hql);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return listOfObjects;
	}
	public String updateTestResultDetails(TestResult test) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteTestResultDetails(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Object[]> listOfSubjects(int classId) {
		// TODO Auto-generated method stub
		try
		{
			hql="select t.subjectId,t.subject from com.mnt.tam.bean.SubjectBean t where t.classId="+classId+" and t.parentSubjectId=0";
			listOfObjects=getHibernateTemplate().find(hql);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return listOfObjects;
	}
	public List<Object[]> getStudents(int classId) {
		// TODO Auto-generated method stub
		List<Student>	listOfStudents=null;

		try
		{
			hql="select t.classId,t.student from com.mnt.tam.bean.StudentClass t where t.classId="+classId;
			listOfObjects=getHibernateTemplate().find(hql);
		//	listOfStudents=getHibernateTemplate().findByNamedQueryAndNamedParam("getStudentTestDetails","classId",classId);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return listOfObjects;
	}
	public String deleteTestResultDetails(int testId, int classId, int subjectId,String subjType) {
		// TODO Auto-generated method stub
		try
		{
			if(subjType.equals("parent"))
			{
			hql = "delete from com.mnt.tam.bean.TestResult u where u.testId=" + testId
					+ " and u.classId=" + classId+" and u.parentSubjectId="+subjectId;
			logger.debug("delete query iss=="+hql);
		List list = getHibernateTemplate().executeFind(
					new HibernateCallback()
					{
						public String doInHibernate(
								org.hibernate.Session session)
								throws HibernateException {
							org.hibernate.Transaction transaction=session.beginTransaction();
							Query q = session.createQuery(hql);
					int count=q.executeUpdate();
					logger.debug("delete count iss=="+count);
							transaction.commit();
							return null;
						}
					});
		msg="S";
		}
			else
			{
				hql = "delete from com.mnt.tam.bean.TestResult u where u.testId=" + testId
						+ " and u.classId=" + classId+" and u.subjectId="+subjectId;
				logger.debug("delete query iss=="+hql);
			List list = getHibernateTemplate().executeFind(
						new HibernateCallback()
						{
							public String doInHibernate(
									org.hibernate.Session session)
									throws HibernateException {
								org.hibernate.Transaction transaction=session.beginTransaction();
								Query q = session.createQuery(hql);
						int count=q.executeUpdate();
						logger.debug("delete count iss=="+count);
								transaction.commit();
								return null;
							}
						});
			msg="S";
			}
		}
		
		catch(Exception e)
		{
			msg="F";
			e.printStackTrace();
		}
		return msg;
	}
	public List<Object[]> getTests(int subjectId) {
		// TODO Auto-generated method stub
		try
		{
			hql="select t.testId,t.test,s.maxMarks from com.mnt.tam.bean.Test t,com.mnt.tam.bean.SubjectBean s where t.subjectId="+subjectId+" and s.subjectId="+subjectId;
			listOfObjects=getHibernateTemplate().find(hql);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return listOfObjects;
	}
	
	public List<Object[]> getChildObjects(int subjectId)
	{
		Map<String,ChildSubjectsJson> map=null;
		List<Object[]> listOfObjects=null;
		try
		{
		hql="select s.subjectId,s.subject,s.maxMarks from com.mnt.tam.bean.SubjectBean s where s.parentSubjectId="+subjectId;
		listOfObjects=getHibernateTemplate().find(hql);
		
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return listOfObjects;
		
	}
	public String deleteAllTestResultDetails(int classId, int subjectId,
			int testId) {
		try {
			hql="select count(*) from  com.mnt.tam.bean.TestResult t where t.parentSubjectId="+subjectId;
			List<Long> count=getHibernateTemplate().find(hql);
			logger.debug("count iss=="+count.get(0));
			if(count.get(0)!=0)
			{
				hql = "delete from com.mnt.tam.bean.TestResult u where u.testId=" + testId
						+ " and u.classId=" + classId+" and u.parentSubjectId="+subjectId;
				logger.debug("delete query iss=="+hql);
			List list = getHibernateTemplate().executeFind(
						new HibernateCallback()
						{
							public String doInHibernate(
									org.hibernate.Session session)
									throws HibernateException {
								org.hibernate.Transaction transaction=session.beginTransaction();
								Query q = session.createQuery(hql);
						int count=q.executeUpdate();
						logger.debug("delete count iss=="+count);
								transaction.commit();
								return null;
							}
						});
			msg="S";
			}
			else
			{
				hql = "delete from com.mnt.tam.bean.TestResult u where u.testId=" + testId
						+ " and u.classId=" + classId+" and u.subjectId="+subjectId;
				logger.debug("delete query iss=="+hql);
			List list = getHibernateTemplate().executeFind(
						new HibernateCallback()
						{
							public String doInHibernate(
									org.hibernate.Session session)
									throws HibernateException {
								org.hibernate.Transaction transaction=session.beginTransaction();
								Query q = session.createQuery(hql);
						int count=q.executeUpdate();
						logger.debug("delete count iss=="+count);
								transaction.commit();
								return null;
							}
						});
			msg="S";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			msg="F";
			// TODO: handle exception
		}
		return msg;
	}
	public Long duplicateCheck(int classId, int subjectId, int testId) {
		//SELECT COUNT(*) FROM testresult t WHERE t.`Subject_Id`="13" OR t.`ParentSubject_Id`="13" AND t.`Test_Id`="10" AND t.`Class_Id`="1"
	List<Long> count=null;
	try {
		// TODO Auto-generated method stub
		hql = "select count(*) from com.mnt.tam.bean.TestResult u where "
				+ " u.subjectId="
				+ subjectId + " or u.parentSubjectId=" + subjectId+" and u.testId="+testId+" and u.classId="+classId;
		count = getHibernateTemplate().find(hql);
		logger.debug("testresult count iss==="+count.get(0));
	} catch (Exception e) {
		// TODO: handle exception
		logger.error(e.getMessage());
	}	
	
		return count.get(0);
	}
}
