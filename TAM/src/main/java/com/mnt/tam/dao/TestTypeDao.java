package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.TestType;

public interface TestTypeDao {
    public String saveTestTypeDetails(TestType object);

    public List<Object[]> searchTestTypeDetails();

    public List<Object[]> basicSearchForTestType(String dbField,
	    String operations, String basicSearchId);

    public List<TestType> editTestTypeDetails(int TestTypeId);

    public String updateTestTypeDetails(TestType TestType);

    public String deleteTestTypeDetails(int id);

    public Long duplicateCheck(String testType, String testTypeId);
}
