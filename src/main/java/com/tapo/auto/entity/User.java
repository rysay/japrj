package com.tapo.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user", schema="mydb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private int id;

    @Column(name="name" ,length = 100, nullable = false, unique=true)
    private String name;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "user_enabled", nullable = false)
    private int enabled;

    @Column(name = "authority", nullable = false)
    private int authority;

    @Column(name="mail_address" ,length = 200, nullable=true)
    private String mailAddress;

    @ManyToOne(targetEntity=UserRole.class)
    @JoinColumn(name="authority",referencedColumnName = "id", insertable=false, updatable=false)
    private UserRole userRole;
}
