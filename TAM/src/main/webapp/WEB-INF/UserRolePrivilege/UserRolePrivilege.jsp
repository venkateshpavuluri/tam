<!-- @Copyright MNTSOFT
@author Parvathi
@version 1.0 24-04-2014 -->


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/common.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {$('#events').click(function(event) {$("#UserRolePrivilegeForm").validate(
															{
																rules : {
																	userId : {
																		required : true,
																	
																	},
																	roleId:{
																		required:true,
																	},
																	privilegeId:{
																		required:true,
																	}
																	

																},
																messages : {
																	userId :
																		{
																		required:"<font style='color: red;'><b>User is Required</b></font>",
																		
																		},
																		roleId :
																		{
																		required:"<font style='color: red;'><b>Role is Required</b></font>",
																		
																		},
																		privilegeId :
																		{
																		required:"<font style='color: red;'><b>Privilege is Required</b></font>",
																		
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
      var userId = $('#userId').val();
      var roleId=$('#roleId').val();
      var userRoleId = $('#userRoleId').val();
      $
              .ajax({
                type: "POST",
                url: "checkUserRoleUpdateDuplicate.mnt",
                data: "userId=" + userId+ "&roleId=" + roleId+"&userRoleId=" + userRoleId,
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
      var roleName = $('#roleId').val();
      var userName= $('#userId').val();
      var privelege = $('#privilegeId').val();
      $
              .ajax({
                type: "POST",
                url: "checkUserRolesDuplicate.mnt",
                data: "roleName=" + roleName + "&userName=" + userName+"&privelege=" + privelege,
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
		 $('#UserRolePrivilegeForm').attr('action','UserRolePrivilegeSaveDetails.mnt');
		 $('#events').val('Save');
			$('#UserRolePrivilegeName').val('');$('#address').val('');$('#city').val('');$('#state').val('');$('#country').val('');$('#UserRolePrivilegeId').val('0');
	});
	var msg=$('#editMsg').val();
	if(msg=="Edit")
		{
		
		  $('#UserRolePrivilegeForm').attr('action','UserRolePrivilegeUpdate.mnt');
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
</head>
<body>
<div>



<form:form action="UserRolePrivilegeSearch.mnt" method="get" commandName="UserRolePrivilege">
<table class="tableGeneral">
<tr>
<td colspan="3">
<c:if test="${param.list!=null}"> 
										<div class="alert-success" id="savemessage">
											<strong><s:message code="label.success"/></strong>
											<s:message code="label.UserRolePrivilege"/> <s:message code="label.saveSuccess"></s:message>
										</div>
									</c:if>
								<c:if test="${param.listWar!=null}"> 
										<div class="alert-danger" id="savemessage">
											<strong><s:message code="label.error"/> </strong>
												<s:message code="label.UserRolePrivilege"/> <s:message code="label.saveFail"></s:message>
										</div>
								</c:if>	
								<c:if test="${UserRolePrivilegeUpdate!=null}">
										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.UserRolePrivilege"/> <s:message code="label.updateSuccess"></s:message>
										</div>
									</c:if><c:if test="${UserRolePrivilegeUpdateFail!=null }">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.UserRolePrivilege"/> <s:message code="label.updateFail"></s:message>
										</div>
									</c:if><c:if test="${UserRolePrivilegeDeleted!=null }">

										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.UserRolePrivilege"/> <s:message code="label.deleteSuccess"></s:message>
										</div>
                                 </c:if>
								<c:if test="${UserRolePrivilegeDeleteFail!=null}">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.UserRolePrivilege"/> <s:message code="label.deleteFail"></s:message>
										</div>
									</c:if></td>
</tr>
<tr>
								<td><s:message code="label.searchby" /></td>
								<td><form:select path="xmlLabel" cssClass="select">
										<form:options items="${xmlItems}" />
									</form:select> <s:bind path="UserRolePrivilege.operations">
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



<c:if test="${listOfUserRolePrivileges!=null}">
						<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
						<display:table id="listOfUserRolePrivileges" name="listOfUserRolePrivileges"
							requestURI="UserRolePrivilegeSearch.mnt" pagesize="5" class="table">
						 <display:column property="userName"
								titleKey="label.user" media="html" sortable="true"></display:column> 
							
							 <display:column property="roleName"
								titleKey="label.Role" media="html" sortable="true"></display:column> 
						 <display:column property="privilegeName"
								titleKey="label.Privilege" media="html" sortable="true"></display:column> 
									<display:column titleKey="label.edit" style="color:white">
										<a
											href="UserRolePrivilegeEdit.mnt?UserRolePrivilegeId=<c:out value="${listOfUserRolePrivileges.userRolePrivilegeId}"/>"
											style="color: red"><img src="images/Edit.jpg"
											width="20px" height="20px" /></a>
									</display:column>
									<display:column titleKey="label.delete">
										<a
											href="UserRolePrivilegeDelete.mnt?UserRolePrivilegeId=<c:out value="${listOfUserRolePrivileges.userRolePrivilegeId}"/>"
											style="color: red"
											onclick="return confirm('Do u want to Delete The Record?')"><img
											src="images/Delete.jpg" width="20px" height="20px" /></a>
						</display:column>
								<display:setProperty name="paging.banner.placement" value="bottom" />
						</display:table>
					</c:if>
</div>
<div id="addForm" style="display:none" >
<form:form action="UserRolePrivilegeSaveDetails.mnt" id="UserRolePrivilegeForm"  method="POST" commandName="UserRolePrivilege">
<table class="tableGeneral">
<tr>
<form:hidden path="editMsg" />
 <form:hidden path="userRolePrivilegeId" id="userRolePrivilegeId"/>  

</tr>

<tr>
<td><s:message code="label.user"/><font color="red">*</font> </td><td><form:select path="userId" id="userId" cssClass="select">
<form:option value="">--Select--</form:option>
<form:options items="${userDetails}"/>
</form:select> </td>
</tr>
<tr>
<td><s:message code="label.Role"/><font color="red">*</font> </td><td><form:select path="roleId" id="roleId" cssClass="select">
<form:option value="">--Select--</form:option>
<form:options items="${roleDetails}"/>
</form:select> </td>
</tr>
	<tr>
<td><s:message code="label.Privilege"/><font color="red">*</font> </td><td><form:select path="privilegeId" id="privilegeId" cssClass="select" onchange="duplicateChecking()">
<form:option value="">--Select--</form:option>
<form:options items="${privilegeDetails}"/>
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