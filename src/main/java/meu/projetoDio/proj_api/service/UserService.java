package meu.projetoDio.proj_api.service;

import meu.projetoDio.proj_api.domain.model.Contact;
import meu.projetoDio.proj_api.domain.model.User;

public interface UserService extends CrudService<Long, User>{
    Contact insertNewContact(Long idContato, Contact model);
}
