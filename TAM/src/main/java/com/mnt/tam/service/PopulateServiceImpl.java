/**
 * Copyright MNTSOFT 
 */
package com.mnt.tam.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.dao.PoPulateDao;



/**
 * @author pvenkateswarlu
 * @version 1.0 09-10-2013
 */
@Service("populateService")
public class PopulateServiceImpl implements PopulateService {
@Autowired
private	PoPulateDao populateDao;	
	public List<Object[]> poPulate(String sql) {
		List<Object[]> list = null;
		try {
			list = populateDao.poPulate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public Map<String, String> populatePopUp(String sql) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		List<Object[]> list = null;

		try {
			list = populateDao.poPulate(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				map.put((String) objects[0], (String) objects[1]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public Map<Integer, String> populateSelectBox(String sql) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<Object[]> list = null;
		try {
			list = populateDao.poPulate(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[])iterator.next();
				map.put((Integer) objects[0],(String) objects[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
