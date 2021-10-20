package com.example.library.controllers;

import com.example.library.DTOs.ResourceDTO;
import com.example.library.collections.Resource;
import com.example.library.services.interfaces.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable() String id){
        return service.getById(id)
                .map(resourceDTO -> new ResponseEntity<>(resourceDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO){
        return new ResponseEntity<>(service.create(resourceDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteResource(@PathVariable String id){
        if(service.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResourceDTO> updateResource(@RequestBody ResourceDTO resourceDTO){
        return new ResponseEntity<>(service.update(resourceDTO), HttpStatus.OK);
    }
}
