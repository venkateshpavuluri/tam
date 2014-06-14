<!-- @Copyright MNTSOFT
@author Parvathi
@version 1.0 24-04-2014 -->


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/common.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {$('#events').click(function(event) {$("#PrivilegeForm").validate(
															{
																rules : {
																	Privilege : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	
																	}
																	

																},
																messages : {
																	Privilege :
																		{
																		required:"<font style='color: red;'><b>Privilege is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																		
																		}
																		
																},

															});
										});
					
		});
					
					
</script>
<script type="text/javascript">
  function duplicateChecking() {
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      var cust = $('#privilegeNameId').val();
      var id = $('#PrivilegeId').val();
      $
              .ajax({
                type: "POST",
                url: "checkPrivilegeUpdateDuplicate.mnt",
                data: "privilege=" + cust + "&privilegeId=" + id,
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
      var privilege = $('#privilegeNameId').val();
      $
              .ajax({
                type: "POST",
                url: "checkPrivilegeDuplicate.mnt",
                data: "privilege=" + privilege,
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
		 $('#eventDuplMessage').hide();
		 $('#PrivilegeForm').attr('action','PrivilegeSaveDetails.mnt');
		 $('#events').val('Save');
			$('#PrivilegeName').val('');$('#address').val('');$('#city').val('');$('#state').val('');$('#country').val('');$('#PrivilegeId').val('0');
	});
	var msg=$('#editMsg').val();
	if(msg=="Edit")
		{
		  $('#PrivilegeForm').attr('action','PrivilegeUpdate.mnt');
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



<form:form action="PrivilegeSearch.mnt" method="get" commandName="Privilege">
<table class="tableGeneral">
<tr>
<td colspan="3">
<c:if test="${param.list!=null}"> 
										<div class="alert-success" id="savemessage">
											<strong><s:message code="label.success"/></strong>
											<s:message code="label.Privilege"/> <s:message code="label.saveSuccess"></s:message>
										</div>
									</c:if>
								<c:if test="${param.listWar!=null}"> 
										<div class="alert-danger" id="savemessage">
											<strong><s:message code="label.error"/> </strong>
												<s:message code="label.Privilege"/> <s:message code="label.saveFail"></s:message>
										</div>
								</c:if>	
								<c:if test="${PrivilegeUpdate!=null}">
										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.Privilege"/> <s:message code="label.updateSuccess"></s:message>
										</div>
									</c:if><c:if test="${PrivilegeUpdateFail!=null }">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.Privilege"/> <s:message code="label.updateFail"></s:message>
										</div>
									</c:if><c:if test="${PrivilegeDeleted!=null }">

										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.Privilege"/> <s:message code="label.deleteSuccess"></s:message>
										</div>
                                 </c:if>
								<c:if test="${PrivilegeDeleteFail!=null}">

										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.Privilege"/> <s:message code="label.deleteFail"></s:message>
										</div>
									</c:if></td>
</tr>
<tr>
								<td><s:message code="label.searchby" /></td>
								<td><form:select path="xmlLabel" cssClass="select">
										<form:options items="${xmlItems}" />
									</form:select> <s:bind path="Privilege.operations">
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



<c:if test="${listOfPrivileges!=null}">
						<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
						<display:table id="listOfPrivileges" name="listOfPrivileges"
							requestURI="PrivilegeSearch.mnt" pagesize="5" class="table">
							<display:column property="privilege"
								titleKey="label.Privilege" media="html" sortable="true"></display:column>
							
							
						
									<display:column titleKey="label.edit" style="color:white">
										<a
											href="PrivilegeEdit.mnt?PrivilegeId=<c:out value="${listOfPrivileges.privilegeId}"/>"
											style="color: red"><img src="images/Edit.jpg"
											width="20px" height="20px" /></a>
									</display:column>
									<display:column titleKey="label.delete">
										<a
											href="PrivilegeDelete.mnt?PrivilegeId=<c:out value="${listOfPrivileges.privilegeId}"/>"
											style="color: red"
											onclick="return confirm('Do u want to Delete The Record?')"><img
											src="images/Delete.jpg" width="20px" height="20px" /></a>
						</display:column>
								<display:setProperty name="paging.banner.placement" value="bottom" />
						</display:table>
					</c:if>
</div>
<div id="addForm" style="display:none" >
<form:form action="PrivilegeSaveDetails.mnt" id="PrivilegeForm"  method="POST" commandName="Privilege">
<table>
<tr>
<td colspan="2" id="eventDuplMessage" style="display: none;" class="alert-warning">
<div>
<strong> <s:message code="label.warning" /></strong>
<s:message code="label.Privilege" />
<s:message code="label.duplicateCheck" />
</div>
</td>
</tr>
</table>
<table class="tableGeneral">
<form:hidden path="editMsg" />
 <form:hidden path="PrivilegeId" id="PrivilegeId"/>  

<tr>
						<td><s:message code="label.Privilege" /><font color="red">*</font> </td><td><form:input
							type="text" path="Privilege" id="privilegeNameId" class="textbox" onkeyup="duplicateChecking()" /></td>
					</tr>
					
<tr><td>
<input type="submit" value="Save" id="events" class="btn btn-primary"></input>
</td>  </tr>
</table>
</form:form>
</div>
</body>
</html>