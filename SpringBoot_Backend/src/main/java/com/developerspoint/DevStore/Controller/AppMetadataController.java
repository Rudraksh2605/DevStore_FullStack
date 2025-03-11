package com.developerspoint.DevStore.Controller;

import com.developerspoint.DevStore.AppMetaData.AppMetaData;
import com.developerspoint.DevStore.AppMetaData.AppMetaDataRepository;
import com.developerspoint.DevStore.Services.AppMetadataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/apps")
public class AppMetadataController {

    private final AppMetadataService service;
    private final AppMetaDataRepository repository;


    public AppMetadataController(AppMetadataService service, AppMetaDataRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/upload")
    public AppMetaData uploadAppMetadata(@RequestBody AppMetaData app) {
        return service.saveAppMetadata(app);
    }

    @GetMapping("/all")
    public List<AppMetaData> getAllApps() {
        return service.getAllApps();
    }

    @GetMapping("/developer/{devName}")
    public List<AppMetaData> getAppsByDeveloper(@PathVariable String devName) {
        return service.getAppsByDeveloper(devName);
    }

    @PutMapping("/update/{id}")
    public AppMetaData updateApp(@PathVariable Long id, @RequestBody AppMetaData updatedApp) {
        return service.updateApp(id, updatedApp); // Call service instead of repository
    }

    @GetMapping("/download/{id}")
    public String getApkDownloadUrl(@PathVariable Long id) {
        Optional<AppMetaData> app = repository.findById(id);
        return app.map(AppMetaData::getApkUrl).orElse("App not found"); // Fixed method call
    }

    @DeleteMapping("/delete/{id}")
    public String deleteApp(@PathVariable Long id) {
        service.deleteApp(id);
        return "App with ID " + id + " deleted successfully!";
        
    }


}
