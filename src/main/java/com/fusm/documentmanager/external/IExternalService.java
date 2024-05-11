package com.fusm.documentmanager.external;

import com.fusm.documentmanager.model.SettingRequest;
import org.springframework.stereotype.Service;

@Service
public interface IExternalService {
    String getSetting(SettingRequest settingRequest);
}
