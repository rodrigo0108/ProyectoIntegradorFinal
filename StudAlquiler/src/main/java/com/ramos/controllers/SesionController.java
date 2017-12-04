package com.ramos.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramos.models.UserLog;
import com.ramos.models.Usuario;
import com.ramos.utils.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Controller
public class SesionController {
	
	@GetMapping("/sesion")
	public String sesion() {

		return "sesion";
	}
	@PostMapping("/sesion")
	public String ingresar(@ModelAttribute("SpringWeb") UserLog userlog,
						   HttpSession session,
						   Model model) {
		
		ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResource = client.resource(Utils.URL_BASE+"/api/v1/login");
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,userlog);
			
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
