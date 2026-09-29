package org.journal.resource;

import org.journal.model.UserDetails;
import org.journal.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/register")
    @Transactional
    public Response createUser(UserDetails user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Username is already in use")
                    .build();
        }

        // krypterar lösenordet när man sparar till db
        String hashedPassword = BCrypt.hashpw(
                user.getPassword(),
                BCrypt.gensalt());
        user.setPassword(hashedPassword);

        userRepository.persist(user);

        return Response.ok(user).build();
    }

    @POST
    @Path("/login")
    public Response login(UserDetails user) {

        UserDetails existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser == null ||
                !BCrypt.checkpw(
                        user.getPassword(), existingUser.getPassword())) {

            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Wrong username or password")
                    .build();
        }

        return Response.ok(existingUser).build();
    }
}