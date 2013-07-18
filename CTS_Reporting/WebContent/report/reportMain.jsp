<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>CTS Warning Reporint Stystem</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <style>
  .leftmost_label{
  	width:100px;
  	text-align:right;
  }
  .leftmost_label2{
  	width:100px;
  	text-align:right;
  	font-weight:bold;
  }
  .leftmost_label_widt{
  	width:250px;
  	text-align:right;
  }
  .leftmost_header1{
    font-size:12px;
  	font-weight:bold;
  	margin-left:5px;
  	width:100px;
  	text-align:left;
  }
  .form_input_text{
  	width:120px;
  }
  .form_selector{
  	margin-left:2px;
  	margin-right:2px;
  	width:120px;
  }
  body {
  
    margin: 10px auto 0 auto;
    font-family: Arial, Helvetica;
    font-size: small;
    background-color: #eee;
    /*background-image: url(data:image/gif;base64,R0lGODlhCAAIAJEAAMzMzP///////wAAACH5BAEHAAIALAAAAAAIAAgAAAINhG4nudroGJBRsYcxKAA7);*/
  }

  /* ------------------------------------------------- */

  #tabs {
    overflow: hidden;
    width: 100%;
    margin: 0;
    padding: 0;
    list-style: none;
  }

  #tabs li {
    float: left;
    margin: 0 -15px 0 0;
  }

  #tabs a {
    float: left;
    position: relative;
    padding: 0 20px;
    height: 0;
    line-height: 30px;
    text-transform: uppercase;
    text-decoration: none;
    color: #fff;      
    border-right: 30px solid transparent;
    border-bottom: 30px solid #3D3D3D;
    border-bottom-color: #777\9;
    opacity: .3;
    filter: alpha(opacity=30);      
  }

  #tabs a:hover,
  #tabs a:focus {
    border-bottom-color: #2ac7e1;
    opacity: 1;
    filter: alpha(opacity=100);
  }

  #tabs a:focus {
    outline: 0;
  }

  #tabs .current {
    z-index: 3;
    border-bottom-color: #3d3d3d;
    opacity: 1;
    filter: alpha(opacity=100);      
  }

  /* ----------- */
  #content {
      background: #fff;
      border-top: 2px solid #3d3d3d;
      padding: 1em;
      /*height: 800px;*/
  }

  .report_title {
    font-size: 2.5em;
  	margin: 0 0 5px 0;
  }
  
  #content h2,
    #content h3,
    #content p {
      margin: 0 0 0 0;
  }  

  /* Demo page only */
  #about {
      color: #999;
      text-align: center;
      font: 0.9em Arial, Helvetica;
  }

  #about a {
      color: #777;
  }   
  </style>  
    
  <script>
  var submitted = new Array(9);
  var report_item_title = new Array(9);
  
  <c:forEach items="${rpItemList}" var="report_item" varStatus="list_status">
  submitted[${list_status.index}] = ${report_item.status == report_item.getSTATE_SUBMITTED() ? "1" : "0"};
  report_item_title[${list_status.index}] = '${report_item.type}';
  </c:forEach>
    
  function submit_page(page_index){
	  submitted[page_index-1] = 1;
	  //alert(page_index);
	  var cur_text = $("#id_tab"+page_index).text();
	   //alert(cur_text.substring(0, cur_text.length-1));
		$("#id_tab"+page_index).text(cur_text.substring(0, cur_text.length-1)+'●');
		if( page_index == currentTab){
			$("#id_main_submit_btn").hide();
			$("#id_main_submitted_btn").show();
  		}
		//$("#id_main_submit_btn").button({icons: {secondary: "ui-icon-check" } });
  }
  
  $(function() {
	  $("#id_main_previous_btn")
	  .button({icons: {primary: "ui-icon-circle-triangle-w" } })
	  .click(function( event ) {
		  previousTab();
	   event.preventDefault();
	  });
	  $("#id_main_next_btn")
	  .button({icons: {secondary: "ui-icon-circle-triangle-e" } })
	  .click(function( event ) {
		  nextTab()
	   event.preventDefault();
	  });
	  $("#id_main_edit_btn")
	  .button({icons: {secondary: "ui-icon-document" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  $("#id_main_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	   if(currentTab == 1){
		   var str = $("#report_item_basic_form").serialize();
		   $.ajax({
			    type:"post",
			    data:str+'&report_no=${report_no}',
			    url:"reportBasicUpdate.do",
			    async: false,
			    success: function(msg){
			       alert(msg);
			    }
			});
	   }else{
		  
		   var narrativeText = CKEDITOR.instances["id_report_"+report_item_title[currentTab-1]+"_narrative"].getData();
		   var recomText = CKEDITOR.instances["id_report_"+report_item_title[currentTab-1]+"_recommendation"].getData();
		   $("#id_report_"+report_item_title[currentTab-1]+"_narrative").val(narrativeText);
		   $("#id_report_"+report_item_title[currentTab-1]+"_recommendation").val(recomText);
		   var str = $("#report_"+report_item_title[currentTab-1]+"_form").serialize(); 
		   $.ajax({
			    type:"post",
			    data:str+'&report_no=${report_no}&report_item_type='+report_item_title[currentTab-1],
			    url:"reportItemUpdate.do",
			    async: false,
			    success: function(msg){
			       alert(msg);
			    }
			});
	   }
	   
	  });
	  $("#id_main_delete_btn")
	  .button({icons: {secondary: "ui-icon-trash" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  $("#id_main_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
		  if(currentTab == 1){
			   var str = $("#report_item_basic_form").serialize();
			   $.ajax({
				    type:"post",
				    data:str+'&report_no=${report_no}&report_item_type=BASIC',
				    url:"reportBasicSubmit.do",
				    async: false,
				    success: function(msg){
				       alert(msg);
				       submit_page(currentTab);
				    }
				});
		   }else{
			  
			   var narrativeText = CKEDITOR.instances["id_report_"+report_item_title[currentTab-1]+"_narrative"].getData();
			   var recomText = CKEDITOR.instances["id_report_"+report_item_title[currentTab-1]+"_recommendation"].getData();
			   $("#id_report_"+report_item_title[currentTab-1]+"_narrative").val(narrativeText);
			   $("#id_report_"+report_item_title[currentTab-1]+"_recommendation").val(recomText);
			   var str = $("#report_"+report_item_title[currentTab-1]+"_form").serialize(); 
			   $.ajax({
				    type:"post",
				    data:str+'&report_no=${report_no}&report_item_type='+report_item_title[currentTab-1],
				    url:"reportItemSubmit.do",
				    async: false,
				    success: function(msg){
				       alert(msg);
				       submit_page(currentTab);
				    }
				});
		   }
		  
	  
	  
	   event.preventDefault();
	  });
	  
	  $("#id_main_submitted_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  $("#id_main_previous_btn").hide();
	  $("#id_main_submitted_btn").hide();
	  
	  //alert(submitted);
	  for(var i = 0 ; i < 9 ; i++){
		  if(submitted[i] == 1)
			  submit_page(i+1);
	  }
  });
  
  function printObject(o) {
	  var out = '';
	  for (var p in o) {
	    out += p + ': ' + o[p] + '\n';
	  }
	  alert(out);
	}
  </script>
</head>
<body>
<%@include file="/header.jsp"%>
<script>

<%if(!"true".equals(islogin)){ %>
document.location = '${pageContext.request.contextPath}/index.do';
<%}%>

</script>



  <ul id="tabs">
      <li><a href="#" id="id_tab1" name="#tab1">BASIC ○</a></li>
      <li><a href="#" id="id_tab2" name="#tab2">TAXI-OUT ○</a></li>
      <li><a href="#" id="id_tab3" name="#tab3">TAKE-OFF ○</a></li>
      <li><a href="#" id="id_tab4" name="#tab4">CLIMB ○</a></li>    
      <li><a href="#" id="id_tab5" name="#tab5">EN-ROUTE ○</a></li>
      <li><a href="#" id="id_tab6" name="#tab6">DECENT ○</a></li>
      <li><a href="#" id="id_tab7" name="#tab7">APPROACH ○</a></li>
      <li><a href="#" id="id_tab8" name="#tab8">LANDING ○</a></li>
      <li><a href="#" id="id_tab9" name="#tab9">TAXI-IN ○</a></li>
  </ul>

  <div id="content">
      <!-- <h1 align="center" class="report_title">PILOT REPORT</h1> -->
      <div id="tab1"></div>
      <div id="tab2"></div>
      <div id="tab3"></div>
      <div id="tab4"></div>
      <div id="tab5"></div>
      <div id="tab6"></div>
      <div id="tab7"></div>
      <div id="tab8"></div>
      <div id="tab9"></div>
  </div>
  
  <div>
  	<table width="100%">
  		<tr>
  			<td align="left"><a id="id_main_previous_btn" href="#">${lang.getStringPrevious()}</a></td>
  			<td align="center"><a id="id_main_edit_btn" href="#">${lang.getStringEdit()}</a>
  			<a id="id_main_save_btn" href="#">${lang.getStringSave()}</a>
  			<a id="id_main_delete_btn" href="#">Delete</a>
  			<a id="id_main_submit_btn" href="#">Submit</a>
  			<a id="id_main_submitted_btn" href="#">Submitted</a></td>
  			<td align="right"><a id="id_main_next_btn" href="#">${lang.getStringNext()}</a></td>
  		</tr>
  	</table>
  </div>

  <script>
  
  	var urlSet = new Array(9);
  	urlSet[0] = "${pageContext.request.contextPath}/reportBASIC.do?report_no=${report_no}&report_item_type="+report_item_title[0];
  	for(var i = 1 ; i < 9 ; i++){
  		urlSet[i] = "${pageContext.request.contextPath}/reportItem.do?report_no=${report_no}&report_item_type="+report_item_title[i];	
  	}
  	
  	/*
  	urlSet[1] = "${pageContext.request.contextPath}/reportTaxiOut.do?report_no=${report_no}";
  	urlSet[2] = "${pageContext.request.contextPath}/reportTakeOff.do?report_no=${report_no}";
  	urlSet[3] = "${pageContext.request.contextPath}/reportClimb.do?report_no=${report_no}";
  	urlSet[4] = "${pageContext.request.contextPath}/report_en_route.do?report_no=${report_no}";
  	urlSet[5] = "${pageContext.request.contextPath}/reportDecent.do?report_no=${report_no}";
  	urlSet[6] = "${pageContext.request.contextPath}/reportApproach.do?report_no=${report_no}";
  	urlSet[7] = "${pageContext.request.contextPath}/reportLanding.do?report_no=${report_no}";
  	urlSet[8] = "${pageContext.request.contextPath}/reportTaxiIn.do?report_no=${report_no}";
  */
  	
    var haveActivated = new Array(9);
    
    for(var i = 0 ; i < 9 ; i++)
    	haveActivated[i] = false;
    
    function resetTabs(){
        $("#content > div").hide(); //Hide all content
        $("#tabs a").attr("class",""); //Reset id's      
    }

    var myUrl = window.location.href; //get URL
    var myUrlTab = myUrl.substring(myUrl.indexOf("#")); // For localhost/tabs.html#tab2, myUrlTab = #tab2     
    var myUrlTabName = myUrlTab.substring(0,4); // For the above example, myUrlTabName = #tab

    (function(){

        $("#content > div").hide(); // Initially hide all content
        
        haveActivated[0] = true;
        $("#tabs li:first a").attr("class","current"); // Activate first tab
        
        $("#content > div:first").fadeIn(); // Show first tab content
        
        $("#tabs a").on("click",function(e) {
            e.preventDefault();

            currentTab = eval($(this).attr("id").substring(6, 7));
            if(!haveActivated[currentTab-1])
            {
            	//alert('first!');
            	haveActivated[currentTab-1] = true;
            	//alert('load : '+urlSet[currentTab-1]);
            	$('#tab'+currentTab).load(urlSet[currentTab-1]);
            }
            
            
            if($(this).attr("id") == 'id_tab1'){
            	$("#id_main_previous_btn").hide();
            }else{
            	$("#id_main_previous_btn").show();
            }
            
            if(submitted[currentTab-1] == 1){
        		$("#id_main_submit_btn").hide();
        		$("#id_main_submitted_btn").show();
        	}else{
        		$("#id_main_submit_btn").show();
        		$("#id_main_submitted_btn").hide();
        	}
            
            if ($(this).attr("class") == "current"){ //detection for current tab
            	return;       
            }
            else {
            	resetTabs();
            	$(this).attr("class","current"); // Activate this
            	$($(this).attr('name')).fadeIn(); // Show content for current tab
            }
        });
        
        $("#tab1").load(urlSet[0]);
        /*
        $("#tab1").load("${pageContext.request.contextPath}/reportBasic.do?report_no=${report_no}");
    	$("#tab2").load("${pageContext.request.contextPath}/reportTaxiOut.do?report_no=${report_no}");
    	$("#tab3").load("${pageContext.request.contextPath}/reportTakeOff.do?report_no=${report_no}");
    	$("#tab4").load("${pageContext.request.contextPath}/reportClimb.do?report_no=${report_no}");
    	$("#tab5").load("${pageContext.request.contextPath}/report_en_route.do?report_no=${report_no}");
    	$("#tab6").load("${pageContext.request.contextPath}/reportDecent.do?report_no=${report_no}");
    	$("#tab7").load("${pageContext.request.contextPath}/reportApproach.do?report_no=${report_no}");
    	$("#tab8").load("${pageContext.request.contextPath}/reportLanding.do?report_no=${report_no}");
    	$("#tab9").load("${pageContext.request.contextPath}/reportTaxiIn.do?report_no=${report_no}");
		*/
    	//changeTab(2);
    })();
    
    var currentTab = 1;
    var min = 1;
    var max = 9;
    function nextTab(){
    	currentTab += 1;
    	if (currentTab > max)
    		currentTab = max;
    	else
    		changeTab(currentTab);
    }
    function previousTab(){
    	currentTab -= 1;
    	if (currentTab < min)
    		currentTab = min;
    	else
    		changeTab(currentTab);
    }
    function changeTab(num){
    	if(!haveActivated[currentTab-1])
        {
        	//alert('first!');
        	haveActivated[num-1] = true;
        	$('#tab'+currentTab).load(urlSet[currentTab-1]);
        }
    	
    	resetTabs();
    	$("#id_tab"+num).attr("class","current"); // Activate this
    	$($("#id_tab"+num).attr('name')).fadeIn(); // Show content for current tab
    	$('html,body').scrollTop(0);
    	
    	if(num == 1){
    		$("#id_main_previous_btn").hide();
    	}
    	else{
    		$("#id_main_previous_btn").show();
    	}
    	
    	if(submitted[num-1] == 1){
    		$("#id_main_submit_btn").hide();
    		$("#id_main_submitted_btn").show();
    	}else{
    		$("#id_main_submit_btn").show();
    		$("#id_main_submitted_btn").hide();
    	}
    	
    	
    }
  </script>
</body>
</html>