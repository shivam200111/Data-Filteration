
package com.example.demo.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomFields {

    @JsonProperty("business_function") // Maps the business_function field
    private String businessFunction;

    // Getters and Setters
    public String getBusinessFunction() {
        return businessFunction;
    }

    public void setBusinessFunction(String businessFunction) {
        this.businessFunction = businessFunction;
    }
}

