package ch.drugify.server.webservices;

import ch.drugify.server.entities.Drugs;
import ch.drugify.server.server.DrugsRepository;
import ch.drugify.server.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class DrugsController {

    @Autowired
    DrugsRepository drugsRepository;

    @RequestMapping(value="/api/drugs/{swiss_drug_id}", method=RequestMethod.GET)
    public String getDrug(@PathVariable("swiss_drug_id") String drugId){
        Drugs selected = drugsRepository.getDrugById(drugId);
        return Converter.convertToJson(selected);
    }
    //
}
