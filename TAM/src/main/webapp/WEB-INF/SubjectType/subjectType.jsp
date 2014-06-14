<!-- @Copyright MNTSOFT
@author yogi
@version 1.0 24-04-2014 -->


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript">
  $(document)
          .ready(
                  function() {
                    $('#event')
                            .click(
                                    function(event) {
                                      $("#subjectForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          subjectType: {
                                                            required: true,
                                                            alphabets: true,
                                                            specialcharacters: true
                                                          }
                                                        },
                                                        messages: {
                                                          subjectType: {
                                                            required: "<font style='color: red;'><b>Subject Type is Required</b></font>",
                                                            alphabets: "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
                                                            specialcharacters: "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
                                                          }
                                                        },
                                                      });
                                    });
                  });
</script>
<script>
  $(document).ready(function() {
    $('#add').click(function() {
      $('#display').hide();
      $('#addMsg').show();
      $('#editSuccMsg').hide();
      $('#subjectForm').attr('action', 'saveSubjectType.mnt');
      $('#event').val('Save');
      $('#subjectType').val('');
      $('#subjectTypeId').val('0');
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      $('#subjectForm').attr('action', 'subjectTypeUpdate.mnt');
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
<script type="text/javascript">
  function duplicateChecking() {
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      var cust = $('#subjectType').val();
      var id = $('#subjectTypeId').val();
      $
              .ajax({
                type: "POST",
                url: "checkSubjectTypeUpdateDuplicate.mnt",
                data: "subjectType=" + cust + "&subjectTypeId=" + id,
                success: function(response) {
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
                error: function(e) {
                }
              });
    } else {
      var subjectName = $('#subjectType').val();
      $
              .ajax({
                type: "POST",
                url: "checkSubjectTypeDuplicate.mnt",
                data: "subjectType=" + subjectName,
                success: function(response) {
                	
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
                error: function(e) {
                }
              });
    }
  }
</script>
</head>
<body>
	<div>
		<%@include file="/common.jsp"%>
		<form:form action="searchSubjectType.mnt" method="get"
			commandName="subTypeCmd">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.subjectType" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.subjectType" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${subjectTypeUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.subjectType" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if> <c:if test="${subjectTypeUpdateFail!=null }">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.subjectType" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if> <c:if test="${subjectTypeDeleted!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.subjectType" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${subjectTypeDeleteFail!=null}">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.subjectType" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="subTypeCmd.operations">
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
		<c:if test="${listOfSubTypes!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfSubTypes" name="listOfSubTypes"
				requestURI="searchSubjectType.mnt" pagesize="5" class="table">
				<display:column property="subjectTypeId" titleKey="subjectTypeId"
					media="none" sortable="true"></display:column>
				<display:column property="subjectType" titleKey="label.subjectType"
					media="html" sortable="true" />
				<display:column titleKey="label.edit" style="color:white">
					<a
						href="subjectTypeEdit.mnt?subjectTypeEdit=<c:out value="${listOfSubTypes.subjectTypeId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="subjectTypelete.mnt?subjectTypelete=<c:out value="${listOfSubTypes.subjectTypeId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="saveSubjectType.mnt" id="subjectForm" method="POST"
			commandName="subTypeCmd">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.subjectType" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
				<tr>
					<td id="editSuccMsg" style="display: none;" colspan="2"><h2>
							<s:message code="label.editSubjectTypeDetails" />
						</h2></td>
					<td id="addMsg" colspan="2"><h2>
							<s:message code="label.addSubjectTypeDetails" />
						</h2></td>
				</tr>
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="subjectTypeId" id="subjectTypeId" />
					<td><s:message code="label.subjectType" /><font color="red">*</font></td>
					<td><form:input path="subjectType" id="subjectType"
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