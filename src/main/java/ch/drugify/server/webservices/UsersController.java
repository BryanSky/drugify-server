package ch.drugify.server.webservices;

import ch.drugify.server.entities.Drugs;
import ch.drugify.server.entities.Users;
import ch.drugify.server.entities.UsersHistory;
import ch.drugify.server.server.DrugsRepository;
import ch.drugify.server.server.UsersHistoryRepository;
import ch.drugify.server.server.UsersRepository;
import ch.drugify.server.utility.Converter;
import ch.drugify.server.validation.ConflictCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class UsersController {

    private static final Logger logger = LoggerFactory
            .getLogger(UsersController.class);

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private UsersHistoryRepository historyRepository;
    @Autowired
    private DrugsRepository drugsRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info(
                "This is Default Home REST page.\n\n The client locale is {}.",
                locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "status";
    }

    @RequestMapping(value = "api/users/", method = RequestMethod.PUT)
    @ResponseBody
    public String getAllPersons(@RequestBody()String userJson) {
        Users user = (Users) Converter.convertFromJson(userJson, Users.class);
        userRepository.addUser(user);
        return "allPersons";
    }


    @RequestMapping(value="/api/users/{user-id}", method=RequestMethod.GET)
    public String getUser(@PathVariable("user-id") String userId){
        Users user = userRepository.getUserById(userId);
        return Converter.convertToJson(user);
    }

    @RequestMapping(value="/api/users/", method=RequestMethod.POST)
    public void postUser(@RequestBody String userJson){
        Users user = (Users) Converter.convertFromJson(userJson, Users.class);
        userRepository.addUser(user);
    }

    @RequestMapping(value="/api/users/{user-id}", method=RequestMethod.PUT)
    public void editUser(@PathVariable("user-id") String userId, @RequestBody String userJson){
        Users user = (Users) Converter.convertFromJson(userJson, Users.class);
        userRepository.addUser(user);
    }

    @RequestMapping(value="/api/users/{user-id}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable("user-id")String userId){
        userRepository.deleteUser(userId);
    }

    @RequestMapping(value="/api/users/{user-id}/drugs", method=RequestMethod.GET)
    public String getConflicts(@PathVariable("user-id")String userId){
        UsersHistory history = historyRepository.getUserHistoryById(userId);
        List<UsersHistory.HistoryItem> items = history.allItems;

        return null;
    }

    @RequestMapping(value="/api/users/{user-id}/drugs", method=RequestMethod.PUT)
    public void getConflicts(@RequestBody String drugList, @PathVariable("user-id")String userId){
        UsersHistory history = new UsersHistory();
        history.allItems = (List<UsersHistory.HistoryItem>) Converter.convertFromJson(drugList, drugList.getClass());
        historyRepository.updateUserHistory(history.getId(), history);
    }

    @RequestMapping(value="/api/users/{user-id}/drugs/{swiss-medic-id}", method=RequestMethod.PUT)
    public void addDrugById(@PathVariable("user-id")String userId, @PathVariable("swiss-medic-id")String drugId){

    }

    public void deleteDrugFromList(@PathVariable("user-id")String userId, @PathVariable("drug-id")String drugId){

    }


    @RequestMapping(value="/api/users/{user-id}/drugs/conflict/{swiss-medic-id}", method=RequestMethod.GET)
    public String getConflictsById(@PathVariable("user-id") String userId, @PathVariable("swiss-medic-id")String medicId){
        UsersHistory history = historyRepository.getUserHistoryById(userId);
        Drugs drug = drugsRepository.getDrugById(medicId);
        List<Drugs> conflictItems = ConflictCheck.checkForConflicts(drug, history.allItems);
        return Converter.convertToJson(conflictItems);
    }
}
