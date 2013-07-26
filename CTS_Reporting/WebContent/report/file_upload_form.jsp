<!DOCTYPE HTML>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
<meta charset="utf-8">
<title>jQuery File Upload Example</title>

	<link href="css/tableStyle.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/fileupload/jquery-1.8.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/fileupload/vendor/jquery.ui.widget.js"></script>

   <script src="${pageContext.request.contextPath}/js/fileupload/jquery.fileupload.js"></script>

<!-- bootstrap just to have good looking page -->
	<script src="${pageContext.request.contextPath}/js/fileupload/bootstrap.min.js"></script>
	<link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap.min.css">

	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
    <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>

<!-- we code these -->
	
	<script>
	$(function () {
		
		init();
		
		//$('#progress').hide();
		$( "#dialog-modal" ).dialog({
			autoOpen: false,
		    height: 80,
		    width: 350,
		    modal: true
		});
		
	    $('#fileupload').fileupload({
	        dataType: 'json',
	        
	        //add: function (e, data) {  
	        //	alert('hihi');
	        //},
	        
	        done: function (e, data) {
	        	//alert('hi');
	        	jQuery("#list4").jqGrid('clearGridData');
				
				var mydata = data.result;
			    
			    $.each(mydata, function (index, file) {
			    	file['no'] = index+1;
			    	jQuery("#list4").jqGrid('addRowData',index,file);
			    });
	        	/*
	        	$("tr:has(td)").remove();
	            $.each(data.result, function (index, file) {
	            	
	            	
	                $("#uploaded-files tbody").append(
	                		$('<tr id="id_'+index+'_'+file.filename+'" />')
	                		.append($('<td class="td0"/>').text(index))
	                		.append($('<td class="td1"/>').html("<a href='${pageContext.request.contextPath}/"+file.filepath+"'>"+file.filename+"</a>"))
	                		.append($('<td class="td2"/>').text(file.filesize))
	                		.append($('<td class="td3"/>').text(file.filetype))
	                		.append($('<td class="td4"/>').html("<a href='javascript:removeFile(\"id_"+index+"_"+file.filename+"\");'>Delete</a>"))
	                		)//end $("#uploaded-files").append()
	            }); 
	            $( "#dialog-modal" ).dialog( "close" );
	            */
	            
			    $( "#dialog-modal" ).dialog( "close" );
	            
	        },
	        
	        progressall: function (e, data) {
	        	
	        	//$('#progress').fadeIn();
	        	$( "#dialog-modal" ).dialog( "open" );
	        	
		        var progress = parseInt(data.loaded / data.total * 100, 10);
		        
		        var loaded_kb = parseInt(data.loaded / (1024), 10);
		        var total_kb = parseInt(data.total / (1024), 10);
		        
		        $('#dialog-modal').dialog('option', 'title', 'Uploading.. '+loaded_kb+'Kb / '+total_kb+'Kb');
		        //$("#dialog-modal").attr('title', 'Now uploading..'+progress+'/100');
		        //console.log(data.loaded + '/'+ data.total+', progress:'+progress); 
		        $('#progress .bar').css(
		            'width',
		            progress + '%'
		        );
		        
		        //if(progress >= 100){
		        //	$( "#dialog-modal" ).dialog( "close" ); //$('#progress').fadeOut();
		        //}
	   		}//,
	   		
			//dropZone: $('#dropzone')
	    });
	    
	    jQuery("#list4").jqGrid({
	    	datatype: "local",
	    	height: 110,
	    	autowidth:true,
	       	colNames:['No.','File Name','File Size', 'File Type', 'Modified Date',''],
	       	colModel:[
				{name:'no',			index:'no', width:20, align:"center",sorttype:"int"},
	       		{name:'filename',	index:'filename', width:120, align:"left", sorttype:"text", formatter:nameLinkfnFormatter},
	       		{name:'filesize',	index:'filesize', width:50, align:"center", sorttype:"text"},
	       		{name:'filetype',	index:'filetype', width:100, align:"center", sorttype:"text" ${isReadOnly == "Y" ? ",hidden:true" : ""}},
	       		{name:'modifieddate',index:'modifieddate', width:80, align:"center",sorttype:"text"},
	       		{name:'deleteFn',	index:'deleteFn', width:30, align:"center", formatter:deleteBtnfnFormatter ${isReadOnly == "Y" ? ",hidden:true" : ""}}
	       	],
	       	multiselect: false,
	       	caption: "Attached File List"
	    });
	    
	    function nameLinkfnFormatter( cellvalue, options, rowObject )
	    {
	    	var return_str = "<a href='javascript:window.open(\""+rowObject['filepath']+"\")'>"+rowObject['filename']+"</a>";
	    	//window.open('managementDetailHazardIdentificationReport.do?report_no=&category=&type=&isreadonly=Y&hazard_no=${hazard_no}','viewReportWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=950,height=900');
	    	return return_str;
	    }
	    
	    function deleteBtnfnFormatter( cellvalue, options, rowObject )
	    {
	    	var return_str = '<a id="id_row_item_'+rowObject['id']+'" href="#">${lang.getStringDelete()}</a>';
	    	return_str += '<script>';
	    	return_str += '$("#id_row_item_'+rowObject['id']+'").button({icons: {secondary: "ui-icon-trash" } }).click(function( event ) {'
	    	return_str += '		removeFile(\"'+rowObject['id']+'\");';
	    	return_str += '    	event.preventDefault();';
	    	return_str += '});';
	    	return_str += '</scr'+'ipt>';
	      	return return_str;
	      	
	    	//var return_str = "<a href='javascript:removeFile(\""+rowObject['id']+"\");'>Delete</a>";
	    	return return_str;
	    }
	    
	    var mydata = eval('${attachedItemList}');
	    
	    $.each(mydata, function (index, file) {
	    	file['no'] = index+1;
	    	jQuery("#list4").jqGrid('addRowData',index,file);
	    });
	    
	    //jQuery("#list4").jqGrid ('setLabel', 'filename', '', 'textalignleft');

	});
	
	function init(){
		var dd = eval('${attachedItemList}');
		$.each(dd, function (index, file) {
        	//alert(file);
        	
            $("#uploaded-files tbody").append(
            		$('<tr id="id_'+index+'_'+file.filename+'" />')
            		.append($('<th class="td0 spec"/>').text(index))
            		.append($('<td class="td1 item"/>').html("<a href='${pageContext.request.contextPath}/"+file.filepath+"'>"+file.filename+"</a>"))
            		.append($('<td class="td2 item"/>').text(file.filesize))
            		.append($('<td class="td3 item"/>').text(file.filetype))
            		.append($('<td class="td4 item"/>').html("<a href='javascript:removeFile(\"id_"+index+"_"+file.filename+"\");'>Delete</a>"))
            		);//end $("#uploaded-files").append()
        }); 
	}

	function removeFile(tr_idx){
		//alert(tr_idx);
		//alert(tr_idx);
		//$("tr:has(td)#"+tr_idx).remove();
		//$("tr:has(td)#"+tr_idx).context.remove()
		//$("#uploaded-files").deleteRow(2);
		
		//printObject($("tr:has(td)#"+tr_idx).context.remove());
		//$('#uploaded-files #'+tr_idx).hide();
		
		if (confirm("Delete this file?")){
		
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/fileDelete.do",
				data: 'file_id='+tr_idx+'&report_no=${report_no}&report_item_type=${report_item_type}',
				success: function(msg){
					//var Result = msg;
					//alert(msg);
					//$(document.getElementById(tr_idx)).remove();
					
					jQuery("#list4").jqGrid('clearGridData');
					
					var mydata = eval(msg);
				    
				    $.each(mydata, function (index, file) {
				    	file['no'] = index;
				    	jQuery("#list4").jqGrid('addRowData',index,file);
				    });
				}
			});
		}
	}

	function printObject(o) {
		  var out = '';
		  for (var p in o) {
		    out += p + ': ' + o[p] + '\n';
		  }
		  alert(out);
		}
	
	</script>
	
	
	
<style>
.textalignleft div { padding-left: 5px; }

.fileinput-button {
  position: relative;
  overflow: hidden;
  float: left;
  margin-right: 4px;
}

.fileinput-button input {
  position: absolute;
  top: 0;
  right: 0;
  margin: 0;
  border: solid transparent;
  border-width: 0 0 100px 200px;
  opacity: 0;
  filter: alpha(opacity=0);
  -moz-transform: translate(-300px, 0) scale(4);
  direction: ltr;
  cursor: pointer;
}

.t_header { text-align: center; }

.th0 {width:5%;}
.th1 {width:20%;}
.th2 {width:99px;}
.th3 {width:99px;}
.th4 {width:200px;}

.td0 {width:5%;}
.td1 {width:20%;}
.td2 {width:99px;}
.td3 {width:99px;}
.td4 {width:170px;}

</style>
</head>

<body>
<div style="width:100%;padding:0px">
	<table width="100%">
	<tr>
	<td align="right">
	<c:if test="${isReadOnly == 'N'}">
	<span class="btn btn-success fileinput-button" style="float:right">
       <i class="icon-plus icon-white"></i>
       <span>Add files...</span>
       <input id="fileupload" type="file" name="files[]" data-url="${pageContext.request.contextPath}/fileUpload.do?report_no=${report_no}&report_item_type=${report_item_type}" multiple>
   </span>
   </c:if>
   </td>
   </tr>
	</table>
	
	<div id="dialog-modal" title="Now uploading.." style="overflow: hidden;">
	  	<div id="progress" class="progress" >
	    	<div class="bar" style="width: 0%;"></div>
		</div>
	</div>	
	
	<!-- 
	<table width="100%" class="" style="margin-bottom: 0px;">
		<thead>
		<tr>
			<th class="th0 t_header spec">No.</th>
			<th class="th1 t_header">File Name</th>
			<th class="th2 t_header">File Size</th>
			<th class="th3 t_header">File Type</th>
			<th class="th4 t_header">Download</th>
		</tr>
		</thead>
	</table>
	<div style="height:100px; overflow: auto;">
	<table width="100%" id="uploaded-files" class="table">
		<tbody >
		
		</tbody>
		
	</table>
	</div>
 	-->
</div>

<table id="list4"></table>

</body> 
</html>


