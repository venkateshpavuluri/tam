package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.Classes;

public interface ClassDao {
    public String saveClassDetails(Classes object);

    public List<Object[]> searchClassDetails();

    public List<Object[]> basicSearchForClass(String dbField,
	    String operations, String basicSearchId);

    public List<Classes> editClassDetails(int ClassId);

    public String updateClassDetails(Classes Class);

    public String deleteClassDetails(int id);

    public Long duplicateCheck(String className, String classId);
}
