/**
 * 
 */
package com.mnt.tam.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Naresh
 * 
 */
@XmlRootElement
public class DatePatternsBean {

	private String datePattern;
	private String conPattern;
	private String dbPattern;

	public String getDatePattern() {
		return datePattern;
	}

	public String getConPattern() {
		return conPattern;
	}

	public String getDbPattern() {
		return dbPattern;
	}

	@XmlElement
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	@XmlElement
	public void setConPattern(String conPattern) {
		this.conPattern = conPattern;
	}

	@XmlElement
	public void setDbPattern(String dbPattern) {
		this.dbPattern = dbPattern;
	}

}
