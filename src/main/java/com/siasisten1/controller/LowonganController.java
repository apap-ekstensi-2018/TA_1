package com.siasisten1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.siasisten1.model.Matkul;
import com.siasisten1.model.Lowongan;
import com.siasisten1.service.LowonganService;
import com.siasisten1.service.MatkulService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("lowongan")
public class LowonganController {
  @Autowired
  LowonganService lowonganDAO;

  @Autowired
  MatkulService matkulDAO;

  @RequestMapping("/view/{id_lowongan}")
  public String show(Model model, @PathVariable(value = "id_lowongan") int id_lowongan) {
    Lowongan lowongan = lowonganDAO.getLowongan(id_lowongan);
    Matkul matkul = matkulDAO.getMatkul(lowongan.getIdMatkul());
    model.addAttribute("matakuliah", matkul);
    model.addAttribute("lowongan", lowongan);
    return "lowongan/show";
  }

  @RequestMapping("/viewall")
  public String show(Model model) {
    List<Lowongan> lowongans = lowonganDAO.getLowongan();
    List<Matkul> matkuls = matkulDAO.getMatkul();

    Map<Integer, Matkul> listMatkul = new HashMap<Integer, Matkul>();

    for(Matkul m : matkuls){
      listMatkul.put(new Integer(m.getId()), m);
    }

    model.addAttribute("lowongans", lowongans);
    model.addAttribute("matkuls", listMatkul);
    return "lowongan/index";
  }

  @RequestMapping("/tambah")
  public String add(Model model) {
    List<Matkul> listMatkul = matkulDAO.getMatkul();
    model.addAttribute("lowongan", new Lowongan());
    model.addAttribute("listStatus", Lowongan.LIST_STATUS);
    model.addAttribute("listMatkul",listMatkul);
    model.addAttribute("linkSubmit", "/lowongan/tambah");
    return "lowongan/form-add";
  }

  @RequestMapping(value="/tambah", method=RequestMethod.POST)
  public String addSubmit(Model model, @ModelAttribute Lowongan lowongan) {
    lowonganDAO.insert(lowongan);
    model.addAttribute("message", "Sukses! Berhasil menambah lowongan");
    return "lowongan/notif";
  }

  @RequestMapping("/ubah/{id_lowongan}")
  public String update(Model model, @PathVariable(value = "id_lowongan") int id_lowongan) {
	Lowongan lowongan = lowonganDAO.getLowongan(id_lowongan);
	if(lowongan != null) {
		List<Matkul> listMatkul = matkulDAO.getMatkul();
	    model.addAttribute("lowongan", lowongan);
	    model.addAttribute("listStatus", Lowongan.LIST_STATUS);
	    model.addAttribute("listMatkul",listMatkul);
	    model.addAttribute("linkSubmit", "/lowongan/ubah/submit");
	    return "lowongan/form-update";
	}else {
		model.addAttribute("message", "Gagal! Lowongan tidak ditemukan");
	    return "lowongan/notif";
	}
  }

  @RequestMapping(value="/ubah/submit", method=RequestMethod.POST)
  public String updateSubmit(Model model, @ModelAttribute Lowongan lowongan) {
    lowonganDAO.update(lowongan);
    model.addAttribute("message", "Sukses! Berhasil mengubah lowongan");
    return "lowongan/notif";
  }
}
