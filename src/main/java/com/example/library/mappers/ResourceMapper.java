package com.example.library.mappers;

import com.example.library.DTOs.ResourceDTO;
import com.example.library.collections.Resource;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    ResourceDTO toResourceDTO(Resource resource);
    List<ResourceDTO> toResourcesDTO(List<Resource> resources);

    @InheritInverseConfiguration
    Resource toResource(ResourceDTO resourceDTO);

}
