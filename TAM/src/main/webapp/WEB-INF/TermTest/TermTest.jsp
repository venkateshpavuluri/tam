<!-- @Copyright MNTSOFT
@author devi
@version 1.0 29-04-2014 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {$('#events').click(function(event) {$("#termTestForm").validate(
															{
																rules : {
																	termtest : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	
																	},
																	termTestCode : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	
																	},
																	
																	termId : {
																		required : true
																	}

																},
																messages : {
																	termtest :
																		{
																		required:"<font style='color: red;'><b>TermTest is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																		
																		},
																	termTestCode :
																		{
																		required:"<font style='color: red;'><b>TermTestCode is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																		
																		},
																																		
																		termId : "<font style='color: red;'><b>Term is Required</b></font>",
																	
																		},
																

															});
										});
					
		});
					
					
</script>
<script type="text/javascript">
	function duplicateChecking() {
		var msg = $('#editMsg').val();
		if (msg == "Edit") {
			var tname = $('#termtest').val();
			var ttid = $('#termtest_Id').val();
			$
					.ajax({
						type : "POST",
						url : "checkTermTestUpdateDuplicate.mnt",
						data : "termtest=" + tname + "&termtest_Id=" + ttid,
						success : function(response) {
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
						error : function(e) {
						}
					});
		} else {
			var ttName = $('#termtest').val();
			$
					.ajax({
						type : "POST",
						url : "checkTermTestDuplicate.mnt",
						data : "termtest=" + ttName,
						success : function(response) {
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
						error : function(e) {
						}
					});
		}
	}
</script>

<script>
$(document).ready(function() {
	$('#add').click(function() {
		 $('#addMsg').show();
		 $('#editSuccMsg').hide();
		 $('#termTestForm').attr('action','termTestSaveDetails.mnt');
		$('#events').val('Save');
			$('#termtest').val('');$('#termTestCode').val('');$('#termId').val('');$('#termtest_Id').val('0');
	});
	var msg=$('#editMsg').val();
	if(msg=="Edit")
		{
		  
		  $('#termTestForm').attr('action','termTestUpdate.mnt');
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>

<form:form action="termTestSearch.mnt" method="get" commandName="termTest">
<table class="tableGeneral">
<tr>
<td colspan="3">
<c:if test="${param.list!=null}"> 
										<div class="alert-success" id="savemessage">
											<strong><s:message code="label.success"/></strong>
											<s:message code="label.termTest"/> <s:message code="label.saveSuccess"></s:message>
										</div>
									</c:if>
								<c:if test="${param.listWar!=null}"> 
										<div class="alert-danger" id="savemessage">
											<strong><s:message code="label.error"/> </strong>
												<s:message code="label.termTest"/> <s:message code="label.saveFail"></s:message>
										</div>
								</c:if>	
								<c:if test="${termTestUpdate!=null}">
										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.termTest"/> <s:message code="label.updateSuccess"></s:message>
										</div>
									</c:if><c:if test="${termTestUpdateFail!=null }">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.termTest"/> <s:message code="label.updateFail"></s:message>
										</div>
									</c:if><c:if test="${termTestDeleted!=null }">

										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.termTest"/> <s:message code="label.deleteSuccess"></s:message>
										</div>
                                 </c:if>
								<c:if test="${termTestDeleteFail!=null}">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.termtTest"/> <s:message code="label.deleteFail"></s:message>
										</div>
									</c:if></td>
</tr>
<tr>
								<td><s:message code="label.searchby" /></td>
								<td><form:select path="xmlLabel" cssClass="select">
										<form:options items="${xmlItems}" />
									</form:select> <s:bind path="termTest.operations">
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



<c:if test="${listOfTermTests!=null}">
						<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
						<display:table id="listOfTermTests" name="listOfTermTests"
							requestURI="termTestSearch.mnt" pagesize="5" class="table">
							<display:column property="termtest"
								titleKey="label.termTest" media="html" sortable="true"></display:column>
							<display:column property="termTestCode" titleKey="label.termTestCode"
								media="html" sortable="true" />
												
							<display:column property="term" titleKey="label.term"
								media="html" sortable="true" />
							
																					
									<display:column titleKey="label.edit" style="color:white">
										<a
											href="termTestEdit.mnt?termTestId=<c:out value="${listOfTermTests.termtest_Id}"/>"
											style="color: red"><img src="images/Edit.jpg"
											width="20px" height="20px" /></a>
									</display:column>
									<display:column titleKey="label.delete">
										<a
											href="termTestDelete.mnt?termtestId=<c:out value="${listOfTermTests.termtest_Id}"/>"
											style="color: red"
											onclick="return confirm('Do u want to Delete The Record?')"><img
											src="images/Delete.jpg" width="20px" height="20px" /></a>
						</display:column>
								<display:setProperty name="paging.banner.placement" value="bottom" />
						</display:table>
					</c:if>
</div>
<div id="addForm" style="display:none" >


<table>
	          <tr>
							<td colspan="2" id="tTestDuplMessage" style="display: none;">
								<div class="alert-warning">
									<strong> <s:message code="label.warning" /></strong>
									<s:message code="label.termTest" />
									<s:message code="label.duplicateCheck" />
								</div>

							</td>
						</tr>
						</table>
<form:form action="termTestSaveDetails.mnt" id="termTestForm"  method="POST" commandName="termTest">
<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.termTest" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
<table class="tableGeneral">
<tr>
<form:hidden path="editMsg" />
<form:hidden path="termtest_Id" id="termtest_Id"/>
<td><s:message code="label.termTest"/><font color="red">*</font> </td><td><form:input path="termtest" id="termtest"  cssClass="textbox" onkeyup="duplicateChecking()"/> </td>
</tr>
<tr>
<td><s:message code="label.termTestCode"/><font color="red">*</font> </td><td><form:input path="termTestCode" id="termTestCode"  cssClass="textbox"/> </td>
</tr>

<tr>
<td><s:message code="label.term"/><font color="red">*</font> </td><td><form:select path="termId" id="termId" cssClass="select">
<form:option value="">--Select--</form:option>
<form:options items="${termDetails}"/>
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