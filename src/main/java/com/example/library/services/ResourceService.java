package com.example.library.services;

import com.example.library.DTOs.ResourceDTO;
import com.example.library.collections.Resource;
import com.example.library.mappers.ResourceMapper;
import com.example.library.repositories.ResourceRepository;
import com.example.library.services.interfaces.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        Optional<Resource> resource = repository.findById(resourceDTO.getId());
        if(resource.isPresent()){
            resource.get().setId(resourceDTO.getId());
            resource.get().setName(resourceDTO.getName());
            resource.get().setKind(resourceDTO.getKind());
            resource.get().setThematic(resourceDTO.getThematic());
            resource.get().setAvaibleResources(resourceDTO.getAvaibleResources());
            resource.get().setLendedResources(resourceDTO.getLendedResources());
            resource.get().setLocalDate(resourceDTO.getLocalDate());
        }
        return mapper.toResourceDTO(repository.save(resource.get()));
    }

    @Override
    public String checkResourceAvailability(String id) {
        return getById(id).map(resourceDTO -> {
            if(isAvailableResource(resourceDTO)){
                return "El recurso esta disponible :D";
            }
            return "El recurso no esta disponible actualmente, la ultima fecha en la que fue prestado fue el " + resourceDTO.getLocalDate();
        }).orElseThrow(() -> new RuntimeException("El recurso que buscas no existe - 404 not found."));
    }

    public boolean isAvailableResource(ResourceDTO resourceDTO){
        return resourceDTO.getAvaibleResources()>resourceDTO.getLendedResources();
    }

    @Override
    public String lendRecourse(String id) {
        return getById(id).map(resourceDTO -> {
            if (isAvailableResource(resourceDTO)){
                resourceDTO.setLendedResources(resourceDTO.getLendedResources() + 1);
                resourceDTO.setLocalDate(LocalDate.now());
                return "Prestamo de " + resourceDTO.getName() + " efectuado correctamente.";
            }
            return "El recurso no esta disponible en este momento.";
        }).orElseThrow(() -> new RuntimeException("¡Recurso no encontrado!"));
    }

    @Override
    public String returnResource(String id) {
        return getById(id).map(resourceDTO -> {
            if(resourceDTO.getLendedResources() > 0){
                resourceDTO.setLendedResources(resourceDTO.getLendedResources() - 1);

                Resource resourceUpdated = mapper.toResource(resourceDTO);
                repository.save(resourceUpdated);
                return "El recurso se ha regresado exitosamente.";
            }
            return "Imposible devolver el recurso ya que este se encuentra prestado o no disponible en este momento!";
        }).orElseThrow(() -> new RuntimeException("El recurso no existe."));
    }

    @Override
    public List<ResourceDTO> recommendByResourceType(String kind) {
        List<ResourceDTO> listOfResources = new ArrayList<>();
        repository.findByKind(kind).forEach(resource -> {
            listOfResources.add(mapper.toResourceDTO(resource));
        });
        return listOfResources;
    }

    @Override
    public List<ResourceDTO> recommendByTheme(String thematic) {
        List<ResourceDTO> listOfResources = new ArrayList<>();
        repository.findByThematic(thematic).forEach(resource -> {
            listOfResources.add(mapper.toResourceDTO(resource));
        });
        return listOfResources;
    }

    @Override
    public List<ResourceDTO> recommendByThemeAndType(String kind, String thematic) {
        List<ResourceDTO> listOfResources = new ArrayList<>();
        List<ResourceDTO> listOfResourcesDTOS = new ArrayList<>();

        listOfResources.addAll(recommendByTheme(thematic));
        listOfResources.addAll(recommendByResourceType(kind));

        listOfResources.stream().distinct().forEach(listOfResourcesDTOS :: add);

        return listOfResourcesDTOS;
    }
}
