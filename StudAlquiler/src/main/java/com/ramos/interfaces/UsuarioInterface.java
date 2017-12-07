package com.ramos.interfaces;

import java.io.File;
import java.util.List;

import com.ramos.models.UserLog;
import com.ramos.models.Usuario;
import com.sun.jersey.api.client.ClientResponse;

public interface UsuarioInterface {
	
	List<Usuario> allUsuarios();
	Usuario findUsuariobyId(String id);
	String crearUsuario(File imagen,String password,String username,String nombres,String apellidos,String correo,String tipo,String telefono,String estado);
	String destroyUsuario(String id);
	ClientResponse login(UserLog userlog);
}
