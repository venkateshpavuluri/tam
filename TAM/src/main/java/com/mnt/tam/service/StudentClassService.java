/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.StudentClass;

/**
 * @author venkateshp
 *
 */
public interface StudentClassService {
	public String saveStudentClassDetails(StudentClass studentClass);
	public List<StudentClass> searchStudentClassDetails(StudentClass studentClass);
	public StudentClass editStudentClassDetails(int studentId);
	public String updateStudentClasssDetails(StudentClass studentClass); 
	public String deleteStudentClassDetails(int id);
	public Long duplicateCheck(String studentClass, String studentId);

}
