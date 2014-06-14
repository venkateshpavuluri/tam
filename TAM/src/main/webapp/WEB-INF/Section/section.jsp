<!-- @Copyright MNTSOFT
@author Parvathi
@version 1.0 24-04-2014 -->


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/common.jsp"%>
 <script type="text/javascript">
$(function () {
	loadXMLDocEdit();
});
</script> 
<script type="text/javascript">
  $(document)
          .ready(
                  function() {
                	  
                	
                    $('#events')
                            .click(
                                    function(event) {
                                      $("#SectionForm")
                                              .validate(
                                                      {
                                                        rules: {
                                                          Section: {
                                                            required: true,
                                                            alphabets: true,
                                                            specialcharacters: true
                                                          },
                                                          classId: {
                                                            required: true
                                                          }
                                                        },
                                                        messages: {
                                                          Section: {
                                                            required: "<font style='color: red;'><b>Section is Required</b></font>",
                                                            alphabets: "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
                                                            specialcharacters: "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"

                                                          },
                                                          classId: {
                                                            required: "<font style='color: red;'><b>Class is Required</b></font>",
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
      var cust = $('#sectionNameId').val();
      var id = $('#sectionId').val();
      $
              .ajax({
                type: "POST",
                url: "checkSectionUpdateDuplicate.mnt",
                data: "sectionName=" + cust + "&sectionId=" + id,
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
      var sectionName = $('#sectionNameId').val();
      $
              .ajax({
                type: "POST",
                url: "checkSectionDuplicate.mnt",
                data: "sectionName=" + sectionName,
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
      $('#SectionForm').attr('action', 'SectionSaveDetails.mnt');
      $('#events').val('Save');
      $('#sectionNameId').val('');
      $('#classId').val('');
      $('#city').val('');
      $('#state').val('');
      $('#country').val('');
      $('#SectionId').val('0');
    });
    var msg = $('#editMsg').val();
    if (msg == "Edit") {

      $('#SectionForm').attr('action', 'SectionUpdate.mnt');
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
	function loadXMLDocEdit() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				 var a = JSON.parse(xmlhttp.responseText);
				//document.getElementById("classId").innerHTML = xmlhttp.responseText;
				$.each(a, function( key, value ) {
					$('#classId')
			         .append($("<option></option>")
			         .attr("value",key)
			         .text(value)); 
					});
			}
		};
		var schoolId = $('#schoolId').val();
		

		var url = "getClasses.mnt?schoolId=" + schoolId;
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
	}
</script>

</head>
<body>
	<div>
		<form:form action="SectionSearch.mnt" method="get"
			commandName="Section">
			<table class="tableGeneral">
				<tr>
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.Section" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.Section" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${SectionUpdate!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.Section" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if>
						<c:if test="${SectionUpdateFail!=null }">
							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.Section" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if>
						<c:if test="${SectionDeleted!=null }">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.Section" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${SectionDeleteFail!=null}">

							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.Section" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
							<form:options items="${xmlItems}" />
			</form:select> 
						<form:select path="operations" cssClass="select">
						<form:option value='='><s:message code="label.equalsTo" /></form:option>
						<form:option value="!="><s:message code="label.notEqualsTo" /></form:option>
						<form:option value="_%"><s:message code="label.beginsWith" /></form:option>
						<form:option value="%_"><s:message code="label.endsWith" /></form:option>
						<form:option value="%_%"><s:message code="label.contains" /></form:option>
						</form:select>

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
		<c:if test="${listOfSections!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfSections" name="listOfSections"
				requestURI="SectionSearch.mnt" pagesize="5" class="table">
				<display:column property="section" titleKey="label.Section"
					media="html" sortable="true"></display:column>
				<display:column property="className" titleKey="label.Class"
					media="html" sortable="true"></display:column>
					<display:column property="schoolName" titleKey="label.school"
					media="html" sortable="true"></display:column>
				<display:column titleKey="label.edit" style="color:white">
					<a
						href="SectionEdit.mnt?SectionId=<c:out value="${listOfSections.sectionId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="SectionDelete.mnt?SectionId=<c:out value="${listOfSections.sectionId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="SectionSaveDetails.mnt" id="SectionForm"
			method="POST" commandName="Section">
			<table>
				<tr>
					<td colspan="2" id="eventDuplMessage" style="display: none;"
						class="alert-warning">
						<div>
							<strong> <s:message code="label.warning" /></strong>
							<s:message code="label.Section" />
							<s:message code="label.duplicateCheck" />
						</div>
					</td>
				</tr>
			</table>
			<table class="tableGeneral">
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="sectionId" id="SectionId" />
				</tr>
				<tr>
					<td><s:message code="label.school" /><font color="red">*</font>
					</td>
					<td><form:select cssClass="select" path="schoolId" id="schoolId" onchange="loadXMLDocEdit()">
							<form:option value="">--Select--</form:option>
							<form:options items="${schoolDetails}" />
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.Class" /><font color="red">*</font>
					</td>
					<%-- <td><form:input type="text" path="classId" class="textbox"
							id="classId"  /></td> --%>
					<%-- <td><form:select cssClass="select" path="classId" id="classId">
							<form:option value="">--Select--</form:option>
							<c:forEach items="selections" var="sss">
								<c:out value=""></c:out>
							</c:forEach>
							
						</form:select></td> --%>
						<td><form:select cssClass="select" path="classId" id="classId"  >
								 <form:option value="${sectionEdit.classId}">${sectionEdit.className}</form:option> 
						</form:select></td>
				</tr>
<tr>
					<td><s:message code="label.Section" /><font color="red">*</font>
					</td>
					<td><form:input type="text" path="Section" class="textbox"
							id="sectionNameId" onkeyup="duplicateChecking()" /></td>
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