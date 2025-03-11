package com.developerspoint.DevStore.AppMetaData;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "app_metadata")
public class AppMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("app_name")
    private String appName;

    @JsonProperty("app_version")
    private String appVersion;

    @JsonProperty("app_description")
    private String appDescription;

    @JsonProperty("app_icon")
    private String appIcon;

    @JsonProperty("apk_url")
    private String apkUrl;

    private String company;

    @JsonProperty("developer_name")
    private String developerName;

    public AppMetaData() {
    }

    public AppMetaData(String appName, String appVersion, String appDescription, String appIcon, String apkUrl, String company, String developerName) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.appDescription = appDescription;
        this.appIcon = appIcon;
        this.apkUrl = apkUrl;
        this.company = company;
        this.developerName = developerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }
}
