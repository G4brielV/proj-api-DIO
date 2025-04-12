package meu.projetoDio.proj_api.service.impl;

import meu.projetoDio.proj_api.domain.model.Contact;
import meu.projetoDio.proj_api.domain.model.Tool;
import meu.projetoDio.proj_api.domain.repository.UserRepository;
import meu.projetoDio.proj_api.service.exception.BusinessException;
import meu.projetoDio.proj_api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import meu.projetoDio.proj_api.domain.model.User;
import meu.projetoDio.proj_api.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public User create(User userToCreate) {
        ofNullable(userToCreate).orElseThrow(() -> new BusinessException("User to create must not be null."));
        ofNullable(userToCreate.getTools()).orElseThrow(() -> new BusinessException("User to create must not be null."));

        validateDuplicateContactNumbers(userToCreate.getContacts());
        validateDuplicateToolsIcons(userToCreate.getTools());
        validateAtLeastOneTool(userToCreate.getTools());

        return this.userRepository.save(userToCreate);
    }


    @Transactional
    public User update(Long id, User userToUpdate) {
        User dbUser = this.findById(id);

        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        validateDuplicateContactNumbers(userToUpdate.getContacts());
        validateDuplicateToolsIcons(userToUpdate.getTools());
        validateAtLeastOneTool(userToUpdate.getTools());

        dbUser.setName(userToUpdate.getName());
        dbUser.setContacts(userToUpdate.getContacts());
        dbUser.setTools(userToUpdate.getTools());

        return this.userRepository.save(dbUser);
    }

    @Transactional
    public void delete(Long id) {
        User dbUser = this.findById(id);
        this.userRepository.delete(dbUser);
    }

    @Transactional
    public Contact insertNewContact(Long id, Contact contactToInsert){
        User dbUser = this.findById(id);
        dbUser.getContacts().add(contactToInsert);
        validateDuplicateContactNumbers(dbUser.getContacts());
        return contactToInsert;
    }

    private void validateAtLeastOneTool(List<Tool> tools) {
        if (tools == null || tools.isEmpty()) {
            throw new BusinessException("User must have at least one tool.");
        }
    }

    private void validateDuplicateContactNumbers(List<Contact> contacts) {
        Set<String> uniqueNumbers = new HashSet<>();

        for (Contact contact : contacts) {
            if (!uniqueNumbers.add(contact.getNumber())) {
                throw new BusinessException("Duplicate contact number found: " + contact.getNumber());
            }
        }
    }

    private void validateDuplicateToolsIcons(List<Tool> tools) {

        Set<String> uniqueIcons = new HashSet<>();

        for (Tool tool : tools) {
            if (!uniqueIcons.add(tool.getIcon())) {
                throw new BusinessException("Duplicate contact number found: " + tool.getIcon());
            }
        }
    }

}
