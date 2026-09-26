package org.journal.repository;

import org.journal.model.JournalEntry;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class JournalEntryRepository implements PanacheRepository<JournalEntry> {

    public List<JournalEntry> findByUser(Long userId) {
        return find("user.id", userId).list();
    }

    public List<JournalEntry> findByUserAndDate(
            Long userId,
            LocalDateTime start,
            LocalDateTime end) {

        return find(
                "user.id = ?1 and timeStamp between ?2 and ?3",
                userId,
                start,
                end).list();
    }
}