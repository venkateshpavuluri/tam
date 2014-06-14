package com.mnt.tam.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="school")
public class School {
	 @Id  
	 @Column(name = "School_Id")  
	 @GeneratedValue(generator="increment")
		@GenericGenerator(name="increment", strategy = "increment")
	 private int schoolId;  
    @Column(name="School_Name")
    private String schoolName;
    @Column(name="Address")
    private String address;
    @Column(name="City")
    private String city;
    @Column(name="State")
    private String state;
    @Column(name="Branch_Code")
    private String branchCode;
    @Column(name="Country")
    private String country;
    @Transient
    private String editMsg;
    @Transient
    private int schoolIdEdit;
    //basic search properties
    @Transient
    private String xmlLabel;
    @Transient
	private String operations;
    @Transient
	private String basicSearchId;
	@ManyToOne
	@JoinColumn(name ="Country", referencedColumnName ="Country_Id", insertable = false, updatable = false)
	private Country countries;
	
	
	
  //getter and setter methods
	public int getSchoolId() {
		return schoolId;
	}

	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getXmlLabel() {
		return xmlLabel;
	}

	public void setXmlLabel(String xmlLabel) {
		this.xmlLabel = xmlLabel;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

	public String getBasicSearchId() {
		return basicSearchId;
	}

	public void setBasicSearchId(String basicSearchId) {
		this.basicSearchId = basicSearchId;
	}

	public String getEditMsg() {
		return editMsg;
	}

	public void setEditMsg(String editMsg) {
		this.editMsg = editMsg;
	}

	public int getSchoolIdEdit() {
		return schoolIdEdit;
	}

	public void setSchoolIdEdit(int schoolIdEdit) {
		this.schoolIdEdit = schoolIdEdit;
	}

	/**
	 * @return the countries
	 */
	public Country getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(Country countries) {
		this.countries = countries;
	}
    

    

}
