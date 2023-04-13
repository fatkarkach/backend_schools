package com.example.fil_rouge.Service;

import com.example.fil_rouge.model.DTO.ActivitesDTO;
import com.example.fil_rouge.model.Entity.Activites;
import com.example.fil_rouge.model.Repository.ActivitesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class Activiteservice {
        @Autowired
        private ActivitesRepository activitesRepository;
        //ajouter un emploi
        public void   saveemploi(Activites activites) {
            activitesRepository.save(activites);
        }
    //get tous les emplois

    public List<ActivitesDTO> getactivites_all(){
/*
        return publicationRepository.findAll();
*/
        return ((List<Activites>) activitesRepository
                .findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }
    private ActivitesDTO convertDataIntoDTO (Activites activitesData) {

        // create instance of our UserLocationDTO class
        ActivitesDTO dto = new ActivitesDTO();
        dto.setId_activites(activitesData.getId_activites());
        dto.setNome_activites(activitesData.getNome_activites());
        dto.setDescription((activitesData.getDescription()));
        dto.setDuree(activitesData.getDuree());
        dto.setAdresse(activitesData.getAdresse());
        dto.setValidation(activitesData.getVille());
        dto.setImage_activites(activitesData.getImage_activites());
        dto.setDate_activites(activitesData.getDate_activites());
        return dto;
    }
    public List<Activites> getactivites_ma(){
/*
        return publicationRepository.findAll();
*/
        return activitesRepository.findAll();
    }

}
