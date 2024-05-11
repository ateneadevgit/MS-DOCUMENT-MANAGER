package com.fusm.documentmanager.external.impl;

import com.fusm.documentmanager.external.IExternalService;
import com.fusm.documentmanager.model.SettingRequest;
import com.fusm.documentmanager.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExternalService implements IExternalService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-settings.complete-path}")
    private String SETTINGS_ROUTE;

    @Value("${ms-settings.path}")
    private String SETTINGS_SERVICE;

    @Override
    public String getSetting(SettingRequest settingRequest) {
        return webClientConnector.connectWebClient(SETTINGS_ROUTE)
                .post()
                .uri(SETTINGS_SERVICE)
                .bodyValue(settingRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
