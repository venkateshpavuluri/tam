/**
 * 
 */
package com.mnt.tam.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mnt.tam.bean.ChildSubjectsJson;
import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.StudentTestResult;
import com.mnt.tam.bean.SubjectBean;
import com.mnt.tam.daoSupport.TamDaoSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author devi
 *
 */
@Repository("strDao")
public class StudentTestResultDaoImpl extends TamDaoSupport implements StudentTestResultDao{
	 private static Logger logger = Logger.getLogger(StudentTestResultDaoImpl.class);
	    private String msg = null;
	    private List<Object[]> listOfObjects = null;
	    private String hql = null;

	    public String saveStudentTestDetails(List<StudentTestResult> list) {
		try {
		 getHibernateTemplate().saveOrUpdateAll(list);
		  msg = "S";
		} catch (Exception e) {
		    msg = "F";
		    logger.error(e.getMessage());
		    e.printStackTrace();
		}
		return msg;
	    }

	    public List<Object[]> searchStudentTestDetails() {
		try {
		    /*hql = "select s.studentResultId, s.test,s.marksGained, s.student, s.termTest from com.mnt.tam.bean.StudentTestResult s ";*/
		    listOfObjects = getHibernateTemplate().findByNamedQuery("getStudentTestResultDetails");
		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
		return listOfObjects;
	    }
	    public List<Object[]> basicSearchForStudentTest(String dbField,
		    String operations, String basicSearchId) {
		try {
	    listOfObjects=getHibernateTemplate().findByNamedQueryAndNamedParam("getBasicSearchForStudentTestResult",new String[]{"dbField","operation","id"}, new Object[]{dbField,operations,basicSearchId});
		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
		return listOfObjects;
	    }
	    public List<Object[]> editStudentTestDetails(int testId,int termTestId,int subjectId,int classId) {
		List<Object[]> listOfSubjects = null;
		try {
			hql="select count(*) from com.mnt.tam.bean.StudentTestResult s where s.testId="+testId+" and s.termTestId="+termTestId+"and classId="+classId+" and s.parentSubjectId="+subjectId;
			List<Long> list=getHibernateTemplate().find(hql);
			if(list.size()!=0)
			{
				if(list.get(0)!=0)
				{
		    hql = "select s.testId,s.termTestId,s.marksGained,s.classId,s.student,s.subjectBean,s.parentSubjectId,s.test from com.mnt.tam.bean.StudentTestResult s where s.testId="+testId+" and s.termTestId="+termTestId+"and s.classId="+classId+" and s.parentSubjectId="+subjectId;
		    listOfSubjects = getHibernateTemplate().find(hql);
				}
				else
				{
					 hql = "select s.testId,s.termTestId,s.marksGained,s.classId,s.student,s.subjectBean,s.subjectId,s.test from com.mnt.tam.bean.StudentTestResult s where s.testId="+testId+" and s.termTestId="+termTestId+"and s.classId="+classId+" and s.subjectId="+subjectId;
					    listOfSubjects = getHibernateTemplate().find(hql);
				}
		    logger.info("list of subjects are ==" + listOfSubjects.size());
			}
		} catch (Exception e) {
		    logger.error(e.getMessage());
		    e.printStackTrace();
		}
		return listOfSubjects;
	    }
	    public String updateStudentTestDetails(StudentTestResult student) {
		try {
		    getHibernateTemplate().update(student);
		    msg = "S";
		} catch (Exception e) {
		    msg = "F";
		    e.printStackTrace();
		}
		return msg;
	    }
	    public String deleteStudentTestDetails(int termTestId,int subjectId,int classId) {
		try {
			hql = "delete from com.mnt.tam.bean.StudentTestResult u where u.classId=" + classId+" and u.termTestId="+termTestId+" and u.subjectId="+subjectId+" or u.parentSubjectId="+subjectId;
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
		    msg = "S";
		} catch (Exception e) {
		    msg = "F";
		    logger.error(e.getMessage());
		}
		return msg;
	    }
		public List<SubjectBean> getSubjects(int termTestId) {
			List<SubjectBean> list=null;
			try {
				// TODO Auto-generated method stub
				hql = "select t.subjectBean from  com.mnt.tam.bean.Test t  where t.termTestId="
						+ termTestId;
				list=getHibernateTemplate().find(hql);
				logger.debug("subjects size iss=="+list.size());
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return list;
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
		public String getEachTestMarks(int subjectId, int classId,
				int studentId, int testId) {
			List<Object> marks=null;		
			String testMarks=null;
			try {
				// TODO Auto-generated method stub
				hql = "select t.marks from com.mnt.tam.bean.TestResult t where t.classId="
						+ classId
						+ " and t.subjectId="
						+ subjectId
						+ " and t.studentId="
						+ studentId
						+ " and t.testId="
						+ testId;
				marks=getHibernateTemplate().find(hql);
				logger.debug("marks size in dao iss=="+marks.size());
				testMarks=(String)marks.get(0);
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return testMarks;
		}
		public List<Object[]> getAllTests(int subjectId, int termTestId) {
			// TODO Auto-generated method stub
			List<Object[]> list=null;
			try
			{
				hql="select t.testId,t.test from com.mnt.tam.bean.Test t where  t.subjectId="+subjectId+" and t.termTestId="+termTestId;
				list=getHibernateTemplate().find(hql);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.getMessage();
			}
			return list;
		}
		public String getMaxMarks(int subjectId) {
			List<Object> list=null;
			String maxMarks=null;
			try {
				// TODO Auto-generated method stub
				hql = "select t.maxMarks from com.mnt.tam.bean.SubjectBean t where t.subjectId="
						+ subjectId;
				list=getHibernateTemplate().find(hql);
				logger.debug("size iss=="+list.size()+"==sub id=="+subjectId);
				if(list.size()!=0)
				{
					maxMarks=String.valueOf((Integer)list.get(0));
				}
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return maxMarks;
		}
		public String deleteStudentTestResultDetails(int classId, int subjectId,
				 int termTestId, String type) {
			try
			{
				if(type.equals("parent"))
				{
				hql = "delete from com.mnt.tam.bean.StudentTestResult u where u.classId=" + classId+" and u.parentSubjectId="+subjectId+" and u.termTestId="+termTestId;
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
					hql = "delete from com.mnt.tam.bean.StudentTestResult u where  u.classId=" + classId+" and u.subjectId="+subjectId+" and u.termTestId="+termTestId;
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
		public String getTestMarks(int termtestId, int testId, int classId,
				int subjectId,int studentId) {
			// TODO Auto-generated method stub
			String marks="";
			try
			{
				hql="select s.marksGained from com.mnt.tam.bean.StudentTestResult s where s.termTestId="+termtestId+" and s.testId="+testId+" and s.classId="+classId+" and s.subjectId="+subjectId+" and s.studentId="+studentId;
			List<Object> list=getHibernateTemplate().find(hql);
			if(list.size()!=0)
			{
				marks=(String)list.get(0);
			}
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return marks;
		}
		public Long duplicateCheck(int termtestId, int testId, int classId,
				int subjectId, int studentId) {
			// TODO Auto-generated method stubtry
			List<Long> list=null;
			Long count=0l;
		try
		{
			hql="select count(*) from com.mnt.tam.bean.StudentTestResult s where s.termTestId="+termtestId+" and s.testId="+testId+" and s.classId="+classId+" and s.subjectId="+subjectId+" and s.studentId="+studentId;
		list=getHibernateTemplate().find(hql);
		System.out.println("count iss=="+list.size()+"==="+hql);
		if(list.size()!=0)
		{
			count=list.get(0);
		}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
			return count;
		}
}
