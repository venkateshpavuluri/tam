<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@include file="/common.jsp" %>
<script>
$(document).ready(function () {
	$('#studentsDisplay').hide(); 
    $('.rotate').css('height', $('.rotate').width());
    var subjectName=$('#subjectIdEdit :selected').text();
$('#editSubjName').append(subjectName); 
    
});
</script>
 <script type="text/javascript">
	$(document).ready(function() {$('#events').click(function(event) {$("#studentTestForm").validate(
															{
																rules : {
																	classId : {
																		required : true,
																	},
																	
																subjectId : {
																		required : true,
																	},
																	termTestId : {
																		required : true,
																	},
																},
																messages : {
																	classId :
																		{
																		required:"<font style='color: red;'><b>Class is Required</b></font>",
																			},
																		
																			subjectId : {
																		required:"<font style='color: red;'><b>Subject is Required</b></font>",
																		},
																		termTestId : {
																			required:"<font style='color: red;'><b>TermTest is Required</b></font>",
																			},
																},
															});
										});
		});
</script> 
<script>
$(document).ready(function() {
	$('#basicSearchId').focus();
	$('#add').click(function() {
		$('#statusMessage').hide();
		$('#termTestId').removeAttr("disabled");
		$('#classId').removeAttr("disabled");
		$('#subjectId').removeAttr("disabled"); 
		$('#studentsDisplay').hide(); 
		$('#AddStudentResults').show();
		$('#EditStudentResults').hide();
		$('#display').hide();
		 $('#addMsg').show();
		 $('#editSuccMsg').hide();
		 $('#studentTestForm').attr('action','saveStudentTestDetails.mnt');
		$('#events').val('Save');
		$('#marksGained').val('');$('#studentId').val('');$('#termTestId').val('');$('#classId').val('');$('#subjectId').val('');
	});
	var msg=$('#editMsg').val();
	if(msg=="Edit")
		{
		$('#EditStudentResults').show();
		$('#AddStudentResults').hide(); 
		$('#studentsDisplay').show(); 
		$('#classId').attr("disabled",true);
		$('#termTestId').attr("disabled",true);
		$('#subjectId').attr("disabled",true); 
		  $('#studentTestForm').attr('action','studentTestUpdate.mnt');
		  $('#addMsg').hide();
		  $('#editSuccMsg').show();
		  $('#events').val('Update');
		  $("#addForm").show();
		}
}
);
function viewAdd()
{
	  $("#addForm").show();
	}
function testMarksForChild(marks,idMarks,title) {
	var maxMarks = $('#'+title+'marks').val();
	if (parseFloat(marks) <= parseFloat(maxMarks)) {
		$('#events').show();
	} else {
		alert("It Does Not Allow Morthan " + maxMarks + " Marks");
		 $('#' + idMarks).val('');
		$('#msg').val();
		$('#events').hide();
	}
}

function testMarksEditChildPa(id,title) {
	var marks = $('#' + id).val();
	var maxmarks = $('#'+title).val();
	if (parseFloat(marks) <= parseFloat(maxmarks)) {
		$('#events').show();

	} else {
		alert("It Does Not Allow Morthan " + maxmarks + " Marks");
		$('#' + id).val('');
		$('#msg').val();
		$('#events').hide();
	}
}


	
</script>
<script>
var allSubTests="";
	function populateSubjects() {
		var termTestId = $('#termTestId').val();
		$("#userdata tbody tr").remove();
		$("#userdata thead th").remove();
		$('#childSubjRow').remove(); 
		$.ajax({
			type : "POST",
			url : "getSubjects.mnt",
			data : "termTestId=" +termTestId,
			dataType : "json",
			success : function(response) {
				var optionsForClass = "";
				optionsForClass = $("#subjectId").empty();
				optionsForClass.append(new Option("-Select-", ""));
				$.each(response, function(i, test) {
					optionsForClass.append(new Option(test.subject,test.subjectId));
				});
			},
			error : function(e) {
			}
		});
	}
	
	
	function testsValidate(testId,marksId,subjectId,studentId)
	{
		var termTestId = $('#termTestIdEdit').val();
var testsId= $('#'+testId).val();
var classId=$('#classIdEdit').val();
		$.ajax({
			type : "POST",
			url : "getTestsMarks.mnt",
			data : "termTestId="+termTestId+"&testId="+testsId+"&classId="+classId+"&subjectId="+subjectId+"&studentId="+studentId,
			dataType : "json",
			success : function(response) {
			if(response=="")
				{
				$('#'+marksId).val('0');
				}
			else
				{
				$('#'+marksId).val(response);
				}
				
			},
			error : function(e) {
				$('#'+marksId).val('0');
			}
		});
	}

	function testMarks(id) {
		var marks = $('#' + id).val();
		var maxmarks = $('#maxMarks').val();
		if (parseFloat(marks) <= parseFloat(maxmarks)) {
			$('#events').show();

		} else {
			alert("It Does Not Allow Morthan " + maxmarks + " Marks");
			$('#' + id).val('');
			$('#msg').val();
			$('#events').hide();
		}
	}
	function testMarksEditi(id) {
		var marks = $('#' + id).val();
		var maxmarks = $('#maxMarksEdit').val();
		if (parseFloat(marks) <= parseFloat(maxmarks)) {
			$('#events').show();

		} else {
			alert("It Does Not Allow Morthan " + maxmarks + " Marks");
			$('#' + id).val('');
			$('#msg').val();
			$('#events').hide();
		}
	}
	
	function getEachTestMarks(testsId,textId,studentId,subjectId) {
		//var subjectId = $('#subjectId').val();
		var classId = $('#classId').val();
		var testId=$('#'+testsId).val();
		var termTestId=$('#termTestId').val();
		$.ajax({
			type : "POST",
			url : "studentTestduplicateCheck.mnt",
			data :"subjectId="+subjectId+"&classId="+classId+"&studentId="+studentId+"&testId="+testId+"&termTestId="+termTestId,
			success : function(response) {
				if(response=="")
					{
					$.ajax({
						type : "POST",
						url : "getMarks.mnt",
						data :"subjectId="+subjectId+"&classId="+classId+"&studentId="+studentId+"&testId="+testId,
						success : function(response) {
							if(response!="")
								{
						$('#'+textId).val(response);}
							else
								{
								alert("Ther Is No Marks For This Student Please Enter The Marks");
								}
						},
						error : function(e) {
						},
					});
					}
				else
					{
					alert("this Test Already Existed Please Select Another Test");
					$('#'+testsId).val(" ");
					}
			},
			error : function(e) {
			},
		});
	}
	function getEachTestMarksForSingle(testsId,textId,studentId) {
		var subjectId = $('#subjectId').val();
		var classId = $('#classId').val();
		var testId=$('#'+testsId).val();
		var termTestId=$('#termTestId').val();
		$.ajax({
			type : "POST",
			url : "studentTestduplicateCheck.mnt",
			data :"subjectId="+subjectId+"&classId="+classId+"&studentId="+studentId+"&testId="+testId+"&termTestId="+termTestId,
			success : function(response) {
				if(response=="")
					{
					$.ajax({
						type : "POST",
						url : "getMarks.mnt",
						data :"subjectId="+subjectId+"&classId="+classId+"&studentId="+studentId+"&testId="+testId,
						success : function(response) {
							if(response!=null)
								{
						$('#marks'+textId).val(response);
						$('#events').show();
								}
							else
								{
								alert("Ther Is No Marks For This Student Please Enter The Marks");
								$('#marks'+textId).val(' ');
								$('#events').show();
								}
						},
						error : function(e) {
							$('#marks'+textId).val(' ');
						},
					});
					}
				else
					{
					alert("This StudentTestResult Already Existed Please Edit and Modify");
					$('#'+testsId).val(" ");
					}
			},
			error : function(e) {
				$('#marks'+textId).val(" ");
			},
		});
	}
	function getSubjects() {
		var classId = $('#classId').val();
		var subjectId = $('#subjectId').val();
		var termTestId = $('#termTestId').val();
		$('#userdata tbody tr').remove();
		$('#userdata thead th').remove();
		$('#childSubjRow').remove(); 
		var subjectName=$('#subjectId :selected').text();
		$.ajax({
			type : "POST",
			url : "getAllTests.mnt",
			data :"subjectId="+subjectId+"&termTestId="+termTestId,
			dataType : "json",
			success : function(response) {
				if(response!=null)
					{
					allSubTests=response;
					}
			},
			error : function(e) {
			},
		});
		$.ajax({
			type : "POST",
			url : "getMaxMarks.mnt",
			data : "subjectId=" +subjectId,
			success : function(response) {
					$('#maxMarks').val(response);
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
					alert("page not found");
				}
			}
		});
		$.ajax({
			type : "POST",
			url : "getStudentsMarks.mnt",
			data : "classId=" +classId+"&subjectId="+subjectId+"&termTestId="+termTestId,
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
											+ "<td><s:bind path='studentTest.studentId'> <input type='hidden' name='studentId' value='"+user.studentId +"'></s:bind>"
											+ user.studentName
											+ "<td id='totalMarks'><s:bind path='studentTest.testIds'> <select name='testIds' id='select"+count+"'  onchange='getEachTestMarksForSingle(this.id,"+count+","+user.studentId+" )'><option> </option> </select></s:bind> <s:bind path='studentTest.marks'> <input type='text' id='marks"+count+"' name='marks' onkeyup='testMarks(this.id)' value=''></s:bind></td>"
											+ "</tr>";
									$(tblRow).appendTo("#userdata tbody");
									var s='select'+count;
									getAllTests(s);
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
					var sss=userg.childSubject+user.studentId+"select";
					var studentMarks='<td> <select  id="'+userg.childSubject+user.studentId+'select" name="'+userg.childSubject+user.studentId+'select" class="select" title="'+userg.childSubject+user.studentId+'" onchange="getEachTestMarks(this.id,this.title,'+user.studentId+','+userg.childSubjectId+')"><option value="">--Select--</option></select> <input style="width:40px" type="text" id="'+userg.childSubject+user.studentId+'" title="'+userg.childSubject+'" name="'+userg.childSubject+user.studentId+'" value="" onkeyup="testMarksForChild(this.value,this.id,this.title)"></td>';
					$(studentMarks).appendTo("#listOfStudents"+i); 
					getAllTests(sss);
					}
				}
			else
				{
				var sss=userg.childSubject+user.studentId+"select";
				
				var studentMarks='<td> <select  id="'+userg.childSubject+user.studentId+'select" name="'+userg.childSubject+user.studentId+'select" class="select" title="'+userg.childSubject+user.studentId+'" onchange="getEachTestMarks(this.id,this.title,'+user.studentId+','+userg.childSubjectId+')"><option value="">--Select--</option></select> <input style="width:40px" type="text"  id="'+userg.childSubject+user.studentId+'" title="'+userg.childSubject+'" name="'+userg.childSubject+user.studentId+'" value="" onkeyup="testMarksForChild(this.value,this.id,this.title);"></td>';
				$(studentMarks).appendTo("#listOfStudents"+i); 
				getAllTests(sss);
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
function getAllTests(id,type)
{
			optionsForClassl = $('#'+id).empty();
			optionsForClassl.append(new Option("-Select-", ""));
			$.each(allSubTests, function(i, tests) {
				optionsForClassl.append(new Option(tests.test,tests.testId));
			});
	}
</script>  
</head>
<body>
<div>
<form:form action="studentTestSearch.mnt" method="get" commandName="studentTest">
<table class="tableGeneral">
<tr id="statusMessage">
<td colspan="3">
<c:if test="${param.list!=null}"> 
										<div class="alert-success" id="savemessage">
											<strong><s:message code="label.success"/></strong>
											<s:message code="label.studentTestResult"/> <s:message code="label.saveSuccess"></s:message>
										</div>
									</c:if>
								<c:if test="${param.listWar!=null}"> 
										<div class="alert-danger" id="savemessage">
											<strong><s:message code="label.error"/> </strong>
												<s:message code="label.studentTestResult"/> <s:message code="label.saveFail"></s:message>
										</div>
								</c:if>	
								<c:if test="${studentTestUpdate!=null}">
										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.studentTestResult"/> <s:message code="label.updateSuccess"></s:message>
										</div>
									</c:if><c:if test="${studentTestUpdateFail!=null }">
										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.studentTestResult"/> <s:message code="label.updateFail"></s:message>
										</div>
									</c:if><c:if test="${studentTestDeleted!=null }">
										<div class="alert-success" id="successmessage">
											<strong><s:message code="label.success"/></strong>
										<s:message code="label.studentTestResult"/> <s:message code="label.deleteSuccess"></s:message>
										</div>
                                 </c:if>
								<c:if test="${studentTestDeleteFail!=null}">
										<div class="alert-danger" id="successmessage">
											<strong><s:message code="label.error"/></strong>
											<s:message code="label.studentTestResult"/> <s:message code="label.deleteFail"></s:message>
										</div>
									</c:if></td>
</tr>
<tr>
								<td><s:message code="label.searchby" /></td>
								<td><form:select path="xmlLabel" cssClass="select">
										<form:options items="${xmlItems}" />
									</form:select> <s:bind path="studentTest.operations">
								<select class="select" name="operations">
								<option value="<s:message code='assignedOperator'/>"><s:message code="label.equalsTo"/> </option>
								<option value="<s:message code='notequalsOperator'/>"><s:message code="label.notEqualsTo"/> </option>
							 <option value="<s:message code='beginsWithOperator'/>"> <s:message code="label.beginsWith"/> </option> 
								<option value="<s:message code='endsWithOperator'/>"><s:message code="label.endsWith"/> </option>
								<option value="<s:message code='containsOperator'/>"><s:message code="label.contains"/></option>
								</select>
									 </s:bind><form:input path="basicSearchId" cssClass="textbox"  id="basicSearchId"/></td>
									 <td><input type="submit" value="<s:message code='label.search'/>" class="btn btn-primary" /> </td>
									 </tr>									 
<tr><td><input type="button" value="Add" class="btn btn-primary" id="add" onclick="viewAdd()"/> </td></tr>
</table></form:form>
</div>
<div id="display">
<c:if test="${listOfStudents!=null}">
						<!-- ============================================Begin OrgDisplayTable=================================================================================================== -->
						<display:table id="listOfStudents" name="listOfStudents"
							requestURI="studentTestSearch.mnt" pagesize="5" class="table">
							 <display:column property="studentResultId"
								titleKey="label.studentResultId" media="none" sortable="true"></display:column>
								<display:column property="testName" titleKey="label.test"
								media="html" sortable="true" />
							<display:column property="termTestName" titleKey="label.termTest"
								media="html" sortable="true" />
								<display:column property="className" titleKey="label.class"
								media="html" sortable="true" />
							
									<display:column property="subjectName" titleKey="label.subject"
								media="html" sortable="true" />
									<display:column titleKey="label.edit" style="color:white">
										<a
											href="studentTestEdit.mnt?testId=<c:out value="${listOfStudents.testId}"/>&termTestId=<c:out value="${listOfStudents.termTestId}"/>&subjectId=<c:out value="${listOfStudents.subjectId}"/>&classId=<c:out value="${listOfStudents.classId}"/>"
											style="color: red"><img src="images/Edit.jpg"
											width="20px" height="20px" /></a>
									</display:column>
									<display:column titleKey="label.delete">
										<a
											href="studentTestDelete.mnt?testId=<c:out value="${listOfStudents.testId}"/>&termTestId=<c:out value="${listOfStudents.termTestId}"/>&subjectId=<c:out value="${listOfStudents.subjectId}"/>&classId=<c:out value="${listOfStudents.classId}"/>"
											style="color: red"
											onclick="return confirm('Do u want to Delete The Record?')"><img
											src="images/Delete.jpg" width="20px" height="20px" /></a> 
						</display:column>--%>
								<display:setProperty name="paging.banner.placement" value="bottom" />
						</display:table>
					</c:if>
</div>
<div id="addForm" style="display:none" >
<form:form action="saveStudentTestDetails.mnt" id="studentTestForm"  method="POST" commandName="studentTest">
<form:hidden path="editMsg" />
<div id="AddStudentResults">
<table class="tableGeneral">
<tr>
</tr>
<tr><td><input type="hidden" id="maxMarks" value=""/><s:message code="label.class"/> <font color="red">*</font></td><td><form:select cssClass="select" path="classId" id="classId">
<form:option value="">--Select--</form:option>
<form:options items="${classDetails}"/>
</form:select> </td></tr>
<tr><td><s:message code="label.termTest"/> <font color="red">*</font></td><td><form:select cssClass="select" path="termTestId" id="termTestId" onchange="populateSubjects()">
<form:option value="">--Select--</form:option>
<form:options items="${termtestDetails}"/>
</form:select> </td></tr>
<tr><td><s:message code="label.subject"/> <font color="red">*</font></td><td><form:select cssClass="select" path="subjectId" id="subjectId" onchange="getSubjects()">
<form:option value="">--Select--</form:option>
<%-- <form:options items="${termtestDetails}"/> --%>
</form:select> </td></tr>

</table></div>

	<div id="studentsDisplay" style="display: none">
			<table id="userdata" border="1" class="tableGeneral">
				<thead>
				</thead>
				<tbody>
				</tbody>
			</table>	</div>
			
			
			
			<div id="EditStudentResults" style="display: none">
		
			<c:if test="${not empty listStudentsMarks }">
		
					<form:hidden path="editMsg" />
					<table class="tableGeneral">
<tr>
</tr>
<form:hidden path="maxMarks" id="maxMarksEdit"/>
<tr><td><s:message code="label.class"/> <font color="red">*</font></td><td><form:select cssClass="select" path="classIdEdit" id="classIdEdit">
<form:option value="">--Select--</form:option>
<form:options items="${classDetails}"/>
</form:select> </td></tr>
<tr><td><s:message code="label.termTest"/> <font color="red">*</font></td><td><form:select cssClass="select" path="termTestIdEdit" id="termTestIdEdit" >
<form:option value="">--Select--</form:option>
<form:options items="${termtestDetails}"/>
</form:select> </td></tr>
<tr ><td><s:message code="label.subject"/> <font color="red">*</font></td><td><form:select cssClass="select" path="subjectIdEdit" id="subjectIdEdit" >
<form:option value="">--Select--</form:option>
<form:options items="${subjectDetails}"/>
</form:select> </td></tr>
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
                  <c:choose>
                  <c:when test="${not empty childSubj }">
                 <c:choose>
                  <c:when test="${listStudentsMarksE.studentId==listOfStudentsForEdit.studentId }">
               
                       <td>
                       <select title="${listStudentsMarksE.subjectName}${listOfStudentsForEdit.studentId}Edit"  id="${listStudentsMarksE.subjectName}test" name="${listStudentsMarksE.subjectName}${listOfStudentsForEdit.studentId}test" onchange="testsValidate(this.id,this.title,${listStudentsMarksE.subjectId},${listOfStudentsForEdit.studentId})">
                         <option value="${listStudentsMarksE.testId}">${listStudentsMarksE.testName}</option>
                       <option value="">--Select--</option>
                       <c:forEach items="${listOfTests}" var="listOfTestss">
                        <c:if test="${listStudentsMarksE.testId != listOfTestss.testId }">
                        <option value="${listOfTestss.testId}">${listOfTestss.testName}</option></c:if>
                       </c:forEach>
                       </select>
								<input type="text" name="${listStudentsMarksE.subjectName}${listOfStudentsForEdit.studentId }" title="${listStudentsMarksE.subjectName}" id="${listStudentsMarksE.subjectName}${listOfStudentsForEdit.studentId}Edit" onkeyup="testMarksEditChildPa(this.id,this.title)"
										value="<c:out value='${listStudentsMarksE.marks}'/>"></input> 
								</td>  
								</c:when>
                  </c:choose>
                   </c:when>
                   <c:otherwise>
                     <c:choose>
                  <c:when test="${listStudentsMarksE.studentId==listOfStudentsForEdit.studentId}">
                   <td>
                   <s:bind path="studentTest.testsEdit">
                      <select name="testsEdit" id="${listStudentsMarksE.testId}${listStudentsMarksE.studentId}" onchange="testsValidate(this.id,${listStudentsMarksE.studentId},${listStudentsMarksE.subjectId},${listOfStudentsForEdit.studentId})">
                         <option value="${listStudentsMarksE.testId}">${listStudentsMarksE.testName}</option>
                       <option value="">--Select--</option>
                       <c:forEach items="${listOfTests}" var="listOfTestss">
                       <c:if test="${listStudentsMarksE.testId != listOfTestss.testId }">
                        <option value="${listOfTestss.testId}">${listOfTestss.testName}</option></c:if>
                       </c:forEach>
                       </select></s:bind>
             <s:bind path="studentTest.studentIdEdit"><input type="hidden" name="studentIdEdit" value="<c:out value='${listOfStudentsForEdit.studentId}'/>"/> </s:bind>
        <s:bind path="studentTest.marksEdit"><input type="text" name="marksEdit" id="${listOfStudentsForEdit.studentId}" onkeyup="testMarksEditi(this.id)"
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
			<tr><td>	
<input type="submit" value="Save" id="events"  class="btn btn-primary"></input>
</td>  </tr>
			</table>
</form:form>
</div>
</body>
</html>