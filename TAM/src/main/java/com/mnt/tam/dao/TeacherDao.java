/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;


import com.mnt.tam.bean.Teacher;

/**
 * @author devi
 *
 */
public interface TeacherDao {
	public String saveTeacherDetails(Teacher object);
	public List<Object[]> searchTeacherDetails(); 
	public List<Object[]> basicSearchForTeacher(String dbField,String operations,String basicSearchId);
    public List<Teacher> editTeacherDetails(int teacherId);
    public String updateTeacherDetails(Teacher teacher);
	public String deleteTeacherDetails(int id);
	public Long checkTeacherCount(String fname,String tid);

}
