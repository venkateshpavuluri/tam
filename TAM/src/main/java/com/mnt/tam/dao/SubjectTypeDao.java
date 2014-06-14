package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.SubjectType;

public interface SubjectTypeDao {

    public String saveSubjectTypeDetails(SubjectType object);

    public List<Object[]> searchSubjectTypeDetails();

    public List<Object[]> basicSearchForSubjectType(String dbField,
	    String operations, String basicSearchId);

    public List<SubjectType> editSubjectTypeDetails(int subjectTypeId);

    public String updateSubjectTypeDetails(SubjectType subjectType);

    public String deleteSubjectTypeDetails(int id);
    
    public Long duplicateCheck(String subjectType, String subjectId);
    
    

}
