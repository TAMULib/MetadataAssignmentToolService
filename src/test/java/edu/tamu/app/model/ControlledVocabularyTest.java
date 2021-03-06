package edu.tamu.app.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

public class ControlledVocabularyTest extends AbstractModelTest {

    @Test
    public void testCreateControlledVocabulary() {
        testControlledVocabulary = controlledVocabularyRepo.create("test");
        assertEquals("Test ControlledVocabulary was not created.", 1, controlledVocabularyRepo.count());
        assertEquals("Expected test ControlledVocabulary was not created.", "test", testControlledVocabulary.getValue());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testDuplicateControlledVocabulary() {
        controlledVocabularyRepo.create("test");
        controlledVocabularyRepo.create("test");
    }

    @Test
    public void testFindControlledVocabulary() {
        testControlledVocabulary = controlledVocabularyRepo.create("test");
        assertEquals("Test ControlledVocabulary was not created.", 1, controlledVocabularyRepo.count());
        ControlledVocabulary assertControlledVocabulary = controlledVocabularyRepo.findByValue("test");
        assertEquals("Expected test ControlledVocabulary was not created.", testControlledVocabulary.getValue(), assertControlledVocabulary.getValue());
    }

    @Test
    public void testDeleteControlledVocabulary() {
        testControlledVocabulary = controlledVocabularyRepo.create("test");
        assertEquals("Test ControlledVocabulary was not created.", 1, controlledVocabularyRepo.count());
        controlledVocabularyRepo.delete(testControlledVocabulary);
        assertEquals("Test ControlledVocabulary was not deleted.", 0, controlledVocabularyRepo.count());
    }

}
