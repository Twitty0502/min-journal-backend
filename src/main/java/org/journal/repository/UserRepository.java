package org.journal.repository;

import org.journal.model.UserDetails;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserDetails> {

    public UserDetails findByUsername(String username) {
        return find("username", username).firstResult();
    }
}