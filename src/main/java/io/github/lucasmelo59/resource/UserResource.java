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
import java.util.Optional;

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
        return Response.ok(service.create(creatingUser)).build();
    }

    @GET
    public Response listallUsers() {

        return Response.ok(service.listALL()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deleteUser(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    @Path("{id}")
    @Transactional
    public User usuario(@PathParam("id") Long id) {
        return service.usuarios(id).orElseThrow(() -> new UserExceception("oi", Response.Status.NO_CONTENT));
    }


    @PUT
    @Path("{id}")
    @Transactional
    public User updateUser(@PathParam("id") Long id, CreatingUser userData) {

        return service.atualizar(id,userData).orElseThrow(() -> new UserExceception("errado", Response.Status.NO_CONTENT));
    }


}
