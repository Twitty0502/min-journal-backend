package org.journal.resource;

import org.journal.model.JournalEntry;
import org.journal.model.UserDetails;

import org.journal.repository.JournalEntryRepository;
import org.journal.repository.UserRepository;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/journals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JournalEntryResource {

    @Inject
    JournalEntryRepository journalEntryRepository;

    @Inject
    UserRepository userRepository;

    @GET
    public List<JournalEntry> getAll(@QueryParam("userId") Long userId) {
        return journalEntryRepository.find("user.id", userId).list();
    }

    @POST
    @Transactional
    public Response create(
            @QueryParam("userId") Long userId,
            JournalEntry journalEntry) {

        UserDetails user = userRepository.findById(userId);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found")
                    .build();
        }

        journalEntry.setUser(user);

        journalEntryRepository.persist(journalEntry);

        return Response.ok(journalEntry).build();
    }
}