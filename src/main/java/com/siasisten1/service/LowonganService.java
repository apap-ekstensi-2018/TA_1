package com.siasisten1.service;

import java.util.List;

import com.siasisten1.model.Lowongan;

public interface LowonganService {
  Lowongan getLowongan(int id);
  List<Lowongan> getLowongan();
  List<Lowongan> getBukaLowongan();
  void insert(Lowongan lowongan);
  void update(Lowongan lowongan);
  void delete(int id);
}
