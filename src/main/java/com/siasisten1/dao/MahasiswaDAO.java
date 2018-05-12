package com.siasisten1.dao;

import java.util.List;

import com.siasisten1.model.Mahasiswa;

public interface MahasiswaDAO {
  Mahasiswa getMahasiswaById(int id);
  Mahasiswa getMahasiswaByNPM(String npm);
  List<Mahasiswa> getAllMahasiswa();
}
