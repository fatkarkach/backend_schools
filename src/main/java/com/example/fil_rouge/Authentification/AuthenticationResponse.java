package com.example.fil_rouge.Authentification;

import com.example.fil_rouge.model.Entity.Activites;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  private boolean user_deja_kayn;
  private Integer id;
  private List<Activites> myactivite;
  private String nom;
  private String image;

  private String token;
}
