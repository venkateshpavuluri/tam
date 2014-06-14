/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.Student;

/**
 * @author venkateshp
 *
 */
public interface StudentService {
	public String saveStudentDetails(Student student);
	public List<Student> searchStudentDetails(Student student);
	public Student editStudentDetails(int studentId);
	public String updateStudentDetails(Student student); 
	public String deleteStudentDetails(int id);
	public Long duplicateCheck(String name, String studentId);
}
