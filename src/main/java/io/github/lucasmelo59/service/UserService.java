package io.github.lucasmelo59.service;

import io.github.lucasmelo59.dto.CreatingUser;

import io.github.lucasmelo59.model.User;
import io.github.lucasmelo59.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    public Optional<User> usuarios(Long id) {

        return repository.findByIdOptional(id);

    }

    public Optional<User> atualizar(Long id, CreatingUser creatingUser) {
        Optional<User> user2 = repository.findByIdOptional(id);
        user2.ifPresent(t -> {
            t.setAge(creatingUser.getAge());
            t.setNome(creatingUser.getName());
            repository.persist(t);
        });

        return user2;
    }


    public User create (CreatingUser creatingUser) {
        User user = new User();
        user.setNome(creatingUser.getName());
        user.setAge(creatingUser.getAge());
        repository.persist(user);
        return user;

    }

    public List<User> listALL () {
        PanacheQuery<User> query = repository.findAll();
        return query.list();
    }


    public void delete (Long id) {
        Optional<User> user = repository.findByIdOptional(id);
        user.ifPresent(t -> repository.deleteById(id));

    }
}
