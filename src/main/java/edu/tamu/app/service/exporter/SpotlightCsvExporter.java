package edu.tamu.app.service.exporter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.tamu.app.comparator.LabelComparator;
import edu.tamu.app.model.Document;
import edu.tamu.app.model.MetadataFieldGroup;
import edu.tamu.app.model.MetadataFieldValue;
import edu.tamu.app.model.Project;

@Service
public class SpotlightCsvExporter extends AbstractExporter {

    private static final Map<String, String> mapping;

    static {
        mapping = new HashMap<String, String>();
        mapping.put("dc.title", "full_title_tesim");
        mapping.put("dc.description", "spotlight_upload_description_tesim");
        mapping.put("dc.attribution", "spotlight_upload_attribution_tesim");
    }

    @Override
    public List<List<String>> extractMetadata(Project project) {
        List<List<String>> metadata = new ArrayList<List<String>>();
        project.getDocuments().stream().filter(isPublished()).collect(Collectors.<Document>toList()).forEach(document -> {
            List<MetadataFieldGroup> metadataFields = document.getFields();
            Collections.sort(metadataFields, new LabelComparator());
            List<String> documentMetadata = new ArrayList<String>();
            documentMetadata.add(0, document.getPublishedUriString());
            metadataFields.forEach(field -> {
                String values = null;
                boolean firstPass = true;
                for (MetadataFieldValue medataFieldValue : field.getValues()) {
                    if (firstPass) {
                        values = medataFieldValue.getValue();
                        firstPass = false;
                    } else {
                        values += ", " + medataFieldValue.getValue();
                    }
                }
                String mappedHeader = mapping.get(field.getLabel().getName());
                if (mappedHeader != null) {
                    documentMetadata.add(values);
                }
                documentMetadata.add(values);
            });
            metadata.add(documentMetadata);
        });
        return metadata;

    }

    @Override
    public List<String> extractMetadataFields(String projectName) {
        Project project = projectRepo.findByName(projectName);
        List<String> metadataHeaders = performMetadataFieldsExtraction(project);
        Collections.sort(metadataHeaders);
        metadataHeaders = addSpotlightFieldLabelMapping(metadataHeaders);
        metadataHeaders.add(0, "url");
        return metadataHeaders;
    }

    private List<String> addSpotlightFieldLabelMapping(List<String> metadataHeaders) {
        List<String> enhancedMetadataHeaders = new ArrayList<String>();
        for (String header : metadataHeaders) {
            String mappedHeader = mapping.get(header);
            if (mappedHeader != null) {
                enhancedMetadataHeaders.add(mappedHeader);
            }
            enhancedMetadataHeaders.add(converteDublinCoreLabelToSpotlightFieldLabel(header));
        }
        return enhancedMetadataHeaders;
    }

    private String converteDublinCoreLabelToSpotlightFieldLabel(String metadataHeader) {
        return metadataHeader.replaceAll(Pattern.quote("."), "-") + "_tesim";
    }

}
