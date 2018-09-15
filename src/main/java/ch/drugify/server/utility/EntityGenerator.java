package ch.drugify.server.utility;

import ch.drugify.server.entities.Drugs;
import ch.drugify.server.entities.HistoryItem;
import ch.drugify.server.server.DrugsRepository;

import java.time.Instant;
import java.util.Random;

public class EntityGenerator {

    public static HistoryItem getHistoryItem(DrugsRepository drugRepo, String swissMedicId){
        Drugs drug = drugRepo.getDrugById(swissMedicId);
        HistoryItem historyItem = new HistoryItem();
        historyItem.setTitle(drug.getTitle());
        historyItem.setSwissMedicId(drug.getAuthNrs());
        long start = getRandomTime(0);
        historyItem.setStart(start);
        historyItem.setEnd(getRandomTime(start));
        historyItem.setCreated(getRandomInstant());
        historyItem.setUpdated(getRandomInstant());
        return historyItem;
    }

    public static long getRandomTime(long after){
        Random rnd = new Random();
        long next = 0;
        while(after > next){
            next = rnd.nextLong();
        }
        return next;
    }

    public static Instant getRandomInstant(){
        Random random = new Random();
        return Instant.now().minusSeconds(random.nextInt() * 100);
    }
}
