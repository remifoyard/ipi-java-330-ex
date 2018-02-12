package com.ipiecoles.java.java320.Controller;

import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.lang.Long;
import java.lang.Integer;
import java.util.Map;

@Controller
@RequestMapping(value = "/employes")
public class employeController {
    @Autowired
    private EmployeService employeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET )
    public String detail(@PathVariable(value = "id") Long idE, Map<String, Object> model){
        if(idE == 0){return "erreur";}
        else {
            Employe emp = employeService.findById(idE);
            model.put("employe", emp);
            return "employes/detail";
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET, params = {"matricule"} )
    public String detail2(@RequestParam("matricule") String matricule, Map<String, Object> model){
        try{
            Employe emp = employeService.findMyMatricule(matricule);
            model.put("employe", emp);
            return "employes/detail";
        }
        catch(EntityNotFoundException exception){
            return "erreur";
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET, params = {"page","size","sortProperty","sortDirection"} )
    public String liste(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sortProperty") String sortProperty, @RequestParam("sortDirection") String sortDirection, Map<String, Object> model){
        Page<Employe> emp = employeService.findAllEmployes(page,size,sortProperty,sortDirection);

        model.put("employes", emp);
        model.put("size", size);
        model.put("totalElem", emp.getTotalElements());
        model.put("totalPages", emp.getTotalPages());
        model.put("iterator", emp.getNumber());
        model.put("page", page);
        model.put("sortProperty", sortProperty);
        model.put("sortDirection", sortDirection);
        model.put("start", page * size +1);
        model.put("end", page * size + emp.getNumberOfElements());
        return "employes/liste";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET )
    public RedirectView deleteEmp(@PathVariable(value = "id") Long idE, RedirectAttributes attributes){
        if(idE == 0 || idE == null){return new RedirectView("erreur");}
        else {
            employeService.deleteEmploye(idE);
            attributes.addAttribute("page", 0);
            attributes.addAttribute("size", 10);
            attributes.addAttribute("sortProperty", "matricule");
            attributes.addAttribute("sortDirection", "ASC");
            attributes.addAttribute("success", "Suppression effectuée");
            return new RedirectView("/employes");
        }
    }
}
