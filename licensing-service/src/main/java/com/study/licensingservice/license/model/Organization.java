package com.study.licensingservice.license.model;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Organization extends RepresentationModel<Organization> {

    private String id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

}