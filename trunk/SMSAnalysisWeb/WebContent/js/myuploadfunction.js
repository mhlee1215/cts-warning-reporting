$(function () {
	//$('#progress').hide();
	$( "#dialog-modal" ).dialog({
		autoOpen: false,
	    height: 300,
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
        	$("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
            	
            	
                $("#uploaded-files").append(
                		$('<tr id="id_'+index+'_'+file.fileName+'" />')
                		.append($('<td/>').html("<a href='"+file.filePath+"'>"+file.fileName+"</a>"))
                		.append($('<td/>').text(file.fileSize))
                		.append($('<td/>').text(file.fileType))
                		.append($('<td/>').html("<a href='javascript:removeFile(\"id_"+index+"_"+file.fileName+"\");'>Delete</a>"))
                		)//end $("#uploaded-files").append()
            }); 
            $( "#dialog-modal" ).dialog( "close" );
            
        },
        
        progressall: function (e, data) {
        	
        	//$('#progress').fadeIn();
        	$( "#dialog-modal" ).dialog( "open" );
        	
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        
	        var loaded_kb = parseInt(data.loaded / (1024*1024), 10);
	        var total_kb = parseInt(data.total / (1024*1024), 10);
	        
	        $('#dialog-modal').dialog('option', 'title', 'Uploading.. '+loaded_kb+'Mb / '+total_kb+'Mb');
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
});

function removeFile(tr_idx){
	//alert(tr_idx);
	//alert(tr_idx);
	//$("tr:has(td)#"+tr_idx).remove();
	//$("tr:has(td)#"+tr_idx).context.remove()
	//$("#uploaded-files").deleteRow(2);
	
	//printObject($("tr:has(td)#"+tr_idx).context.remove());
	//$('#uploaded-files #'+tr_idx).hide();
	
	$.ajax({
		type: "POST",
		url: "controller/delete.html",
		data: 'file_id='+tr_idx,
		success: function(msg){
			var Result = msg;
			alert(msg);
			$(document.getElementById(tr_idx)).remove();
		}
	});
}

function printObject(o) {
	  var out = '';
	  for (var p in o) {
	    out += p + ': ' + o[p] + '\n';
	  }
	  alert(out);
	}