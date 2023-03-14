package com.example.assemblerframework.common;

import org.springframework.stereotype.Component;

import java.util.Map;

/*
*
*
* */
@Component
public class DataFromCMS {

    private Map<String, String> map;
    public Map<String,String> getDataFromCMS(){
        //TODO: get data from cms
        map.put("Prop1", "a");
        map.put("dep1","dep1");
        return map;
    }

}
