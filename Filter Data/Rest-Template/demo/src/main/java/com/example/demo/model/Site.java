package com.example.demo.model;

import com.example.demo.Service.CustomFields;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Site {

    private String display;

    @JsonProperty("custom_fields") // Maps the custom_fields object
    private CustomFields customFields;

    // Getters and Setters
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public CustomFields getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomFields customFields) {
        this.customFields = customFields;
    }
}
