<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Reporting System - Login</title>


<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="js/jquery.js"></script>
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
  <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>
  <style>
    
  .l1_fieldset { border:2px solid rgb(40, 40, 40) }
  .l1_fieldset_legend {
	  padding: 0.2em 0.5em;
	  
	  color:rgb(30, 30, 30);
	  font-weight:bold;
	  font-size:150%;
	  text-align:left;
  }
  .l2_fieldset { border:1px solid rgb(60, 60, 60) }
  .l2_fieldset_legend {
	  padding: 0.2em 0.5em;
	  color:rgb(30, 30, 30);
	  font-weight:bold;
	  font-size:120%;
	  text-align:left;
  }
  
  .example{color:#666;}
  </style>


<!-- / fim dos arquivos utilizados pelo jQuery lightBox plugin -->

<!-- Ativando o jQuery lightBox plugin -->
<script type="text/javascript">
	$(function() {
		//$('#map a').lightBox();
	});
</script>

</head>
<body>
	<div id="templatemo_body_wrapper">
		<div id="templatemo_wrapper">

			<%@include file="header.jsp"%>

<script language="javascript" type="text/javascript">

	$(function() {
		//alert('a');
		texi_out_load_hazard_item();
		
		$("#id_login_login_btn")
		  .button({icons: {primary: "ui-icon-locked" } })
		  .click(function( event ) {
			  
		  // window.open('','targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=950,height=800')
		   //alert($('#id_login_login_info').attr('action'));
		   //$('#id_login_login_info').attr('target', 'targetWindow');
		   if($('input[name="name_work_type_radio"]:checked').val() == 'report' && $("#id_type_selector_reporting").val() == 0){
			   alert('Please, select your type.');
		   }else{
			   $('#id_login_login_info').submit();  
		   }
		   //
		   //event.preventDefault();
		});
		
		$("#id_login_logout_btn")
		  .button({icons: {primary: "ui-icon-unlocked" } })
		  .click(function( event ) {
			  
		  document.location = '${pageContext.request.contextPath}/logout.do';
		   event.preventDefault();
		});
		
		$("#id_type_selector_reporting").show();
        $("#id_type_selector_management").hide();
         
		$("input[name='name_work_type_radio']").change(function() {
			
			if ($("input[name='name_work_type_radio']:checked").val() == 'report'){
                //alert('report activate!');
                $("#id_type_selector_reporting").show();
                $("#id_type_selector_management").hide();
			}
            else if ($("input[name='name_work_type_radio']:checked").val() == 'management'){
            	//alert('management activate!');
            	$("#id_type_selector_reporting").hide();
                $("#id_type_selector_management").show();
			}
            
		});
		
		     
	});

	function changeUserType(userType){
		document.location = '${pageContext.request.contextPath}/changeUserType.do?user_type='+userType;
	}
	function clearText(field) {
		if (field.defaultValue == field.value)
			field.value = '';
		else if (field.value == '')
			field.value = field.defaultValue;
	}
	
	function texi_out_load_hazard_item(){
		  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
		  jQuery("#id_login_flight_information_ListTable").jqGrid({
		  	url:'${pageContext.request.contextPath}/readFlightInformation.do', 
		  	height: 300, 
		  	//width:600,
		  	autowidth:true,
		  	datatype: "xml", 
	     	colNames:['${lang.getStringReportNo()}','${lang.getStringFlightDate()} (UTC)', '${lang.getStringFlightNo()}', '${lang.getStringACType()}','${lang.getStringState()}'],
	     	colModel:[
	     	 			{name:'rp_no'		,index:'rp_no'		,width:80	,align:"left"	,sortable: true},
	     	    		{name:'date'		,index:'date'		,width:80	,align:"center"	,sortable: true},
	     	    		{name:'flight_no'	,index:'flight_no'	,width:60	,align:"center"	,sortable: true},
	     	    		{name:'ac_type'		,index:'ac_type'	,width:80	,align:"center"	,sortable: true},
	     	    		{name:'state'		,index:'state'		,width:75	,align:"center" ,sortable: true}		
	     	    	],
	     	//shrinkToFit:false,
	     	//altRows:true,
	     	hoverrows:false,
	     	rownumbers: false, 
	     	//rowNum:10, 
	     	loadtext:'&nbsp;Loading report items..',
	     	//loadtext:'<img src="/images/icons/icon_processing1.gif" width="16" height="16" title="Processing"></img>&nbsp;Loading task data..',
	     	rowList:[10,20,30], 
	     	//pager: jQuery('#pager1'), 
	     	//pagerpos:'center',
	     	sortname: 'id', 
	     	sortorder: 'desc',
	     	imgpath: gridimgpath,
	     	//multiselect: true,
	     	//viewrecords: true, 
	     	emptyrecords:'no report data',
	     	//caption: "Task List",
	     	toolbar: [false,"top"],
	     	loadComplete: function(){ 
		  		$("#id_login_flight_information_ListTable").jqGrid('setGridWidth', $('#id_login_flight_information_ListTable_parentDiv').width(), true);
		  	},
		  	onSelectRow: function(rowid, status, e) {  			
		        <%if("true".equals(islogin)){ %>
		  		document.location = '${pageContext.request.contextPath}/report.do?'+'report_no='+rowid;
		  		<%}%>
			}
		  });//.navGrid('#pager1',{edit:false,add:false,del:false}); 
		  
	  }
</script>

			<div id="">

				<table width="100%">
					<tr>
						<%if(!"true".equals(islogin)){ %>
						<td valign="top">
							<form method="post" name="name_login_login_info" id="id_login_login_info" action="${pageContext.request.contextPath}/login.do">
							<fieldset class="l2_fieldset" style="height:353px;">
							<legend class="l2_fieldset_legend">${lang.getStringWorkType()}</legend>
								<table width="250">
									<tbody>
										<tr>
											<td><input type="radio" name="name_work_type_radio"
												id="id_work_type_reporting_radio" value="report"
												checked="checked" /></td>
											<td style="width: 300px; text-align: left;">${lang.getStringHazardReportingSystem()}</td>
										</tr>
										<tr>
											<td><input type="radio" name="name_work_type_radio"
												id="id_work_type_management_radio" value="management" /></td>
											<td style="width: 300px; text-align: left;">${lang.getStringHazardManagementSystem()}</td>
										</tr>
									</tbody>
								</table>

								<table>
									<tbody>
										<tr>
											<td class="leftmost_label">${lang.getStringID()}</td>
											<td><input type="text"
												name="id" id="id" class="form_input_text" value="0000"/></td>
										</tr>
										<tr>
											<td class="leftmost_label">${lang.getStringPassword()}</td>
											<td><input type="password" name="password" id="password" class="form_input_text" value="0000"/></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="leftmost_label">${lang.getStringType()}</td>
											<td><select id="id_type_selector_reporting" name="user_type_reporting" onchange="changeUserType(this.value);"
												class="form_selector">
													<option value="0">select</option>
													<option value="P" ${user_type == "P" ? "selected=\"selected\"" : ""}>Pilot</option>
													<option value="C" ${user_type == "C" ? "selected=\"selected\"" : ""}>Cabin</option>
													<option value="G" ${user_type == "G" ? "selected=\"selected\"" : ""}>Ground</option>
													<option value="M" ${user_type == "M" ? "selected=\"selected\"" : ""}>Maintenance</option>
													<option value="D" ${user_type == "D" ? "selected=\"selected\"" : ""}>Dispatcher</option>
											</select>
											
											<select id="id_type_selector_management" name="user_type_management"
												class="form_selector">
													<option value="0">select</option>
													<option value="SM" selected="selected">Safety manager</option>
											</select>
											
											</td>
										</tr>
										<tr>
											<td class="leftmost_label">${lang.getStringLanguage()}</td>
											<td><select id="id_language_selector" name="language"
												class="form_selector">
													<option value="Eng">English</option>
													<option value="Kor">Korean</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">
												<!-- <input type="reset"  name="reset" id="reset" value="Reset" /> -->
											</td>
											<td align="right">
											 <%if("true".equals(islogin)){ %>
											 	<a id="id_login_logout_btn" href="#">${lang.getStringLogout()}</a>
											<%}else{ %>
												
												<a id="id_login_login_btn" href="#">Log in</a>
												<!-- <input type="submit" >Log in</input> -->
											<%} %>
											
											</td>
										</tr>
										<tr>
											<td colspan="2"><div align="right">
													<a href="#">${lang.getStringForgotYourPassword()}</a>
												</div></td>
										</tr>
									</tbody>
								</table>
								</fieldset>
							</form>
						</td>
						<%} %>
						<td width="100%">
							
							<fieldset class="l2_fieldset">
							<legend class="l2_fieldset_legend">${lang.getStringFlightInformation()}</legend>
									
								    <table width="100%">
									<tbody>
									
									<tr>
										<td align="center">
										<div id="id_login_flight_information_ListTable_parentDiv">
										<table id="id_login_flight_information_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
										<div id="pager1" class="scroll"></div>
										</div>
										</td>
									</tr>
									</tbody>
									</table>
									</fieldset>
							
						</td>
					</tr>
				</table>
				<div class="cleaner"></div>
			</div>
			<!-- end of main -->
			<div id="templatemo_main_bottom"></div>
			<div class="cleaner"></div>
		</div>
		<!-- end of templatemo wrapper -->
	</div>
	<!-- end of templatemo body wrapper -->

	<%@include file="footer.jsp"%>

</body>


</html>