package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.SubjectBean;


public interface SubjectService {
    public String saveSubjectDetails(SubjectBean subject);

    public List<SubjectBean> searchSubjectDetails(SubjectBean subject);

    public SubjectBean editSubjectDetails(int subejctId);

    public String updateSubjectDetails(SubjectBean subject);

    public String deleteSubjectDetails(int id);

    public Long duplicateChek(String subject, String subjectId);
}
