package com.siasisten1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class LowonganController {
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
}
