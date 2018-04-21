package com.siasisten1.service;

import java.util.List;

import com.siasisten1.model.Lowongan;

public interface LowonganService {
  Lowongan getLowongan(int id);
  List<Lowongan> getLowongan();
  Lowongan delete(int id);
}
