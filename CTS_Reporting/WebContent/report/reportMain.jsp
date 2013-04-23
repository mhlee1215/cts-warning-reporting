<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>CTS Warning Reporint Stystem</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <style>
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
      padding: 2em;
      /*height: 220px;*/
  }

  #content h2,
    #content h3,
    #content p {
      margin: 0 0 15px 0;
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
</head>
<body>




  <ul id="tabs">
      <li><a href="#" id="id_tab1" name="#tab1">BASIC</a></li>
      <li><a href="#" id="id_tab2" name="#tab2">TAXI-OUT</a></li>
      <li><a href="#" id="id_tab3" name="#tab3">TAKE-OFF</a></li>
      <li><a href="#" id="id_tab4" name="#tab4">CLIMB</a></li>    
      <li><a href="#" id="id_tab5" name="#tab5">EN-ROUTE</a></li>
      <li><a href="#" id="id_tab6" name="#tab6">DECENT</a></li>
      <li><a href="#" id="id_tab7" name="#tab7">APPROACH</a></li>
      <li><a href="#" id="id_tab8" name="#tab8">LANDING</a></li>
      <li><a href="#" id="id_tab9" name="#tab9">TAXI-IN</a></li>
  </ul>

  <div id="content">
      <h1 align="center">PILOT REPORT</h1>
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
  
  

  <script>
    function resetTabs(){
        $("#content > div").hide(); //Hide all content
        $("#tabs a").attr("class",""); //Reset id's      
    }

    var myUrl = window.location.href; //get URL
    var myUrlTab = myUrl.substring(myUrl.indexOf("#")); // For localhost/tabs.html#tab2, myUrlTab = #tab2     
    var myUrlTabName = myUrlTab.substring(0,4); // For the above example, myUrlTabName = #tab

    (function(){

        $("#content > div").hide(); // Initially hide all content
        $("#tabs li:first a").attr("class","current"); // Activate first tab
        $("#content > div:first").fadeIn(); // Show first tab content
        
        $("#tabs a").on("click",function(e) {
            e.preventDefault();
            if ($(this).attr("class") == "current"){ //detection for current tab
            	return;       
            }
            else {
            	//alert($(this).attr('name'));
            	resetTabs();
            	$(this).attr("class","current"); // Activate this
            	$($(this).attr('name')).fadeIn(); // Show content for current tab
            }
        });

        /*for (i = 1; i <= $("#tabs li").length; i++) {
          if (myUrlTab == myUrlTabName + i) {
              resetTabs();
              $("a[name='"+myUrlTab+"']").attr("id","current"); // Activate url tab
              $(myUrlTab).fadeIn(); // Show url tab content        
          }
        }*/
        
        $("#tab1").load("reportItem_basic.jsp");
        //$("#tab1").load("reportItem_taxi-out.jsp");
    	$("#tab2").load("reportItem_taxi-out.jsp");
    	$("#tab3").load("reportItem_take-off.jsp");
    	$("#tab4").load("reportItem_climb.jsp");
    	$("#tab5").load("reportItem_en-route.jsp");
    	$("#tab6").load("reportItem_decent.jsp");
    	$("#tab7").load("reportItem_approach.jsp");
    	$("#tab8").load("reportItem_landing.jsp");
    	$("#tab9").load("reportItem_taxi-in.jsp");

    })()
    
    function changeTab(num){
    	resetTabs();
    	$("#id_tab"+num).attr("class","current"); // Activate this
    	$($("#id_tab"+num).attr('name')).fadeIn(); // Show content for current tab
    	$('html,body').scrollTop(0);
    }
  </script>
</body>
</html>