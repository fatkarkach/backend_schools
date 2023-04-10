package com.example.fil_rouge.Authentification;


import com.example.fil_rouge.Configuration.JwtService;
import com.example.fil_rouge.model.Entity.Role;
import com.example.fil_rouge.model.Entity.User;
import com.example.fil_rouge.model.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
            .nom_etablissement(request.getNom_etablissement())
            .ville(request.getVille())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .num_tele(request.getNum_tele())
            .image_etablissement(request.getImage_etablissement())
            .Description(request.getDescription())
            .niveau_scolaire(request.getNiveau_scolaire())
            .type_ecole(request.getType_ecole())
            .validation(request.getValidation())
            .adresse(request.getAdresse())
            .role(Role.USER)
            .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    System.out.println(jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
