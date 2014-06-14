/**
 * 
 */
package com.mnt.tam.service;

import java.util.List;


import com.mnt.tam.bean.TermTest;

/**
 * @author devi
 *
 */
public interface TermTestService {
	public String saveTermTestDetails(TermTest termtest);
	public List<TermTest> searchTermTestDetails(TermTest termtest);
	public TermTest editTermTestDetails(int termTestId);
	public String updateTermTestDetails(TermTest termtest); 
	public String deleteTermTestDetails(int id);
    public Long checkTermTestDup(String ttestName, String ttid);
}
