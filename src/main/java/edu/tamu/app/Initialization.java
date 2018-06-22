package edu.tamu.app;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import edu.tamu.app.observer.FileMonitorManager;
import edu.tamu.app.observer.FileObserverRegistry;
import edu.tamu.app.observer.MapFileListener;
import edu.tamu.app.observer.ProjectListener;
import edu.tamu.app.service.SyncService;
import edu.tamu.app.utilities.FileSystemUtility;

@Component
public class Initialization implements CommandLineRunner {

    public static String HOST;

    public static String ASSETS_PATH;

    public static String PROJECTS_PATH = "projects";

    @Value("${app.host}")
    private String host;

    @Value("${app.assets.path}")
    private String assetsPath;

    @Value("${app.assets.folders}")
    private String[] assetsFolders;

    @Autowired
    private SyncService syncService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private FileMonitorManager fileMonitorManager;

    @Autowired
    private FileObserverRegistry fileObserverRegistry;

    @Override
    public void run(String... args) throws Exception {

        setHost(host);

        setAssetsPath(assetsPath);

        for (String folder : assetsFolders) {
            FileSystemUtility.createDirectory(ASSETS_PATH + File.separator + folder);
        }

        fileObserverRegistry.register(new ProjectListener(ASSETS_PATH, PROJECTS_PATH));
        fileObserverRegistry.register(new MapFileListener(ASSETS_PATH, "maps"));

        syncService.sync();

        // NOTE: this must be last on startup, otherwise it will invoke all file observers
        fileMonitorManager.start();
    }

    private void setHost(String host) {
        HOST = host;
    }

    private void setAssetsPath(String host) {
        try {
            ASSETS_PATH = resourceLoader.getResource(assetsPath).getURI().getPath();
        } catch (IOException e) {
            ASSETS_PATH = assetsPath;
        }
    }

}