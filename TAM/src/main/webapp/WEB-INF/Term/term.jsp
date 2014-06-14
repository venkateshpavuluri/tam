
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
											$('#termForm')
													.validate(
															{
																rules : {
																	term : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	},
																},
																messages : {
																	term : {
																		required : "<font style='color: red;'><b>Term is Required</b></font>",
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
			var term = $('#term').val();
			var tid = $('#term_Id').val();
			$
					.ajax({
						type : "POST",
						url : "checkTermUpdateDuplicate.mnt",
						data : "term=" + term + "&term_Id=" + tid,
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
			var term = $('#term').val();
			$
					.ajax({
						type : "POST",
						url : "checkTermDuplicate.mnt",
						data : "term=" + term,
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
			$('#termForm').attr('action', 'termAdd.mnt');
			$('#event').val('Save');
			$('#term').val('');
			$('#term_Id').val('0');
		});
		var msg = $('#editMsg').val();
		if (msg == "Edit") {
			
			$('#termForm').attr('action', 'termUpdate.mnt');
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
		<form:form action="termSearch.mnt" method="post"
			commandName="termCommand">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.term" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.term" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${termUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.term" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if> <c:if test="${termUpdateFail!=null }">



							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.term" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if> <c:if test="${termDel!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.term" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${termDelErr!=null}">


							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.term" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="termCommand.operations">
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
		<c:if test="${termBeanView!=null}">
			<display:table name="termBeanView" id="termBeanView" class="table"
				requestURI="termSearch.mnt" pagesize="5">
				<display:column property="term_Id" sortable="true" title="TermId"
					media="none" />
				<display:column property="term" sortable="true"
					titleKey="label.term" media="html" />
				<display:column titleKey="label.edit">
					<a
						href="termEdit.mnt?termEditId=<c:out value="${termBeanView.term_Id}"/>"><img
						src="images/Edit.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="termDelete.mnt?termDelId=<c:out value="${termBeanView.term_Id}"/> "
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
							<td colspan="2" id="termDuplMessage" style="display: none;">
								<div class="alert-warning">
									<strong> <s:message code="label.warning" /></strong>
									<s:message code="label.term" />
									<s:message code="label.duplicateCheck" />
								</div>

							</td>
						</tr>
						</table>
		<form:form action="termAdd.mnt" id="termForm" method="POST"
			commandName="termCommand">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.term" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
			
				<form:hidden path="atId" id="atId" />
				
		
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="term_Id" id="term_Id" />
					<td><s:message code="label.term" /><font color="red">*</font>
					</td>
					<td><form:input path="term" id="term" cssClass="textbox" onkeyup="duplicateChecking()"/></td>
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