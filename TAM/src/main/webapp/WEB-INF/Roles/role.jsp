<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common.jsp"%>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//AddForm Validations
						$('#event')
								.click(
										function(event) {
											//alert($('#sdType').val());
											$('#roleForm')
													.validate(
															{
																rules : {
																	role_Name : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	},
																},
																messages : {
																	role_Name : {
																		required : "<font style='color: red;'><b>Role is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	}
																},

															});
										});

					});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<script type="text/javascript">
	function duplicateChecking() {
		var msg = $('#editMsg').val();
		if (msg == "Edit") {
			var role = $('#role_Name').val();
			var id = $('#role_Id').val();
			$
					.ajax({
						type : "POST",
						url : "checkRoleUpdateDuplicate.mnt",
						data : "role_Name=" + role + "&role_Id=" + id,
						success : function(response) {
							if (response != "") {
								document.getElementById("eventDuplMessage").style.display = "block";
								$('#event').attr('disabled', 'disabled');
								$('#event').hide();
							} else {
								document.getElementById("eventDuplMessage").style.display = "none";
								$('#event').show();
								$('#event').removeAttr('disabled');
							}
						},
						error : function(e) {
						}
					});
		} else {
			var roleName = $('#role_Name').val();
			$
					.ajax({
						type : "POST",
						url : "checkRoleDuplicate.mnt",
						data : "role_Name=" + roleName,
						success : function(response) {
							if (response != "") {
								document.getElementById("eventDuplMessage").style.display = "block";
								$('#event').attr('disabled', 'disabled');
								$('#event').hide();
							} else {
								document.getElementById("eventDuplMessage").style.display = "none";
								$('#event').show();
								$('#event').removeAttr('disabled');
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
			$('#roleForm').attr('action', 'roleAdd.mnt');
			$('#event').val('Save');
			$('#role_Name').val('');
			$('#role_Id').val('0');
		});
		var msg = $('#editMsg').val();
		if (msg == "Edit") {

			$('#roleForm').attr('action', 'roleUpdate.mnt');
			$('#addMsg').hide();
			$('#editSuccMsg').show();
			$('#event').val('Update');
			$("#addForm").show();
		}
	});
	function viewAdd() {
		$("#addForm").show();
	}
</script>
</head>
<body>
	<div>
		<form:form action="roleSearch.mnt" method="post"
			commandName="roleCommand">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.role" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.role" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${roleUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.role" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if> <c:if test="${roleUpdateFail!=null }">



							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.role" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if> <c:if test="${roleDel!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.role" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${roleDelErr!=null}">


							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.role" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="roleCommand.operations">
							<select class="select" name="operations">
								<option value="<s:message code='assignedOperator'/>">
									<s:message code="label.equalsTo" />
								</option>
								<option value="<s:message code='notequalsOperator'/>">
									<s:message code="label.notEqualsTo" />
								</option>
								<option value="<s:message code='beginsWithOperator'/>">
									<s:message code="label.beginsWith" />
								</option>
								<option value="<s:message code='endsWithOperator'/>">
									<s:message code="label.endsWith" />
								</option>
								<option value="<s:message code='containsOperator'/>">
									<s:message code="label.contains" />
								</option>
							</select>
						</s:bind> <form:input path="basicSearchId" cssClass="textbox" /></td>
					<td><input type="submit"
						value="<s:message code='label.search'/>" class="btn btn-primary" />
					</td>
				</tr>
				<tr>
					<td><input type="button" value="Add" class="btn btn-primary"
						id="add" onclick="viewAdd()" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div id="display">
		<c:if test="${roleView!=null}">
			<display:table name="roleView" id="roleView" class="table"
				requestURI="roleSearch.mnt" pagesize="5">
				<display:column property="role_Id" sortable="true" title="RoleId"
					media="none" />
				<display:column property="role_Name" sortable="true"
					titleKey="label.role" media="html" />
				<display:column titleKey="label.edit">
					<a
						href="roleEdit.mnt?roleEditId=<c:out value="${roleView.role_Id}"/>"><img
						src="images/Edit.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="roleDelete.mnt?roleDelId=<c:out value="${roleView.role_Id}"/> "
						onclick="return confirm('Do You Want To Delete This Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<table>
			<tr>
				<td colspan="2" id="roleDuplMessage" style="display: none;">
					<div class="alert-warning">
						<strong> <s:message code="label.warning" /></strong>
						<s:message code="label.role" />
						<s:message code="label.duplicateCheck" />
					</div>

				</td>
			</tr>
		</table>

		<form:form action="roleAdd.mnt" id="roleForm" method="POST"
			commandName="roleCommand">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.role" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="role_Id" id="role_Id" />
					<td><s:message code="label.role" /><font color="red">*</font>
					</td>
					<td><form:input path="role_Name" id="role_Name"
							cssClass="textbox" onkeyup="duplicateChecking()" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Save" id="event"
						class="btn btn-primary"></input></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>