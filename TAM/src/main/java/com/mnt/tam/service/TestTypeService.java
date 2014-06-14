package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.TestType;

public interface TestTypeService {
	public String saveTestTypeDetails(TestType TestType);
	public List<TestType> searchTestTypeDetails(TestType TestType);
	public TestType editTestTypeDetails(int TestTypeId);
	public String updateTestTypeDetails(TestType TestType); 
	public String deleteTestTypeDetails(int id);
	public Long duplicateCheck(String testType, String testTypeId);
}
