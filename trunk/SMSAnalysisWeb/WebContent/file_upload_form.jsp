<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>Spring MVC Multiple File Upload</title>
<link href="flot/examples.css" rel="stylesheet" type="text/css" />
<link href="css/tableStyle.css" rel="stylesheet" type="text/css" />
<style>
	body {font-size: 1em; font-family: "Trebuchet MS";}
	h1 {font-size: 2em;}
</style>

<script src="js/jquery.1.9.1.min.js"></script>

<script>
$(document).ready(function() {
	
});
</script>
</head>
<body>
<h1>SMS Analysis web</h1>

<form:form method="post" action="save.html" 
		modelAttribute="uploadForm" enctype="multipart/form-data">

	<p>Select files to upload. </p>
	<br>

	<table id="fileTable">
		<tr>
			<td><input name="files[0]" type="file" /> </td>
		</tr>
		<tr>
			<td><input type="submit" value="Upload" /></td>
		</tr>
	</table>
	<br/>
</form:form>
</body>
</html>
