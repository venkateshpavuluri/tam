<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {$('#events').click(function(event) {$("#usersForm").validate(
															{
																rules : {
																	userName : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	
																	},
																	password : {
																		required : true
																
																	},
																	isActive : {
																		required : true
																	},

																	validFrom : {
																		required : true,
																		
																	},
																	validTo : {
																		required : true,
																		
																	
																	},
																	

																},
																messages : {
																	userName :
																		{
																		required:"<font style='color: red;'><b>UserName is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																		
																		},
																		password : {
																			required:"<font style='color: red;'><b>Password is Required</b></font>",
																		
																			},
																	
																			isActive:{
																		required:"<font style='color: red;'><b>IsActive is Required</b></font>",
																		
																		},
																		validFrom:{
																			required:"<font style='color: red;'><b>ValidFrom is Required</b></font>",
																			
																			},
																			validTo:{
																				required:"<font style='color: red;'><b>ValidTo is Required</b></font>",
																				
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
      var cust = $('#userName').val();
      var id = $('#userId').val();
      $
              .ajax({
                type: "POST",
                url: "checkUserUpdateDuplicate.mnt",
                data: "userName=" + cust + "&userId=" + id,
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
      var uName = $('#userName').val();
      $
              .ajax({
                type: "POST",
                url: "checkUserDuplicate.mnt",
                data: "userName=" +uName,
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
		 $('#addMsg').show();
		 $('#editSuccMsg').hide();
		 $('#usersForm').attr('action','saveUsersDetails.mnt');
		$('#events').val('Save');
			$('#userName').val('');$('#password').val('');$('#isActive').val('');$('#validFrom').val('');$('#validTo').val('');$('#userId').val('0');
	});
	var msg=$('#editMsg').val();
	if(msg=="Edit")
		{
		 
		  $('#usersForm').attr('action','updateUsers.mnt');
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
<!--  Date picker -->
<script  type="text/javascript">
function dateFun(datePattern) {
	
	$(
			'#validFrom,#validTo')
	.datepicker({
		dateFormat : datePattern,
		changeMonth : true,
		changeYear : true
	});
}
		
</script>
	
</head>
<body>
<div>

<form:form action="searchUsers.mnt" method="get" commandName="users">
<table class="tableGeneral">
<tr>
<td colspan="3">
<c:if test="${param.list!=null}"> 
										<div class="alert-success" id="savemessage">
											<strong><s:message code="label.success"/></strong>
											<s:message code="label.users"/> <s:message code="label.saveSuccess"></s:message>
										</div>
									</c:if>
								<c:if test="${param.listWar!=null}"> 
										<div class="alert-danger" id="savemessage">
											<strong><s:message code="label.error"/> </strong>
												<s:message code="label.users"/> <s:message code="label.saveFail"></s:message>
										</div>
								</c:if>	
								<c:if test="${userUpdate!=null}">
										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.users"/> <s:message code="label.updateSuccess"></s:message>
										</div>
									</c:if><c:if test="${userUpdateFail!=null }">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.users"/> <s:message code="label.updateFail"></s:message>
										</div>
									</c:if><c:if test="${userDeleted!=null }">

										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.users"/> <s:message code="label.deleteSuccess"></s:message>
										</div>
                                 </c:if>
								<c:if test="${userDeleteFail!=null}">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.users"/> <s:message code="label.deleteFail"></s:message>
										</div>
									</c:if></td>
</tr>
<tr>
								<td><s:message code="label.searchby" /></td>
								<td><form:select path="xmlLabel" cssClass="select">
										<form:options items="${xmlItems}" />
									</form:select> <s:bind path="users.operations">
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



<c:if test="${listOfUsers!=null}">
						<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
						<display:table id="listOfUsers" name="listOfUsers"
							requestURI="SearchUsers.mnt" pagesize="5" class="table">
						<%-- 	<display:column property="userId" sortable="true" title="userId"
					media="none" /> --%>
							<display:column property="userName"
								titleKey="label.userName" media="html" sortable="true"></display:column>
							<display:column property="password" titleKey="label.password"
								media="html" sortable="true" />
							<display:column property="isActive" titleKey="label.isActive"
								media="html" sortable="true" />
							<display:column property="validFrom" titleKey="label.validFrom"
								media="html" sortable="true" />
							<display:column property="validTo" titleKey="label.validTo"
								media="html" sortable="true" />
						
									<display:column titleKey="label.edit" style="color:white">
										<a
											href="usersEdit.mnt?userEditId=<c:out value="${listOfUsers.userId}"/>"
											style="color: red"><img src="images/Edit.jpg"
											width="20px" height="20px" /></a>
									</display:column>
									<display:column titleKey="label.delete">
										<a
											href="usersDelete.mnt?userDelId=<c:out value="${listOfUsers.userId}"/>"
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
							<td colspan="2" id="userDuplMessage" style="display: none;">
								<div class="alert-warning">
									<strong> <s:message code="label.warning" /></strong>
									<s:message code="label.users" />
									<s:message code="label.duplicateCheck" />
								</div>

							</td>
						</tr>
						</table>
<form:form action="saveUsersDetails.mnt" id="usersForm"  method="POST" commandName="users">
<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.users" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
<table class="tableGeneral">
<tr>
<form:hidden path="editMsg" />
<form:hidden path="userId" id="userId"/>
<td><s:message code="label.userName"/><font color="red">*</font> </td><td><form:input path="userName" id="userName"  cssClass="textbox" onkeyup="duplicateChecking()"/> </td></tr>
<tr><td><s:message code="label.password"/><font color="red">*</font> </td><td><form:input path="password" id="password"  cssClass="textbox" /> </td></tr>

<tr>
<td><s:message code="label.isActive"/> <font color="red">*</font></td> <td><form:select path="isActive" id="isActive" cssClass="select">
<form:option value="">--Select--</form:option>
<form:option value="0">true</form:option>
<form:option value="0">false</form:option>
</form:select>  </td></tr>
<tr><td><s:message code="label.validFrom"/><font color="red">*</font> </td><td><form:input path="validFrom" id="validFrom"  cssClass="textbox"/> </td>
</tr>
<tr><td><s:message code="label.validTo"/><font color="red">*</font> </td><td><form:input path="validTo" id="validTo"  cssClass="textbox"/> </td>
</tr>

<tr><td>
<input type="submit" value="Save" id="events" class="btn btn-primary"></input>
</td>  </tr>
</table>
</form:form>
</div>

</body>
</html>