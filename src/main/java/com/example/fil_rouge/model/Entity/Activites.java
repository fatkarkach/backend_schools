package com.example.fil_rouge.model.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Activites {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_activites", nullable = false)
    private int id_activites;
    @Basic
    @Column(name = "nome_activites", nullable = false)
    private String nome_activites;
    @Basic
    @Column(name = "image_activites",nullable = false)
    private String  image_activites;

    @Basic
    @Column(name = "description",nullable = false)
    private String  description;
    @Basic
    @Column(name = "date_activites",nullable = false)
    private  String date_activites;
    @Basic
    @Column(name = "duree",nullable = false)
    private String duree;

    @Basic
    @Column(name = "ville",nullable = false)
    private String   ville;
    @Basic
    @Column(name = "adresse",nullable = false)
     private  String  adresse ;

    @Basic
    @Column(name = "validation",nullable = false)
    private String  validation;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User usersByIdUser;
}
