package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.TermBean;

public interface TermService {
	public String saveTermDetails(Object object);
	public List<Object[]> searchTermDetails(Object object);
	public TermBean editTermDetails(int termId);
	public String updateTermDetails(TermBean term); 
	public String deleteTermDetails(int id);
	public Long checkTermCout(String term,String tid);
}
