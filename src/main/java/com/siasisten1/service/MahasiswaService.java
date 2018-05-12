package com.siasisten1.service;

import java.util.HashMap;
import java.util.List;

import com.siasisten1.model.Mahasiswa;

public interface MahasiswaService {
  Mahasiswa getMahasiswaById(int id);
  
  Mahasiswa getMahasiswaByNPM(String npm);
  
  List<Mahasiswa> getAllMahasiswa();
  
//  HashMap<String, Mahasiswa> getMahasiswa(List<String> npms);
}
