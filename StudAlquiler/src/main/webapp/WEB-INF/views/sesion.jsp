<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>

    <meta charset="utf-8">
    <link rel="icon" type="image/png" sizes="96x96" href="<c:url value="/resources/img/logo.png" />">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>StudAlquiler</title>

     <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/clean-blog.min.css" />" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand" style="color: black" href="">StudAlquiler</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
       
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" style="color: black" href="<%=request.getContextPath()%>">Inicio</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" style="color: black" href="nosotros">Sobre nosotros</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" style="color: black" href="empieza">Empieza tu b�squeda</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" style="color: black" href="sesion">Inicio de sesi�n</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

     <!-- Page Header -->
    <header class="masthead">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
          
          </div>
        </div>
      </div>
    </header>
				
	<br>
	<br>
	<br>
    <!-- Main Content -->
				
    <div class="container">
      <div class="row">
		      
         <div class="col-lg-8 col-md-10 mx-auto">
			<div class="list-inline text-center">
              <h2>Inicio de sesi�n</h2>
            </div>
         
		 <br>
		 <br>
          <form  method='post'  >
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>Nombre de usuario</label>
                <input type="text" class="form-control" placeholder="Nombre de usuario" name="username" required>
                <p class="help-block text-danger"></p>
              </div>
            </div>
			<br>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>Contrase�a</label>
                <input type="password" class="form-control" placeholder="Contrase�a"name="password" required>
                <p class="help-block text-danger"></p>
              </div>
            </div>
			<br>
            
            <div class="list-inline text-center" style="color: red">
              <p>${error}</p>
            </div>
			<br>
            <div class="list-inline text-center">
             <button type="submit" class="btn btn-secondary" >Ingresar</button>
            </div>

          </form>
          <br>
			<div class="list-inline text-center">
              <a href="registro">�Desea registrarse?</a>
            </div>
        </div>
      </div>
    </div>
	<br>

    <hr>
	<!-- Footer -->
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <ul class="list-inline text-center">
              <li class="list-inline-item">
                <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
            </ul>
            <p class="copyright text-muted">Copyright &copy; StudAlquiler 2017</p>
          </div>
        </div>
      </div>
    </footer>
    <!-- Bootstrap core JavaScript -->
    <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.bundle.min.js" />"></script>

    <!-- Custom scripts for this template -->
    <script src="<c:url value="/resources/js/clean-blog.min.js" />"></script>

  </body>

</html>
