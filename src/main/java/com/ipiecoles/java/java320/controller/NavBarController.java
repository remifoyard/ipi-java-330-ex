package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public class NavBarController {

    @Autowired
    protected EmployeService employeService;

    public String index(Map<String, Object> model) {
        model.put("nbEmployes", employeService.countAllEmploye());
        return "index";
    }

    @GetMapping(value = "/", params = "matricule")
    public String findByMatricule(@RequestParam String matricule, Map<String, Object> model){
        this.index(model);
        model.put("model", employeService.findMyMatricule(matricule));
        return "employes/detail";
    }

}
