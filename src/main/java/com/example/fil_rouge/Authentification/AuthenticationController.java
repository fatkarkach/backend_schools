package com.example.fil_rouge.Authentification;

import com.example.fil_rouge.Service.Userservice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/faho")
@RequiredArgsConstructor
public class AuthenticationController {

  @Autowired
  private Userservice recruteurService;

  private final AuthenticationService service;

//  @PostMapping("/register")
//  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
//    System.out.println(request.getDescription());
//    return ResponseEntity.ok(service.register(request));
//  }
@PostMapping("/register")
@CrossOrigin(origins = "http://localhost:4200")
public AuthenticationResponse register(@ModelAttribute RegisterRequest request, @RequestParam("file") MultipartFile my_file) {
  String name_image =null;

  RegisterRequest registerRequest=request;
  Optional<MultipartFile> optionalMy_file = Optional.ofNullable(my_file);
  if (optionalMy_file.isPresent()) {
    if(recruteurService.test_type_file(optionalMy_file.get())){
      name_image=recruteurService.saveImageActivites(optionalMy_file.get());
      System.out.println(name_image);
      registerRequest.setImage_etablissement(name_image);
      return service.register(registerRequest);
    }
  }
  return AuthenticationResponse.builder()
          .token(null)
          .myactivite(null)
          .user_deja_kayn(false)
          .id(null)
          .build();
}
  @PostMapping("/authenticate")
  @CrossOrigin(origins = "http://localhost:4200")
//  public AuthenticationRequest authenticate(
  public AuthenticationResponse authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return service.authenticate(request);
  }


}
