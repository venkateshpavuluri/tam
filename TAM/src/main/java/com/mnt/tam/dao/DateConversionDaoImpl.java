/**
 * 
 */
package com.mnt.tam.dao;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


import com.mnt.tam.bean.DatePatternsBean;
import com.mnt.tam.daoSupport.TamDaoSupport;

/**
 * @author devi
 *
 */
@Repository("dateDao")
public class DateConversionDaoImpl extends TamDaoSupport implements DateConversionDao{
	@Autowired
	ServletContext context;
	JAXBContext jaxbContext;
	DatePatternsBean bean = null;

	public DatePatternsBean getDatePattern() throws JAXBException {
		File file = new File(context.getRealPath("/Resources/datePattern.xml"));
		jaxbContext = JAXBContext.newInstance(DatePatternsBean.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		bean = (DatePatternsBean) jaxbUnmarshaller.unmarshal(file);
		return bean;
	}
	
	public Date dateParse(String s, String se) throws ParseException,
			IOException {
		Date date = null;
		try {
			bean=getDatePattern();
			if (se.equals("se")) {
				SimpleDateFormat formatter = new SimpleDateFormat(
						bean.getDbPattern());
				date = formatter.parse(s);
			} else {
				SimpleDateFormat formatter = new SimpleDateFormat(
						bean.getConPattern());
				date = formatter.parse(s);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return date;
	}

	public String dateFormat(Date d, String se) throws IOException {
		String string = null;
		try {
			bean=getDatePattern();
			if (se.equals("se")) {
				DateFormat df = new SimpleDateFormat(bean.getConPattern());
				string = df.format(d);

			} else {
				DateFormat df = new SimpleDateFormat(bean.getDbPattern());
				string = df.format(d);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return string;

	}


}
