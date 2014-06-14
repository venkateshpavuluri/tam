<html>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
<button onClick="GetData();">GO</button>
<table id="excelDataTable" border="1">
  </table>
</body>
<script type='text/javascript'>
function GetData()
{
alert("dh");
$.getJSON('', 
function(data) 		
{
alert("sfdgs");
var str=JSON.stringify(data);
/*alert(str);*/
var res = str.substring(30,str.length-1);
/*alert(res);*/
var obj = jQuery.parseJSON(res);
buildHtmlTable(obj) ;
});
}
// Builds the HTML Table out of data.
function buildHtmlTable(data) {
    var columns = addAllColumnHeaders(data);
	/*alert(data.length);*/
    for (var i = 0 ; i < data.length ; i++) {
        var row$ = $('<tr/>');
		var tt='&nbsp;';
		row$.append($('<td/>').html(tt));
        for (var colIndex = 0 ; colIndex < columns.length ; colIndex++) {

		   var cellValue = '';
		   if(cellValue=='')
		    {
		   cellValue= 'Contract Price:' +  data[i][columns[colIndex]] +'<br/>' ;
		    }
		   else
		   {cellValue= cellValue +''+ data[i][columns[colIndex]] +'<br/>' ;}
		 
            if (cellValue == null) { cellValue =""; }
			}	
				
		row$.append($('<td/>').html(cellValue));
		cellValue='';
		$("#excelDataTable").append(row$);	
    }
}
// Adds a header row to the table and returns the set of columns.
// Need to do union of keys from all records as some records may not contain
// all records
function addAllColumnHeaders(data)
{
    var columnSet = [];
    var headerTr$ = $('<td/>');

    for (var i = 0 ; i < data.length ; i++) {
	/*alert(data.length);*/
        var rowHash = data[i];
        for (var key in rowHash) {
            if ($.inArray(key, columnSet) == -1){
                columnSet.push(key);
                headerTr$.append($('<th/>').html(key));
            }
        }
    }
    $("#excelDataTable").append(headerTr$);
    return columnSet;
}
</script>
</html>