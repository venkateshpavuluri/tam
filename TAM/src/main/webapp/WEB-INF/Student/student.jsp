<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/common.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {$('#events').click(function(event) {$("#studentForm").validate(
															{
																rules : {
																	fName : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	
																	},
																	dob : {
																		required : true
																
																	},
																	city : {
																		required : true
																	},

																	state : {
																		required : true,
																		minlength:2
																	
																	},
																	country : {
																		required : true,
																	},
																	fatehrName : {
																		required : true,
																	},

																	mName : {
																		required : true,
																	},
																	schoolId : {
																		required : true
																	}
																},
																messages : {
																	fName :
																		{
																		required:"<font style='color: red;'><b>First Name is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																		},
																		dob : {
																			required:"<font style='color: red;'><b>Date Of Birth is Required</b></font>",
																			},
																	city : {
																		required:"<font style='color: red;'><b>City is Required</b></font>",
																		minlength:"<font style='color: red;'><b>Minimum 2 Characters Required</b></font>",
																		},
																	state : {
																		required:"<font style='color: red;'><b>State is Required</b></font>",
																		minlength:"<font style='color: red;'><b>Minimum 2 Characters Required</b></font>",
																		},
																	country:{
																		required:"<font style='color: red;'><b>Country is Required</b></font>",
																		
																		},
																		fatehrName : {
																		required:"<font style='color: red;'><b>Father Name is Required</b></font>",
																		},
																		schoolId : "<font style='color: red;'><b>School is Required</b></font>",
																	mName :{
																		required:"<font style='color: red;'><b>Mother Name is Required</b></font>",
																		},
																},

															});
										});
					
		});
</script>
<script type="text/javascript">
  function duplicateChecking() {
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      var cust = $('#fName').val();
      var id = $('#studentId').val();
      $
              .ajax({
                type: "POST",
                url: "checkStudentUpdateDuplicate.mnt",
                data: "fName=" + cust + "&studentId=" + id,
                success: function(response) {
                  if (response != "") {
                    document.getElementById("eventDuplMessage").style.display = "block";
                    $('#events').attr('disabled', 'disabled');
                    $('#events').hide();
                  } else {
                    document.getElementById("eventDuplMessage").style.display = "none";
                    $('#events').show();
                    $('#events').removeAttr('disabled');
                  }
                },
                error: function(e) {
                }
              });
    } else {
      var fName = $('#fName').val();
      $
              .ajax({
                type: "POST",
                url: "checkStudentDuplicate.mnt",
                data: "fName=" + fName,
                success: function(response) {
                  if (response != "") {
                    document.getElementById("eventDuplMessage").style.display = "block";
                    $('#events').attr('disabled', 'disabled');
                    $('#events').hide();
                  } else {
                    document.getElementById("eventDuplMessage").style.display = "none";
                    $('#events').show();
                    $('#events').removeAttr('disabled');
                  }
                },
                error: function(e) {
                }
              });
    }
  }
</script>
<script>
$(document).ready(function() {
	$('#add').click(function() {
		$('#display').hide();
		 $('#addMsg').show();
		 $('#editSuccMsg').hide();
		 $('#eventDuplMessage').hide();
		 $('#events').show();
		 $('#studentForm').attr('action','studentSaveDetails.mnt');
		 $('#events').val('Save');
			$('#fName').val('');$('#midName').val('');$('#lName').val('');$('#admissionNo').val('');$('#dob').val('');$('#city').val('');$('#state').val('');$('#country').val('');$('#fatehrName').val('');$('#mName').val('');$('#fOccupation').val('');$('#mOccupation').val('');$('#mobileNo').val('');$('#phoneNo').val('');$('#studentId').val('0');$('#schoolId').val('');
	});
	var msg=$('#editMsg').val();
	if(msg=="Edit")
		{
		  $('#studentForm').attr('action','studentUpdate.mnt');
		  $('#addMsg').hide();
		  $('#editSuccMsg').show();
		  $('#events').val('Update');
		  $("#addForm").show();
		}
}
);
function viewAdd()
{
	  $("#addForm").show();
	}
</script>
<script >
$(function() {
	$("#dob").datepicker({changeMonth:true,changeYear:true});
});
</script>
</head>
<body>
<div>

<form:form action="studentSearch.mnt" method="get" commandName="student">
<table class="tableGeneral">
<tr>
<td colspan="3">
<c:if test="${param.list!=null}"> 
										<div class="alert-success" id="savemessage">
											<strong><s:message code="label.success"/></strong>
											<s:message code="label.student"/> <s:message code="label.saveSuccess"></s:message>
										</div>
									</c:if>
								<c:if test="${param.listWar!=null}"> 
										<div class="alert-danger" id="savemessage">
											<strong><s:message code="label.error"/> </strong>
												<s:message code="label.student"/> <s:message code="label.saveFail"></s:message>
										</div>
								</c:if>	
								<c:if test="${studentUpdate!=null}">
										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.student"/> <s:message code="label.updateSuccess"></s:message>
										</div>
									</c:if><c:if test="${studentUpdateFail!=null }">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.student"/> <s:message code="label.updateFail"></s:message>
										</div>
									</c:if><c:if test="${studentDeleted!=null }">

										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.student"/> <s:message code="label.deleteSuccess"></s:message>
										</div>
                                 </c:if>
								<c:if test="${studentDeleteFail!=null}">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.student"/> <s:message code="label.deleteFail"></s:message>
										</div>
									</c:if></td>
</tr>
<tr>
								<td><s:message code="label.searchby" /></td>
								<td><form:select path="xmlLabel" cssClass="select">
										<form:options items="${xmlItems}" />
									</form:select> <s:bind path="student.operations">
								<select class="select" name="operations">
								<option value="<s:message code='assignedOperator'/>"><s:message code="label.equalsTo"/> </option>
								<option value="<s:message code='notequalsOperator'/>"><s:message code="label.notEqualsTo"/> </option>
							 <option value="<s:message code='beginsWithOperator'/>"> <s:message code="label.beginsWith"/> </option> 
								<option value="<s:message code='endsWithOperator'/>"><s:message code="label.endsWith"/> </option>
								<option value="<s:message code='containsOperator'/>"><s:message code="label.contains"/></option>
								</select>
									 </s:bind><form:input path="basicSearchId" cssClass="textbox" /></td>
									 <td><input type="submit" value="<s:message code='label.search'/>" class="btn btn-primary" /> </td>
									 </tr>									 
<tr><td><input type="button" value="Add" class="btn btn-primary" id="add" onclick="viewAdd()"/> </td></tr>
</table></form:form>
</div>
<div id="display">



<c:if test="${listOfStudents!=null}">
						<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
						<display:table id="listOfStudents" name="listOfStudents"
							requestURI="studentSearch.mnt" pagesize="5" class="table">
							<display:column property="fName"
								titleKey="label.fName" media="html" sortable="true"></display:column>
							<display:column property="schoolName" titleKey="label.school"
								media="html" sortable="true" />
							<display:column property="dob" titleKey="label.dob"
								media="html" sortable="true" />
							<display:column property="admissionNo" titleKey="label.addmissionNo"
								media="html" sortable="true" />
							<display:column property="city" titleKey="label.city"
								media="html" sortable="true" />
							<display:column property="state" titleKey="label.state"
								sortable="true" />
							<display:column property="country" titleKey="label.country"
								sortable="true" />
							<display:column property="phoneNo" titleKey="label.phoneNo"
								sortable="true" />
							<display:column property="mobileNo" titleKey="label.mobileNo"
								sortable="true" />
						
									<display:column titleKey="label.edit" style="color:white">
										<a
											href="studentEdit.mnt?studentId=<c:out value="${listOfStudents.studentId}"/>"
											style="color: red"><img src="images/Edit.jpg"
											width="20px" height="20px" /></a>
									</display:column>
									<display:column titleKey="label.delete">
										<a
											href="studentDelete.mnt?studentId=<c:out value="${listOfStudents.studentId}"/>"
											style="color: red"
											onclick="return confirm('Do u want to Delete The Record?')"><img
											src="images/Delete.jpg" width="20px" height="20px" /></a>
						</display:column>
								<display:setProperty name="paging.banner.placement" value="bottom" />
						</display:table>
					</c:if>
</div>
<div id="addForm" style="display:none" >
<form:form action="studentSaveDetails.mnt" id="studentForm"  method="POST" commandName="student">
<table>
<tr>
<td colspan="2" id="eventDuplMessage" style="display: none;" class="alert-warning">
<div>
<strong> <s:message code="label.warning" /></strong>
<s:message code="label.fName" />
<s:message code="label.duplicateCheck" />
</div>
</td>
</tr>
</table>
<table class="tableGeneral">

<tr>
<form:hidden path="editMsg" />
<form:hidden path="studentId" id="studentId"/>
<td><s:message code="label.fName"/><font color="red">*</font> </td><td><form:input path="fName" id="fName"  cssClass="textbox" onkeyup="duplicateChecking()"/> </td>
<td><s:message code="label.mName"/> </td><td><form:input path="midName" id="midName"  cssClass="textbox" /> </td>
</tr>
<tr>
<td><s:message code="label.lName"/> </td><td><form:input path="lName" id="lName"  cssClass="textbox"/> </td>
<td><s:message code="label.addmissionNo"/> </td><td><form:input path="admissionNo" id="admissionNo"  cssClass="textbox"/> </td>
</tr>
<tr>
<td><s:message code="label.dob"/><font color="red">*</font> </td><td><form:input path="dob" id="dob" cssClass="textbox"/> </td>
<td><s:message code="label.address"/> </td><td><form:textarea path="address" id="address" cssClass="textArea"/> </td>
</tr>
<tr>
<td><s:message code="label.city"/><font color="red">*</font> </td><td><form:input path="city" id="city" cssClass="textbox"/> </td>
<td><s:message code="label.state"/><font color="red">*</font> </td><td><form:input path="state" id="state" cssClass="textbox"/> </td>
</tr>
<tr>
<td><s:message code="label.country"/><font color="red">*</font> </td><td><form:select path="country" cssClass="select">
<form:option value="">--Select--</form:option>
<form:options items="${countryDetails}"></form:options>
</form:select>  </td>
<td><s:message code="label.fatherName"/> <font color="red">*</font></td><td><form:input path="fatehrName" id="fatehrName" cssClass="textbox" /> </td>

</tr>
<tr>
<td><s:message code="label.motherName"/><font color="red">*</font> </td><td><form:input path="mName" id="mName"  cssClass="textbox"/> </td>
<td><s:message code="label.fatherOccupation"/> </td><td><form:input path="fOccupation" id="fOccupation" cssClass="textbox"/> </td>

</tr>
<tr>
<td><s:message code="label.motherOccupation"/> </td><td><form:input path="mOccupation" id="mOccupation" cssClass="textbox" /> </td>
<td><s:message code="label.mobileNo"/> </td><td><form:input path="mobileNo" id="mobileNo" cssClass="textbox"/> </td>

</tr>
<tr>
<td><s:message code="label.phoneNo"/> </td><td><form:input path="phoneNo" id="phoneNo" cssClass="textbox"/> </td>
<td><s:message code="label.school"/><font color="red">*</font> </td><td><form:select path="schoolId" id="schoolId" cssClass="select">
<form:option value="">--Select--</form:option>
<form:options items="${schoolDetails}"/>
</form:select> </td>
</tr>
<tr><td>
<input type="submit" value="Save" id="events" class="btn btn-primary"></input>
</td>  </tr>
</table>
</form:form>
</div>
</body>
</html>