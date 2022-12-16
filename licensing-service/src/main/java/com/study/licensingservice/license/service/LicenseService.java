package com.study.licensingservice.license.service;

import com.study.licensingservice.license.config.ServiceConfig;
import com.study.licensingservice.license.model.License;
import com.study.licensingservice.license.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messages;
    private final LicenseRepository licenseRepository;
    private final ServiceConfig config;


    public License getLicense(String licenseId, String organizationId){
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId).orElseThrow(() -> new IllegalArgumentException(
                String.format(messages.getMessage("license.search.error.message", null, null))
        ));
        return license.withComment(config.getProperty());
    }

    public License createLicense(License license, String organizationId){
        license.setLicenseId(UUID.randomUUID().toString());
        license.setOrganizationId(organizationId);
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license){
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId){
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        return String.format(messages.getMessage("license.delete.message", null, null),licenseId);
    }
}
