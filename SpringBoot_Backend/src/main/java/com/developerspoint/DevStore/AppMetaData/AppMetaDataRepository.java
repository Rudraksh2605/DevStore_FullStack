package com.developerspoint.DevStore.AppMetaData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppMetaDataRepository extends JpaRepository<AppMetaData, Long> {
    List<AppMetaData> findByDeveloperName(String developerName);

}
