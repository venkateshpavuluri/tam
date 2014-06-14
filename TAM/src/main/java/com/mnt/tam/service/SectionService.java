package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.Section;

public interface SectionService {
	public String saveSectionDetails(Section Section);
	public List<Section> searchSectionDetails(Section Section);
	public Section editSectionDetails(int SectionId);
	public String updateSectionDetails(Section Section); 
	public String deleteSectionDetails(int id);
	public Long duplicateCheck(String section, String sectionId);
}
