package com.example.fil_rouge.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivitesDTO {
    private  int id_activites;
    private  String  nome_activites;
    private  String image_activites;
    private  String  description;
    private  String date_activites;
    private  String duree;
    private  String ville;
    private  String adresse;
    private  String validation;
    private int id_user;
}
