package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.Classes;


public interface ClassService {
	public String saveClassesDetails(Classes Classes);
	public List<Classes> searchClassesDetails(Classes Classes);
	public Classes editClassesDetails(int ClassesId);
	public String updateClassesDetails(Classes Classes); 
	public String deleteClassesDetails(int id);
	public Long duplicateCheck(String className, String classId);
}
