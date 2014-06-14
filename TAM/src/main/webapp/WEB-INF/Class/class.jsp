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
                                      $("#ClassesForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          className: {
                                                            required: true,
                                                            specialcharacters: true
                                                          },
                                                          schoolId: {
                                                            required: true
                                                          }
                                                        },
                                                        messages: {
                                                          className: {
                                                            required: "<font style='color: red;'><b>Class Name is Required</b></font>",
                                                            specialcharacters: "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
                                                          },
                                                          schoolId: {
                                                            required: "<font style='color: red;'><b>School is Required</b></font>",
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
      var cust = $('#classNameId').val();
      var id = $('#classId').val();
      $
              .ajax({
                type: "POST",
                url: "checkClassUpdateDuplicate.mnt",
                data: "classNameId=" + cust + "&classId=" + id,
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
      var className = $('#classNameId').val();
      $
              .ajax({
                type: "POST",
                url: "checkClassDuplicate.mnt",
                data: "className=" + className,
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
      $('#ClassesForm').attr('action', 'ClassesSaveDetails.mnt');
      $('#events').val('Save');
      $('#classNameId').val('');
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      $('#ClassesForm').attr('action', 'ClassesUpdate.mnt');
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

		<form:form action="ClassesSearch.mnt" method="get"
			commandName="Classes">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.Class" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.Class" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${ClassesUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.Class" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if> <c:if test="${ClassesUpdateFail!=null }">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.Class" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if> <c:if test="${ClassesDeleted!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.Class" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${ClassesDeleteFail!=null}">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.Class" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="Classes.operations">
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
		<c:if test="${listOfClassess!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfClassess" name="listOfClassess"
				requestURI="ClassesSearch.mnt" pagesize="5" class="table">

				<display:column property="className" titleKey="label.Class"
					media="html" sortable="true"></display:column>
				<display:column property="schoolName" titleKey="label.school"
					media="html" sortable="true"></display:column>

				<display:column titleKey="label.edit" style="color:white">
					<a
						href="ClassesEdit.mnt?ClassesId=<c:out value="${listOfClassess.classId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="ClassesDelete.mnt?ClassesId=<c:out value="${listOfClassess.classId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="ClassesSaveDetails.mnt" id="ClassesForm"
			method="POST" commandName="Classes">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.Class" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="classId" id="classId" />
				</tr>
				<tr>
					<td><s:message code="label.Class" /><font color="red">*</font>
					</td>
					<td><form:input type="text" path="className" id="classNameId"
							class="textbox" onkeyup="duplicateChecking()" /></td>
				</tr>
				<tr>
					<td><s:message code="label.school" /> <font color="red">*</font>
					</td>
					<td><form:select class="select" path="schoolId" id="schoolId">
							<form:option value="">--Select--</form:option>
							<form:options items="${schoolDetails}" />
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