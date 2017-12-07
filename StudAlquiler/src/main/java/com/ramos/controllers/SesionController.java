package com.ramos.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramos.models.UserLog;
import com.ramos.models.Usuario;
import com.ramos.services.UsuarioImpl;
import com.ramos.utils.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Controller
public class SesionController {
	@Autowired
	private UsuarioImpl usuarioImpl;
	
	@GetMapping("/sesion")
	public String sesion(HttpSession session) {
		Usuario usuario= (Usuario) session.getAttribute("usuario");
		
		if(usuario!=null) {
			if(usuario.getTipo().equals("admin")) {
				
				return "redirect:/admin/home";
			}else {
				return "redirect:/arren/home";
			}
		}else {
			return "sesion";
			
		}

		
	}
	@PostMapping("/sesion")
	public String ingresar(@ModelAttribute("SpringWeb") UserLog userlog,
						   HttpSession session,
						   Model model) {
		
		
		ClientResponse response = usuarioImpl.login(userlog) ;
			
			if(response.getStatus()==200) {
				Usuario usuario= response.getEntity(Usuario.class);
				session.setAttribute("usuario", usuario);
				if(usuario.getEstado().equals("Habilitado")) {
					if(usuario.getTipo().equals("admin")) {
						
						return "redirect:/admin/home";
						
					}else {
						return "redirect:/arren/home";
					}
				}else {
					return "redirect:sesion";
				}
				
				
			}else if(response.getStatus()==401){
				model.addAttribute("error", "Credenciales incorrectos");
				return "sesion";
			}else {
				return "redirect:sesion";
			}
		
	}
}
