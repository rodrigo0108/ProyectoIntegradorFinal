package com.ramos.controllers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AdministradorController {
	
	@Autowired
	private CentroEducativoImpl centroEducativoImpl;
	
	@Autowired
	private InmuebleImpl inmuebleImpl;

	@Autowired
	private UsuarioImpl usuarioImpl;
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	
	public File convert(MultipartFile multipart) throws IllegalStateException, IOException 
	{
			Date date = new Date();
			File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+sdf.format(date)+multipart.getOriginalFilename());
		    multipart.transferTo(convFile);
		    return convFile;
	}
	
	@GetMapping("admin/home")
	public String admin_home(Model model,HttpSession httpSession) {
		
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		
		if(usuario!=null) {
			return "admin/home";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
	}
	
	// Inmuebles
	@GetMapping("admin/list_inmuebles")
	public String list_inmuebles(Model model,HttpSession httpSession) {
		
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		
		if(usuario!=null) {
			List<Inmueble> inmuebles =inmuebleImpl.allInmuebles();
			model.addAttribute("inmuebles", inmuebles);
			return "admin/list_inmuebles";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
	}
	
	@GetMapping("admin/editar_inmueble/{id}")
	public String editar_inmueble(@PathVariable String id,HttpSession httpSession,Model model,Model model2) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		if(usuario!=null) {
			Inmueble inmueble=inmuebleImpl.findInmueblebyId(id);
			List<CentroEducativo> centros_educativos =centroEducativoImpl.findAllCentros();
			model.addAttribute("inm",inmueble);
			model2.addAttribute("lista",centros_educativos);
			return "admin/editar_inmueble";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}
	
	@PostMapping("admin/editar_inmueble/{id}/actualizar")
	public String actualizar(@PathVariable String id,HttpSession httpSession,Model model,@ModelAttribute("inmueble") Inmueble inmueble) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		if(usuario!=null) {
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
			return "redirect:/admin/list_inmuebles";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}
	
	@GetMapping("admin/inmueble/{id}/borrar")
	public String borrar_inmueble(@PathVariable("id") String id,HttpSession httpSession) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		if(usuario!=null) {
			String respuesta_borrado=inmuebleImpl.destroyInmueble(id);
			return  "redirect:/admin/list_inmuebles";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}

	// Centros Educativos
	@GetMapping("admin/list_centros")
	public String list_centros(Model model,HttpSession httpSession) {
		
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		
		//if(usuario!=null) {
			List<CentroEducativo> centros_educativos =centroEducativoImpl.findAllCentros();
			model.addAttribute("centro_educativos", centros_educativos);
			return "admin/list_centros";
		/*}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}*/
	}
	
	@GetMapping("admin/crear_centro")
	public String crear_centro(Model model,HttpSession httpSession) {
		
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		
		if(usuario!=null) {
			
			return "admin/crear_centro";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
	}
	
	@PostMapping("admin/crear_centro")
	public String guardar_centro(Model model,HttpSession httpSession,
								@RequestParam(value = "logo_imagen")MultipartFile multipart_logo,
								@RequestParam(value = "foto_imagen")MultipartFile multipart_imagen,
								@ModelAttribute("centroeducativo") CentroEducativo centroEducativo
								) throws IllegalStateException, IOException {
		File logo=convert(multipart_logo);
		File imagen=convert(multipart_imagen);
		
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		
		if(usuario!=null) {
			String respuesta_creacion=centroEducativoImpl.crearCentro(centroEducativo.getTipo(), centroEducativo.getNombre(), centroEducativo.getDireccion(), logo, imagen);
		
			return "redirect:/admin/list_centros";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
	}
	
	@GetMapping("admin/editar_centro/{id}")
	public String editar_centro(@PathVariable String id,Model model,HttpSession httpSession) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		if(usuario!=null) {
			CentroEducativo centroEducativo=centroEducativoImpl.findCentrobyId(id);
			model.addAttribute("centro",centroEducativo);
			return "admin/editar_centro";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
	}
	
	@PostMapping("admin/editar_centro/{id}/actualizar")
	public String actualizar_centro(@PathVariable String id,HttpSession httpSession,Model model,@ModelAttribute("centroeducativo") CentroEducativo centro) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		if(usuario!=null) {
			Client client = Client.create();
			WebResource webResource = client.resource("https://proyecto-alquiler-api-kevinghanz.c9users.io/api/v1/centros_educativos/"+id);
			
			String envio = "{\"tipo\": \""+centro.getTipo() +"\", \"nombre\":\""+centro.getNombre()+"\",\"direccion\":\""+
					centro.getDireccion()+"\"}";
			ClientResponse response = webResource.accept("application/json").type("application/json").put(ClientResponse.class, envio);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
			}
			return "redirect:/admin/list_centros";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}
	
	@GetMapping("admin/centro/{id}/borrar")
	public String borrar_centro(@PathVariable("id") String id,HttpSession httpSession) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		if(usuario!=null) {
			String respuesta_borrado=centroEducativoImpl.destroyCentro(id);
			return  "redirect:/admin/list_centros";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}
	// Usuarios
	@GetMapping("admin/list_users")
	public String list_users(Model model,HttpSession httpSession) {
		
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		
		if(usuario!=null) {
			List<Usuario> usuarios= usuarioImpl.allUsuarios();
			model.addAttribute("usuarios", usuarios);
			return "admin/list_users";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
	}
	
	@GetMapping("admin/editar_user/{id}")
	public String editar_user(@PathVariable String id,Model model,HttpSession httpSession) {
		Usuario usuario= (Usuario) httpSession.getAttribute("usuario");
		if(usuario!=null) {
			Usuario user =usuarioImpl.findUsuariobyId(id);
			model.addAttribute("user",user);
			return "admin/editar_user";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
	}
	
	@PostMapping("admin/editar_user/{id}/actualizar")
	public String actualizar_user(@PathVariable String id,HttpSession httpSession,Model model,@ModelAttribute("usuario") Usuario usuario) {
		Usuario usuario2= (Usuario) httpSession.getAttribute("usuario");
		if(usuario2!=null) {
			Client client = Client.create();
			WebResource webResource = client.resource("https://proyecto-alquiler-api-kevinghanz.c9users.io/api/v1/usuarios/"+id);
			
			String envio = "{\"username\": \""+usuario.getUsername() +"\", \"nombres\":\""+usuario.getNombres()+"\",\"apellidos\":\""+
					usuario.getApellidos()+"\",\"correo\":\""+usuario.getCorreo()+"\",\"telefono\":\""+usuario.getTelefono()+"\"}";
			ClientResponse response = webResource.accept("application/json").type("application/json").put(ClientResponse.class, envio);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
			}
			return "redirect:/admin/list_users";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}
	@GetMapping("admin/usuario/{id}/eliminar")
	public String eliminarUser(@PathVariable("id") String id,HttpSession httpSession) {
		Usuario usuario2= (Usuario) httpSession.getAttribute("usuario");
		if(usuario2!=null) {
			String respuesta_borrado=usuarioImpl.destroyUsuario(id);
			
			return  "redirect:/admin/list_users";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}
	
	@GetMapping("admin/usuario/{id}/deshabilitar")
	public String deshabilitarUser(@PathVariable("id") String id,HttpSession httpSession) {
		Usuario usuario2= (Usuario) httpSession.getAttribute("usuario");
		if(usuario2!=null) {
			Client client = Client.create();
			WebResource webResource = client.resource("https://proyecto-alquiler-api-kevinghanz.c9users.io/api/v1/habilitar/"+id);
			
			String envio = "{\"estado\": \""+"Deshabilitado" +"\"}";
			ClientResponse response = webResource.accept("application/json").type("application/json").put(ClientResponse.class, envio);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
			}
			
			return  "redirect:/admin/list_users";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}
	
	@GetMapping("admin/usuario/{id}/habilitar")
	public String habilitarUser(@PathVariable("id") String id,HttpSession httpSession) {
		Usuario usuario2= (Usuario) httpSession.getAttribute("usuario");
		if(usuario2!=null) {
			Client client = Client.create();
			WebResource webResource = client.resource("https://proyecto-alquiler-api-kevinghanz.c9users.io/api/v1/habilitar/"+id);
			
			String envio = "{\"estado\": \""+"Habilitado" +"\"}";
			ClientResponse response = webResource.accept("application/json").type("application/json").put(ClientResponse.class, envio);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
			}
			
			return  "redirect:/admin/list_users";
		}else {
			//si no esta logueado redirige a sesion
			return "redirect:/sesion";
		}
		
	}
}
