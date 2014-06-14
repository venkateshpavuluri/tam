/**
 * @Copyright MNTSOFT
 */
package com.mnt.tam.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.util.logging.resources.logging;

import com.mnt.tam.dao.XmlLabelsDao;


/**
 * @author Naresh
 * @version 1.0 11-10-2013
 * 
 */
@Service("xmlService")
public class XmlLabelsServiceImpl implements XmlLabelsService {
@Autowired
	XmlLabelsDao xmlDao;
	public Map<String, String> populateXmlLabels(String name) {
		Map<String, String> map = null;
		try {
			map = xmlDao.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public List<Object[]> populateXml(String name) {
		   List<Object[]> returnString= null;
			try {
				returnString = xmlDao.populateXml(name);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return returnString;
		}

}
