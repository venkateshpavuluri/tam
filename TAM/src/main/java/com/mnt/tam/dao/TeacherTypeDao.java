package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.TeacherType;

public interface TeacherTypeDao {
    public String saveTeacherTypeDetails(TeacherType object);

    public List<Object[]> searchTeacherTypeDetails();

    public List<Object[]> basicSearchForTeacherType(String dbField,
	    String operations, String basicSearchId);

    public List<TeacherType> editTeacherTypeDetails(int TeacherTypeId);

    public String updateTeacherTypeDetails(TeacherType TeacherType);

    public String deleteTeacherTypeDetails(int id);
    
    public Long duplicateCheck(String teacherType, String teacherTypeId);
}
