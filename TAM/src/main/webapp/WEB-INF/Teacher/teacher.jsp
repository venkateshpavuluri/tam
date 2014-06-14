<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
  $(document)
          .ready(
                  function() {
                    $('#events')
                            .click(
                                    function(event) {
                                      $("#teacherForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          fName: {
                                                            required: true,
                                                            alphabets: true,
                                                            specialcharacters: true

                                                          },
                                                          dob: {
                                                            required: true

                                                          },

                                                          gender: {
                                                            required: true

                                                          },

                                                          teacherTypeId: {
                                                            required: true,
                                                          },

                                                        },
                                                        messages: {
                                                          fName: {
                                                            required: "<font style='color: red;'><b>First Name is Required</b></font>",
                                                            alphabets: "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
                                                            specialcharacters: "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"

                                                          },
                                                          dob: "<font style='color: red;'><b>Date Of Birth is Required</b></font>",
                                                          gender: "<font style='color: red;'><b>Gender is Required</b></font>",
                                                          teacherTypeId: "<font style='color: red;'><b>TeacherType is Required</b></font>",

                                                        },

                                                      });
                                    });

                  });
</script>
<script type="text/javascript">
  function duplicateChecking() {
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      var fname = $('#fName').val();
      var tid = $('#teacherId').val();
      $
              .ajax({
                type: "POST",
                url: "checkTeacherUpdateDuplicate.mnt",
                data: "fName=" + fname + "&teacherId=" + tid,
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
      var fName = $('#fName').val();
      $
              .ajax({
                type: "POST",
                url: "checkTeacherDuplicate.mnt",
                data: "fName=" + fName,
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
    $("#editChild").hide();
    $('#add').click(function() {
      $("#editChild").hide();
      $("#addChild").show();
      $('#addMsg').show();
      $('#editSuccMsg').hide();
      $('#teacherForm').attr('action', 'saveTeacherDetails.mnt');
      $('#events').val('Save');
      $('#classId').val('');
      $('#subjectId').val('');
      $('#fName').val('');
      $('#midName').val('');
      $('#lName').val('');
      $('#dob').val('');
      $('#gender').val('');
      $('#address').val('');
      $('#city').val('');
      $('#state').val('');
      $('#country').val('');
      $('#zip').val('');
      $('#email').val('');
      $('#phoneNo').val('');
      $('#mobileNo').val('');
      $('#teacherId').val('0');
      $('#teacherTypeId').val('');
      $('#classIdEdit').val('');
      $('#subjectIdEdit').val('');
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {

      $('#teacherForm').attr('action', 'updateTeacherDetails.mnt');
      $('#addMsg').hide();
      $('#editSuccMsg').show();
      $('#events').val('Update');
      $("#addForm").show();
      $("#addChild").hide();
      $("#editChild").show();
    }
  });
  function viewAdd() {
    $("#addForm").show();
  }
</script>
<script>
  $(function() {
    $("#dob").datepicker({changeMonth:true,changeYear:true});
  });
</script>

<script type='text/javascript'>
  function deleteRow(id) {

    $('#f' + id).fadeOut(400, function() {
      $('#f' + id).remove();
    });
  }
  $(document)
          .ready(
                  function() {
                    var counter = 1;

                    $('#del_file').hide();
                    $('.addCF')
                            .click(
                                    function() {

                                      $('#file_tools')
                                              .before(
                                                      '<tr id="f'+counter+'"><td><select name="teachers['+counter+'].classId" >'
                                                              + '<option value=" ">---select---</option>'
                                                              + '<c:forEach items="${classDetails}" var="entry">'
                                                              + '<option value="'+${entry.key}+'">${entry.value}</option></c:forEach></select></td>'

                                                              + '<td><select name="teachers['+counter+'].subjectId" >'
                                                              + '<option value=" ">---select---</option>'
                                                              + '<c:forEach items="${subjectDetails}" var="entry">'
                                                              + '<option value="'+${entry.key}+'">${entry.value}</option></c:forEach></select></td>'
                                                              + '<td><img src="images/button_cancel.png" style="cursor:pointer; cursor:hand; width:25px; height:25px;" class="deleterow"  onclick="deleteRow('
                                                              + counter
                                                              + ')"/></td></tr>');

                                      $('#del_file').fadeIn(0);
                                      counter++;
                                    });

                    $('#sumbnid').click(function(event) {

                      $("#addForm").validate();

                      // the following method must come AFTER .validate()

                      $('#addForm').find('[name^="case"]').each(function() {

                        $(this).rules('add', {
                          required: true,
                        });
                      });
                    });

                    $('#del_file').click(function() {
                      if (counter == 3) {
                        $('#del_file').hide();
                      }
                      counter--;
                      $('#f' + counter).remove();
                    });
                    $("#doc .deleterow").click(function() {

                      var td = $(this).parent();
                      var tr = td.parent();

                      tr.fadeOut(400, function() {
                        tr.remove();
                        return false;
                      });
                    });
                    return false;
                  });
</script>


</head>
<body>
	<div>

		<form:form action="searchTeacherDetails.mnt" method="get"
			commandName="teacher">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.teacher" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.teacher" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${teacherUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.teacher" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if>
						<c:if test="${teacherUpdateFail!=null }">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.teacher" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if>
						<c:if test="${teacherDeleted!=null }">

							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.teacher" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${teacherDeleteFail!=null}">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.teacher" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="teacher.operations">
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



		<c:if test="${listOfTeachers!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfTeachers" name="listOfTeachers"
				requestURI="searchTeacherDetails.mnt" pagesize="5" class="table">
				<display:column property="fName" titleKey="label.fName" media="html"
					sortable="true"></display:column>
				<display:column property="teacherType" titleKey="label.teacherType"
					media="html" sortable="true" />
				<display:column property="dob" titleKey="label.dob" media="html"
					sortable="true" />
				<display:column property="gender" titleKey="label.gender"
					media="html" sortable="true" />
				<display:column property="address" titleKey="label.address"
					media="html" sortable="true" />
				<display:column property="city" titleKey="label.city" media="html"
					sortable="true" />
				<display:column property="state" titleKey="label.state"
					sortable="true" />
				<display:column property="country" titleKey="label.country"
					sortable="true" />
				<display:column property="zip" titleKey="label.zip" sortable="true" />
				<display:column property="email" titleKey="label.email"
					sortable="true" />
				<display:column property="phoneNo" titleKey="label.phoneNo"
					sortable="true" />
				<display:column property="mobileNo" titleKey="label.mobileNo"
					sortable="true" />

				<display:column titleKey="label.edit" style="color:white">
					<a
						href="teacherEdit.mnt?teacherEditId=<c:out value="${listOfTeachers.teacherId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="teacherDelete.mnt?teacherDelId=<c:out value="${listOfTeachers.teacherId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">

		<table>
			<tr>
				<td colspan="2" id="teacherDuplMessage" style="display: none;">
					<div class="alert-warning">
						<strong> <s:message code="label.warning" /></strong>
						<s:message code="label.teacher" />
						<s:message code="label.duplicateCheck" />
					</div>

				</td>
			</tr>
		</table>
		<form:form action="saveTeacherDetails.mnt" id="teacherForm"
			method="POST" commandName="teacher">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.teacher" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="teacherId" id="teacherId" />
					<td><s:message code="label.fName" /><font color="red">*</font>
					</td>
					<td><form:input path="fName" id="fName" cssClass="textbox"
							onkeyup="duplicateChecking()" /></td>
					<td><s:message code="label.mName" /></td>
					<td><form:input path="midName" id="midName" cssClass="textbox" />
					</td>
				</tr>
				<tr>
					<td><s:message code="label.lName" /></td>
					<td><form:input path="lName" id="lName" cssClass="textbox" />
					</td>
					<td><s:message code="label.dob" /><font color="red">*</font></td>
					<td><form:input path="dob" id="dob" cssClass="textbox" /></td>
				</tr>
				<tr>
					<td><s:message code="label.gender" /><font color="red">*</font>
					</td>
					<td><form:radiobutton path="gender" value="Male" />Male<form:radiobutton
							path="gender" value="Female" />Female</td>
					<td><s:message code="label.address" /></td>
					<td><form:textarea path="address" id="address"
							cssClass="textbox" /></td>
				</tr>
				<tr>
					<td><s:message code="label.city" /></td>
					<td><form:input path="city" id="city" cssClass="textbox" /></td>
					<td><s:message code="label.state" /></td>
					<td><form:input path="state" id="state" cssClass="textbox" />
					</td>
				</tr>
				<tr>
					<td><s:message code="label.country" /></td>
					<td><form:select path="country" id="country" cssClass="select">
							<form:option value="">--Select--</form:option>
							<form:options items="${countryDetails}" />
						</form:select></td>
					<td><s:message code="label.zip" /></td>
					<td><form:input path="zip" id="zip" cssClass="textbox" /></td>
				</tr>
				<tr>
					<td><s:message code="label.email" /></td>
					<td><form:input path="email" id="email" cssClass="textbox" />
					</td>
					<td><s:message code="label.phoneNo" /></td>
					<td><form:input path="phoneNo" id="phoneNo" cssClass="textbox" />
					</td>

				</tr>
				<tr>
					<td><s:message code="label.mobileNo" /></td>
					<td><form:input path="mobileNo" id="mobileNo"
							cssClass="textbox" /></td>
					<td><s:message code="label.teacherType" /> <font color="red">*</font></td>
					<td><form:select path="teacherTypeId" id="teacherTypeId"
							cssClass="select">
							<form:option value="">--Select--</form:option>
							<form:options items="${teacherTypeDetails}" />
						</form:select></td>
				</tr>
			</table>
			<div id="addChild">
				<table id="doc" class="table2">
					<tr>
						<th>Class<span class=required>*</span></th>
						<th>Subject<span class=required>*</span></th>
					</tr>
					<tr id='f0'>
						<td><form:select path="teachers[0].classId" id="classId">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${classDetails}" />
							</form:select></td>
						<td><form:select path="teachers[0].subjectId" id="subjectId">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${subjectDetails}" />
							</form:select></td>
						<td><a href="javascript:void(0);" class="addCF" id="addCF"><img
								src="images/add (1).png"></img></a></td>
					</tr>
					<tr id='file_tools'>
						<td></td>
					</tr>
					<tr>
						<td><input type="submit" value="Save" id="events"
							class="btn btn-primary"></input></td>
					</tr>
				</table>
			</div>
			<div id="editChild">
				<table id="doc" class="table2">
					<tr>
						<th>Class<span class=required>*</span></th>
						<th>Subject<span class=required>*</span></th>
					</tr>
					<c:forEach items="${teacher.teacherSubjects}" var="teachers"
						varStatus="status">


						<tr>
							<form:hidden path="teachers[${status.index}].teacherSubjectId"
								id="teachSubId" />
							<td><form:select path="teachers[${status.index}].classId"
									id="classIdEdit">
									<form:option value="">--Select</form:option>
									<form:options items="${classDetails}" />
								</form:select></td>
							<td><form:select path="teachers[${status.index}].subjectId"
									id="subjectIdEdit">
									<form:option value="">--Select</form:option>
									<form:options items="${subjectDetails}" />
								</form:select></td>
						</tr>
					</c:forEach>

					<tr>
						<td><input type="submit" value="Update" id="events"
							class="btn btn-primary"></input></td>
					</tr>
				</table>
			</div>

		</form:form>

	</div>
</body>
</html>