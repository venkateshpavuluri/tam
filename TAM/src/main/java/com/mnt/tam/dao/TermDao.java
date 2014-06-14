package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.TermBean;

public interface TermDao {
	
	public String saveTermDetails(Object object);
	public List<Object[]> termBasicSearch(String label, String operator, String searchName);
	public List<Object[]> searchTermDetails();
	public List<TermBean> editTermDetails(int termId);
	public String deleteTermDetails(int id);
	public String updateTermDetails(TermBean term);
	public Long checkTermCout(String term,String tid);
	




}
