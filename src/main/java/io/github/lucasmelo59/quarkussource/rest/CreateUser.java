package io.github.lucasmelo59.quarkussource.rest;

import io.github.lucasmelo59.quarkussource.domain.model.User;
import io.github.lucasmelo59.quarkussource.domain.repository.UserRepository;
import io.github.lucasmelo59.quarkussource.rest.dto.CreatingUser;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.exception.PanacheQueryException;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")

@Consumes(MediaType.APPLICATION_JSON)

@Produces({MediaType.APPLICATION_JSON})

public class CreateUser {

    public CreateUser (UserRepository repository){}

    @POST
    @Transactional
    public Response createUsers(CreatingUser creatingUser) {
        User user = new User();
        user.setAge(creatingUser.getAge());
        user.setNome(creatingUser.getName());
        user.persist();
        return Response.ok(user).build();
    }

    @GET
    public Response listallUsers() {
        PanacheQuery<PanacheEntityBase> query = User.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        User user = User.findById(id);
        if( user !=  null) {
            user.delete();
            return Response.ok().build();

        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreatingUser userData) {
        User user = User.findById(id);
        if ( user != null) {
            user.setAge(userData.getAge());
            user.setNome(userData.getName());
            user.persist();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
