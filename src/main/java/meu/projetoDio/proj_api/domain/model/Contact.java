package meu.projetoDio.proj_api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tab_contact")
@Getter
@Setter
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    
    private String number;

    private String lastMessage;

    private Boolean pinned;

    private Boolean muted;

}
