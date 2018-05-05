package com.siasisten1.service;

import java.util.List;

import com.siasisten1.model.Matkul;

public interface MatkulService {
  Matkul getMatkul(int id);
  List<Matkul> getAllLowongan();
}
