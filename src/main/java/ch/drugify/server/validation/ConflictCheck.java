package ch.drugify.server.validation;

import ch.drugify.server.entities.Drugs;
import ch.drugify.server.entities.HistoryItem;
import ch.drugify.server.entities.UsersHistory;

import java.util.Collections;
import java.util.List;

public class ConflictCheck {
    public static List<Drugs> checkForConflicts(Drugs drug, List<HistoryItem> alreadyConsumed){
        return Collections.emptyList();
    }

    public static List<Drugs> checkForConflicts(List<HistoryItem> alreadyConsumed){
        return Collections.emptyList();
    }
}
