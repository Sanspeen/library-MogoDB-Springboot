package com.example.library.services;

import com.example.library.DTOs.ResourceDTO;
import com.example.library.collections.Resource;
import com.example.library.mappers.ResourceMapper;
import com.example.library.repositories.ResourceRepository;
import com.example.library.services.interfaces.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService implements IResourceService {

    @Autowired
    private ResourceRepository repository;

    @Autowired
    ResourceMapper mapper;

    @Override
    public List<ResourceDTO> list() {
        return mapper.toResourcesDTO(repository.findAll());
    }

    @Override
    public ResourceDTO create(ResourceDTO resourceDTO) {
        Resource resource = mapper.toResource(resourceDTO);
        return mapper.toResourceDTO(repository.save(resource));
    }

    @Override
    public boolean delete(String id) {
        return getById(id).map(resourceDTO -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<ResourceDTO> getById(String id) {
        return repository.findById(id)
                .map(resource -> mapper.toResourceDTO(resource));
    }

    @Override
    public ResourceDTO update(ResourceDTO resourceDTO) {
        return null;
    }

    @Override
    public String checkResourceAvailability(String id) {
        return null;
    }

    @Override
    public String lendRecourse(String id) {
        return null;
    }

    @Override
    public String returnResource(String id) {
        return null;
    }

    @Override
    public List<ResourceDTO> recommendByResourceType(String kind) {
        return null;
    }

    @Override
    public List<ResourceDTO> recommendByTheme(String thematic) {
        return null;
    }

    @Override
    public List<ResourceDTO> recommendByThemeAndType(String kind, String thematic) {
        return null;
    }
}
