/* 
 * ProjectFieldProfileRepo.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.app.model.InputType;
import edu.tamu.app.model.Project;
import edu.tamu.app.model.ProjectFieldProfile;
import edu.tamu.app.model.repo.custom.CustomProjectFieldProfileRepo;

/**
 * 
 * 
 * @author
 *
 */
@Repository
public interface ProjectFieldProfileRepo extends JpaRepository<ProjectFieldProfile, Long>, CustomProjectFieldProfileRepo {
	
	public ProjectFieldProfile create(Project project, String gloss, Boolean isRepeatable, Boolean isReadOnly, Boolean isHidden, Boolean isRequired, InputType inputType, String defaultValue);
	
	public void delete(ProjectFieldProfile field);
	
	public void deleteAll();
	
	public ProjectFieldProfile findByProject(Project project);

}
