package com.ramos.services;

import java.io.File;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ramos.interfaces.UsuarioInterface;
import com.ramos.models.Usuario;
import com.ramos.utils.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

@Service
public class UsuarioImpl implements UsuarioInterface{
	
	@Override
	public List<Usuario> allUsuarios() {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResourceGet = client.resource(Utils.URL_BASE+"/api/v1/usuarios");
		List<Usuario> usuarios = webResourceGet.get(new GenericType<List<Usuario>>() {});
		return usuarios;
	}
	
	@Override
	public Usuario findUsuariobyId(String id) {
		ClientConfig clientConfig2 = new DefaultClientConfig();
		clientConfig2.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client2 = Client.create(clientConfig2);
		WebResource webResourceGet2 = client2.resource(Utils.URL_BASE+"/api/v1/usuarios/"+id);
		ClientResponse response2  = webResourceGet2.accept("application/json").get(ClientResponse.class);
		Usuario usuario= response2.getEntity(Usuario.class);
		return usuario;
	}

	@Override
	public String crearUsuario(File imagen, String password, String username, String nombres, String apellidos,
			String correo, String tipo, String telefono, String estado) {
		
		try {

			ClientConfig clientConfig = new DefaultClientConfig();
		
				Client client = Client.create(clientConfig);
				WebResource webResource = client.resource(Utils.URL_BASE+"/api/v1/usuarios");
				FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("imagen",imagen,MediaType.APPLICATION_OCTET_STREAM_TYPE);
				fileDataBodyPart.setContentDisposition(FormDataContentDisposition.name("imagen").fileName(imagen.getName()).build());
				
				final MultiPart multiPart = new FormDataMultiPart()
		        		.field("password", password, MediaType.TEXT_PLAIN_TYPE)
		                .field("username", username, MediaType.TEXT_PLAIN_TYPE)
		                .field("nombres", nombres, MediaType.TEXT_PLAIN_TYPE)
		                .field("apellidos", apellidos, MediaType.TEXT_PLAIN_TYPE)
		                .field("correo", correo, MediaType.TEXT_PLAIN_TYPE)
		                .field("tipo", tipo, MediaType.TEXT_PLAIN_TYPE)
		                .field("telefono",telefono, MediaType.TEXT_PLAIN_TYPE)
		                .field("estado", estado, MediaType.TEXT_PLAIN_TYPE)
		                .bodyPart(fileDataBodyPart);
		        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

		        ClientResponse response = webResource.type("multipart/form-data").post(ClientResponse.class,multiPart);
		        
		        client.destroy();
			return response.toString();
		}catch (Exception e) {
			return e.getMessage();
		}
		
	}



	

}
