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
                                      $("#studenClasstForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          studentClass: {
                                                            required: true,
                                                            alphabets: true,
                                                            specialcharacters: true
                                                          },
                                                          studentId: {
                                                            required: true
                                                          },
                                                          classId: {
                                                            required: true
                                                          },
                                                          sectionId: {
                                                            required: true,
                                                          },
                                                          year: {
                                                            required: true,
                                                          },
                                                        },
                                                        messages: {
                                                          studentClass: {
                                                            required: "<font style='color: red;'><b>Student Class is Required</b></font>",
                                                            alphabets: "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
                                                            specialcharacters: "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
                                                          },
                                                          studentId: {
                                                            required: "<font style='color: red;'><b>Student is Required</b></font>",
                                                          },
                                                          classId: {
                                                            required: "<font style='color: red;'><b>Class is Required</b></font>",
                                                          },
                                                          sectionId: {
                                                            required: "<font style='color: red;'><b>Section is Required</b></font>",
                                                          },
                                                          year: {
                                                            required: "<font style='color: red;'><b>Year is Required</b></font>",
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
      var cust = $('#studentClassNameId').val();
      var id = $('#studentClassId').val();
      $
              .ajax({
                type: "POST",
                url: "checkStudentClassUpdateDuplicate.mnt",
                data: "stdClass=" + cust + "&stdClassId=" + id,
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
      var stdName = $('#studentClassNameId').val();
      $
              .ajax({
                type: "POST",
                url: "checkStudentClassDuplicate.mnt",
                data: "stdClass=" + stdName,
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
      $('#studenClasstForm').attr('action', 'studentClassSaveDetails.mnt');
      $('#events').val('Save');
      $('#studentClassId').val('0');
      $('#schoolId').val('');
      $('#sectionId').val('');
      $('#classId').val('');
      $('#year').val('');
      $('#studentClass').val('');
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      $('#studenClasstForm').attr('action', 'studentClassUpdate.mnt');
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
		<form:form action="studentClassSearch.mnt" method="get"
			commandName="studentClass">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.studentClass" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.studentClass" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${studentClassUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.studentClass" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if> <c:if test="${studentClassUpdateFail!=null }">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.studentClass" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if> <c:if test="${studentClassDeleted!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.studentClass" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${studentClassDeletedFail!=null}">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.studentClass" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="student.operations">
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
		<c:if test="${listOfStudentClasses!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfStudentClasses" name="listOfStudentClasses"
				requestURI="studentClassSearch.mnt" pagesize="5" class="table">

				<display:column property="year" titleKey="label.year" media="html"
					sortable="true" />
				<display:column property="studentName" titleKey="label.student"
					media="html" sortable="true" />
				<display:column property="sectionName" titleKey="label.section"
					media="html" sortable="true" />
				<display:column property="className" titleKey="label.class"
					media="html" sortable="true" />
				<display:column titleKey="label.edit" style="color:white">
					<a
						href="studentClassEdit.mnt?studentClassId=<c:out value="${listOfStudentClasses.studentClassId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="studentClassDelete.mnt?studentClassId=<c:out value="${listOfStudentClasses.studentClassId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="studentClassSaveDetails.mnt" id="studenClasstForm"
			method="POST" commandName="studentClass">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.studentClass" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="studentClassId" id="studentClassId" />
				
				</tr>
				<tr>
					<td><s:message code="label.year" /> <font color="red">*</font>
					</td>
					<td><form:input path="year" id="year" cssClass="textbox" /></td>
				</tr>
				<tr>
					<td><s:message code="label.student" /> <font color="red">*</font>
					</td>
					<td><form:select path="studentId" cssClass="select">
							<form:option value="">--Select--</form:option>
							<form:options items="${studentDetails }" />
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.section" /> <font color="red">*</font>
					</td>
					<td><form:select path="sectionId" id="sectionId"
							cssClass="select">
							<form:option value="">--Select--</form:option>
							<form:options items="${sectionDetails}" />
						</form:select></td>
				</tr>
				<tr>
				<td><s:message code="label.class" /> <font color="red">*</font></td>
					<td><form:select path="classId" id="classId" cssClass="select" onchange="duplicateChecking()">
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