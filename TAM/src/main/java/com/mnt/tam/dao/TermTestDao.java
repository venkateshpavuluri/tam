/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;


import com.mnt.tam.bean.TermTest;

/**
 * @author devi
 *
 */
public interface TermTestDao {
	public String saveTermTestDetails(TermTest object);
	public List<Object[]> searchTermTestDetails(); 
	public List<Object[]> basicSearchForTermTest(String dbField,String operations,String basicSearchId);
    public List<TermTest> editTermTestDetails(int termtestId);
    public String updateTermTestDetails(TermTest termtest);
	public String deleteTermTestDetails(int id);
    public Long checkTermTestDup(String ttestName, String ttid);

}
