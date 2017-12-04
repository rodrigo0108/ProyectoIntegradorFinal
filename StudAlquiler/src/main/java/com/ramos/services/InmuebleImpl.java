package com.ramos.services;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ramos.interfaces.InmuebleInterface;
import com.ramos.models.Inmueble;
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
public class InmuebleImpl implements InmuebleInterface{
	@Override
	public List<Inmueble> allInmuebles() {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResourceGet = client.resource(Utils.URL_BASE+"/api/v1/inmuebles");
		List<Inmueble> inmuebles = webResourceGet.get(new GenericType<List<Inmueble>>() {});
		return inmuebles;
	}
	@Override
	public List<Inmueble> findInmueblesbyCentro(String id) {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResourceGet = client.resource(Utils.URL_BASE+"/api/v1/centros_educativos/"+id+"/inmuebles");
		List<Inmueble> inmuebles = webResourceGet.get(new GenericType<List<Inmueble>>(){});
		return inmuebles;
	}

	@Override
	public Inmueble findInmueblebyId(String id) {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResourceGet = client.resource(Utils.URL_BASE+"/api/v1/inmuebles/"+id);
		ClientResponse response  = webResourceGet.accept("application/json").get(ClientResponse.class);
		Inmueble inmueble= response.getEntity(Inmueble.class);
		return inmueble;
	}

	@Override
	public List<Inmueble> findInmueblesbyUsuario(String id) {
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResourceGet = client.resource(Utils.URL_BASE+"/api/v1/usuarios/"+id+"/inmuebles");
		List<Inmueble> inmuebles = webResourceGet.get(new GenericType<List<Inmueble>>() {});
		
		return inmuebles;
	}

	@Override
	public List<Inmueble> findInmueblesbyDistrito(String distrito,String centro_educativo_id)  {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResourceGet = client.resource(Utils.URL_BASE+"/api/v1/centros_educativos/"+centro_educativo_id+"/inmuebles/"+distrito);
		List<Inmueble> inmuebles = webResourceGet.get(new GenericType<List<Inmueble>>() {});
		return inmuebles;
	}

	@Override
	public String destroyInmueble(String id) {
		Client client = Client.create();
		WebResource webResource = client.resource("https://proyecto-alquiler-api-kevinghanz.c9users.io/api/v1/inmuebles/"+id);
		ClientResponse response = webResource.delete(ClientResponse.class);
		String respuesta = response.getEntity(String.class);
		return respuesta;
		
	}

	@Override
	public String crearInmueble(File imagen, String tipo, String direccion, String distrito, String departamento,
			String longitud, String latitud, String precio, String num_dormitorios, String num_banios,
			String area_total, String estado, String descripcion, String usuario_id, String centro_educativo_id) {
		
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			
			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource(Utils.URL_BASE+"/api/v1/inmuebles");
			
	        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("imagen",imagen,MediaType.APPLICATION_OCTET_STREAM_TYPE);
	        fileDataBodyPart.setContentDisposition(FormDataContentDisposition.name("imagen").fileName(imagen.getName()).build());
	        
	        final MultiPart multiPart = new FormDataMultiPart()
	                .field("tipo", tipo, MediaType.TEXT_PLAIN_TYPE)
	                .field("direccion", direccion, MediaType.TEXT_PLAIN_TYPE)
	                .field("distrito", distrito, MediaType.TEXT_PLAIN_TYPE)
	                .field("departamento", departamento, MediaType.TEXT_PLAIN_TYPE)
	                .field("longitud", longitud, MediaType.TEXT_PLAIN_TYPE)
	                .field("latitud", latitud, MediaType.TEXT_PLAIN_TYPE)
	                .field("precio", precio, MediaType.TEXT_PLAIN_TYPE)
	                .field("num_dormitorios",num_dormitorios, MediaType.TEXT_PLAIN_TYPE)
	                .field("num_banios", num_banios, MediaType.TEXT_PLAIN_TYPE)
	                .field("area_total", area_total, MediaType.TEXT_PLAIN_TYPE)
	                .field("estado", estado, MediaType.TEXT_PLAIN_TYPE)
	                .field("descripcion", descripcion, MediaType.TEXT_PLAIN_TYPE)
	                .field("usuario_id", usuario_id, MediaType.TEXT_PLAIN_TYPE)
	                .field("centro_educativo_id", centro_educativo_id, MediaType.TEXT_PLAIN_TYPE)
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
