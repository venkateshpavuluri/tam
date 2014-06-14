<!-- @Copyright MNTSOFT
@author pvenkateswarlu
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
                                      $("#testForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          test: {
                                                            required: true,
                                                            alphabets: true,
                                                            specialcharacters: true

                                                          },
                                                          testTypeId: {
                                                            required: true

                                                          },
                                                          subjectId: {
                                                            required: true
                                                          },

                                                          termTestId: {
                                                            required: true,
                                                          },

                                                        },
                                                        messages: {
                                                          test: {
                                                            required: "<font style='color: red;'><b> Test is Required</b></font>",
                                                            alphabets: "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
                                                            specialcharacters: "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"

                                                          },
                                                          testTypeId: {
                                                            required: "<font style='color: red;'><b>Test Type is Required</b></font>",

                                                          },
                                                          subjectId: {
                                                            required: "<font style='color: red;'><b>Subject is Required</b></font>",

                                                          },
                                                          termTestId: {
                                                            required: "<font style='color: red;'><b>Term Test is Required</b></font>",

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
      var testName = $('#testName').val();
      var testId=$('#testId').val();
      $
              .ajax({
                type: "POST",
                url: "checkTestUpdateDuplicate.mnt",
                data: "testName=" + testName + "&testId=" + testId,
                success: function(response) {
                  if (response != "") {
                    document.getElementById("eventDuplMessage").style.display = "block";
                    $('#events').hide();
                    $('#events').attr('disabled','disabled');
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
      var ttName = $('#testName').val();
      $
              .ajax({
                type: "POST",
                url: "checkTestNameDuplicate.mnt",
                data: "testName=" + ttName,
                success: function(response) {
                  if (response != "") {
                    document.getElementById("eventDuplMessage").style.display = "block";
                    $('#events').attr('disabled','disabled');
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
      $('#testForm').attr('action', 'saveTestDetails.mnt');
      $('#events').val('Save');
      $('#testId').val('0');
      $('#testName').val('');
      $('#testTypeId').val('');
      $('#subjectId').val('');
      $('#termTestId').val('');
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      $('#testForm').attr('action', 'testUpdate.mnt');
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
<script>
  $(function() {
    $("#dob").datepicker();
  });
</script>
</head>
<body>
	<div>

		<form:form action="testSearch.mnt" method="get" commandName="test">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.test" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.test" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${testUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.test" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if> <c:if test="${testUpdateFail!=null }">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.test" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if> <c:if test="${testDeleted!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.test" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${testDeleteFail!=null}">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.test" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="test.operations">
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



		<c:if test="${listOfTests!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfTests" name="listOfTests"
				requestURI="testSearch.mnt" pagesize="5" class="table">
				<display:column property="test" titleKey="label.test" media="html"
					sortable="true"></display:column>
				<display:column property="subjectName" titleKey="label.subject"
					media="html" sortable="true" />
				<display:column property="testTypeName" titleKey="label.testType"
					media="html" sortable="true" />
				<display:column property="termTestName" titleKey="label.termTest"
					media="html" sortable="true" />
				<display:column titleKey="label.edit" style="color:white">
					<a
						href="testEdit.mnt?testId=<c:out value="${listOfTests.testId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="testDelete.mnt?testId=<c:out value="${listOfTests.testId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="saveTestDetails.mnt" id="testForm" method="POST"
			commandName="test">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.test" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">

				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="testId" id="testId" />
					<td><s:message code="label.test" /><font color="red">*</font>
					</td>
					<td><form:input path="test" id="testName" cssClass="textbox"
							onkeyup="duplicateChecking()" /></td>
				</tr>
				<tr>
					<td><s:message code="label.testType" /><font color="red">*</font>
					</td>
					<td><form:select path="testTypeId" cssClass="select">
							<form:option value="">--Select--</form:option>
							<form:options items="${testTypeDetails }" />
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.subject" /> <font color="red">*</font></td>
					<td><form:select path="subjectId" id="subjectId"
							cssClass="select">
							<form:option value="">--Select--</form:option>
							<form:options items="${subjectDetails}" />
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.termTest" /><font color="red">*</font>
					</td>
					<td><form:select path="termTestId" id="termTestId"
							cssClass="select">
							<form:option value="">--Select--</form:option>
							<form:options items="${termTestDetails}" />
						</form:select></td>
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