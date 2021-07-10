package com.es.spaceship.sale.models.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;
    private String name;
    private String planetOrigin;
    private String spacies;
    private String shipsOwner;
    private boolean sale;
    private String password;
    private String email;
    private boolean isexception;
    private boolean isLicense;
    private boolean isEnable;
    private boolean isPirates;
    private boolean isfraud;
    private int warning;
    private LocalDate punishment;
    @Column(name = "rol")
    @ManyToMany(targetEntity = Rol.class, fetch = FetchType.LAZY)
    private List<Rol> rol;
    private String nick;

    @Column(name = "ships")
    @JoinTable(name = "ship_self_defense")
    @ManyToMany(targetEntity = Ships.class, fetch = FetchType.LAZY)
    private List<Ships> shipSelfDefense;

}
