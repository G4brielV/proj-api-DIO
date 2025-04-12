package meu.projetoDio.proj_api.controller.dto;

import meu.projetoDio.proj_api.domain.model.User;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record UserDto(
        Long id,
        String name,
        List<ContactDto> contacts,
        List<ToolDto> tools
) {
    public UserDto(User model){
        this(
                model.getId(),
                model.getName(),
                ofNullable(model.getContacts()).orElse(emptyList()).stream().map(ContactDto::new).collect(toList()),
                ofNullable(model.getTools()).orElse(emptyList()).stream().map(ToolDto::new).collect(toList())
        );
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setContacts(ofNullable(this.contacts).orElse(emptyList()).stream().map(ContactDto::toModel).collect(toList()));
        model.setTools(ofNullable(this.tools).orElse(emptyList()).stream().map(ToolDto::toModel).collect(toList()));
        return model;
    }
}
