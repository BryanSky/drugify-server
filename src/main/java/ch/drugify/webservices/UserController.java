package ch.drugify.webservices;

import ch.drugify.entities.Drugs;
import ch.drugify.entities.Users;
import ch.drugify.entities.UsersHistory;
import ch.drugify.repositories.DrugsRepository;
import ch.drugify.repositories.UsersHistoryRepository;
import ch.drugify.repositories.UsersRepository;
import ch.drugify.utility.Converter;
import ch.drugify.validation.ConflictCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private DrugsRepository drugsRepository;
    @Autowired
    private UsersHistoryRepository historyRepository;

    @RequestMapping(value="/api/users/{user-id}", method=RequestMethod.GET)
    public String getUser(@RequestParam("user-id") String userId){
        Optional<Users> user = userRepository.findById(userId);
        return Converter.convertToJson(user.get());
    }

    @RequestMapping(value="/api/users/", method=RequestMethod.POST)
    public void postUser(@RequestBody String userJson){
        Users user = (Users) Converter.convertFromJson(userJson, Users.class);
        userRepository.save(user);
    }

    @RequestMapping(value="/api/users/{user-id}", method=RequestMethod.PUT)
    public void editUser(@RequestParam("user-id") String userId, @RequestBody String userJson){
        Users user = (Users) Converter.convertFromJson(userJson, Users.class);
        userRepository.save(user);
    }

    @RequestMapping(value="/api/users/{user-id}", method=RequestMethod.DELETE)
    public void deleteUser(@RequestParam("user-id")String userId){
        userRepository.deleteById(userId);
    }

    @RequestMapping(value="/api/users/{user-id}/drugs", method=RequestMethod.GET)
    public String getConflicts(@RequestParam("user-id")String userId){
        Optional<UsersHistory> history = historyRepository.findById(userId);
        List<UsersHistory.HistoryItem> items = history.get().allItems;
        return null;
    }

    @RequestMapping(value="/api/users/{user-id}/drugs", method=RequestMethod.PUT)
    public void getConflicts(@RequestParam("drugList")String drugList, @RequestParam("user-id")String userId){
        UsersHistory history = new UsersHistory();
        history.allItems = (List<UsersHistory.HistoryItem>) Converter.convertFromJson(drugList, drugList.getClass());
        historyRepository.save(history);
    }

    @RequestMapping(value="/api/users/{user-id}/drugs/{drug-id}", method=RequestMethod.PUT)
    public void addDrugById(@RequestParam("user-id")String userId, @RequestParam("drug-id")String drugId){

    }

    public void deleteDrugFromList(@RequestParam("user-id")String userId, @RequestParam("drug-id")String drugId){

    }


    @RequestMapping(value="/api/users/{user-id}/drugs/conflict/{swiss-medic-id}", method=RequestMethod.GET)
    public String getConflictsById(@RequestParam("user-id") String userId, @RequestParam("swiss-medic-id")String medicId){
        UsersHistory history = historyRepository.findById(userId).get();
        Drugs drug = drugsRepository.findById(medicId).get();
        List<Drugs> conflictItems = ConflictCheck.checkForConflicts(drug, history.allItems);
        return Converter.convertToJson(conflictItems);
    }
}
