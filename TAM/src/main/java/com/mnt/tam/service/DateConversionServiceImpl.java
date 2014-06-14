/**
 * 
 */
package com.mnt.tam.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.DatePatternsBean;
import com.mnt.tam.dao.DateConversionDao;

/**
 * @author devi
 *
 */
@Service("dateService")
public class DateConversionServiceImpl implements DateConversionService {
	@Autowired
	DateConversionDao dateDao;

	public Date dateParse(String s, String se) throws ParseException,
			IOException {
		Date date = null;
		try {
			date = dateDao.dateParse(s, se);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return date;
	}

	public String dateFormat(Date d, String se) throws IOException {
		String string = null;
		try {
			string = dateDao.dateFormat(d, se);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public DatePatternsBean getDatePattern() throws JAXBException {
		// TODO Auto-generated method stub

		DatePatternsBean bean=dateDao.getDatePattern();
		
		return bean;
	}





}
