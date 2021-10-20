package com.example.library.controllers;

import com.example.library.DTOs.ResourceDTO;
import com.example.library.collections.Resource;
import com.example.library.services.interfaces.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    @Autowired
    private IResourceService service;

    @GetMapping
    List<ResourceDTO> listResources(){
        return service.list();
    }
}
