/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.mnt.tam.bean.ChildSubjectsJson;
import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.Test;
import com.mnt.tam.bean.TestResult;

/**
 * @author venkateshp
 *
 */
public interface TestResultService {
	public String saveTestResultDetails(TestResult testResult,HttpServletRequest request,HttpServletResponse response);
	public List<TestResult> searchTestResultDetails(TestResult testResult);
	public TestResult editTestResultDetails(int testId,int classId,int subjectId);
	public String updateTestResultDetails(TestResult testResult,HttpServletRequest request,HttpServletResponse response); 
	public String deleteTestResultDetails(int classId,int subjectId,int testId);
	public JSONArray getSubjects(int subjectId);
	public JSONArray getStudents(int classId,int subjectId);
	public JSONArray getTests(int classId);
	public Map<String, List<ChildSubjectsJson>> getChildObjects(int subjectId);
	public List<Student> getStudentForEdit(int classId);
	public Long duplicateCheck(int classId,int subjectId,int testId);

}
