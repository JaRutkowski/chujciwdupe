package com.jfs.operations.lite.controller.api.admin.impl;

import com.jfs.operations.lite.config.AppVersionProperties;
import com.jfs.operations.lite.controller.api.admin.AdminControllerApi;
import com.jfs.operations.lite.infrastructure.aspect.annotation.AutoMeasuringTime;
import com.jfs.operations.lite.service.file.feed.DataFeedingService;
import com.jfs.operations.lite.service.file.impl.dto.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminControllerApi {
    private final AppVersionProperties appVersionProperties;
    private final DataFeedingService dataFeedingService;

    //    private FeatureManager manager;
    //    public static final Feature EXAMPLE = new NamedFeature("TEST");

    private final FeatureManager manager;
    public static final Feature HELLO_WORLD = new NamedFeature("HELLO_WORLD");

    @Override
    public Map<String, String> version() {
        return Map.of("version", appVersionProperties.version(),
                "deployedAt", appVersionProperties.deployedAt());
    }

    @Override
    public ResponseEntity<Map<String, Config>> feedData() {
        dataFeedingService.feedData();
        return ResponseEntity.ok(dataFeedingService.getConfigMap());
    }

    @Override
    @AutoMeasuringTime
    public ResponseEntity<Map<String, Config>> reloadData() {
        dataFeedingService.reloadData();
        return ResponseEntity.ok(dataFeedingService.getConfigMap());
    }

    @Override
    public ResponseEntity<String> test() {
        if (manager.isActive(HELLO_WORLD))
            return ResponseEntity.ok("active");
        else
            return ResponseEntity.ok("not active");
    }
}
