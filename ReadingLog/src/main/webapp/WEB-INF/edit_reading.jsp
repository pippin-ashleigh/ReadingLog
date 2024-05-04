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

<p class="error">${error}</p>
<div class="text-center">
<form:form action="/readings/${reading.id}/edit" method="POST" modelAttribute="reading">
<input type="hidden" name="_method" value="put">  
		<div class="form-control">
		<form:label path="title">Title:</form:label>
		<form:input type="text" path="title"/>
		<form:errors path="title" class="text-danger"/>
		</div>
		<div class="form-control">
		<form:label path="url">URL:</form:label>
		<form:input type="text" path="url"/>
		<form:errors path="url" class="text-danger"/>
		</div>
		<div class="form-control">
		<form:label for="comment" path="comment">Comments:</form:label>
		<form:textarea rows="4" path="comment"/>
		<form:errors path="comment" class="text-danger"/>
		</div>
		
		<div>
			<label for="listOfTags">Tags:</label>
			<input class="input" name="listOfTags" id="listOfTags"/>
		</div>
		
		
		
		<div class="form-control">
		<form:label path="readDate">Date Read:</form:label>
		<form:input type="date" path="readDate"/>
		<form:errors path="readDate" class="text-danger"/>
		</div>
		<a class="btn btn-danger" href="/dashboard">Cancel</a>
		<input type="submit" value="Save">
	</form:form>
 </div>
