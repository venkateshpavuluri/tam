/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;

import com.mnt.tam.bean.ChildSubjectsJson;
import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.StudentTestResult;


/**
 * @author devi
 *
 */
public interface StudentTestResultService {
	 public String saveStudentTestDetails(StudentTestResult student,HttpServletRequest request);
	    public List<StudentTestResult> searchStudentTestDetails(StudentTestResult student);
	    public StudentTestResult editStudentTestDetails(int testId,int termTestId,int subjectId,int classId);
	    public String updateStudentTestDetails(StudentTestResult subject,HttpServletRequest request);
	    public String deleteStudentTestDetails(int termTestId,int subjectId,int classId);
	    public JSONArray getSubjects(int termTestId);
		public JSONArray getStudents(int classId,int subjectId);
		public String getEachTestMarks(int subjectId,int classId,int studentId,int testId);
		public JSONArray getAllTests(int subjectId,int termTestId);
		public List<StudentTestResult> getAllTestsForEdit(int subjectId,int termTestId);
		public String getMaxMarks(int subjectId);
		public Map<String, List<ChildSubjectsJson>> getChildObjects(int subjectId);
		public List<Student> getStudentForEdit(int classId);
		public String getTestMarks(int termtestId,int testId,int classId,int subjectId,int studentId);
		public Long duplicateCheck(int termtestId,int testId,int classId,int subjectId,int studentId);
	
}
