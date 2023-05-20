package com.turkcellcamp.maintenanceservice.api.clients;

import com.turkcellcamp.commonpackage.utils.dto.ChangeCarStateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "FILTER-SERVICE", fallback = FilterClientFallback.class)
public interface FilterClient {

    @PutMapping("/api/v1/filters/change-car-state")
    void changeCarState(@RequestBody ChangeCarStateRequest request);
}
