package edu.tamu.app.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.tdl.vireo.annotations.Order;

import edu.tamu.app.enums.InputType;

public class MetadataFieldTest extends AbstractClass {

	@Before
    public void setUp() {
        testProject = projectRepo.create("testProject");
        testProfile = projectFieldProfileRepo.create(testProject, "testGloss", false, false, false, false, InputType.TEXT, "default");
        testLabel = metadataFieldLabelRepo.create("testLabel", testProfile);
        testDocument = documentRepo.create(testProject, "testDocument", "txtUri", "pdfUri", "txtPath", "pdfPath", "Unassigned");
        metadataFieldLabelRepo.save(testLabel);
        assertEquals("MetadataFieldRepo is not empty.", 0, metadataFieldGroupRepo.count());
    }

    @Test
    @Order(1)
    public void testCreateMetadataField() {
        testField = metadataFieldGroupRepo.create(testDocument, testLabel);
        assertEquals("Test MetadataField was not created.", 1, metadataFieldGroupRepo.count());
        assertEquals("Expected Test MetadataField was not created.", testLabel.getName(), testField.getLabel().getName());
    }

    @Test
    @Order(2)
    public void testFindMetadataField() {
        testField = metadataFieldGroupRepo.create(testDocument, testLabel);
        assertEquals("Test MetadataField was not created.", 1, metadataFieldGroupRepo.count());
        testField = metadataFieldGroupRepo.findByDocumentAndLabel(testDocument, testLabel);
        assertEquals("Test MetadataField was not found.", testLabel.getName(), testField.getLabel().getName());
    }

    @Test
    @Order(3)
    public void testDeleteMetadataField() {
        testField = metadataFieldGroupRepo.create(testDocument, testLabel);
        assertEquals("Document repository is empty.", 1, metadataFieldGroupRepo.count());
        metadataFieldGroupRepo.delete(testField);
        assertEquals("Test Document was not removed.", 0, metadataFieldGroupRepo.count());
    }

    @Test
    @Order(4)
    public void testCascadeOnDeleteMetadataField() {
        testField = metadataFieldGroupRepo.create(testDocument, testLabel);
        assertEquals("Test field was not created.", 1, metadataFieldGroupRepo.count());

        assertEquals("MetadataFieldValue repository is not empty.", 0, metadataFieldValueRepo.count());
        MetadataFieldValue testValue = metadataFieldValueRepo.create("test", testField);
        assertEquals("Test MetadataFieldValue was not created.", 1, metadataFieldValueRepo.count());

        testField.addValue(testValue);

        testField = metadataFieldGroupRepo.save(testField);

        assertEquals("Test MetadataField with expected MetadataFieldValue was not save.", testValue.getValue(), ((MetadataFieldValue) testField.getValues().toArray()[0]).getValue());

        metadataFieldGroupRepo.delete(testField);
        assertEquals("Test field was not deleted.", 0, metadataFieldGroupRepo.count());

        assertEquals("Test MetadataFieldValue was not deleted.", 0, metadataFieldValueRepo.count());
    }

}
