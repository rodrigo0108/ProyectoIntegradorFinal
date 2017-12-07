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
	<style>
	::-webkit-input-placeholder {
   font-size: 80%;
}
	</style>
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
      <div class="col-md-12">
		      <form id="form" name="form" method='post'  enctype="multipart/form-data">
								<br>
                                 
									<div class="row">
										<div class="col-md-6">
                                             <div class="floating-label-form-group controls" ">
                									<label style="font-size: 80%;">Nombres</label>
                                                <input type="text" name="nombres" class="form-control border-input" placeholder="Inserte sus nombres o su nombre" required>
              									</div>
                                                
                                        </div>
                                        <div class="col-md-6">
                                             <div class="floating-label-form-group controls">
                                                <label style="font-size: 80%;">Apellidos</label>
                                                <input type="text" name="apellidos" class="form-control border-input" placeholder="Inserte sus apellidos" required>
                                            </div>
                                        </div>
									</div>
									</br>
									<div class="row">
										<div class="col-md-4">
											<div class="floating-label-form-group controls">
                                               <label style="font-size: 80%;">Nombre de usuario</label>
                                                <input type="text" name="username" class="form-control border-input" placeholder="Inserte su nombre de usuario" required>
                                            </div>
										</div>
										
										<div class="col-md-4">
											<div class="floating-label-form-group controls">
                                               <label style="font-size: 80%;">Correo</label>
                                                <input type="email" name="correo" class="form-control border-input" placeholder="Inserte su correo" required>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="floating-label-form-group controls">
                                               <label style="font-size: 60%;" for="telefono">Teléfono, formato: 9 caracteres numéricos</label>
                                                <input type="text" name="telefono" class="form-control border-input" placeholder="Inserte su telefono" pattern="^\d{9}$" required>
                                            </div>
										</div>
								</div>
								</br>
								<div class="row">
										<div class="col-md-6">
											<div class="floating-label-form-group controls">
                                               <label>Contraseña</label>
                                                <input type="password" name="password" id="password" class="form-control border-input" placeholder="Inserte su contraseña" required>
                                            </div>
										</div>
										<div class="col-md-6">
											<div class="floating-label-form-group controls" style="border:none;">
                                               <label>Confirmar contraseña</label>
                                                <input type="password" name="confirm_password" id="confirm_password" class="form-control border-input" placeholder="Inserte su contraseña" required>
                                            </div>
										</div>
										
								</div>
								</br>
								<div class="row">
										<div class="col-md-12">
											<div class="floating-label-form-group controls">
    											<label>Imagen</label>
      											<input id="file" class="form-control border-input" type="file" name="archivo" style="font-size: 90%;" max-size=50000 required>
    											</div>
    									 </div>
  								</div>
								</br>
								</br>
                                    <div class="text-center">
                                        <button type="submit"  name="submit" class="btn btn-info btn-fill btn-wd" >Registrarse</button>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
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
    
	<script>
    $(document).ready(function(){
    
        var $submitBtn = $("#form button[type='submit']");
        var $passwordBox = $("#password");
        var $confirmBox = $("#confirm_password");
        var $errorMsg =  $('</br><span style="color: red;" id="error_msg">Las contraseñas no coinciden</span>');  
        
        
        $submitBtn.removeAttr("disabled");

        function checkMatchingPasswords(){
            if($confirmBox.val() != "" && $passwordBox.val != ""){
                if( $confirmBox.val() != $passwordBox.val() ){
                    $submitBtn.attr("disabled", "disabled");
                    $errorMsg.insertAfter($confirmBox);
                }
            }
        }
   
        function resetPasswordError(){
            $submitBtn.removeAttr("disabled");
            var $errorCont = $("#error_msg");
            if($errorCont.length > 0){
                $errorCont.remove();
            }  
        }


        $("#confirm_password, #password, #file")
             .on("keydown", function(e){
                if(e.keyCode == 13 || e.keyCode == 9) {
                    checkMatchingPasswords();
  
                }
                
             })
             .on("blur", function(){                    
                checkMatchingPasswords();
                
            })
            .on("focus", function(){
                resetPasswordError();
            })
            

    });
  </script>
  
 
  </body>

</html>
