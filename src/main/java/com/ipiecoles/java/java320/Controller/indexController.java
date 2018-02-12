package com.ipiecoles.java.java320.Controller;

import com.ipiecoles.java.java320.model.Commercial;
import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class indexController {

    @Autowired
    private EmployeService employeservice;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model){
        Long nbrEmploye = employeservice.countAllEmploye();
        model.put("nbrEmploye", nbrEmploye);
        return "index";
    }

}
