package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.School;

public interface SchoolService {
	public String saveSchoolDetails(School School);
	public List<School> searchSchoolDetails(School School);
	public School editSchoolDetails(int SchoolId);
	public String updateSchoolDetails(School School); 
	public String deleteSchoolDetails(int id);
	public Long duplicateCheck(String school, String schoolId,String branchCode);
}
