package com.example.fil_rouge.Controller;

import com.example.fil_rouge.Authentification.AuthenticationResponse;
import com.example.fil_rouge.Service.Activiteservice;
import com.example.fil_rouge.Service.Userservice;
import com.example.fil_rouge.model.DTO.ActivitesDTO;
import com.example.fil_rouge.model.Entity.Activites;
import com.example.fil_rouge.model.Entity.User;
import com.example.fil_rouge.model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faho")
public class HomeController {
    @Autowired
    private Activiteservice activiteservice;
    @Autowired
    private Userservice userservice;

    @Autowired
    private UserRepository userRepository;


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/auth/save")
    @ResponseBody
    public ActivitesDTO testpost( @RequestBody ActivitesDTO test){
        ActivitesDTO st=test;

        Optional<User> user= userservice.getuser(test.getId_user());
        if(user.isPresent()){

            Activites activites=new Activites();
            activites.setDescription(st.getDescription());
            activites.setDate_activites(st.getDate_activites());
            activites.setNome_activites(st.getNome_activites());
            activites.setAdresse(st.getAdresse());
            activites.setDuree(st.getDuree());
            activites.setVille(st.getVille());
            activites.setUsersByIdUser(user.get());
            activiteservice.saveemploi(activites);
            System.out.println("emploi tzadt bravou ");
        }
        return test;
    }
    //get my les offes
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/auth/getallactivites")
    @ResponseBody
    public List<ActivitesDTO> getma_activites() {
        return  activiteservice.getactivites_all();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth/getactivites")
    @ResponseBody
    List<User> all() {
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth/get_image/{filename}")
    public Resource getImage(@PathVariable String filename){
        return userservice.getImageActivites(filename);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth/getmaactivites/{id_user}")
    public ResponseEntity<AuthenticationResponse> getmaoffera(@PathVariable int id_user) {
        Optional<User> user = userRepository.findById(id_user);
        AuthenticationResponse aaa = null;
        if (user.isPresent()) {
            aaa = AuthenticationResponse.builder()
                    .token(null)
                    .user_deja_kayn(true)
                    .id(user.get().getId_user())
                    .nom(user.get().getNom_etablissement())
                    .image(user.get().getImage_etablissement())
                    .myactivite((List<Activites>) user.get().getActivitesCollection())
                    .build();
        }
        return ResponseEntity.ok(aaa);
    }



}
