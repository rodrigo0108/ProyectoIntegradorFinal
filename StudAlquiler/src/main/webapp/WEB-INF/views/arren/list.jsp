	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html lang="en">
	<head>
		<meta charset="utf-8" />
		<link rel="icon" type="image/png" sizes="96x96" href="<c:url value="/resources/img/logo.png" />">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
		<title>StudAlquiler</title>
		<script>
		function eliminarInmueble(id){
		    var doIt=confirm('�Estas seguro que deseas eliminar el inmueble?');
		  if(doIt){
			location.href ="inmueble/"+id+"/borrar";
		    }
		  else{
		
		    }
		}
		</script>
		<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	    <meta name="viewport" content="width=device-width" />
	
	
	  <!-- Bootstrap core CSS     -->
	  	<link href="<c:url value="/resources/css/bootstrap3.min.css" />" rel="stylesheet">
	
	    <!-- Animation library for notifications   -->
	    <link href="<c:url value="/resources/css/animate.min.css" />" rel="stylesheet">
	
	    <!--  Paper Dashboard core CSS    -->
	    <link href="<c:url value="/resources/css/paper-dashboard.css" />" rel="stylesheet">
	
	    <!--  CSS for Demo Purpose, don't include it in your project     -->
	    <link href="<c:url value="/resources/css/demo.css" />" rel="stylesheet">
	
	
	    <!--  Fonts and icons     -->
	    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
	    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
	    <link href="<c:url value="/resources/css/themify-icons.css" />" rel="stylesheet">
	
	</head>
	<body>
	
	<div class="wrapper">
		<div class="sidebar" data-background-color="white" data-active-color="danger">
	
	    <!--
			Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
			Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
		-->
	
	    	<div class="sidebar-wrapper">
	            <div class="logo">
	                <a class="simple-text">
	                    StudAlquiler
	                </a>
	            </div>
	
	            <ul class="nav">
	                <li>
	                    <a href="home">
	                        <i class="ti-home"></i>
	                         <p>Publicar</p>
	                    </a>
	                </li>
	                <li>
	                    <a href="perfil">
	                        <i class="ti-user"></i>
	                        <p>Perfil</p>
	                    </a>
	                </li>
	                <li class="active">
	                    <a href="list">
	                        <i class="ti-bookmark-alt"></i>
	                        <p>Mis publicaciones</p>
	                    </a>
	                </li>
	                
	            </ul>
	    	</div>
	    </div>
	
	    <div class="main-panel">
			<nav class="navbar navbar-default">
	            <div class="container-fluid">
	                <div class="navbar-header">
	                    <button type="button" class="navbar-toggle">
	                        <span class="sr-only">Toggle navigation</span>
	                        <span class="icon-bar bar1"></span>
	                        <span class="icon-bar bar2"></span>
	                        <span class="icon-bar bar3"></span>
	                    </button>
	                    <a class="navbar-brand" href="#">Mis publicaciones</a>
	                </div>
	                <div class="collapse navbar-collapse">
	                    <ul class="nav navbar-nav navbar-right">
	                         <li>
	                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="margin-right:20px">
	                                <i class="ti-github"></i>
									<p>${usuario.nombres}</p>
	                            </a>
	                        </li>
	                        
							<li>
	                            <a href="<%=request.getContextPath()%>/logout">
									<i class="ti-power-off"></i>
									<p>Cerrar sesi�n</p>
	                            </a>
	                        </li>
	                    </ul>
	
	                </div>
	            </div>
	        </nav>
	
	
	        <div class="content">
				
	            <div class="container-fluid">
					
	                <div class="row">
					
						
						 <div class="col-md-12">
	                        <div class="card">
	                            <div class="header">
	                                <h4 class="title">Publicaciones</h4>
	                                <p class="category">Aqui puedes modificar tus publicaciones</p>
	                            </div>
	                            <div class="content table-responsive table-full-width">
	                                <table class="table table-striped">
	                                    <thead>
	                                        <th>ID</th>
	                                    	<th>Tipo</th>
	                                    	<th>Direcci�n</th>
	                                    	<th>Distrito</th>
	                                    	<th>Departamento</th>
	                                    	<th>Precio</th>
	                                    	<th>N�mero de dormitorios</th>
	                                    	<th>N�mero de ba�os</th>
	                                    	<th>�rea total</th>
	                                    	<th>Estado</th>
	                                    	<th>Descripci�n</th>
	                                    	<th>Imagen</th>
	                                    	
	                                    </thead>
	                                    <tbody>
	                                    <c:forEach var="dep" items="${inmuebles}">
											<tr>
												<td>${dep.id}</td>
												<td>${dep.tipo}</td>
												<td>${dep.direccion}</td>
												<td>${dep.distrito}</td>
												<td>${dep.departamento}</td>
												<td>${dep.precio}</td>
												<td>${dep.num_dormitorios}</td>
												<td>${dep.num_banios}</td>
												<td>${dep.area_total}</td>
												<td>${dep.estado}</td>
												<td>${dep.descripcion}</td>
												<td>${dep.imagen}</td>
												<td><a	href="editar_inmueble/${dep.id}"
															class="btn btn-info btn-xs"> <i
															class="ti-pencil"></i>Editar
												</a></td>
												<td>
												<button class="btn btn-outline-danger btn-xs" type="reset" name="delete" onclick="eliminarInmueble(${dep.id});" >
    											<i class="ti-trash"></i> Eliminar
												</button>									
													</td>
												</tr>
												</c:forEach>
	                                       
	                                    </tbody>
	                                </table>
	
	                            </div>
	                        </div>
	                        
	                    </div>
	
						</div>
						
					
	                </div>
	            </div>
	        </div>
	
	
	        <footer class="footer">
	            <div class="container-fluid">
	                
					<div class="copyright pull-right">
	                    &copy; <script>document.write(new Date().getFullYear())</script>, StudAlquiler <a href="">Creative Tim</a>
	                </div>
	            </div>
	        </footer>
	
	    </div>
	</div>
	
	
	</body>
	
	       <!--   Core JS Files   -->
	    
	    <script src="<c:url value="/resources/js/jquery-1.10.2.js" />"></script>
	    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	    <script src="<c:url value="/resources/js/paper-dashboard.js" />"></script>
	
	
	</html>
