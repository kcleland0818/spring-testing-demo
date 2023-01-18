package com.example.springtestingdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Rescue {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    private String address;

    private String city;

    private String state;

    @Column(nullable = false)
    private String websiteUrl;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "rescue")
    private List<Cat> cats = new ArrayList<>();
}
