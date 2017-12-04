package com.ramos.services;

import java.io.File;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ramos.interfaces.CentroEducativoInterface;
import com.ramos.models.CentroEducativo;
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
public class CentroEducativoImpl implements CentroEducativoInterface {
	
	private static final Logger logger = LoggerFactory.getLogger(CentroEducativoImpl.class);

	@Override
	public List<CentroEducativo> findAllCentros() {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResourceGet = client.resource(Utils.URL_BASE+"/api/v1/centros_educativos");
		List<CentroEducativo> centros_educativos = webResourceGet.get(new GenericType<List<CentroEducativo>>() {});
		
		return centros_educativos;
	}

	@Override
	public String destroyCentro(String id) {
		Client client = Client.create();
		WebResource webResource = client.resource("https://proyecto-alquiler-api-kevinghanz.c9users.io/api/v1/centros_educativos/"+id);
		ClientResponse response = webResource.delete(ClientResponse.class);
		String respuesta = response.getEntity(String.class);
		return respuesta;
		
	}

	@Override
	public String crearCentro(String tipo, String nombre, String direccion, File logo, File imagen) {
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			
			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource(Utils.URL_BASE+"/api/v1/centros_educativos");
			
			FileDataBodyPart fileDataBodyPart2 = new FileDataBodyPart("logo",logo,MediaType.APPLICATION_OCTET_STREAM_TYPE);
	        fileDataBodyPart2.setContentDisposition(FormDataContentDisposition.name("logo").fileName(logo.getName()).build());
	        
	        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("imagen",imagen,MediaType.APPLICATION_OCTET_STREAM_TYPE);
	        fileDataBodyPart.setContentDisposition(FormDataContentDisposition.name("imagen").fileName(imagen.getName()).build());
	        
	        final MultiPart multiPart = new FormDataMultiPart()
	                .field("tipo", tipo, MediaType.TEXT_PLAIN_TYPE)
	                .field("nombre", nombre, MediaType.TEXT_PLAIN_TYPE)
	                .field("direccion", direccion, MediaType.TEXT_PLAIN_TYPE)
	                .bodyPart(fileDataBodyPart2)
	                .bodyPart(fileDataBodyPart);
	        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);


	        ClientResponse response = webResource.type("multipart/form-data").post(ClientResponse.class,multiPart);
	        client.destroy();
	        
	        return response.toString();
		}catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public CentroEducativo findCentrobyId(String id) {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResourceGet = client.resource(Utils.URL_BASE+"/api/v1/centros_educativos/"+id);
		ClientResponse response  = webResourceGet.accept("application/json").get(ClientResponse.class);
		CentroEducativo centro_educativo= response.getEntity(CentroEducativo.class);
		return centro_educativo;
	}
	

}
