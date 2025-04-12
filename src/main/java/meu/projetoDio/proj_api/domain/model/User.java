package meu.projetoDio.proj_api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "tab_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Contact> contacts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tool> tools;
}
