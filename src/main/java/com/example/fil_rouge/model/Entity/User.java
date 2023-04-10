package com.example.fil_rouge.model.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_user", nullable = false)
    private int id_user;
    @Basic
    @Column(name = "nom_etablissement", nullable = false, length = 255)
    private String nom_etablissement;
    @Basic
    @Column(name = "ville", nullable = false, length = 255)
    private String ville;

    @Basic
    @Column(name = "adresse", nullable = false, length = 255)
    private String adresse;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "Description", nullable = false, length = 255)
    private String Description;
    @Basic
    @Column(name = "num_tele", nullable = false)
    private String num_tele;

    @Basic
    @Column(name = "image_etablissement", nullable = false)
    private String image_etablissement;
    @Basic
    @Column(name = "niveau_scolaire", nullable = false)
    private String niveau_scolaire;

    @Basic
    @Column(name = "type_ecole", nullable = false)
    private String type_ecole;

    @Basic
    @Column(name = "validation", nullable = false)
    private String validation;

    @OneToMany(mappedBy = "usersByIdUser")
    private Collection<Activites> activitesCollection;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
