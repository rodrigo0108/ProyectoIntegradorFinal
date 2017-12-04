package com.ramos.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ramos.models.CentroEducativo;
import com.ramos.models.UserLog;
import com.ramos.models.Usuario;
import com.ramos.services.CentroEducativoImpl;
import com.ramos.services.UsuarioImpl;

@Controller
public class IndexController {
	
	@Autowired
	private UsuarioImpl usuarioImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	public File convert(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	    File convFile = new File( multipart.getOriginalFilename());
	    multipart.transferTo(convFile);
	    return convFile;
	}
	
	@GetMapping("/")
	public String index() {

		return "index";
	}
	
	@GetMapping("/nosotros")
	public String nosotros() {

		return "nosotros";
	}
	
	@GetMapping("/registro")
	public String registro() {
		
		return "registro";
	}
	
	@PostMapping("/registro")
	public String registrarse(@RequestParam(value = "archivo")MultipartFile multipart_imagen,
			@RequestParam(value = "password") String password,@ModelAttribute("SpringWeb") Usuario usuario) throws IllegalStateException, IOException{
		File imagen=convert(multipart_imagen);
		String tipo="arrendador";
		String estado="Habilitado";
		String mensaje=usuarioImpl.crearUsuario(imagen, password, usuario.getUsername(), usuario.getNombres(), usuario.getApellidos(), usuario.getCorreo(), tipo,usuario.getTelefono(), estado);
		System.out.println(mensaje);
		
		return "redirect:/sesion";
	}
}
