package com.es.spaceship.sale.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ProbeInformationDTO {

    private Integer numberShips;
    private Map<String, Long> typesShips;
    private Long totalAttackPower;
    private Long fleetDefense;

}
