package org.journal.repository;

import org.journal.model.JournalEntry;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JournalEntryRepository implements PanacheRepository<JournalEntry> {

}
