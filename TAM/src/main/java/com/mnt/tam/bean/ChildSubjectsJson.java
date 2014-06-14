/**
 * 
 */
package com.mnt.tam.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.LinkedHashMap;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

/**
 * @author venkateshp
 * 
 */
public class ChildSubjectsJson implements Serializable, JSONStreamAware {
	private int childSubjectId;
	private String childSubject;
	private String marks;
	private String stage;
	private String childSubjs;
	private int childSpans;
	private int totalSubjects;
	

	/**
	 * @return the totalSubjects
	 */
	public int getTotalSubjects() {
		return totalSubjects;
	}
	/**
	 * @param totalSubjects the totalSubjects to set
	 */
	public void setTotalSubjects(int totalSubjects) {
		this.totalSubjects = totalSubjects;
	}
	/**
	 * @return the childSpans
	 */
	public int getChildSpans() {
		return childSpans;
	}
	/**
	 * @param childSpans the childSpans to set
	 */
	public void setChildSpans(int childSpans) {
		this.childSpans = childSpans;
	}
	/**
	 * @return the childSubjs
	 */
	public String getChildSubjs() {
		return childSubjs;
	}
	/**
	 * @param childSubjs the childSubjs to set
	 */
	public void setChildSubjs(String childSubjs) {
		this.childSubjs = childSubjs;
	}
	/**
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}
	/**
	 * @param stage the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}
	/**
	 * @return the childSubjectId
	 */
	public int getChildSubjectId() {
		return childSubjectId;
	}
	/**
	 * @param childSubjectId
	 *            the childSubjectId to set
	 */
	public void setChildSubjectId(int childSubjectId) {
		this.childSubjectId = childSubjectId;
	}
	/**
	 * @return the childSubject
	 */
	public String getChildSubject() {
		return childSubject;
	}
	/**
	 * @param childSubject
	 *            the childSubject to set
	 */
	public void setChildSubject(String childSubject) {
		this.childSubject = childSubject;
	}
	/**
	 * @return the marks
	 */
	public String getMarks() {
		return marks;
	}
	/**
	 * @param marks
	 *            the marks to set
	 */
	public void setMarks(String marks) {
		this.marks = marks;
	}
	@SuppressWarnings("unchecked")
	public void writeJSONString(Writer out) throws IOException {
		@SuppressWarnings("rawtypes")
		LinkedHashMap obj = new LinkedHashMap();
		obj.put("childSubjectId",childSubjectId);
		obj.put("childSubject",childSubject);
		obj.put("stage", stage);
		obj.put("childSubjs",childSubjs);
		obj.put("childSpans",childSpans);
		obj.put("marks",marks);
		JSONValue.writeJSONString(obj, out);
	}
}
