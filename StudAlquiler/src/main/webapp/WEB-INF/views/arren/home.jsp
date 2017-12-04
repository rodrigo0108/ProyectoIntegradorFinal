<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
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
	
	<script src="http://maps.google.com/maps/api/js?key=AIzaSyAkvodR5k-I7v-3Qv4hsIS03HxNo7APE_M&libraries=places" type="text/javascript">
	</script>
	<script>
		var map;
		function initialize() {
  
			var latlng = new google.maps.LatLng(-12.044071678914312,-76.95056819919046);
			var map = new google.maps.Map(document.getElementById('map-canvas'), {
	    		center: latlng,
	   			zoom: 15,
	    		mapTypeId: google.maps.MapTypeId.ROADMAP
		});
			var marker = new google.maps.Marker({
	    		position: latlng,
	    		map: map,
	    		title: 'Set lat/lon values for this property',
	    		draggable: true
		});
			
			if(document.getElementById("latbox").value==""){
				document.getElementById("latbox").value = -12.044071678914312;
				document.getElementById("lngbox").value = -76.95056819919046;
			}
	
		google.maps.event.addListener(marker, 'dragend', function (event) {
	    document.getElementById("latbox").value = this.getPosition().lat();
	    document.getElementById("lngbox").value = this.getPosition().lng();
		});
	
		}
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
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
                <li class="active">
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
                <li>
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
                    <a class="navbar-brand" href="#">Bienvenido</a>
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
								<p>Cerrar sesión</p>
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
                                <h4 class="title">Publica tu alquiler</h4>
                                <p class="category">Completa los datos</p>
                            </div>
                            <div class="content">
                                <form method='post' enctype="multipart/form-data" >
								<br>
                                     <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Tipo de alquiler</label>
													<select class="form-control border-input" name='tipo'>
														<option value="Departamento">Departamento</option>
														<option value="Casa">Casa</option>
														<option value="Piso">Piso</option>
														<option value="Finca">Finca</option>
													</select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Centro de estudio cercano</label>
                                                
                                                <select class="form-control border-input" name='centro_educativo_id'>
    												<c:forEach var="centro" items="${lista}">
     													<option value="${centro.id}">${centro.nombre}</option>
    												</c:forEach>
												</select>  
                                            </div>
                                        </div>
                                    </div>
									<div class="row">
										<div class="col-md-12">
                                            <div class="form-group">
                                                <label>Dirección</label>
                                                <input type="text" name="direccion" class="form-control border-input" placeholder="Dirección" required>
                                            </div>
                                        </div>
									</div>
									<div class="row">
										<div class="col-md-6">
												<label>Distrito</label>
												<select class="form-control border-input" name='distrito'>
														<option value="Santa Anita">Santa Anita</option>
														<option value="Ate">Ate</option>
														<option value="Cercado de Lima">Cercado de Lima</option>
														<option value="Barranco">Barranco</option>
														<option value="Comas">Comas</option>
														<option value="Chorrillos">Chorrillos</option>
														<option value="El Agustino">El Agustino</option>
														<option value="La Molina">La Molina</option>
														<option value="La Victoria">La Victoria</option>
														<option value="Miraflores">Miraflores</option>
														<option value="Lince">Lince</option>
														<option value="Rimac">Rimac</option>
														<option value="San Borja">San Borja</option>
														<option value="San Luis">San Luis</option>
														<option value="San Isidro">San Isidro</option>
														<option value="San Miguel">San Miguel</option>
														<option value="Pueblo Libre">Pueblo Libre</option>
												</select>
                                                
										</div>
										<div class="col-md-6">
												<label>Departamento</label>
												<select class="form-control border-input" name='departamento' required>
														<option value="Lima">Lima</option>
												</select>
                                                
										</div>
									</div>
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
                                                <label>Precio(S/.)</label>
												<input type="number" name="precio" min="50" max="5000" class="form-control border-input" placeholder="precio" required>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group">
                                                <label>Número de dormitorios</label>
												<input type="number" name="num_dormitorios" min="0" max="15" class="form-control border-input" placeholder="número de dormitorios" required>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
                                                <label>Número de baños</label>
												<input type="number" name="num_banios" min="0" max="5" class="form-control border-input" placeholder="número de baños" required>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group">
                                                <label>Área (m2)</label>
												<input type="number" name="area_total" min="150" max="500" class="form-control border-input" placeholder="área" required>
											</div>
										</div>
										</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
    											<label>Imagen</label>
      											<input  class="form-control border-input" type="file" name="foto" required>
    											</div>
    									 </div>
  									</div>
									
									
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Descripción</label>
                                                <textarea name="descripcion" rows="5" class="form-control border-input" placeholder="Usted puede describir su inmueble" required></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                            <label>Mueva el marcador al lugar donde va alquilar</label>
                                                <div id="map-canvas" style="height:500px; width:100%"></div>
                                            </div>
                                        </div>
                                    </div>
                                    
									 <div style="display: none;">
 									<input  name="latitud" id="latbox" ></input>
 									<br>
  									<input name="longitud" id="lngbox"></input>
 									</div>
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-info btn-fill btn-wd">Crear</button>
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