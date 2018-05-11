package com.siasisten1.service;

import java.util.List;
import java.util.HashMap;

import com.siasisten1.model.Matkul;

public interface MatkulService {
  List<Matkul> getMatkul();
  HashMap<Integer, Matkul> getMatkul(List<Integer> ids);
  Matkul getMatkul(int id);
}
