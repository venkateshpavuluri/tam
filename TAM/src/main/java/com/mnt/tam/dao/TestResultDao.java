/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;

import org.json.simple.JSONArray;

import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.TestResult;

/**
 * @author venkateshp
 *
 */
public interface TestResultDao {
	public String saveTestResultDetails(List<TestResult> listOfTests);
	public List<Object[]> searchTestResultDetails(); 
	public List<Object[]> basicSearchForTestResult(String dbField,String operations,String basicSearchId);
    public List<Object[]> editTestResultDetails(int testId,int classId,int subjectId);
    public String updateTestResultDetails(TestResult test);
	public String deleteTestResultDetails(int testId,int classId,int subjectId,String subjType);
	public List<Object[]> listOfSubjects(int subjectId);
	public List<Object[]> getStudents(int classId);
	public List<Object[]> getTests(int classId);
	public List<Object[]> getChildObjects(int subjectId);
	public String deleteAllTestResultDetails(int classId,int subjectId,int testId);
	public Long duplicateCheck(int classId, int subjectId, int testId);
}
