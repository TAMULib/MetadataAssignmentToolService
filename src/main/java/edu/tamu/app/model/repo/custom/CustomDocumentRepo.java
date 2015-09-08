/* 
 * CustomDocumentRepo.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model.repo.custom;

import edu.tamu.app.model.Document;
import edu.tamu.app.model.Project;

/**
 * 
 * 
 * @author
 *
 */
public interface CustomDocumentRepo {

	public Document create(Project project, String name, String txtUri, String pdfUri, String txtPath, String pdfPath, String status);
	
	public void delete(Document document);
	
	public void deleteAll();
	
}
