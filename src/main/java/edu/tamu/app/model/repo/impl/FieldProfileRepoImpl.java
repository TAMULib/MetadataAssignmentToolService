package edu.tamu.app.model.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.app.model.FieldProfile;
import edu.tamu.app.model.InputType;
import edu.tamu.app.model.Project;
import edu.tamu.app.model.repo.FieldProfileRepo;
import edu.tamu.app.model.repo.custom.FieldProfileRepoCustom;
import edu.tamu.weaver.data.model.repo.impl.AbstractWeaverRepoImpl;

public class FieldProfileRepoImpl extends AbstractWeaverRepoImpl<FieldProfile, FieldProfileRepo> implements FieldProfileRepoCustom {

    @Autowired
    private FieldProfileRepo fieldProfileRepo;

    @Override
    public FieldProfile create(Project project, String gloss, Boolean isRepeatable, Boolean isReadOnly, Boolean isHidden, Boolean isRequired, InputType inputType, String defaultValue) {
        return fieldProfileRepo.save(new FieldProfile(project, gloss, isRepeatable, isReadOnly, isHidden, isRequired, inputType, defaultValue));
    }

    @Override
    protected String getChannel() {
        return "/channel/field-profile";
    }

}
