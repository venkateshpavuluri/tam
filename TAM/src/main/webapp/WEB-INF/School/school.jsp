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
                                      $("#schoolForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          schoolName: {
                                                            required: true,
                                                            alphabets: true,
                                                            specialcharacters: true

                                                          },
                                                          
                                                          address: {
                                                            required: true

                                                          },
                                                          branchCode: {
                                                              required: true

                                                            },
                                                          city: {
                                                            required: true
                                                          },
                                                          state: {
                                                            required: true,
                                                            minlength: 2
                                                          },
                                                          country: {
                                                            required: true,

                                                          }
                                                        },
                                                        messages: {
                                                          schoolName: {
                                                            required: "<font style='color: red;'><b>School Name is Required</b></font>",
                                                            alphabets: "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
                                                            specialcharacters: "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
                                                          },
                                                          address: {
                                                            required: "<font style='color: red;'><b>Address is Required</b></font>",
                                                          },
                                                          branchCode: {
                                                              required: "<font style='color: red;'><b>Branch Code is Required</b></font>",
                                                            },
                                                          city: {
                                                            required: "<font style='color: red;'><b>City is Required</b></font>",
                                                            minlength: "<font style='color: red;'><b>Minimum 2 Characters Required</b></font>",
                                                          },
                                                          state: {
                                                            required: "<font style='color: red;'><b>State is Required</b></font>",
                                                            minlength: "<font style='color: red;'><b>Minimum 2 Characters Required</b></font>",
                                                          },
                                                          country: {
                                                            required: "<font style='color: red;'><b>Country is Required</b></font>",
                                                          }
                                                        },
                                                      });
                                    });

                  });
</script>

<script>
  $(document).ready(function() {
	  $('#statusMessages').hide();
 	  $('#basicSearchId').focus();
    $('#add').click(function() {
    	  $('#schoolNameId').focus();
    	  $('#statusMessages').hide();
      $('#display').hide();
      $('#addMsg').show();
      $('#editSuccMsg').hide();
      $('#schoolForm').attr('action', 'schoolSaveDetails.mnt');
      $('#events').val('Save');
      $('#schoolNameId').val('');
      $('#address').val('');
      $('#city').val('');
      $('#state').val('');
      $('#country').val('');
      $('#schoolId').val('0');
      $('#branchCodeId').val('');
      
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {

      $('#schoolForm').attr('action', 'schoolUpdate.mnt');
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
<script type="text/javascript">
  function duplicateChecking() {
    var msg = $('#editMsg').val();
    if (msg == "Edit") {
      var cust = $('#schoolNameId').val();
      var id = $('#schoolId').val();
      var branchCode=$('#branchCodeId').val();
      $.ajax({
                type: "POST",
                url: "checkSchoolUpdateDuplicate.mnt",
                data: "school=" + cust + "&schoolId=" +id+"&branchCode="+branchCode,
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
      var schoolName = $('#schoolNameId').val();
      if(schoolName=="")
    	  {
    	  alert("Please Enter School Name");
    	  $("#branchCodeId").val('');
    	  }
  
      $.ajax({type: "POST",
                url: "checkSchoolDuplicate.mnt",
                data: "school=" + schoolName+"&branchCode="+branchCode,
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
</head>
<body>
	<div>
		<%@include file="/common.jsp"%>
		<form:form action="schoolSearch.mnt" method="get" commandName="school">
			<table class="tableGeneral">
				<tr id="statusMessages">
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savesucmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.school" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savefmessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.school" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${schoolUpdate!=null}">
							<div class="alert-success" id="upSucmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.school" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if>
						<c:if test="${schoolUpdateFail!=null }">

							<div class="alert-danger" id="upfmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.school" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if>
						<c:if test="${schoolDeleted!=null }">

							<div class="alert-success" id="delsucmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.school" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${schoolDeleteFail!=null}">

							<div class="alert-danger" id="delfmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.school" />
								<s:message code="label.deleteFail"></s:message>
							</div>
							
						</c:if></td>
				</tr>
				
				
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="school.operations">
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
						<form:input path="basicSearchId" id="basicSearchId" cssClass="textbox" /></td>
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



		<c:if test="${listOfSchools!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfSchools" name="listOfSchools"
				requestURI="schoolSearch.mnt" pagesize="5" class="table">
				<display:column property="schoolName" titleKey="label.schoolName"
					media="html" sortable="true"></display:column>
					<display:column property="branchCode" titleKey="label.branchCode"
					media="html" sortable="true"></display:column>
				<display:column property="address" titleKey="label.address"
					media="html" sortable="true" />

				<display:column property="city" titleKey="label.city" media="html"
					sortable="true" />
				<display:column property="state" titleKey="label.state"
					sortable="true" />
				<display:column property="country" titleKey="label.country"
					sortable="true" />



				<display:column titleKey="label.edit" style="color:white">
					<a
						href="schoolEdit.mnt?schoolId=<c:out value="${listOfSchools.schoolId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="schoolDelete.mnt?schoolId=<c:out value="${listOfSchools.schoolId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="schoolSaveDetails.mnt" id="schoolForm"
			method="POST" commandName="school">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.schoolName" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="schoolId" id="schoolId" />

				</tr>
				<tr>
					<td><s:message code="label.schoolName" /><font color="red">*</font>
					</td>
					<td><form:input type="text" path="schoolName" class="textbox"
							id="schoolNameId"  /></td>
				</tr>
				
					<tr>
					<td><s:message code="label.branchCode" /><font color="red">*</font>
					</td>
					<td><form:input type="text" path="branchCode" class="textbox"
							id="branchCodeId" onkeyup="duplicateChecking()" /></td>
				</tr>
				
			
				
				<tr>
					<td><s:message code="label.address" /><font color="red">*</font>
					</td>
					<td><form:textarea type="text" path="address" class="textbox" /></td>
				</tr>
				<tr>
					<td><s:message code="label.city" /><font color="red">*</font>
					</td>
					<td><form:input type="text" path="city" class="textbox" /></td>
				</tr>
				<tr>
					<td><s:message code="label.state" /><font color="red">*</font>
					</td>
					<td><form:input type="text" path="state" class="textbox" /></td>
				</tr>
				<tr>
					<td><s:message code="label.country" /><font color="red">*</font>
					</td>
					<td><form:select path="country" id="country" cssClass="select">

							<form:option value="">--Select--</form:option>
							<form:options items="${countryDetails}" />
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