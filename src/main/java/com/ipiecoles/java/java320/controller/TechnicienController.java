package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.model.Technicien;
import com.ipiecoles.java.java320.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RequestMapping("/techniciens")
@Controller
public class TechnicienController extends NavBarController{
    @Autowired
    private TechnicienService technicienService;

    @PostMapping("/{id}")
    public RedirectView save(@PathVariable(name = "id") Long id, Technicien employe, Map<String, Object> model) {
        super.index(model);
        if(employe != null){
            employe = this.employeService.updateEmploye(id, employe);
        }
        model.put("model", employe);

        return new RedirectView("/employes/ " + id);
    }

    @PostMapping("/save")
    public RedirectView saveNew(Technicien employe, Map<String, Object> model) {
        super.index(model);
        employe = this.employeService.creerEmploye(employe);
        model.put("model", employe);

        return new RedirectView("/employes/ " + employe.getId());    }
}
