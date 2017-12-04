package com.ramos.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ramos.models.CentroEducativo;
import com.ramos.models.Inmueble;
import com.ramos.models.Usuario;
import com.ramos.services.CentroEducativoImpl;
import com.ramos.services.InmuebleImpl;
import com.ramos.services.UsuarioImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
public class ArrendadorController {
	
	@Autowired
	private CentroEducativoImpl centroEducativoImpl;
	
	@Autowired
	private InmuebleImpl inmuebleImpl;

	
	public File convert(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	    File convFile = new File( multipart.getOriginalFilename());
	    multipart.transferTo(convFile);
	    return convFile;
	}
	
	@GetMapping("arren/home")
	public String arren_home(Model model,HttpSession httpSession) {
		
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		
		if(usuario!=null) {
			
			List<CentroEducativo> centros_educativos=centroEducativoImpl.findAllCentros();
			model.addAttribute("lista", centros_educativos);
			return "arren/home";
			
		}else {
			return "redirect:/sesion";
		}
	}
	
	@PostMapping("arren/home")
	public String crear_inmueble(@RequestParam(value = "foto")MultipartFile multipart_imagen,
			HttpSession httpSession,@ModelAttribute("inmueble") Inmueble inmueble) throws IllegalStateException, IOException {
		
		File imagen=convert(multipart_imagen);
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		String id=String.valueOf(usuario.getId());
		String estado="habilitado";
		
		String respuesta_crear=inmuebleImpl.crearInmueble(imagen, inmueble.getTipo(), inmueble.getDireccion(), inmueble.getDistrito(), inmueble.getDepartamento(), String.valueOf(inmueble.getLongitud()), String.valueOf(inmueble.getLatitud()),String.valueOf(inmueble.getPrecio()), String.valueOf(inmueble.getNum_dormitorios()), String.valueOf(inmueble.getNum_banios()), String.valueOf(inmueble.getArea_total()), estado, inmueble.getDescripcion(), id,String.valueOf(inmueble.getCentro_educativo_id()));
		System.out.println(respuesta_crear);
		return "redirect:/arren/list";
	}
	
	@GetMapping("arren/inmueble/{id}/borrar")
	public String borrar_inmueble(@PathVariable("id") String id) {
		String respuesta_borrado=inmuebleImpl.destroyInmueble(id);
		System.out.println(respuesta_borrado);
		return  "redirect:/arren/list";
	}

	
	@GetMapping("arren/perfil")  
	public String perfil_arren(HttpSession httpSession) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		if(usuario!=null) {
			return "arren/perfil";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}

	}
	
	@GetMapping("arren/list")  
	public String list_arren(HttpSession httpSession,Model model) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");

		if(usuario!=null) {
			String id=String.valueOf(usuario.getId());
			List<Inmueble> inmuebles =inmuebleImpl.findInmueblesbyUsuario(id);
			model.addAttribute("inmuebles", inmuebles);
			return "arren/list";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}

	}
	
	@GetMapping("arren/editar_inmueble/{id}")
	public String editar_inmueble(@PathVariable String id,HttpSession httpSession,Model model,Model model2) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		if(usuario!=null) {
			Inmueble inmueble=inmuebleImpl.findInmueblebyId(id);
			List<CentroEducativo> centros_educativos =centroEducativoImpl.findAllCentros();
			model.addAttribute("inm",inmueble);
			model2.addAttribute("lista",centros_educativos);
			return "arren/editar_inmueble";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}
	@PostMapping("arren/editar_inmueble/{id}/actualizar")
	public String actualizar(@PathVariable String id,HttpSession httpSession,Model model,@ModelAttribute("inmueble") Inmueble inmueble) {
		Client client = Client.create();
		WebResource webResource = client.resource("https://proyecto-alquiler-api-kevinghanz.c9users.io/api/v1/inmuebles/"+id);
		
		String envio = "{\"tipo\": \""+inmueble.getTipo() +"\", \"direccion\":\""+inmueble.getDireccion()+"\",\"distrito\":\""+
				inmueble.getDistrito()+"\",\"departamento\":\""+inmueble.getDepartamento()+"\",\"longitud\":\""+inmueble.getLongitud()+
				"\",\"latitud\":\""+inmueble.getLatitud()+"\",\"precio\":\""+inmueble.getPrecio()+"\",\"num_dormitorios\":\""
				+inmueble.getNum_dormitorios()+"\",\"num_banios\":\""+inmueble.getNum_banios()+"\",\"area_total\":\""+inmueble.getArea_total()+
				"\",\"descripcion\":\""+inmueble.getDescripcion()+"\",\"centro_educativo_id\":\""+inmueble.getCentro_educativo_id()+"\"}";
		ClientResponse response = webResource.accept("application/json").type("application/json").put(ClientResponse.class, envio);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}
		return "redirect:/arren/list";
	}
	@GetMapping("/logout")  
    public String logout(HttpSession httpSession) {
    	httpSession.removeAttribute("usuario");
        return "redirect:sesion";
    }
	
}
