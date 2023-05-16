package com.turkcellcamp.commonpackage.events.inventory;

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
public class BrandDeletedEvent implements Event {

    private UUID brandId;
}
