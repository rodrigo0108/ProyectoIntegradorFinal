package com.ramos.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ramos.models.CentroEducativo;
import com.ramos.models.Inmueble;
import com.ramos.models.Usuario;
import com.ramos.services.CentroEducativoImpl;
import com.ramos.services.InmuebleImpl;
import com.ramos.services.UsuarioImpl;

@Controller
public class EmpiezaController {
	
	@Autowired
	private CentroEducativoImpl centroEducativoImpl;
	
	@Autowired
	private InmuebleImpl inmuebleImpl;
	
	@Autowired
	private UsuarioImpl usuarioImpl;
	
	@GetMapping("/empieza")
	public String empieza(Model model) {
		
		List<CentroEducativo> centros_educativos=centroEducativoImpl.findAllCentros();
		model.addAttribute("lista", centros_educativos);
		return "empieza";
		
	}
	
	@GetMapping("/vistas/vista/{id}")
	public String vista(@PathVariable int id,Model model) {
		String id_con=String.valueOf(id);
		List<Inmueble> inmuebles=inmuebleImpl.findInmueblesbyCentro(id_con);		
		model.addAttribute("lista2", inmuebles);
		return "vistas/vista";
	}
	
	@RequestMapping(value="/vistas/vista/{id}", method=RequestMethod.GET, params="distrito")
	public String filtro(@PathVariable int id,@RequestParam("distrito")String distrito,Model model) throws UnsupportedEncodingException {
		distrito = URLEncoder.encode(distrito, "UTF-8");
		List<Inmueble> inmuebles= inmuebleImpl.findInmueblesbyDistrito(distrito, String.valueOf(id));
		model.addAttribute("lista2", inmuebles);
		
		return "vistas/vista";
		
	}
	@GetMapping("/vistas/vista2/{id}")
	public String vista2(@PathVariable int id,Model model,Model model2) {
		String id_con=String.valueOf(id);
		Inmueble inmueble=inmuebleImpl.findInmueblebyId(id_con);
		String id_usuario=String.valueOf(inmueble.getUsuario_id());
		Usuario usuario=usuarioImpl.findUsuariobyId(id_usuario);

		model.addAttribute("inmueble",inmueble);
		model2.addAttribute("usuario",usuario);
		return "vistas/vista2";
	}
}
