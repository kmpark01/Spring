<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<jsp:include page="layout/header.jsp"/> 
	<h1>
		Hello world!  
	</h1>

	<P>  The time on the server is ${serverTime}. </P>
	<jsp:include page="layout/footer.jsp"/> 
</body>
</html>
