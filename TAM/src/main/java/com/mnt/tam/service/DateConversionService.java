/**
 * 
 */
package com.mnt.tam.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.JAXBException;

import com.mnt.tam.bean.DatePatternsBean;



/**
 * @author devi
 *
 */
public interface DateConversionService {
public Date dateParse(String s, String se) throws ParseException,IOException;
public String dateFormat(Date d, String se) throws IOException;
public DatePatternsBean getDatePattern() throws JAXBException ;

}
