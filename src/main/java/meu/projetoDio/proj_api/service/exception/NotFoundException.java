package meu.projetoDio.proj_api.service.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super("Resource not found.");
    }
}
