package com.example.fil_rouge.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id_user;
    private String nom_etablissement;
    private String ville;
    private  String adresse;
    private String email;
    private String password;
    private String Description;
    private String num_tele;
    private String image_etablissement;
    private String niveau_scolaire;
    private  String type_ecole;
    private  String validation;

}
