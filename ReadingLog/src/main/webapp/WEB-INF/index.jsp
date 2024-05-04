<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login or Register</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
   
   <nav class="navbar navbar-expand-lg bg-body-tertiary navbar-dark bg-danger"">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">Welcome to Your Reading Log</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/dashboard">Dashboard</a>
        </li>
          <li class="nav-item">
          <a class="nav-link" href="/logout" class="txt-danger">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
   
   <div class="container">
   <div class="row">
   <div class="col">
   <form:form class="p-4" action="/login" method="POST" modelAttribute="loginUser">
   <h2>Login</h2>
   		<div class="mb-3">
   			<form:label path="email" for="" class="form-label">Email address</form:label>
   			<form:input path="email" type="email" class="form-control" id="" aria-describedby=""/>
   			<form:errors path="email" class="text-danger"/>
   		</div>
   		   		<div class="mb-3">
   			<form:label path="password" for="" class="form-label">Password</form:label>
   			<form:input path="password" type="password" class="form-control" id="" aria-describedby=""/>
   			<form:errors path="password" class="text-danger"/>
   		</div>
   	
   		<button type="submit" class="btn btn-primary">Login</button>
 	</form:form>
 	</div>
 	<div class="col">
 	   <form:form class="p-4" action="/register" method="POST" modelAttribute="registerUser">
   <h2>Register</h2>
   		<div class="mb-3">
   			<form:label path="userName" for="" class="form-label">Username</form:label>
   			<form:input path="userName" type="text" class="form-control" id="" aria-describedby=""/>
   			<form:errors path="userName" class="text-danger"/>
   		</div>
   		<div class="mb-3">
   			<form:label path="email" for="" class="form-label">Email address</form:label>
   			<form:input path="email" type="text" class="form-control" id="" aria-describedby=""/>
   			<form:errors path="email" class="text-danger"/>
   		</div>
   		   		<div class="mb-3">
   			<form:label path="password" for="" class="form-label">Password</form:label>
   			<form:input path="password" type="password" class="form-control" id="" aria-describedby=""/>
   			<form:errors path="password" class="text-danger"/>
   		</div>
   		
   		   		   		<div class="mb-3">
   			<form:label path="confirm" for="" class="form-label">Confirm Password</form:label>
   			<form:input path="confirm" type="password" class="form-control" id="" aria-describedby=""/>
   			<form:errors path="confirm" class="text-danger"/>
   		</div>
   	
   		<button type="submit" class="btn btn-secondary">Register</button>
 	</form:form>
   </div>
   </div>
   </div>
   
</body>
</html>