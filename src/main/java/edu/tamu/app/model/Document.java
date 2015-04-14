/* 
 * Document.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model;

import edu.tamu.app.model.impl.DocumentProfileImpl;

/**
 * Document interface.
 * 
 * @author
 *
 */
public interface Document {

	/**
	 * Gets name.
	 * 
	 * @return		String
	 */
	public String getName();

	/**
	 * Sets name.
	 * 
	 * @param 		name			String
	 */
	public void setName(String name);
	
	/**
	 * Gets pdf uri.
	 * 
	 * @return		String
	 */
	public String getPdfUri();

	/**
	 * Sets pdf uri.
	 * 
	 * @param 		uri					String
	 */
	public void setPdfUri(String uri);
	
	/**
	 * Gets status.
	 * 
	 * @return		String
	 */
	public String getStatus();

	/**
	 * Sets status.
	 * 
	 * @param 		status				String
	 */
	public void setStatus(String status);
	
	/**
	 * Gets annotator.
	 * 
	 * @return		String
	 */
	public String getAnnotator();

	/**
	 * Sets annotator.
	 * 
	 * @param 		annotator			String
	 */
	public void setAnnotator(String annotator);
	
	/**
	 * Gets notes.
	 * 
	 * @return		String
	 */
	public String getNotes();

	/**
	 * Sets notes.
	 * 
	 * @param 		annotator			String
	 */
	public void setNotes(String notes);
	
	/**
	 * Gets documentProfile.
	 * 
	 * @return		DocumentProfileImpl
	 */
	public DocumentProfileImpl getDocumentProfile();

	/**
	 * Sets documentProfile.
	 * 
	 * @param 		profile			DocumentProfileImpl
	 */
	public void setDocumentProfile(DocumentProfileImpl profile);
	
	
}
