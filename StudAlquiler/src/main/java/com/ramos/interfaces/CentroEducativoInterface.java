package com.ramos.interfaces;

import java.io.File;
import java.util.List;

import com.ramos.models.CentroEducativo;

public interface CentroEducativoInterface {
	
	List<CentroEducativo> findAllCentros();
	String destroyCentro(String id);
	String crearCentro(String tipo, String nombre, String direccion, File logo,File imagen);
	CentroEducativo findCentrobyId(String id);
	

}
