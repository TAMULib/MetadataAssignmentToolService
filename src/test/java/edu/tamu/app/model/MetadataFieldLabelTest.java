package edu.tamu.app.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import edu.tamu.app.annotations.Order;
import edu.tamu.app.enums.InputType;

public class MetadataFieldLabelTest extends AbstractModelTest {

    @Before
    public void setUp() {
        testProject = projectRepo.create("testProject");
        testProfile = projectFieldProfileRepo.create(testProject, "testGloss", false, false, false, false, InputType.TEXT, "default");
        assertEquals("MetadataFieldLabelRepo is not empty.", 0, metadataFieldLabelRepo.count());
    }

    @Test
    @Order(1)
    public void testCreateMetadataFieldLabel() {
        metadataFieldLabelRepo.create("test", testProfile);
        assertEquals("MetadataFieldLabel was not created.", 1, metadataFieldLabelRepo.count());
    }

    @Test
    @Order(2)
    public void testDuplicateMetadataFieldLabel() {
        metadataFieldLabelRepo.create("test", testProfile);
        metadataFieldLabelRepo.create("test", testProfile);
        assertEquals("MetadataFieldLabel has duplicate.", 1, metadataFieldLabelRepo.count());
    }

    @Test
    @Order(3)
    public void testFindMetadataFieldLabel() {
        MetadataFieldLabel assertLabel = metadataFieldLabelRepo.create("test", testProfile);
        assertEquals("MetadataFieldLabel was not found.", assertLabel.getName(), metadataFieldLabelRepo.findByNameAndProfile("test", testProfile).getName());
    }

    @Test
    @Order(4)
    @Transactional // TODO: figure out why metadata field label will not delete without transactional!
    public void testDeleteMetadataFieldLabel() {
        metadataFieldLabelRepo.create("test", testProfile);
        assertEquals("MetadataFieldLabel was not created.", 1, metadataFieldLabelRepo.count());
        MetadataFieldLabel label = metadataFieldLabelRepo.findByNameAndProfile("test", testProfile);
        assertNotNull("Metadatafield was not retrieved!", label);
        metadataFieldLabelRepo.delete(label);
        assertEquals("MetadataFieldLabel was not deleted.", 0, metadataFieldLabelRepo.count());
    }

    @After
    public void cleanUp() {
        metadataFieldLabelRepo.deleteAll();
        projectFieldProfileRepo.deleteAll();
        projectRepo.deleteAll();
        // assertEquals("MetadataFieldLabel was not created.", 1, metadataFieldLabelRepo.count());
        // metadataFieldLabelRepo.delete(metadataFieldLabelRepo.findByName("test"));
        assertEquals("MetadataFieldLabel was not deleted.", 0, metadataFieldLabelRepo.count());
    }

}
