package com.ramos.interfaces;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ramos.models.Inmueble;

public interface InmuebleInterface {
	
	List<Inmueble> allInmuebles();
	List<Inmueble> findInmueblesbyCentro(String id);
	Inmueble findInmueblebyId(String id);
	List<Inmueble> findInmueblesbyUsuario(String id);
	List<Inmueble> findInmueblesbyDistrito(String distrito,String centro_educativo_id);
	String destroyInmueble(String id);
	String crearInmueble(File imagen,String tipo,String direccion,String distrito,String departamento,String longitud,String latitud,String precio,String num_dormitorios,String num_banios,String area_total,String estado,String descripcion,String usuario_id,String centro_educativo_id);
}
