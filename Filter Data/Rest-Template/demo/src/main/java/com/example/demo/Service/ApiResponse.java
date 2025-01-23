package com.example.demo.Service;

import com.example.demo.model.Site;

import java.util.List;

public class ApiResponse {

    private List<Site> results;

    // Getters and Setters
    public List<Site> getResults() {
        return results;
    }

    public void setResults(List<Site> results) {
        this.results = results;
    }
}
