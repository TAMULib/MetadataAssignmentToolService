/* 
 * AppHttpRequestService.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import edu.tamu.framework.model.HttpRequest;
import edu.tamu.framework.service.HttpRequestService;

/**
 * Class AppHttpRequestService
 * 
 * @author 
 *
 */
@Service
public class AppHttpRequestService extends HttpRequestService {

	/**
	 * Gets and sets request
	 * 
	 * @param       destination     String
	 * @param       user            String
	 * @param       index           int
	 * 
	 * @see edu.tamu.framework.service.HttpRequestService#getRequestAndSetRequest(java.lang.String, java.lang.String, int)
	 * 
	 */
	@Override
	public HttpServletRequest getRequestAndSetRequest(String destination, String user, int index) {

		HttpServletRequest httpServletRequest = null;
		
		HttpRequest request = requests.get(index);
		
		if(destination.contains("/download/{file:.+}")) {
			if(request.getUser().equals(user) && request.getDestination().contains("/rest/bookbag/download")) {
				httpServletRequest = request.getRequest();
				requests.remove(index);
			}
		}		
		
		return httpServletRequest;
	}

}
