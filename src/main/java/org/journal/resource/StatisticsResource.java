package org.journal.resource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.journal.model.JournalEntry;
import org.journal.repository.JournalEntryRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/statistics")
@Produces(MediaType.APPLICATION_JSON)
public class StatisticsResource {

    // för journalsökningen i db
    @Inject
    JournalEntryRepository journalEntryRepository;

    @GET
    public Map<String, Double> getStatistics(
            // de värden vi hämtar för att ta ut statistiken
            @QueryParam("userId") Long userId,
            @QueryParam("start") String start,
            @QueryParam("end") String end) {

        // parsa texten från URL:en till LocalDateTime
        // för att kunna använda datumen i sökning
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);

        // hämta alla journaler för vald användare som är skapade
        // inom spannet av valt start och slutdatum
        List<JournalEntry> journals = journalEntryRepository.findByUserAndDate(
                userId,
                startDate,
                endDate);

        // skapar en map utifrån status t.ex. HAPPY och en procent utifrån andel
        Map<String, Double> statistics = new HashMap<>();

        // alla journaler för valda tidsperioden
        int total = journals.size();

        // ifall inga journaler hittas för tidsperioden
        if (total == 0) {
            return statistics;
        }

        // Går igenom alla journaler och hämtar statusen(FEELING) från varje journal.
        // Räknar ut hur många procent varje journal motsvarar av det totala antalet.
        // t.ex. 1 glad av 5 totala journaler blir ju då 20% av totalen
        // Lägger sedan ihop procenten för journaler med samma status i Map:en.
        for (JournalEntry journal : journals) {

            String status = journal.getStatus().name();

            statistics.put(
                    status,
                    statistics.getOrDefault(status, 0.0)
                            + (100.0 / total));
        }

        return statistics;
    }
}