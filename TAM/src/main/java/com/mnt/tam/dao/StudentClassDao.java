/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.StudentClass;

/**
 * @author venkateshp
 * 
 */
public interface StudentClassDao {
    public String saveStudentClassDetails(StudentClass studentClass);

    public List<Object[]> searchStudentClassDetails();

    public List<Object[]> basicSearchForStudentClass(String dbField,
	    String operations, String basicSearchId);

    public List<Object[]> editStudentClassDetails(int studentId);

    public String updateStudentClassDetails(StudentClass studentClass);

    public String deleteStudentClassDetails(int id);

    public Long duplicateCheck(String studentClass, String studentId);
}
