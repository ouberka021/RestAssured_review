package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class CategoryPOJO {
    private  int id;
    private  String name;
    private  String image;
    private String creationAt;


}
