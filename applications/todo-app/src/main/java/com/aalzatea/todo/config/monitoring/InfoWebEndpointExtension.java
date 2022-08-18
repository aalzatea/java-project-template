package com.aalzatea.todo.config.monitoring;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
@RequiredArgsConstructor
public class InfoWebEndpointExtension {

    private final InfoEndpoint infoEndpoint;

    @ReadOperation
    public WebEndpointResponse<Map<String, Object>> info() {
        Map<String, Object> info = new HashMap<>(this.infoEndpoint.info());
        int status = 200;

        putInfoMapValues(info);

        return new WebEndpointResponse<>(info, status);
    }

    private void putInfoMapValues(Map<String, Object> info) {
        info.put("name", "Java Template Applicaton");
        info.put("description", "This application is a Java template to be used as a skeleton for different applications.");
        info.put("version", "0.0.1");
        info.put("java_version", "17");
        info.put("type", "Spring Boot");
    }
}
