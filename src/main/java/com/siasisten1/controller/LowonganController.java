package com.siasisten1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.siasisten1.dao.LowonganMapper;
import com.siasisten1.model.Lowongan;
import com.siasisten1.service.LowonganService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LowonganController {
  @Autowired
  LowonganService lowonganDAO;
	
  @RequestMapping("/lowongan/view/{id_lowongan}")
  public String show(Model model, @PathVariable(value = "id_lowongan") int id_lowongan) {
    model.addAttribute("student", "da");
    return "lowongan/show";
  }

  @RequestMapping("/lowongan/viewall")
  public String show(Model model) {
    model.addAttribute("student", "da");
    return "lowongan/index";
  }
  
  @RequestMapping("/lowongan/tambah")
  public String add(Model model) {
	model.addAttribute("lowongan", new Lowongan());
	return "lowongan/form-add";
  }
  
  @RequestMapping(value="/lowongan/tambah", method=RequestMethod.POST)
  public String addSubmit(Model model, @ModelAttribute Lowongan lowongan) {
	lowonganDAO.insert(lowongan);
	return "lowongan/notif";
  }
  
  @RequestMapping("/lowongan/ubah/{id_lowongan}")
  public String update(Model model, @PathVariable(value = "id_lowongan") int id_lowongan) {
	return "lowongan/form-update";
  }
  
  @RequestMapping(value="/lowongan/ubah/submit", method=RequestMethod.POST)
  public String updateSubmit(Model model, @ModelAttribute Lowongan lowongan) {
	lowonganDAO.update(lowongan);
	return "lowongan/notif";
  }
}
