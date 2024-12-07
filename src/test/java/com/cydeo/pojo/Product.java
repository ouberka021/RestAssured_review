package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private int id;
    private String title;
    private int price;
    @JsonProperty("images")
    private List<String> allImages;


    private CategoryPOJO category;
    /*
    Map also work bu we doing POJO practice private Map<String, String> category;
    */



}
/*
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Product{
    private int id;
    private String title;
    private int price;
    @JsonProperty("images")
    private List<String> allImages;

   /*
   Map also work bu we doing POJO practice
   private Map<String,String> category;
   */

