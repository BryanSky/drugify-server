package ch.drugify.webservices;

import ch.drugify.entities.Drugs;
import ch.drugify.repositories.DrugsRepository;
import ch.drugify.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrugController {
    @Autowired
    DrugsRepository drugsRepository;

    @RequestMapping(value="/api/drugs/{swiss-drug-id", method=RequestMethod.GET)
    public String getDrug(@RequestParam("swiss-drug-id")String drug_id){
        Drugs selected = drugsRepository.findById(drug_id).get();
        return Converter.convertToJson(selected);
    }
}


