package edu.tamu.app.service.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import edu.tamu.app.model.Document;

public class FedoraPCDMRepository extends FedoraRepository {

	public FedoraPCDMRepository(String repoUrl, String restPath, String containerPath, String username,
			String password) {
		super(repoUrl, restPath, containerPath, username, password);
	}
	
	@Override
	public Document push(Document document) throws IOException {
		System.out.println("PRETENDING TO PUSH");
		System.out.println("container is: "+createContainer(buildContainerUrl(),getContainerPath()));
		return null;
	}

	@Override
	protected String createContainer(String containerUrl, String slugName) throws IOException {
		System.out.println("creating container: "+containerUrl+"/");
		HttpURLConnection connection = buildBasicFedoraConnection(containerUrl+"/");
        connection.setRequestProperty("CONTENT-TYPE", "application/rdf+xml");
		connection.setRequestMethod("PUT");		

		connection.setDoOutput(true);

		if(slugName != null) connection.setRequestProperty("slug", slugName);
		
		OutputStream os = connection.getOutputStream();

		Model pcdmObject = buildPCDMObject(containerUrl, os);
		pcdmObject.write(os);
		System.out.println("*** JENA GENERATED RDF+XML ***");
		System.out.println(os.toString());		
		os.close();

		
		int responseCode = connection.getResponseCode();
				
		if(responseCode != 201) {
			System.out.println("message: "+connection.getResponseMessage());
			throw new IOException("Could not create container. Server responded with " + responseCode);
		}
				
		return connection.getHeaderField("Location");
	}
	
	private Model buildPCDMObject(String containerUrl, OutputStream os) throws FileNotFoundException {
		/*
			@prefix pcdm: <http://pcdm.org/models#>
			  
			<> a pcdm:Object .
		 */

		Model model = ModelFactory.createDefaultModel();
		Resource resource = model.createResource(containerUrl);
		resource.addProperty(RDF.type,"http://pcdm.org/models#Object");
		return model;
	}

}
