package com.siasisten1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.siasisten1.model.Ruang;
import com.siasisten1.model.Matkul;
import com.siasisten1.model.Lowongan;
import com.siasisten1.service.AsdosService;
import com.siasisten1.service.LowonganService;
import com.siasisten1.service.MatkulService;
import com.siasisten1.service.RuangService;
import com.siasisten1.service.PengajuanService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("lowongan")
public class LowonganController {
  @Autowired
  LowonganService lowonganDAO;

  @Autowired
  MatkulService matkulDAO;


  @Autowired
  RuangService ruangDAO;


  @RequestMapping("/view/{id_lowongan}")
  public String show(Model model, @PathVariable(value = "id_lowongan") int id_lowongan) {
    Lowongan lowongan = lowonganDAO.getLowongan(id_lowongan);
    Matkul matkul = matkulDAO.getMatkul(lowongan.getIdMatkul());
    Ruang ruang = new Ruang(lowongan.getIdMatkul(), "An error occured on get Ruang", 0);

    try {
      Ruang ruangan = ruangDAO.getRuang(lowongan.getIdMatkul());
      if(ruangan != null) ruang = ruangan;

    } catch (Exception e){}

    model.addAttribute("title", "SIASISTEN | Menampilkan Lowongan");
    model.addAttribute("matakuliah", matkul);
    model.addAttribute("lowongan", lowongan);
    model.addAttribute("ruang", ruang);
    model.addAttribute("linkDelete", "/lowongan/ubah/" + lowongan.getId());
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

    model.addAttribute("title", "SIASISTEN | Menampilkan Semua Lowongan");
    model.addAttribute("lowongans", lowongans);
    model.addAttribute("matkuls", listMatkul);
    return "lowongan/index";
  }

  @RequestMapping("/tambah")
  public String add(Model model) {
    List<Matkul> listMatkul = matkulDAO.getMatkul();
    model.addAttribute("title", "SIASISTEN | Menambah Lowongan");
    model.addAttribute("lowongan", new Lowongan());
    model.addAttribute("listStatus", Lowongan.LIST_STATUS);
    model.addAttribute("listMatkul",listMatkul);
    model.addAttribute("linkSubmit", "/lowongan/tambah");
    return "lowongan/form-add";
  }

  @RequestMapping(value="/tambah", method=RequestMethod.POST)
  public String addSubmit(Model model, @ModelAttribute Lowongan lowongan) {
    lowonganDAO.insert(lowongan);
    model.addAttribute("title", "SIASISTEN | Berhasil menambah Lowongan");
    model.addAttribute("message", "Sukses! Berhasil menambah lowongan");
    return "lowongan/notif";
  }

  @RequestMapping("/ubah/{id_lowongan}")
  public String update(Model model, @PathVariable(value = "id_lowongan") int id_lowongan) {
    Lowongan lowongan = lowonganDAO.getLowongan(id_lowongan);
    if(lowongan != null) {
      List<Matkul> listMatkul = matkulDAO.getMatkul();
      model.addAttribute("title", "SIASISTEN | Berhasil Mengubah Lowongan");
      model.addAttribute("lowongan", lowongan);
      model.addAttribute("listStatus", Lowongan.LIST_STATUS);
      model.addAttribute("listMatkul",listMatkul);
      model.addAttribute("linkSubmit", "/lowongan/ubah/submit");
      return "lowongan/form-update";
    }else {
      model.addAttribute("title", "SIASISTEN | Gagal Mengubah Lowongan");
      model.addAttribute("message", "Gagal! Lowongan tidak ditemukan");
      return "lowongan/notif";
    }
  }

  @RequestMapping(value="/ubah/{id_lowongan}", method=RequestMethod.POST)
  public String delete(Model model, @PathVariable(value = "id_lowongan") int id_lowongan) {
    Lowongan lowongan = lowonganDAO.getLowongan(id_lowongan);

    if(lowongan == null){
      model.addAttribute("title", "SIASISTEN | Gagal Mengubah Lowongan");
      model.addAttribute("message", "Gagal! Lowongan tidak ditemukan.");
    } else {
      lowonganDAO.delete(id_lowongan);
      Lowongan deleteLowongan = lowonganDAO.getLowongan(id_lowongan);

      System.out.println(deleteLowongan.getDeleted_at());
      if (deleteLowongan.getDeleted_at() == null){
        model.addAttribute("title", "SIASISTEN | Gagal Mengubah Lowongan");
        model.addAttribute("message", "Gagal! Lowongan id " + lowongan.getId() + " gagal dihapus.");
      } else {
        model.addAttribute("title", "SIASISTEN | Berhasil Mengubah Lowongan");
        model.addAttribute("message", "Sukses! Berhasil menghapus lowongan id " + lowongan.getId());
      }
    }
    return "lowongan/notif";
  }

  @RequestMapping(value="/ubah/submit", method=RequestMethod.POST)
  public String updateSubmit(Model model, @ModelAttribute Lowongan lowongan) {
    lowonganDAO.update(lowongan);
    model.addAttribute("title", "SIASISTEN | Berhasil Menghapus Lowongan");
    model.addAttribute("message", "Sukses! Berhasil mengubah lowongan");
    return "lowongan/notif";
  }
}
