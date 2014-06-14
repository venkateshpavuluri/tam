/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.StudentClass;
import com.mnt.tam.bean.Test;

/**
 * @author venkateshp
 * 
 */
public interface TestDao {
    public String saveTestDetails(Test test);

    public List<Object[]> searchTestDetails();

    public List<Object[]> basicSearchForTest(String dbField, String operations,
	    String basicSearchId);

    public List<Object[]> editTestDetails(int testId);

    public String updateTestDetails(Test test);
    
    public Long duplicateCheck(String testName,String testId);

    public String deleteTestDetails(int id);
}
