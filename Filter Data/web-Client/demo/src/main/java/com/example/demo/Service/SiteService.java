package com.example.demo.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.ParameterizedTypeReference;


import com.example.demo.model.Site;

@Service
public class SiteService {

    // Inject properties from application.properties
    @Value("${helios.api.url}")
    private String apiUrl;

    @Value("${helios.api.token}")
    private String apiToken;

    // Injected WebClient for making external HTTP requests
    private final WebClient webClient;

    // Constructor for injecting the WebClient
    public SiteService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Method to fetch site information from the external API
    public List<Site> getSiteInfo() {
        try {
            // Make the GET request to the external API
            Map<String, Object> responseBody = webClient.get()
                .uri(apiUrl)
                .headers(httpHeaders -> {
                    // Set Authorization and Content-Type headers
                    httpHeaders.set("Authorization", apiToken); // Use injected token
                    httpHeaders.set("Content-Type", "application/json");
                })
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block(); // Blocking for simplicity; consider a reactive approach if appropriate

            // Extract the 'results' key from the response body (list of sites)
            List<Map<String, Object>> sites = (List<Map<String, Object>>) responseBody.get("results");

            // Check if the 'results' key exists in the response
            if (sites == null) {
                throw new RuntimeException("Null results from API");
            }

            // Map each site in the response to a Site entity object
            return sites.stream()
                    .map(site -> {
                        // Create a new Site entity and populate fields
                        Site siteEntity = new Site();
                        siteEntity.setDisplay((String) site.get("display"));
                        siteEntity.setBusinessFunction(
                                // Check if custom fields exist and extract the business function value
                                Optional.ofNullable((Map<String, Object>) site.get("custom_fields"))
                                        .map(customFields -> (String) customFields.get("business_function"))
                                        .orElse(null)
                        );
                        return siteEntity;
                    })
                    .collect(Collectors.toList()); // Collect and return as a List of Site entities

        } catch (Exception ex) {
            // Handle any errors that occur during the API call
            throw new RuntimeException("Error occurred while calling the external API: " + ex.getMessage(), ex);
        }
    }
}
