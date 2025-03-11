package com.developerspoint.DevStore.Services;

import com.developerspoint.DevStore.AppMetaData.AppMetaData;
import com.developerspoint.DevStore.AppMetaData.AppMetaDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppMetadataService {
    private final AppMetaDataRepository repository;

    public AppMetadataService(AppMetaDataRepository repository) {
        this.repository = repository;
    }

    public AppMetaData saveAppMetadata(AppMetaData app) {
        return repository.save(app);
    }

    public List<AppMetaData> getAllApps() {
        return repository.findAll();
    }

    public List<AppMetaData> getAppsByDeveloper(String developerName) {
        return repository.findByDeveloperName(developerName);
    }

    public AppMetaData updateApp(Long id, AppMetaData updatedApp) {
        return repository.findById(id).map(app -> {
            app.setAppName(updatedApp.getAppName());
            app.setAppVersion(updatedApp.getAppVersion());
            app.setAppDescription(updatedApp.getAppDescription());
            app.setAppIcon(updatedApp.getAppIcon());
            app.setApkUrl(updatedApp.getApkUrl());
            app.setCompany(updatedApp.getCompany());
            app.setDeveloperName(updatedApp.getDeveloperName());
            return repository.save(app);
        }).orElseThrow(() -> new RuntimeException("App metadata not found with id: " + id));
    }

    public void deleteApp(Long id) {
        repository.deleteById(id);
    }

    public String getApkDownloadUrl(Long id) {
        Optional<AppMetaData> app = repository.findById(id);
        return app.map(AppMetaData::getApkUrl).orElse("App not found");
    }


}
