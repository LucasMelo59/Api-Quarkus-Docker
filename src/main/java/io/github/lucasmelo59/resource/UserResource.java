package io.github.lucasmelo59.resource;

import io.github.lucasmelo59.exception.UserExceception;
import io.github.lucasmelo59.model.User;
import io.github.lucasmelo59.repository.UserRepository;
import io.github.lucasmelo59.dto.CreatingUser;
import io.github.lucasmelo59.service.UserService;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.hibernate.annotations.NotFound;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")

@Consumes(MediaType.APPLICATION_JSON)

@Produces({MediaType.APPLICATION_JSON})

public class UserResource {
    @Inject
    UserRepository repository;
    @Inject
   UserService service;
    @POST
    @Transactional
    public Response createUsers(CreatingUser creatingUser) {
        User user = new User();
        user.setAge(creatingUser.getAge());
        user.setNome(creatingUser.getName());
        repository.persist(user);
        return Response.ok(user).build();
    }

    @GET
    public Response listallUsers() {
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        User user = repository.findById(id);
        if (user != null) {
            repository.delete(user);
            return Response.ok().build();

        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @GET
    @Path("{id}")
    @Transactional
    public User usuario(@PathParam("id") Long id) {
        return service.usuarios(id).orElseThrow(() -> new NullPointerException("oi"));
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreatingUser userData) {

        return service.atualizar(id).
    }


}
