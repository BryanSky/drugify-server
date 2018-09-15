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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin
@Controller
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private UsersHistoryRepository historyRepository;
    @Autowired
    private DrugsRepository drugsRepository;

    @RequestMapping(value = "/api/users/", method = RequestMethod.PUT, produces="application/json")
    @ResponseBody
    public void storeNewUser(@RequestBody String userJson) {
        Users user = (Users) Converter.convertFromJson(userJson, Users.class);
        userRepository.addUser(user);
    }


    @RequestMapping(value="/api/users/{user-id}", method=RequestMethod.GET, produces="application/json")
    public String getUser(@PathVariable("user-id") String userId){
        Users user = userRepository.getUserById(userId);
        return Converter.convertToJson(user);
    }

    @RequestMapping(value="/api/users/", method=RequestMethod.POST, produces="application/json")
    public void postUser(@RequestBody String userJson){
        Users user = (Users) Converter.convertFromJson(userJson, Users.class);
        userRepository.addUser(user);
    }

    @RequestMapping(value="/api/users/{user-id}", method=RequestMethod.PUT, produces="application/json")
    public void editUser(@PathVariable("user-id") String userId, @RequestBody String userJson){
        Users user = (Users) Converter.convertFromJson(userJson, Users.class);
        userRepository.updateUser(userId, user);
    }

    @RequestMapping(value="/api/users/{user-id}", method=RequestMethod.DELETE, produces="application/json")
    public void deleteUser(@PathVariable("user-id")String userId){
        userRepository.deleteUser(userId);
    }

    @RequestMapping(value="/api/users/{user-id}/drugs", method=RequestMethod.GET, produces="application/json")
    public String getAllDrugsOfUser(@PathVariable("user-id")String userId){
        UsersHistory history = historyRepository.getUserHistoryById(userId);
        if(history==null)return "";
        List<UsersHistory.HistoryItem> items = history.allItems;
        return Converter.convertToJson(items);
    }

    @RequestMapping(value="/api/users/{user-id}/drugs", method=RequestMethod.PUT, produces="application/json")
    public void getConflicts(@RequestBody String drugList, @PathVariable("user-id")String userId){
        UsersHistory history = new UsersHistory();
        if(history==null)return;
        history.allItems = (List<UsersHistory.HistoryItem>) Converter.convertFromJson(drugList, drugList.getClass());
        historyRepository.updateUserHistory(history.getId(), history);
    }

    @RequestMapping(value="/api/users/{user-id}/drugs/{drug-id}", method=RequestMethod.DELETE, produces="application/json")
    public void deleteDrugFromList(@PathVariable("user-id")String userId, @PathVariable("drug-id")String drugId){
        UsersHistory history = historyRepository.getUserHistoryById(userId);
        if(history==null)return;
        history.allItems.stream().forEach(i -> {
            if(i.drugId.equals(drugId)){
                history.allItems.remove(i);
            }
        });
        historyRepository.updateUserHistory(userId, history);
    }

    @RequestMapping(value="/api/users/{user-id}/history/{swiss-medical-id}", method=RequestMethod.PUT, produces="application/json")
    public void addDrugToList(@PathVariable("user-id")String userId, @PathVariable("swiss-medical-id") String drugId){
        Drugs drug = drugsRepository.getDrugById(drugId);
        if(drug!=null){
            historyRepository.updateUserHistory(userId, drug);
        }
    }


    @RequestMapping(value="/api/users/{user-id}/drugs/conflict/{swiss-medic-id}", method=RequestMethod.GET, produces="application/json")
    public String getConflictsById(@PathVariable("user-id") String userId, @PathVariable("swiss-medic-id")String medicId){
        UsersHistory history = historyRepository.getUserHistoryById(userId);
        Drugs drug = drugsRepository.getDrugById(medicId);
        if(history!=null){
            List<Drugs> conflictItems = ConflictCheck.checkForConflicts(drug, history.allItems);
            return Converter.convertToJson(conflictItems);
        }
        return "";
    }
}
