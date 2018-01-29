package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.model.Manager;
import com.ipiecoles.java.java320.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RequestMapping("/managers")
@Controller
public class ManagerController extends NavBarController{
    @Autowired
    private ManagerService managerService;

    @GetMapping("/{id}/techniciens/{idTech}/delete")
    public String deleteTechnicien(@PathVariable("id") Long id, @PathVariable("idTech") Long idTech, Map<String, Object> model){
        super.index(model);
        managerService.deleteTechniciens(id, idTech);
        model.put("model", employeService.findById(id));
        return "employes/detail";
    }

    @GetMapping("/{id}/techniciens/{matricule}/add")
    public String deleteTechnicien(@PathVariable("id") Long id, @PathVariable("matricule") String matricule, Map<String, Object> model){
        super.index(model);
        managerService.addTechniciens(id, matricule);
        model.put("model", employeService.findById(id));
        return "employes/detail";
    }

    @PostMapping("/{id}")
    public RedirectView save(@PathVariable(name = "id") Long id, Manager employe, Map<String, Object> model) {
        super.index(model);
        if(employe != null){
            employe = this.employeService.updateEmploye(id, employe);
        }
        model.put("model", employe);

        return new RedirectView("/employes/ " + id);
    }

    @PostMapping("/save")
    public RedirectView saveNew(Manager employe, Map<String, Object> model) {
        super.index(model);
        employe = this.employeService.creerEmploye(employe);
        model.put("model", employe);

        return new RedirectView("/employes/ " + employe.getId());
    }
}