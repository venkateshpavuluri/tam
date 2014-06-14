<!-- @Copyright MNTSOFT
@author yogi
@version 1.0 29-04-2014 -->


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
                                      $("#subjectForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          subject: {
                                                            required: true,
                                                            alphabets: true,
                                                            specialcharacters: true
                                                          },
                                                          maxMarks: {
                                                            required: true,
                                                            number: true
                                                          },
                                                          subjectTypeId: {
                                                            required: true
                                                          },

                                                          /* parentSubjectId : {
                                                          	required : true,
                                                          }, */
                                                          classId: {
                                                            required: true,
                                                          },
                                                        },
                                                        messages: {
                                                          subject: {
                                                            required: "<font style='color: red;'><b>Subject Name is Required</b></font>",
                                                            alphabets: "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
                                                            specialcharacters: "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
                                                          },
                                                          maxMarks: {
                                                            required: "<font style='color: red;'><b>Maximum Marks are Required</b></font>",
                                                            number: "<font style='color: red;'><b>Maximum Marks are allowed only number</b></font>",
                                                          },
                                                          subjectTypeId: {
                                                            required: "<font style='color: red;'><b>Subject name is Required</b></font>",
                                                          },
                                                          classId: {
                                                            required: "<font style='color: red;'><b>Class is Required</b></font>",
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
      var cust = $('#subject').val();
      var id = $('#subjectId').val();
      $
              .ajax({
                type: "POST",
                url: "checkSubjectUpdateDuplicate.mnt",
                data: "subject=" + cust + "&subjectId=" + id,
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
      var subject = $('#subject').val();
      $
              .ajax({
                type: "POST",
                url: "checkSubjectDuplicate.mnt",
                data: "subject=" + subject,
                success: function(response) {
                 /*  alert(response); */
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
      $('#subjectForm').attr('action', 'saveSubjectDetails.mnt');
      $('#events').val('Save');
      $('#subject').val('');
      $('#maxMarks').val('');
      $('#subjectTypeId').val('');
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      $('#subjectForm').attr('action', 'subjectUpdate.mnt');
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
		<form:form action="subjectSearch.mnt" method="get"
			commandName="subjectCmd">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.subject" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.subject" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${subjectUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.subject" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if>
						<c:if test="${subjectUpdateFail!=null }">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.subject" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if>
						<c:if test="${subjectDeleted!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.subject" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${subjectDeleteFail!=null}">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.subject" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="subjectCmd.operations">
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
						</s:bind>
						<form:input path="basicSearchId" cssClass="textbox" /></td>
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



		<c:if test="${listOfSubjects!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfSubjects" name="listOfSubjects"
				requestURI="subjectSearch.mnt" pagesize="5" class="table">
				<display:column property="subjectId" titleKey="label.subjectId"
					media="none" sortable="true"></display:column>
				<display:column property="className" titleKey="label.class"
					media="html" sortable="true" />
				<display:column property="subject" titleKey="label.subject"
					media="html" sortable="true" />
				<display:column property="maxMarks" titleKey="label.maxMarks"
					media="html" sortable="true" />
				<display:column titleKey="label.edit" style="color:white">
					<a
						href="subjectEdit.mnt?subjectId=<c:out value="${listOfSubjects.subjectId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="subjectDelete.mnt?subjectId=<c:out value="${listOfSubjects.subjectId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>--%>
								<display:setProperty name="paging.banner.placement"
					value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="saveSubjectDetails.mnt" id="subjectForm"
			method="POST" commandName="subjectCmd">
			<table>
<tr>
<td colspan="2" id="eventDuplMessage" style="display: none;" class="alert-warning">
<div>
<strong> <s:message code="label.warning" /></strong>
<s:message code="label.subject" />
<s:message code="label.duplicateCheck" />
</div>
</td>
</tr>
</table>
			<table class="tableGeneral">
				<tr>
					<form:hidden path="editMsg" />
				</tr>
				<tr>
					<form:hidden path="subjectId" id="subjectId" />
				</tr>
				<tr>
					<td><s:message code="label.subject" /><font color="red">*</font>
					</td>
					<td><form:input path="subject" id="subject" cssClass="textbox" onkeyup="duplicateChecking()" />
					</td>
				</tr>
				<tr>
					<td><s:message code="label.maxMarks" /> <font color="red">*</font>
					</td>
					<td><form:input path="maxMarks" id="maxMarks"
							cssClass="textbox" /></td>
				</tr>
				<tr>
					<td><s:message code="label.subjectType" /> <font color="red">*</font>
					</td>
					<td><form:select cssClass="select" path="subjectTypeId"
							id="subjectTypeId">
							<form:option value="">--Select--</form:option>
							<form:options items="${subjectDetails}" />
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.parentSubject" /><font color="red">*</font>
					</td>
					<td><form:select path="parentSubjectId" cssClass="select"
							id="parentSubjectId">
							<form:option value="0">--Select--</form:option>
							<form:options items="${parenDetails}" />
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.class" /><font color="red">*</font>
					</td>
					<td><form:select cssClass="select" path="classId" id="classId">
							<form:option value="">--Select--</form:option>
							<form:options items="${classDetails}" />
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