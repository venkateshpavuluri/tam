package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.SubjectBean;

public interface SubjectDao {

    public String saveSubjectDetails(SubjectBean object);

    public List<Object[]> searchSubjectDetails();

    public List<Object[]> basicSearchForSubject(String dbField,
	    String operations, String basicSearchId);

    public List<SubjectBean> editSubjectDetails(int subjectId);

    public String updateSubjectDetails(SubjectBean subject);

    public String deleteSubjectDetails(int id);
    
    public Long duplicateCheck(String subject, String subjectId);

}
