<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Light Gray - Contact Form</title>
<meta name="keywords" content="free css templates, contact form, light gray, clean, simple, professional, CSS, HTML" />
<meta name="description" content="Light Gray Contact Form - Free CSS Template from templatemo.com" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
function clearText(field)
{
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;
}
</script>

<link rel="stylesheet" type="text/css" href="css/jquery.lightbox-0.5.css" />    
    
<!-- Arquivos utilizados pelo jQuery lightBox plugin -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.lightbox-0.5.css" media="screen" />
<!-- / fim dos arquivos utilizados pelo jQuery lightBox plugin -->

<!-- Ativando o jQuery lightBox plugin -->
<script type="text/javascript">
$(function() {
    $('#map a').lightBox();
});
</script>

</head>
<body>
<div id="templatemo_body_wrapper">
<div id="templatemo_wrapper">
	    
   	<%@include file = "header.jsp"%>
    
    <div id="templatemo_main">
    
    	
        <div class="col_w450 float_c">
          
            <div id="contact_form">
        
                <h4>Login form</h4>
                
                	<form method="post" name="contact" action="#">
                    
                            <label for="author">Email:</label> <input name="author" type="text" class="input_field" id="author" maxlength="50" />
                          	<div class="cleaner_h10"></div>
                            
                            <label for="email">Password:</label> <input name="email" type="password" class="input_field" id="email" maxlength="50" />
                          	<div class="cleaner_h10"></div>
                            
                            <input type="submit" class="submit_btn float_l" name="submit" id="submit" value="Send" />
                            <input type="reset" class="submit_btn float_r" name="reset" id="reset" value="Reset" />
                        
                   </form>

            </div> 
        </div>
        
        
        
        <div class="cleaner"></div>
    </div> <!-- end of main -->
	<div id="templatemo_main_bottom"></div>
	<div class="cleaner"></div>
</div> <!-- end of templatemo wrapper -->
</div> <!-- end of templatemo body wrapper -->

<%@include file = "footer.jsp"%>

</body>
</html>