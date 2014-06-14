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
                                      $("#TeacherTypeForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          teacherType: {
                                                            required: true,
                                                            alphabets: true,
                                                            specialcharacters: true

                                                          }

                                                        },
                                                        messages: {
                                                          teacherType: {
                                                            required: "<font style='color: red;'><b>Teacher Type is Required</b></font>",
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
      var cust = $('#teacherTypeNameId').val();
      var id = $('#teacherTypeId').val();
      $
              .ajax({
                type: "POST",
                url: "checkTeacherTypeUpdateDuplicate.mnt",
                data: "teacherType=" + cust + "&teacherTypeId=" + id,
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
      var teacherType = $('#teacherTypeNameId').val();
      $
              .ajax({
                type: "POST",
                url: "checkTeacherTypeDuplicate.mnt",
                data: "teacherType=" + teacherType,
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
      $('#TeacherTypeForm').attr('action', 'TeacherTypeSaveDetails.mnt');
      $('#events').val('Save');
      $('#TeacherTypeName').val('');
      $('#address').val('');
      $('#city').val('');
      $('#state').val('');
      $('#country').val('');
      $('#TeacherTypeId').val('0');
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {

      $('#TeacherTypeForm').attr('action', 'TeacherTypeUpdate.mnt');
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



		<form:form action="TeacherTypeSearch.mnt" method="get"
			commandName="TeacherType">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.TeacherType" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.TeacherType" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${TeacherTypeUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.TeacherType" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if> <c:if test="${TeacherTypeUpdateFail!=null }">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.TeacherType" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if> <c:if test="${TeacherTypeDeleted!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.TeacherType" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${TeacherTypeDeleteFail!=null}">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.TeacherType" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="TeacherType.operations">
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



		<c:if test="${listOfTeacherTypes!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfTeacherTypes" name="listOfTeacherTypes"
				requestURI="TeacherTypeSearch.mnt" pagesize="5" class="table">
				<display:column property="teacherType" titleKey="label.TeacherType"
					media="html" sortable="true"></display:column>



				<display:column titleKey="label.edit" style="color:white">
					<a
						href="TeacherTypeEdit.mnt?TeacherTypeId=<c:out value="${listOfTeacherTypes.teacherTypeId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="TeacherTypeDelete.mnt?TeacherTypeId=<c:out value="${listOfTeacherTypes.teacherTypeId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="TeacherTypeSaveDetails.mnt" id="TeacherTypeForm"
			method="POST" commandName="TeacherType">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.TeacherType" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="teacherTypeId" id="teacherTypeId" />

				</tr>
				<tr>
					<td><s:message code="label.TeacherType" /> <font color="red">*</font>
					</td>
					<td><form:input type="text" path="teacherType" class="textbox"
							id="teacherTypeNameId" onkeyup="duplicateChecking()" /></td>
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