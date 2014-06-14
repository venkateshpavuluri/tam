package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.TeacherType;

public interface TeacherTypeService {
	public String saveTeacherTypeDetails(TeacherType TeacherType);
	public List<TeacherType> searchTeacherTypeDetails(TeacherType TeacherType);
	public TeacherType editTeacherTypeDetails(int TeacherTypeId);
	public String updateTeacherTypeDetails(TeacherType TeacherType); 
	public String deleteTeacherTypeDetails(int id);
	public Long duplicateCheck(String teacherType, String teacherTypeId);
}
