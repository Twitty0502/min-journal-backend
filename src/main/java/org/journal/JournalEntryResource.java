package org.journal;

import org.journal.model.JournalEntry;
import org.journal.repository.JournalEntryRepository;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/journals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JournalEntryResource {

    @Inject
    JournalEntryRepository journalEntryRepository;

    @GET
    public List<JournalEntry> getAll() {
        return journalEntryRepository.listAll();
    }

    @POST
    @Transactional
    public JournalEntry create(JournalEntry journalEntry) {
        journalEntryRepository.persist(journalEntry);
        return journalEntry;
    }
}