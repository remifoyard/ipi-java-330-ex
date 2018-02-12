package com.ipiecoles.java.java320.Controller;

import com.ipiecoles.java.java320.model.Commercial;
import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.model.Technicien;
import com.ipiecoles.java.java320.model.Manager;
import com.ipiecoles.java.java320.service.EmployeService;
import com.ipiecoles.java.java320.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@RequestMapping(value = "/techniciens")
public class technicienController {

    @Autowired
    private EmployeService employeService;
    @Autowired
    private TechnicienService technicienService;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String indexTechnicien(@PathVariable(value = "id") Long idCom, Technicien technicien, Map<String, Object> model) {
        if (idCom == 0) {
            return "erreur";
        } else {
            Technicien tech = employeService.updateEmploye(idCom,technicien);
            model.put("employe", tech);
            return "employes/detail";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView newTechnicien(Technicien technicien, Map<String, Object> model, RedirectAttributes attributes) {
        Technicien tech = employeService.creerEmploye(technicien);
        attributes.addAttribute("success","Création du technicien");
        model.put("employe", tech);
        return new RedirectView("/employes/" + tech.getId());
    }

    @RequestMapping(value = "/{id}/manager/add", method = RequestMethod.GET, params = "matricule")
    public String addManager(@PathVariable(value = "id") Long id, @RequestParam("matricule") String matricule, Map<String, Object> model) {
        if (id == 0) {
            return "erreur";
        } else {
            Technicien tech = (Technicien) employeService.findById(id);
            Manager mana = (Manager) employeService.findMyMatricule(matricule);
            tech.setManager(mana);
            tech = employeService.updateEmploye(id, tech);
            model.put("employe", tech);
            //technicienService.addManager(id,matricule);
            return "employes/detail";
        }
    }


    @RequestMapping(value = "/{id}/manager/{idmanager}/delete", method = RequestMethod.GET )
    public String deleteManager(@PathVariable(value = "id") Long id, @PathVariable(value = "id") Long idManager, Map<String, Object> model){
        if(id == 0 || id == null){return "erreur";}
        else {
            Technicien tech = (Technicien) employeService.findById(id);
            //If manager = idManger alors on set = null
            tech.setManager(null);
            this.employeService.updateEmploye(id,tech);
            model.put("employe", tech);
            return "/employes/detail";
        }
    }
}