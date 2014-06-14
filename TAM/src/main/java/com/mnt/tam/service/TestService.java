/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.Test;

/**
 * @author venkateshp
 *
 */
public interface TestService {
	public String saveTestDetails(Test test);
	public List<Test> searchTestDetails(Test test);
	public Test editTestDetails(int testId);
	public String updateTestDetails(Test test); 
	public String deleteTestDetails(int id);
	public Long duplicateCheck(String testName, String testId);
}
