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
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
   
   <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">Welcome to Your Reading Log, ${user.userName}</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link " href="/">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/dashboard">Dashboard</a>
        </li>
          <li class="nav-item">
          <a class="nav-link" href="/logout" class="txt-danger">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<p>Welcome to the dashboard. There's nothing to see here yet.</p>

<div class="container">
<div class="row">
<div class="col">
<p>Sort by: <button>tag</button> <button>tag</button>  <button>tag</button>  <button>tag</button></p>
</div>
</div>
<div class="row">
<div class="col">
<a href="/readings/new">Add New Reading Log</a>
</div>
<div class="col">
<a href="#">What Other Users Are Reading</a>
</div>
</div>
<div class="row">
<div class="col">

<div class="card" style="width:100%;">
  <div class="card-body">
    <h5 class="card-title">Reading Title</h5>
    <h6 class="card-subtitle mb-2 text-muted">URL</h6>
    <p class="card-text">User comment about the reading.</p>
    <p class="card-footer">tags: tag, tag, tag</p>

  </div>
</div>


</div>
</div>
</div>
   