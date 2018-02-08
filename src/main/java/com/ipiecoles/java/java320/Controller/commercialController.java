package com.ipiecoles.java.java320.Controller;

import com.ipiecoles.java.java320.model.Commercial;
import com.ipiecoles.java.java320.service.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(value = "/commercials")
public class commercialController {

    @Autowired
    private CommercialService commercialService;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String indexCommercial(@PathVariable(value = "id") Long idCom, Commercial commercial, Map<String, Object> model) {
        if (idCom == 0) {
            return "erreur";
        } else {
            Commercial com = commercialService.updateEmploye(idCom,commercial);
            model.put("employe", com);
            return "employes/detail";
        }
    }
}