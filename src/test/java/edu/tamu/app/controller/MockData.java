package edu.tamu.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.tamu.app.enums.AppRole;
import edu.tamu.app.enums.InputType;
import edu.tamu.app.model.AppUser;
import edu.tamu.app.model.Document;
import edu.tamu.app.model.FieldProfile;
import edu.tamu.app.model.MetadataFieldGroup;
import edu.tamu.app.model.MetadataFieldLabel;
import edu.tamu.app.model.Project;
import edu.tamu.app.model.Suggestion;
import edu.tamu.framework.model.Credentials;

public class MockData {

    protected static AppUser TEST_USER1 = new AppUser("123456789", "Jane", "Daniel", AppRole.ROLE_ADMIN.toString());
    protected static AppUser TEST_USER2 = new AppUser("234567891", "Jack", "Daniel", "ROLE_USER");
    protected static AppUser TEST_USER3 = new AppUser("345678912", "Jill", "Daniel", "ROLE_ADMIN");

    static {
        TEST_USER1.setId(1l);
        TEST_USER2.setId(2l);
        TEST_USER3.setId(3l);
    }

    protected static List<AppUser> mockUserList = new ArrayList<AppUser>(Arrays.asList(new AppUser[] { TEST_USER1, TEST_USER2, TEST_USER3 }));

    public AppUser createUser(String uin, String firstName, String lastName, String role) {
        return new AppUser(uin, firstName, lastName, AppRole.ROLE_ADMIN.toString());
    }

    public AppUser saveAppUser(AppUser appUser) {
        for (AppUser user : mockUserList) {
            if (user.getId().equals(appUser.getId())) {
                user.setFirstName(appUser.getFirstName());
                user.setLastName(appUser.getLastName());
                user.setRole(appUser.getRole());
                return user;
            }
        }
        return null;
    }

    // Credentials
    protected Credentials credentials = new Credentials();

    // Project
    protected static Project TEST_PROJECT1 = new Project("Project Name 1");
    protected static Project TEST_PROJECT2 = new Project("Project Name 2");
    protected static Project TEST_PROJECT3 = new Project("Project Name 3");

    static {
        TEST_PROJECT1.setId(1l);
        TEST_PROJECT2.setId(2l);
        TEST_PROJECT3.setId(3l);
    }

    protected static List<Project> mockProjectList = new ArrayList<Project>(Arrays.asList(new Project[] { TEST_PROJECT1, TEST_PROJECT2, TEST_PROJECT3 }));

    public Project saveProject(Project modifiedProject) {
        for (Project project : mockProjectList) {
            if (project.getName().equals(modifiedProject.getName())) {
                project.setIsLocked(modifiedProject.getIsLocked());
                project.setName(modifiedProject.getName());
            }
        }
        return null;
    }

    public Project findProjectById(Long projectId) {
        for (Project project : mockProjectList) {
            if (project.getId().equals(projectId)) {
                return project;
            }
        }
        return null;
    }

    public Project findProjectbyName(String projectName) {
        for (Project project : mockProjectList) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }

    // Suggestion
    protected static Suggestion suggestion1 = new Suggestion("suggestion label1", "suggestion value1");
    protected static Suggestion suggestion2 = new Suggestion("suggestion label2", "suggestion value2");
    protected static Suggestion suggestion3 = new Suggestion("suggestion label3", "suggestion value3");

    protected static List<Suggestion> mockSuggestionList = Arrays.asList(new Suggestion[] { suggestion1, suggestion2, suggestion3 });

    // Document
    protected static Document TEST_DOCUMENT1 = new Document(TEST_PROJECT1, "Doc1 name", "txtUri1", "pdfUri1", "txtPath1", "pdfPath1", "documentPath1", "Unassigned");
    protected static Document TEST_DOCUMENT2 = new Document(TEST_PROJECT1, "Doc2 name", "txtUri2", "pdfUri2", "txtPath2", "pdfPath2", "documentPath2", "Pending");
    protected static Document TEST_DOCUMENT3 = new Document(TEST_PROJECT1, "Doc3 name", "txtUri3", "pdfUri3", "txtPath3", "pdfPath3", "documentPath3", "Accepted");

    static {
        TEST_DOCUMENT1.setId(1l);
        TEST_DOCUMENT2.setId(2l);
        TEST_DOCUMENT3.setId(3l);
        TEST_DOCUMENT1.setProject(TEST_PROJECT1);
    }

    protected static List<Document> mockDocumentList = new ArrayList<Document>(Arrays.asList(new Document[] { TEST_DOCUMENT1, TEST_DOCUMENT2, TEST_DOCUMENT3 }));

    public Document saveDocument(Document modifiedDocument) {
        Document returnDocument = null;
        for (Document document : mockDocumentList) {
            if (document.getName().equals(modifiedDocument.getName())) {
                document.setProject(modifiedDocument.getProject());
                document.setName(modifiedDocument.getName());
                document.setTxtUri(modifiedDocument.getTxtUri());
                document.setPdfUri(modifiedDocument.getPdfUri());
                document.setStatus(modifiedDocument.getStatus());
                returnDocument = document;
                break;
            }
        }
        return returnDocument;
    }

    public Document findDocumentByProjectNameandName(String projectName, String documentName) {
        for (Document document : mockDocumentList) {
            if (document.getName().equals(documentName) && document.getProject().getName().equals(projectName)) {
                return document;
            }
        }
        return null;
    }

    // MetadataHeaders
    protected static List<String> mockSpotlightExportedMetadataHeaders = new ArrayList<String>();
    protected static List<List<String>> mockSpotlightMetdata = new ArrayList<List<String>>();

    protected static List<String> mockDspaceCSVExportedMetadataHeaders = new ArrayList<String>();
    protected static List<List<String>> mockDspaceCSVExportedMetadata = new ArrayList<List<String>>();

    // metadatagroup
    protected static FieldProfile TEST_PROFILE1 = new FieldProfile(TEST_PROJECT1, "testGloss", false, false, false, false, InputType.TEXT, "default");
    protected static MetadataFieldLabel TEST_META_LABEL = new MetadataFieldLabel("metadataFieldLabel", TEST_PROFILE1);
    protected static MetadataFieldGroup metadataFieldGroup1 = new MetadataFieldGroup(TEST_DOCUMENT1, TEST_META_LABEL);
    protected static MetadataFieldGroup metadataFieldGroup2 = new MetadataFieldGroup(TEST_META_LABEL);

    static {
        metadataFieldGroup1.setId(1l);
        metadataFieldGroup2.setId(2l);
    }

    protected static List<MetadataFieldGroup> mockMetadataFieldGroupList = new ArrayList<MetadataFieldGroup>(Arrays.asList(new MetadataFieldGroup[] { metadataFieldGroup1, metadataFieldGroup2 }));
}
