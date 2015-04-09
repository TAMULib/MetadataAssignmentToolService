/* 
 * DocumentRepo.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.app.model.impl.DocumentImpl;

/**
 * Document repository.
 * 
 * @author
 *
 */
@Repository
public interface DocumentRepo extends JpaRepository <DocumentImpl, Long> {
	
	/**
	 * Retrieve document by filename.
	 * 
	 * @param 		filename			String
	 * 
	 * @return		DocumentImpl
	 * 
	 */
	public DocumentImpl findByFilename(String filename);
	
	/**
	 * Retrieve document by filename.
	 * 
	 * @param 		page				Pageable
	 * @param 		filename			String
	 * 
	 * @return		Page<DocumentImpl>
	 * 
	 */
	public Page<DocumentImpl> findByFilenameContainingIgnoreCase(Pageable page, String filename);
	
	/**
	 * Retrieve document by filename and status.
	 * 
	 * @param 		page				Pageable
	 * @param 		filename			String
	 * @param 		status				String
	 * 
	 * @return		Page<DocumentImpl>
	 * 
	 */
	public Page<DocumentImpl> findByFilenameContainingIgnoreCaseAndStatusContainingIgnoreCase(Pageable page, String filename, String status);
	
	/**
	 * Retrieve document by filename and annotator.
	 * 
	 * @param 		page				Pageable
	 * @param 		filename			String
	 * @param 		annotator			String
	 * 
	 * @return		Page<DocumentImpl>
	 * 
	 */
	public Page<DocumentImpl> findByFilenameContainingIgnoreCaseAndAnnotatorContainingIgnoreCase(Pageable page, String filename, String annotator);
	
	
	/**
	 * Retrieve document by status.
	 * 
	 * @param 		page				Pageable
	 * @param 		status				String
	 * 
	 * @return		Page<DocumentImpl>
	 * 
	 */
	public Page<DocumentImpl> findByStatusContainingIgnoreCase(Pageable page, String status);
	
	/**
	 * Retrieve document by status and annotator.
	 * 
	 * @param 		page				Pageable
	 * @param 		status				String
	 * @param 		annotator			String
	 * 
	 * @return		Page<DocumentImpl>
	 * 
	 */
	public Page<DocumentImpl> findByStatusContainingIgnoreCaseAndAnnotatorContainingIgnoreCase(Pageable page, String status, String annotator);
	
	
	/**
	 * Retrieve document by annotator.
	 * 
	 * @param 		page				Pageable
	 * @param 		annotator			String
	 * 
	 * @return		Page<DocumentImpl>
	 * 
	 */
	public Page<DocumentImpl> findByAnnotatorContainingIgnoreCase(Pageable page, String annotator);
		
	/**
	 * Retrieve document by filename and status.
	 * 
	 * @param 		page				Pageable
	 * @param 		filename			String
	 * @param 		status				String
	 * @param 		annotator			String
	 * 
	 * @return		Page<DocumentImpl>
	 * 
	 */
	public Page<DocumentImpl> findByFilenameContainingIgnoreCaseAndStatusContainingIgnoreCaseAndAnnotatorContainingIgnoreCase(Pageable page, String filename, String status, String annotator);
	
}
