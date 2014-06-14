package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.SubjectType;

public interface SubjectTypeService {
    public String saveSubjectTypeDetails(SubjectType subjectType);

    public List<SubjectType> searchSubjectTypeDetails(SubjectType subjectType);

    public SubjectType editSubjectTypeDetails(int subjectTypeId);

    public String updateSubjectTypeDetails(SubjectType subjectType);

    public String deleteSubjectTypeDetails(int id);
    
    public Long duplicateCheck(String subjectType, String subjectId);

}
