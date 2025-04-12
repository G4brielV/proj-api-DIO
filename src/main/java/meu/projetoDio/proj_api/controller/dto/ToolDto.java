package meu.projetoDio.proj_api.controller.dto;

import meu.projetoDio.proj_api.domain.model.Tool;

public record ToolDto(Long id, String icon, String description)  {
    public ToolDto(Tool model){
        this(model.getId(), model.getIcon(), model.getDescription());
    }

    public Tool toModel() {
        Tool model = new Tool();
        model.setId(this.id);
        model.setIcon(this.icon);
        model.setDescription(this.description);
        return model;

    }
}
