package com.ramos.interfaces;

import java.io.File;
import java.util.List;

import com.ramos.models.Usuario;

public interface UsuarioInterface {
	
	List<Usuario> allUsuarios();
	Usuario findUsuariobyId(String id);
	String crearUsuario(File imagen,String password,String username,String nombres,String apellidos,String correo,String tipo,String telefono,String estado);

}
