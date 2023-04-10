package com.example.fil_rouge.Authentification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faho")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    System.out.println(request.getDescription());
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
//  public AuthenticationRequest authenticate(
  public AuthenticationResponse authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return service.authenticate(request);
  }


}
