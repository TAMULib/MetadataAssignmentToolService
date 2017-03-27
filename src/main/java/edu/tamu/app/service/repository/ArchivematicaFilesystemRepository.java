package edu.tamu.app.service.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;

import edu.tamu.app.model.Document;
import edu.tamu.app.utilities.CsvUtility;
import edu.tamu.app.utilities.FileSystemUtility;

public class ArchivematicaFilesystemRepository implements Repository {

    @Value("${app.mount}")
    private String mount;

    @Autowired
    private CsvUtility csvUtility;

    private String archivematicaTopDirectory;

    @Autowired
    private ResourceLoader resourceLoader;

    public ArchivematicaFilesystemRepository(String archivematicaDirectoryName) throws IOException {

        // if this is an absolute path (preceded with the file separator) treat
        // it as such
        if (archivematicaDirectoryName.startsWith(File.separator)) {
            archivematicaTopDirectory = archivematicaDirectoryName;
        } // otherwise, if this is not preceded with the file separator, then it
          // will be a relative path in the MAGPIE static directory. Perhaps it
          // could be symlinked to somewhere.
        else {
            archivematicaTopDirectory = resourceLoader.getResource("classpath:static").getURL().getPath()
                    + File.separator + mount + File.separator + archivematicaDirectoryName;
        }
    }

    @Override
    public Document push(Document document) throws IOException {

        Path itemDirectoryName = FileSystemUtility.getWindowsSafePath(
                resourceLoader.getResource("classpath:static").getURL().getPath() + document.getDocumentPath());

        File documentDirectory = itemDirectoryName.toFile();

        // make the top level container for the transfer package
        File archivematicaPackageDirectory = new File(archivematicaTopDirectory + File.separator
                + document.getProject().getName() + "_" + document.getName());

        System.out.println("Writing Archivematica Transfer Package for Document " + itemDirectoryName.toString()
                + " to " + archivematicaPackageDirectory.getCanonicalPath());

        if (!archivematicaPackageDirectory.isDirectory())
            archivematicaPackageDirectory.mkdir();

        // make the logs, metadata, and objects subdirectories
        File metadataSubdirectory = new File(archivematicaPackageDirectory.getPath() + File.separator + "metadata");
        if (!metadataSubdirectory.isDirectory())
            metadataSubdirectory.mkdir();

        File logsSubdirectory = new File(archivematicaPackageDirectory.getPath() + File.separator + "logs");
        if (!logsSubdirectory.isDirectory())
            logsSubdirectory.mkdir();

        File objectsSubdirectory = new File(archivematicaPackageDirectory.getPath() + File.separator + "objects");
        if (!objectsSubdirectory.isDirectory())
            objectsSubdirectory.mkdir();

        // make the submissionDocumentation subdirectory in the metadata
        // subdirectory
        File submissionDocumentationSubdirectory = new File(
                metadataSubdirectory.getPath() + File.separator + "submissionDocumentation");
        if (!submissionDocumentationSubdirectory.isDirectory())
            submissionDocumentationSubdirectory.mkdir();

        // make the specific item subdirectory in the objects subdirectory
        File singleObjectSubdirectory = new File(objectsSubdirectory.getPath() + File.separator + document.getName());
        if (!singleObjectSubdirectory.isDirectory())
            singleObjectSubdirectory.mkdir();

        // assume for now that there are some number of tiffs in the document
        // directory and obtain the tiffs and md5 checksum from disk
        File[] tiffFiles = documentDirectory.listFiles(new OnlyTiff());

        File md5Listing = new File(metadataSubdirectory.getPath() + File.separator + "checksum.md5");

        for (File tiff : tiffFiles) {
            System.out.println("Found tiff " + tiff.getPath());
            // make the md5hash listing from the tiffs
            addChecksumToListing(tiff, md5Listing);
        }

        // write the metadata csv in the metadata subdirectory
        // write the ArchiveMatica CSV for this document
        csvUtility.generateOneArchiveMaticaCSV(document, metadataSubdirectory.getPath());

        // copy the item directory and tiffs to the objects subdirectory
        for (File tiff : tiffFiles) {
            File destinationTiff = new File(singleObjectSubdirectory.getPath() + File.separator + tiff.getName());
            FileUtils.copyFile(tiff, destinationTiff);
        }

        return document;
    }

    private void addChecksumToListing(File tiffFile, File md5Listing) {

        String checksum = null;
        try {
            checksum = DigestUtils.md5Hex(new FileInputStream(tiffFile)) + " *" + tiffFile.getName();
            Path p = Paths.get(md5Listing.getPath());
            List<String> lines = md5Listing.exists() ? Files.readAllLines(p) : new ArrayList<String>();

            if (lines.isEmpty()) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy h:mm:ssa");
                LocalDateTime now = LocalDateTime.now();
                lines.add("# MD5 Generated by MagPie (https://github.com/TAMULib/MetadataAssignmentToolUI)");
                lines.add("# Generated " + dtf.format(now));
                lines.add("");
            }

            lines.add(checksum);
            Files.write(p, lines);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class OnlyTiff implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(".tif") || name.toLowerCase().endsWith(".tiff");
        }
    }

    class OnlyMD5 implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(".md5");
        }
    }

}
