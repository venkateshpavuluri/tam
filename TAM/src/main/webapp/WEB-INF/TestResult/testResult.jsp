
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@include file="/common.jsp" %> 
<style>
th {
    border-collapse:collapse;
    border: 1px black solid;
}
tr:nth-of-type(5) td:nth-of-type(1) {
    visibility: hidden;
}
.rotate {
     -moz-transform: rotate(-90.0deg);  /* FF3.5+ */
       -o-transform: rotate(-90.0deg);  /* Opera 10.5 */
  -webkit-transform: rotate(-90.0deg);  /* Saf3.1+, Chrome */
             filter:  progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083);  /* IE6,IE7 */
         -ms-filter: "progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083)"; /* IE8 */
}
</style>
<script>
$(document).ready(function () {
	$('#studentsDisplay').hide(); 
    $('.rotate').css('height', $('.rotate').width());
    var subjectName=$('#subjectIdEdit :selected').text();
$('#editSubjName').append(subjectName); 
    
});
</script>

  <script>
  $(document).ready(function() {
 
	$('#w-input-search').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/getTags',
		paramName: "tagName",
		delimiter: ",",
	   transformResult: function(response) {
 
		return {      	
		  //must convert json to javascript object before process
		  suggestions: $.map($.parseJSON(response), function(item) {
 
		      return { value: item.tagName, data: item.id };
		   })
 
		 };
 
            }
 
	 });
 
  });
  </script>
  
    <script>
  $(document).ready(function() {
 
	$('#basicSearch').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/getTags',
		paramName: "tagName",
		delimiter: ",",
	   transformResult: function(response) {
		return {      	
		  //must convert json to javascript object before process
		  suggestions: $.map($.parseJSON(response), function(item) {
		      return { value: item.tagName, data: item.id };
		   })
 
		 };
 
            }
 
	 });
 
  });
  </script>
  



 <script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#events')
								.click(
								
										function(event) {
											$('#testResultForm')
													.validate(
														
															{
																rules : {
																	classId : {
																		required : true
																	},
																	subjectId : {
																		required : true
																	},
																	testId : {
																		required : true
																	}

																},
																messages : {
																	classId : {
																		required : "<font style='color: red;'><b>Class is Required</b></font>",
																	},
																	subjectId : {
																		required : "<font style='color: red;'><b>Subject is Required</b></font>",

																	},
																	testId : {
																		required : "<font style='color: red;'><b>Test is Required</b></font>",
																	}

																}

															});
										});

					});
</script> 
<script>
	function populateSubjects() {
		var classId = $('#classId').val();
	
		$.ajax({
			type : "POST",
			url : "forSubjects.mnt",
			data : "classId=" + classId,
			dataType : "json",
			success : function(response) {
				var optionsForClass = "";
				optionsForClass = $("#subjectId").empty();
				optionsForClass.append(new Option("-Select-", ""));
				$.each(response, function(i, tests) {
					optionsForClass.append(new Option(tests.subject, tests.subjectId));
				});
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
					alert("page not found");
				}
			}
		});
	}
</script>  
<script>
	function populateTests() {
		var subjectId = $('#subjectId').val();
		$("#userdata tbody tr").remove();
		$("#userdata thead th").remove();
		$.ajax({
			type : "POST",
			url : "forTests.mnt",
			data : "subjectId=" +subjectId,
			dataType : "json",
			success : function(response) {
				var optionsForClass = "";
				optionsForClass = $("#testId").empty();
				optionsForClass.append(new Option("-Select-", ""));
				$.each(response, function(i, tests) {
					$('#maxMarks').val(tests.maxMarks);
					optionsForClass.append(new Option(tests.test,tests.testId));
				});
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
					alert("page not found");
				}
			}
		});
	}
</script>  
<script>
	function populateStudents() {
		var classId = $('#classId').val();
		var subjectId = $('#subjectId').val();
		var testId = $('#testId').val();
		var subjectName=$('#subjectId :selected').text();
		$("#userdata tbody tr").remove();
		$("#userdata thead tr").remove();
		$("#childSubjRow").remove();
		var dupResponse="";
		$.ajax({
			type : "POST",
			url : "duplicateCheck.mnt",
			data : "classId=" +classId+"&subjectId="+subjectId+"&testId="+testId,
			success : function(response) {
				dupResponse=response;
				if (response!="") {
					document.getElementById("testResultDup").style.display = "block";
					//$('#salesDuplMessage').html(response);
					$('#events').hide();
				} else {
					document.getElementById("testResultDup").style.display = "none";
					$('#events').show();
					$.ajax({
								type : "POST",
								url : "forStudents.mnt",
								data : "classId=" +classId+"&subjectId="+subjectId,
								dataType : "json",
								success : function(response) {
									if (response == "") {
										$('#studentsDisplay').hide(); 
									
									} else {
										$('#studentsDisplay').show(); 
										var map="";
										var count=0;
										var listOfStudents="";
										var totalSubjects="";
										var usersSubjCount=0;
										$.each(response,function(i, users) {
											map=users.map;
											listOfStudents=users.listOfStudents;
											usersSubjCount=users.totalSubjsCount;
					
											if(users.totalSubjsCount==0)
												{
											 /* 	$('#subjName').remove();  */
											 	var tableHead="<tr><th id='studentName'>Student Name </th><th id='marksth'>Total Marks </th> </tr>";
												$(tableHead).appendTo("#userdata thead");  
								
												}
											else
												{
												var tableHead="<tr><th rowspan=''id='studentName'>Student Name </th><th colspan=''id='subjName'> <div align='center'>"+subjectName+"</div> </th><th rowspan='' id='marksth'>Marks </th> </tr>";
												$(tableHead).appendTo("#userdata thead"); 
												}
											totalSubjects=users.totalSubjsCount-1;
											$('#studentName').attr('rowspan',3);
											$('#subjName').attr('colspan',totalSubjects);
											$('#marksth').attr('rowspan',3);
										});
										$.each(listOfStudents,function(i, user) {
										     count++;
														var tblRow = "<tr id='listOfStudents"+i+"'>"
																+ "<td><s:bind path='testResult.studentId'> <input type='hidden' name='studentId' value='"+user.studentId +"'></s:bind>"
																+ user.studentName
																+ "<td id='totalMarks'><s:bind path='testResult.marks'> <input type='text' id='marks"+count+"' name='marks' onkeyup='testMarks(this.id)' value=''></s:bind></td>"
																+ "</tr>";
														$(tblRow).appendTo("#userdata tbody");
									});  
									var childSubjects="";
							 $.each(map,function(k, userd) {
								
								 childSubjects=userd;
							 });
								if(childSubjects!="")
								 {
						          var thead="<tr id='childSubjRow'></tr><tr id='subChildSubRow'></tr>";
								$(thead).appendTo("#userdata thead"); 
								 }
								var tHead="";	
								var rowspanCount=0;
								var totalsubjctscount=0;
						$.each(childSubjects,function(g, userg) {
							
							if(userg.stage==1)
								{
						if(userg.childSpans!=0)
							{
							rowspanCount=userg.childSpans;
							rowspanCount=2;
						var trow="<th height='43' colspan='"+userg.childSpans+"'><div align='center'>"+userg.childSubject+"</div></th>";
						$(trow).appendTo("#childSubjRow"); 
						
							}
						else
							{
							totalsubjctscount+=1;
							var trow="<th height='43' rowspan='"+rowspanCount+"'><input type='hidden' id='"+userg.childSubject+"marks"+"' value='"+userg.marks+"'/><div align='center'>"+userg.childSubject+"</div></th>";
							$(trow).appendTo("#childSubjRow"); 
							}
								}
							else{
								totalsubjctscount+=1;
								var subChild="<th width='45' height='40'>"+userg.childSubject+" <input type='hidden' id='"+userg.childSubject+"marks"+"' value='"+userg.marks+"' /></th>";
								$(subChild).appendTo("#subChildSubRow");
							}
							$(tHead).appendTo("#childData thead");
									});
						$('#subjName').attr('colspan',totalsubjctscount);
						
						$.each(listOfStudents,function(i, user) {
							$.each(childSubjects,function(g, userg) {
								$('#totalMarks').remove();
								$('#marksth').remove();
								if(userg.stage==1)
									{
									if(userg.childSpans==0)
										{
										var sss=userg.childSubject;
										var studentMarks='<td><input type="text" id="'+userg.childSubject+user.studentId+'" title="'+userg.childSubject+'" name="'+userg.childSubject+user.studentId+'" value="" onkeyup="testMarksForChild(this.value,this.id,this.title)"></td>';
										$(studentMarks).appendTo("#listOfStudents"+i); 
										}
									}
								else
									{
									var sss=userg.childSubject;
									var studentMarks='<td><input type="text" id="'+userg.childSubject+user.studentId+'" title="'+userg.childSubject+'" name="'+userg.childSubject+user.studentId+'" value="" onkeyup="testMarksForChild(this.value,this.id,this.title);"></td>';
									$(studentMarks).appendTo("#listOfStudents"+i); 
									
									}
							});
							
						});
									}
								},
								error : function(e) {
									alert("eee");
								},
								statusCode : {
									406 : function() {
										alert("page not found");
									}
								}
							});
				}
			},
			error : function(e) {
				//alert('Error' + e);
			}
		});
	}
	function testMarksForChild(marks,idMarks,title) {
		var maxMarks = $('#'+title+'marks').val();
		if (parseFloat(marks) <= parseFloat(maxMarks)) {
			$('#saveButton').show();
		} else {
			alert("It Does Not Allow Morthan " + maxMarks + " Marks");
			 $('#' + idMarks).val('');
			$('#msg').val();
			$('#saveButton').hide();
		}
	}
	function testMarks(id) {
		var marks = $('#' + id).val();
		var maxmarks = $('#maxMarks').val();
		if (parseFloat(marks) <= parseFloat(maxmarks)) {
			$('#saveButton').show();

		} else {
			alert("It Does Not Allow Morthan " + maxmarks + " Marks");
			$('#' + id).val('');
			$('#msg').val();
			$('#saveButton').hide();
		}
	}
	function testMarksEditi(id) {
		var marks = $('#' + id).val();
		var maxmarks = $('#maxMarksEdit').val();
		if (parseFloat(marks) <= parseFloat(maxmarks)) {
			$('#saveButton').show();

		} else {
			alert("It Does Not Allow Morthan " + maxmarks + " Marks");
			$('#' + id).val('');
			$('#msg').val();
			$('#saveButton').hide();
		}
	}
	function testMarksEditChildPa(id,title) {
		var marks = $('#' + id).val();
		var maxmarks = $('#'+title).val();
		if (parseFloat(marks) <= parseFloat(maxmarks)) {
			$('#saveButton').show();
		} else {
			alert("It Does Not Allow Morthan " + maxmarks + " Marks");
			$('#' + id).val('');
			$('#msg').val();
			$('#saveButton').hide();
		}
	}
</script>
<script>
	$(document).ready(function() {
		$('#basicSearchId').focus();
		$('#add').click(function() {
			$('#satusMessages').hide(); 
			$('#studentsDisplay').hide(); 
		 	$('#testId').removeAttr("disabled");
			$('#classId').removeAttr("disabled");
			$('#subjectId').removeAttr("disabled"); 
			$('#editStudents').hide();
			$('#addMsg').show();
			$('#testResultForm').attr('action', 'saveTestResultDetails.mnt');
			$('#events').val('Save');
			$('#testResultId').val('0');
			$('#testId').val('');
			$('#classId').val('');
			$('#subjectId').val('');
			$('#addForm').show(); 
			$('#AddTestResults').show(); 
			
		});
		var msg = $('#editMsg').val();
		if (msg == "Edit") {
			$('#testId').attr("disabled",true);
			$('#classId').attr("disabled",true);
			$('#subjectId').attr("disabled",true); 
			$('#AddTestResults').hide(); 
			$('#studentsDisplay').show(); 
			$('#editStudents').show();
			$('#testResultForm').attr('action', 'testResultUpdate.mnt');
			$('#editSuccMsg').show();
			$('#events').val('Update');
			$("#addForm").show();
		}
	});
	function viewAdd() {
		$('#addForm').show();
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
		<form:form action="testResultSearch.mnt" method="get"
			commandName="testResult">
			<table class="tableGeneral">
				<tr id="satusMessages">
					<td colspan="3"><c:if test="${param.list!=null}">
							<div class="alert-success" id="savemessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.testResult" />
								<s:message code="label.saveSuccess"></s:message>
							</div>
						</c:if> <c:if test="${param.listWar!=null}">
							<div class="alert-danger" id="savemessage">
								<strong><s:message code="label.error" /> </strong>
								<s:message code="label.testResult" />
								<s:message code="label.saveFail"></s:message>
							</div>
						</c:if> <c:if test="${testResultUpdated!=null}">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.testResult" />
								<s:message code="label.updateSuccess"></s:message>
							</div>
						</c:if>
						<c:if test="${testResultUpdateFail!=null }">
							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.testResult" />
								<s:message code="label.updateFail"></s:message>
							</div>
						</c:if>
						<c:if test="${testResultDeleted!=null }">
							<div class="alert-success" id="successmessage">
								<strong><s:message code="label.success" /></strong>
								<s:message code="label.testResult" />
								<s:message code="label.deleteSuccess"></s:message>
							</div>
						</c:if> <c:if test="${testResultDeleteFail!=null}">
							<div class="alert-danger" id="successmessage">
								<strong><s:message code="label.error" /></strong>
								<s:message code="label.testResult" />
								<s:message code="label.deleteFail"></s:message>
							</div>
						</c:if></td>
				</tr>
				<tr>
					<td><s:message code="label.searchby" /></td>
					<td><form:select path="xmlLabel" cssClass="select">
					<form:option value="">--Select--</form:option>
							<form:options items="${xmlItems}" />
						</form:select> <s:bind path="testResult.operations">
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
						<form:input id="basicSearchId" path="basicSearchId" cssClass="textbox" /></td>
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
		<c:if test="${listOfTestResults!=null}">
			<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
			<display:table id="listOfTestResults" name="listOfTestResults"
				requestURI="testResultSearch.mnt" pagesize="5" class="table">
				<display:column property="className" titleKey="label.class"
					media="html" sortable="true"></display:column>
				<display:column property="testName" titleKey="label.test"
					media="html" sortable="true" />
				<display:column property="subjectName" titleKey="label.subject"
					media="html" sortable="true"></display:column>
				<display:column titleKey="label.edit" style="color:white" >
					<a
						href="testResultEdit.mnt?testId=<c:out value="${listOfTestResults.testId}"/>&classId=<c:out value="${listOfTestResults.classId}"/>&subjectId=<c:out value="${listOfTestResults.subjectId}"/>"
						style="color: red"><img src="images/Edit.jpg" width="20px"
						height="20px" /></a>
				</display:column>
				<display:column titleKey="label.delete">
					<a
						href="testResultDelete.mnt?testId=<c:out value="${listOfTestResults.testId}"/>&classId=<c:out value="${listOfTestResults.classId}"/>&subjectId=<c:out value="${listOfTestResults.subjectId}"/>"
						style="color: red"
						onclick="return confirm('Do u want to Delete The Record?')"><img
						src="images/Delete.jpg" width="20px" height="20px" /></a>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom" />
			</display:table>
		</c:if>
	</div>
	<div id="addForm" style="display: none">
		<form:form action="saveTestResultDetails.mnt" id="testResultForm"
			method="POST" commandName="testResult">
			<div id="AddTestResults">
			<table class="tableGeneral">
		
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="testResultId" id="testResultId" />
					<td><input type="hidden" name="maxMarks" id="maxMarks"></input>
						<s:message code="label.class" /><font color="red">*</font></td>
					<td><form:select path="classId" cssClass="select" 
							disabled="false" id="classId" onchange="populateSubjects()">
							<form:option value="">--Select--</form:option>
						<form:options items="${classDetails }" />
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.subject" /><font color="red">*</font></td>
					<td><form:select path="subjectId" disabled="false"
							id="subjectId" onchange="populateTests()" cssClass="select">
							<form:option value="">--Select--</form:option>
							<%-- <form:options items="${subjectDetails}" /> --%>
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.test" /><font color="red">*</font></td>
					<td><form:select path="testId" disabled="false" id="testId" onchange="populateStudents()"
							cssClass="select">
							<form:option value="">--Select--</form:option>
						<%-- 	<form:options items="${testDetails}" /> --%>
						</form:select></td><td colspan="2" id="testResultDup" style="display: none;"
								class="alert-warning">
								<div>
									<strong> <s:message code="label.warning" /></strong>
									<s:message code="label.testResult" />
									<s:message code="label.duplicateCheck" />  <s:message code="label.testExistsMsg" />
								</div>
							</td>
				</tr>
	
			</table></div>
			
				<div id="studentsDisplay" style="display: none">
			<table id="userdata" border="1" class="tableGeneral">
				<thead>
				</thead>
				<tbody>
				</tbody>
			</table>	</div>
			
			
			<div id="editStudents">
			
			<c:if test="${not empty listStudentsMarks }">
			<table>
				<tr>
					<form:hidden path="editMsg" />
					<form:hidden path="testResultId" id="testResultId" />
					<form:hidden path="maxMarks" id="maxMarksEdit" />
					<td>
						<s:message code="label.class" /></td>
					<td><form:select path="classIdEdit" cssClass="select" 
							disabled="false" id="classIdEdit" onchange="populateSubjects()">
							<form:option value="">--Select--</form:option>
						<form:options items="${classDetails }" />
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.subject" /></td>
					<td><form:select path="subjectIdEdit" disabled="false"
							id="subjectIdEdit" onchange="populateTests()" cssClass="select">
							<form:option value="">--Select--</form:option>
							<form:options items="${subjectDetails}" /> 
						</form:select></td>
				</tr>
				<tr>
					<td><s:message code="label.test" /></td>
					<td><form:select path="testIdEdit" disabled="false" id="testIdEdit" onchange="populateStudents()"
							cssClass="select">
							<form:option value="">--Select--</form:option>
					<form:options items="${testDetails}" /> 
						</form:select></td>
				</tr> 
			</table>
			</c:if>
				<table class="tableGeneral" border="1">
					 <thead>

					 <c:forEach var="mapOfSubjects" items="${listOfSubjects}">
					 <c:set var="parentSubj" value="${mapOfSubjects.key}"></c:set>
					 <c:set var="childSubj" value="${mapOfSubjects.value}"></c:set>
					 </c:forEach>
					 <c:forEach var="childSubjectsb" items="${childSubj}">
					 <c:set value="${(childSubjectsb.totalSubjects)-1}" var="totalSubjectsc"></c:set>
					 </c:forEach>
		
    <tr>
        <th width="130" rowspan="3" >Student Name </th>
      <th height="30" colspan="<c:out value="${totalSubjectsc}"></c:out>" ><div align="center" id="editSubjName"></div></th>
    </tr>
    <tr>
  		 <c:forEach var="childSubjects" items="${childSubj}">
  		 <c:choose>
  		 <c:when test="${childSubjects.stage==1}">
  		 <c:choose>
  		 <c:when test="${childSubjects.childSpans!=0}">
<th height='43' colspan="${childSubjects.childSpans}"><div align='center'> <c:out value="${childSubjects.childSubject}"></c:out> </div></th>
  		 </c:when>
  		 <c:otherwise>
  		 <th height='43' rowspan='2'> <input type="hidden" id="${childSubjects.childSubject}" value="${childSubjects.marks}"/> <div align='center'>${childSubjects.childSubject}</div></th>
  		 </c:otherwise>
  		 </c:choose>
  		 </c:when>
  		 </c:choose>
					 </c:forEach>
    </tr>
    <tr>
    	 <c:forEach var="childSubjects" items="${childSubj}">
  		 <c:choose>
  		 <c:when test="${childSubjects.stage==2}">
  		 <th width='45' height='40'><input type="hidden" id="${childSubjects.childSubject}" value="${childSubjects.marks}"/> ${childSubjects.childSubject}</th> 
  		 </c:when>
  		 </c:choose>
					 </c:forEach></tr>
					 </thead>
					 <tbody>
					<c:set var="count" value="0" scope="page"/>
					<c:forEach items="${listOfStudentsForEdit}" var="listOfStudentsForEdit">
						<tr>
						<td><c:out value="${listOfStudentsForEdit.fNameEdit}"></c:out> </td>
                  <c:forEach items="${listStudentsMarks}" var="listStudentsMarksE">
                  <c:out value="${childSubjects}"></c:out>
                  <c:choose>
                  <c:when test="${not empty childSubj }">
                 <c:choose>
                  <c:when test="${listStudentsMarksE.studentId==listOfStudentsForEdit.studentId}">
                       <td>
								<input type="text" name="${listStudentsMarksE.subjectName}${listOfStudentsForEdit.studentId }" title="${listStudentsMarksE.subjectName}" id="${listStudentsMarksE.subjectName}${listOfStudentsForEdit.studentId}Edit" onkeyup="testMarksEditChildPa(this.id,this.title)"
										value="<c:out value='${listStudentsMarksE.marks}'/>"></input> 
								</td>  </c:when>
                  </c:choose>
                   </c:when>
                   <c:otherwise>
                     <c:choose>
                  <c:when test="${listStudentsMarksE.studentId==listOfStudentsForEdit.studentId}">
                   <td>
                    <s:bind path="testResult.studentIdEdit"><input type="hidden" name="studentIdEdit" value="<c:out value='${listOfStudentsForEdit.studentId}'/>"/> </s:bind>
        <s:bind path="testResult.marksEdit"><input type="text" name="marksEdit" id="${listOfStudentsForEdit.studentId}" onkeyup="testMarksEditi(this.id)"
										value="<c:out value='${listStudentsMarksE.marks}'/>"></input> </s:bind>
                   </td></c:when></c:choose>
                   </c:otherwise>
                  </c:choose>
</c:forEach></tr>
  		 </c:forEach>
	</tbody>
				</table>
			</div>
			<table>
				<tr id="saveButton">
					<td ><input type="submit" value="Save" id="events"
						class="btn btn-primary"></input></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>