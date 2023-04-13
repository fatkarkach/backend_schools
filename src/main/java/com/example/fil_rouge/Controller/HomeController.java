package com.example.fil_rouge.Controller;

import com.example.fil_rouge.Authentification.AuthenticationResponse;
import com.example.fil_rouge.Service.Activiteservice;
import com.example.fil_rouge.Service.Userservice;
import com.example.fil_rouge.model.DTO.ActivitesDTO;
import com.example.fil_rouge.model.Entity.Activites;
import com.example.fil_rouge.model.Entity.User;
import com.example.fil_rouge.model.Repository.ActivitesRepository;
import com.example.fil_rouge.model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faho/activite")
public class HomeController {
    @Autowired
    private Activiteservice activiteservice;
    @Autowired
    private Userservice userservice;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivitesRepository activitesRepository;


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/auth/save" , method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public @ResponseBody ActivitesDTO testpost( @ModelAttribute ActivitesDTO test , @RequestParam("file")
    MultipartFile photoData){
        ActivitesDTO st=test;
        String image_activites =null;
        Optional<User> user= userservice.getuser(test.getId_user());
        Optional<MultipartFile> optionalMy_file = Optional.ofNullable(photoData);
        if(user.isPresent()){
            Activites activites=new Activites();
            if (optionalMy_file.isPresent()) {
                System.out.println(image_activites);
                if(userservice.test_type_file(optionalMy_file.get())){
                    System.out.println(image_activites);
                    image_activites=userservice.saveImageActivites(optionalMy_file.get());
                    System.out.println(image_activites);
                    activites.setImage_activites(image_activites);
                    activites.setDescription(st.getDescription());
                    activites.setDate_activites(st.getDate_activites());
                    activites.setNome_activites(st.getNome_activites());
                    activites.setValidation(st.getValidation());
                    activites.setAdresse(st.getAdresse());
                    activites.setDuree(st.getDuree());
                    activites.setVille(st.getVille());
                    activites.setUsersByIdUser(user.get());
                    activiteservice.saveemploi(activites);
                    System.out.println("emploi tzadt bravou ");
                }
            }
        }
        return test;
    }
    //get my les offes
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth/getallactivites")
    @ResponseBody
    public List<ActivitesDTO> getma_activites() {
        return  activiteservice.getactivites_all();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth/getetablissement")
    @ResponseBody
    List<User> all() {
        return userRepository.findAll();
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/auth/get_image/{filename}")
//    public Resource getImage(@PathVariable String filename){
//        return userservice.getImageActivites(filename);
//    }
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


    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/auth/getallmaactivites/{id_user}")
    @ResponseBody
    public List<Activites> getallmaactivites(@PathVariable int id_user) {
        List<Activites> all_activite = activiteservice.getactivites_ma();
        List<Activites> all_ma_activite = new ArrayList<>();
        for (Activites ac :all_activite) {
            System.out.println(id_user);
            if (ac.getUsersByIdUser().getId_user() == id_user)
            {
                System.out.println(ac.getUsersByIdUser().getId_user());
                all_ma_activite.add(ac);
            }
        }
        return  all_ma_activite;
    }

//    update validation
    @CrossOrigin(origins = "http://localhost:4200/")
    @PatchMapping("/auth/updatemaactivites/{id_activites}/{validation}")
    @ResponseBody
        public Activites updatemaactivites(@PathVariable int id_activites, @PathVariable String  validation) {
    Optional<Activites> activitesOptional = activitesRepository.findById(id_activites);
    if (activitesOptional.isPresent()) {
        Activites activites = activitesOptional.get();
        activites.setValidation(validation);
        return activitesRepository.save(activites);
    }else {
        return null;
    }
}

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/auth/get_image/{filename}")
    @ResponseBody
    public Resource download_image(@PathVariable String filename){
        if (!filename.isEmpty())
            return userservice.getImageActivites(filename);
        else
            return null;
    }

}
