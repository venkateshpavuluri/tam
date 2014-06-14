/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.Student;

/**
 * @author venkateshp
 * 
 */
public interface StudentDao {
    public String saveStudentDetails(Student object);

    public List<Object[]> searchStudentDetails();

    public List<Object[]> basicSearchForStudent(String dbField,
	    String operations, String basicSearchId);

    public List<Student> editStudentDetails(int studentId);

    public String updateStudentDetails(Student student);

    public String deleteStudentDetails(int id);
    
    public Long duplicateCheck(String name, String studentId);
}
