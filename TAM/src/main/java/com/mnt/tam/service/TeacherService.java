/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.Teacher;



/**
 * @author devi
 *
 */
public interface TeacherService {
	public String saveTeacherDetails(Teacher teacher);
	public List<Teacher> searchTeacherDetails(Teacher teacher);
	public Teacher editTeacherDetails(int teacherId);
	public String updateTeacherDetails(Teacher teacher); 
	public String deleteTeacherDetails(int id);
    public Long checkTeacherCount(String fName, String tid);
}
