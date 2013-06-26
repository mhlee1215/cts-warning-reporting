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

<script language="javascript" type="text/javascript">

	$(function() {
		//alert('a');
		texi_out_load_hazard_item();
	});

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
		  	width:600,
		  	datatype: "xml", 
		     	colNames:['${lang.getStringReportNo()}','${lang.getStringFlightDate()} (UTC)', '${lang.getStringFlightNo()}', '${lang.getStringACType()}','${lang.getStringState()}'],
		     	colModel:[
		     	 			{name:'rp_no'		,index:'rp_no'		,width:80	,align:"left"	,sortable: true},
		     	    		{name:'date'		,index:'date'		,width:80	,align:"center"	,sortable: true},
		     	    		{name:'flight_no'	,index:'flight_no'	,width:60	,align:"center"	,sortable: true},
		     	    		{name:'ac_type'		,index:'ac_type'	,width:80	,align:"center"	,sortable: true},
		     	    		{name:'state'		,index:'state'		,width:75	,align:"center" ,sortable: true}		
		     	    	],
		     	shrinkToFit:true,
		     	//altRows:true,
		     	hoverrows:false,
		     	rownumbers: false, 
		     	rowNum:10, 
		     	loadtext:'&nbsp;Loading report items..',
		     	//loadtext:'<img src="/images/icons/icon_processing1.gif" width="16" height="16" title="Processing"></img>&nbsp;Loading task data..',
		     	rowList:[10,20,30], 
		     	//pager: '#pager1', 
		     	//pagerpos:'center',
		     	sortname: 'id', 
		     	sortorder: 'desc',
		     	imgpath: gridimgpath,
		     	//multiselect: true,
		     	//viewrecords: true, 
		     	emptyrecords:'no report data',
		     	//caption: "Task List",
		     	toolbar: [false,"top"],
		     	loadError : function(xhr,st,err) { 
		  	   	jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText+". Please reload running status table."); 
		  	},
		  	loadComplete: function(){ 
		  		
		  	},
		  	onSelectRow: function(id){ 
		  		//var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
		  	    //alert(localRowData);
		    }
		  }).navGrid('#pager1',{edit:false,add:false,del:false}); 
		  
	  }
</script>


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

			<div id="">

				<table>
					<tr>
						<td>
							<form method="post" name="contact" action="${pageContext.request.contextPath}/login.do">
							<fieldset>
								<table width="250">
									<tbody>
										<tr>
											<td colspan="2"><h4>${lang.getStringWorkType()}</h4></td>
										</tr>
										<tr>
											<td><input type="radio" name="work_type"
												id="approach_new_hazard_yes" value="report"
												checked="checked" /></td>
											<td style="width: 300px; text-align: left;">${lang.getStringHazardReportingSystem()}</td>
										</tr>
										<tr>
											<td><input type="radio" name="work_type"
												id="approach_new_hazard_no" value="management" /></td>
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
											<td><select id="id_type_selector" name="user_type"
												class="form_selector">
													<option value="0">select</option>
													<option value="P" selected="selected">Pilot</option>
													<option value="C">Cabin</option>
													<option value="G">Ground</option>
													<option value="M">Maintenance</option>
													<option value="D">Dispatcher</option>
											</select></td>
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
											 	<button><a href="${pageContext.request.contextPath}/logout.do">${lang.getStringLogout()}</a></button>
											<%}else{ %>
												<input type="submit" name="submit" id="submit" value="Log in" />
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
						<td width="100%">
							<div>
									
								    <table>
									<tbody>
									<tr>
										<td align="center"><h2>${lang.getStringFlightInformation()}</h2></td>
									</tr>
									<tr>
										<td align="center" width="723px;" ><table id="id_login_flight_information_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
										<div id="pager1" class="scroll"></div>
										</td>
									</tr>
									</tbody>
									</table>
							</div>
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