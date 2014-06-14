package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.Section;

public interface SectionDao {
	public String saveSectionDetails(Section Section);
	public List<Object[]> searchSectionDetails(Section Section);
	public List<Section>  editSectionDetails(int SectionId);
	public String updateSectionDetails(Section Section); 
	public String deleteSectionDetails(int id);
	public List<Object[]> basicSearchForSection(String dbField,String operations,String basicSearchId);
	public Long duplicateCheck(String section, String sectionId);
}
