package com.workflow.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Warrior implements Serializable {

    private String name;

    private Integer hp;

    private Boolean isAlive;

    private static final Long SERIAL_VERSION_UID = 1L;
}
