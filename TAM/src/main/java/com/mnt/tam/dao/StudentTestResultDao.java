/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;

import org.json.simple.JSONArray;

import com.mnt.tam.bean.StudentTestResult;
import com.mnt.tam.bean.SubjectBean;

/**
 * @author devi
 * 
 */
public interface StudentTestResultDao {
	public String saveStudentTestDetails(List<StudentTestResult> list);

	public List<Object[]> searchStudentTestDetails();

	public List<Object[]> basicSearchForStudentTest(String dbField,
			String operations, String basicSearchId);

	public List<Object[]> editStudentTestDetails(int testId, int termTestId,
			int subjectId, int classId);

	public String updateStudentTestDetails(StudentTestResult student);

	public String deleteStudentTestDetails(int termTestId,int subjectId,int classId);

	public List<SubjectBean> getSubjects(int termTestId);

	public List<Object[]> getChildObjects(int subjectId);

	public List<Object[]> getStudents(int classId);

	public String getEachTestMarks(int subjectId, int classId, int studentId,
			int testId);

	public List<Object[]> getAllTests(int subjectId, int termTestId);

	public String getMaxMarks(int subjectId);
	public Long duplicateCheck(int termtestId,int testId,int classId,int subjectId,int studentId);

	public String deleteStudentTestResultDetails(int classId, int subjectId,
			int termTestId, String type);

	public String getTestMarks(int termtestId, int testId, int classId,
			int subjectId,int studentId);
}
