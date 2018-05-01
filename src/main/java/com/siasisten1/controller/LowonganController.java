package com.siasisten1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.siasisten1.model.Matkul;
import com.siasisten1.model.Lowongan;
import com.siasisten1.service.LowonganService;
import com.siasisten1.service.MatkulService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LowonganController {
  @Autowired
  LowonganService lowonganDAO;

  @Autowired
  MatkulService matkulDAO;

  @RequestMapping("/lowongan/view/{id_lowongan}")
  public String show(Model model, @PathVariable(value = "id_lowongan") int id_lowongan) {
    Lowongan lowongan = lowonganDAO.getLowongan(id_lowongan);
    model.addAttribute("lowongan", lowongan);
    return "lowongan/show";
  }

  @RequestMapping("/lowongan/viewall")
  public String show(Model model) {
    List<Lowongan> lowongans = lowonganDAO.getLowongan();
    model.addAttribute("lowongans", lowongans);
    return "lowongan/index";
  }

  @RequestMapping("/lowongan/tambah")
  public String add(Model model) {
    List<Matkul> listMatkul = matkulDAO.getAllLowongan();
    System.out.println("======INIIIIIIIIIIIIIIIIIII===="+listMatkul.get(0).getNama_matkul());
    model.addAttribute("lowongan", new Lowongan());
    model.addAttribute("listStatus", Lowongan.LIST_STATUS);
    model.addAttribute("listMatkul",listMatkul);
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
