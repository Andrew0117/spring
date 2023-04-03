package org.web.user.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web.user.filter.OsobaFilter;
import org.web.user.service.OsobaService;
import org.web.user.vm.OsobaVM;

import java.io.Serializable;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class OsobaController implements Serializable {

    private static final long serialVersionUID = 8693543019055027217L;

    private static final Logger LOGGER = Logger.getLogger(OsobaController.class);

    @Autowired
    private OsobaService osobaService;

    @RequestMapping(value = "/osoba", method = RequestMethod.GET)
    public String osobaGET(Model model) {
        List<OsobaVM> osobaVMList = osobaService.getAll();

        model.addAttribute("osoba", new OsobaFilter());
        model.addAttribute("osobaVMList", osobaVMList);
        return "osoba";
    }

    @RequestMapping(value = "/osoba", method = RequestMethod.POST)
    public String osobaPOST(
            @ModelAttribute(value = "osoba") OsobaFilter osoba,
            Model model) {
        List<OsobaVM> osobaVMList = osobaService.findOsobaFilter(osoba.getOsobaVM());

        model.addAttribute("osoba", osoba);
        model.addAttribute("osobaVMList", osobaVMList);
        return "osoba";
    }

}
