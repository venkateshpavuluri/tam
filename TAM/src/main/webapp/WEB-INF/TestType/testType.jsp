<!-- @Copyright MNTSOFT
@author Parvathi
@version 1.0 24-04-2014 -->


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/common.jsp"%>
<script type="text/javascript">
  $(document)
          .ready(
                  function() {
                    $('#events')
                            .click(
                                    function(event) {
                                      $("#TestTypeForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          testType: {
                                                            required: true,
                                                            alphabets: true,
                                                            specialcharacters: true
                                                          }
                                                        },
                                                        messages: {
                                                          testType: {
                                                            required: "<font style='color: red;'><b>Test Type is Required</b></font>",
                                                            alphabets: "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
                                                            specialcharacters: "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
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
      var cust = $('#testTypeName').val();
      var id = $('#testTypeId').val();
      $
              .ajax({
                type: "POST",
                url: "checkTestUpdateDuplicate.mnt",
                data: "testTypeName=" + cust + "&testTypeId=" + id,
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
      var testTypeName = $('#testTypeName').val();
      $
              .ajax({
                type: "POST",
                url: "checkTestDuplicate.mnt",
                data: "testTypeName=" + testTypeName,
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
      $('#testTypeName').val('');
      $('#TestTypeForm').attr('action', 'TestTypeSaveDetails.mnt');
      $('#events').val('Save');
      //$('#TestTypeName').val('');$('#address').val('');$('#city').val('');$('#state').val('');$('#country').val('');$('#TestTypeId').val('0');
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      $('#TestTypeForm').attr('action', 'TestTypeUpdate.mnt');
      $('#addMsg').hide();
      $('#editSuccMsg').show();
      $('#events').val('Update');
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
		<form:form action="TestTypeSearch.mnt" method="get"
			commandName="TestType">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.testType" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.testType" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${TestTypeUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.testType" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if> <c:if test="${TestTypeUpdateFail!=null }">
							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.testType" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if> <c:if test="${TestTypeDeleted!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.testType" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${TestTypeDeleteFail!=null}">
							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.testType" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="TestType.operations">
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
		<c:if test="${listOfTestTypes!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfTestTypes" name="listOfTestTypes"
				requestURI="TestTypeSearch.mnt" pagesize="5" class="table">
				<display:column property="testType" titleKey="label.testType"
					media="html" sortable="true"></display:column>
				<display:column titleKey="label.edit" style="color:white">
					<a
						href="TestTypeEdit.mnt?TestTypeId=<c:out value="${listOfTestTypes.testTypeId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="TestTypeDelete.mnt?TestTypeId=<c:out value="${listOfTestTypes.testTypeId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="TestTypeSaveDetails.mnt" id="TestTypeForm"
			method="POST" commandName="TestType">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.testType" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="testTypeId" id="testTypeId" />
				</tr>
				<tr>
					<td><s:message code="label.testType" /><font color="red">*</font>
					</td>
					<td><form:input type="text" path="testType" id="testTypeName"
							class="textbox" onkeyup="duplicateChecking()" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Save" id="events"
						class="btn btn-primary"></input></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>