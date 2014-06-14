/**
 * 
 */
package com.mnt.tam.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author devi
 *
 */
@Entity
@Table(name="teachersubject")
public class TeacherSubject {
	@Id
	@Column(name="TeacherSubject_Id")
	 @GeneratedValue(generator="increment")
		@GenericGenerator(name="increment", strategy = "increment")
	private int teacherSubjectId;
	@Column(name="Teacher_Id")
	private int teacherId;
	@Column(name="Class_Id")
	private String classId;
	@ManyToOne
    @JoinColumn(name="Class_Id",referencedColumnName="Class_Id",insertable=false,updatable=false)
	private Classes classBean;
	@Transient
	private String className;
	@Column(name="Subject_Id")
	private String subjectId;
	@ManyToOne
    @JoinColumn(name="Subject_Id",referencedColumnName="Subject_Id",insertable=false,updatable=false)
	private SubjectBean subjectBean;
	@Transient
	private String subject;
	
	
	//getters & setters
	
	
	public int getTeacherSubjectId() {
		return teacherSubjectId;
	}
	public void setTeacherSubjectId(int teacherSubjectId) {
		this.teacherSubjectId = teacherSubjectId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public Classes getClassBean() {
		return classBean;
	}
	public void setClassBean(Classes classBean) {
		this.classBean = classBean;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public SubjectBean getSubjectBean() {
		return subjectBean;
	}
	public void setSubjectBean(SubjectBean subjectBean) {
		this.subjectBean = subjectBean;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	
	

}
