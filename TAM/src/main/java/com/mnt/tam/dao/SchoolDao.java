package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.School;

/**
 * @author Parvathi
 * 
 */
public interface SchoolDao {
    public String saveSchoolDetails(School object);

    public List<Object[]> searchSchoolDetails();

    public List<Object[]> basicSearchForSchool(String dbField,
	    String operations, String basicSearchId);

    public List<School> editSchoolDetails(int schoolId);

    public String updateSchoolDetails(School school);

    public String deleteSchoolDetails(int id);
    
    public Long duplicateCheck(String school, String schoolId,String branchCode);

}
