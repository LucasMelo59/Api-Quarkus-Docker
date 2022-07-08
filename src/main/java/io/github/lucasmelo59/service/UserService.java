package io.github.lucasmelo59.service;

import io.github.lucasmelo59.dto.CreatingUser;
import io.github.lucasmelo59.form.AtualizacaoFOrm;
import io.github.lucasmelo59.model.User;
import io.github.lucasmelo59.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    public Optional<User> usuarios(Long id) {

        return repository.findByIdOptional(id);

    }

    public Optional<User> atualizar (Long id, CreatingUser creatingUser){

        User user = repository.findById(id);
        if(user != null) {
            user.setNome(creatingUser.getName());
            user.setAge(creatingUser.getAge());
            repository.persist(user);
            return Optional.of(user);
        }
        return Optional.of(user);
    }

}
