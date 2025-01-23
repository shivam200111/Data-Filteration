package com.example.demo.Service;

import com.example.demo.model.Site;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteService {

    @Value("${helios.api.url}")
    private String apiUrl;

    @Value("${helios.api.token}")
    private String authToken;

    @Value("${helios.api.cookies}")
    private String cookies;

    public List<SiteResponse> fetchSiteInfo() {
        // Set up headers with authentication token and cookies
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token " + authToken);
        headers.set("Cookie", cookies);

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl);

        // Send the request with the headers and get the response
        ResponseEntity<ApiResponse> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, new org.springframework.http.HttpEntity<>(headers), ApiResponse.class);

        // Prepare the result
        List<SiteResponse> resultList = new ArrayList<>();
        if (response.getBody() != null && response.getBody().getResults() != null) {
            for (Site site : response.getBody().getResults()) {
                // Extract display and business_function
                String display = site.getDisplay();
                String businessFunction = site.getCustomFields() != null ? site.getCustomFields().getBusinessFunction() : "N/A";

                // Add to result list
                resultList.add(new SiteResponse(display, businessFunction));
            }
        }
        return resultList;
    }

    // DTO class to return in the API response
    public static class SiteResponse {
        private String display;
        private String businessFunction;

        public SiteResponse(String display, String businessFunction) {
            this.display = display;
            this.businessFunction = businessFunction;
        }

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
    }
}
