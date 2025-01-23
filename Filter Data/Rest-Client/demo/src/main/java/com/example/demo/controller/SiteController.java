package com.example.demo.controller;

import com.example.demo.model.Site;
import com.example.demo.Service.SiteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    // REST endpoint to get site info
    @GetMapping("/site-data")
    public List<Site> getSiteInfo() {
        return siteService.getSiteInfo();
    }
    // @GetMapping
    // public void getSiteInf() {
    //     siteService.test();
    // }
}
