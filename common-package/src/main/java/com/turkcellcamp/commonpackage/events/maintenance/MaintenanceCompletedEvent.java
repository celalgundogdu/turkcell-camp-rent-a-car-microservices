package com.turkcellcamp.commonpackage.events.maintenance;

import com.turkcellcamp.commonpackage.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceCompletedEvent implements Event {

    private UUID carId;
}
