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
    

    <div class="content">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                
                    <a class="navbar-brand" href="#">StudAlquiler</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                                                
						<li>
                            <a href="<%=request.getContextPath()%>/admin/list_users">
								<i class="ti-power-off"></i>
								<p>Atrás</p>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                
                <div class="row">

                    <div class="col-lg-12 col-md-5">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Editar Usuario</h4>
                                <p class="category">Complete los datos</p>
                            </div>
                            <div class="content">
                                <form method='post' action="${user.id}/actualizar">
								<br>
                                     <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Nombre de usuario</label>
													<input type="text" name="username" class="form-control border-input" placeholder="Nombre de usuario" value="${user.username}" required>
                                            </div>
                                        </div>
                                       <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Nombres</label>
													<input type="text" name="nombres" class="form-control border-input" placeholder="Nombres" value="${user.nombres}" required>
                                            </div>
                                        </div>
                                    </div>
									<div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Apellidos</label>
													<input type="text" name="apellidos" class="form-control border-input" placeholder="Apellidos" value="${user.apellidos}" required>
                                            </div>
                                        </div>
                                       <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Correo</label>
													<input type="email"name="correo" class="form-control border-input" placeholder="Correo" value="${user.correo}" required>
                                            </div>
                                        </div>
                                    </div>
									<div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Teléfono, formato: 9 caracteres numéricos</label>
													<input type="text"name="telefono" class="form-control border-input" placeholder="Correo" value="${user.telefono}" pattern="^\d{9}$" required>
                                            </div>
                                        </div>
                                    </div>
                                   

                                    <div class="text-center">
                                        <button type="submit" class="btn btn-info btn-fill btn-wd">Actualizar</button>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                                <div class="footer">
                                   
                                    <div class="stats">
                                        
                                    </div>
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