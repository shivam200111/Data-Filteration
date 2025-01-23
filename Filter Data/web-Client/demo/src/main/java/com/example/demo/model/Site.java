package com.example.demo.model;

public class Site {
    
    private String display;
    private String businessFunction;

    // Getters and Setters
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getBusinessFunction() {
        return businessFunction;
    }

    public void setBusinessFunction(String businessFunction) {
        this.businessFunction = businessFunction;
    }

    @Override
    public String toString() {
        return "Site{" +
                "display='" + display + '\'' +
                ", businessFunction='" + businessFunction + '\'' +
                '}';
    }
}
