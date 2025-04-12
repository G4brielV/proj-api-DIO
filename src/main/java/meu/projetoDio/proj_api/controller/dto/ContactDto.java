package meu.projetoDio.proj_api.controller.dto;

import meu.projetoDio.proj_api.domain.model.Contact;


public record ContactDto(
        Long id,
        String name,
        String number,
        String lastMessage,
        Boolean pinned,
        Boolean muted

) {
    public ContactDto(Contact model){
        this(
                model.getId(),
                model.getName(),
                model.getNumber(),
                model.getLastMessage(),
                model.getPinned(),
                model.getMuted()
        );
    }


    public Contact toModel() {
        Contact model = new Contact();
        model.setId(this.id);
        model.setName(this.name);
        model.setNumber(this.number);
        model.setLastMessage(this.lastMessage);
        model.setPinned(this.pinned);
        model.setMuted(this.muted);
        return model;
    }
}
