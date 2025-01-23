package com.example.demo.controller;

import com.example.demo.Service.SiteService;
import com.example.demo.Service.SiteService.SiteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping("/s")
    public List<SiteResponse> getSiteInfo() {
        return siteService.fetchSiteInfo();
    }
}
