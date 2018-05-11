package com.siasisten1.service;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten1.dao.MatkulDAO;
import com.siasisten1.model.Matkul;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MatkulServiceRest implements MatkulService{

  @Autowired
  private MatkulDAO matkulDAO;

  @Override
  public Matkul getMatkul(int id) {
    log.info("REST - select matkul with id {}",id);
    return matkulDAO.getMatkul(id);
  }

  @Override
  public List<Matkul> getMatkul() {
    log.info("REST - select all matkul");
    return matkulDAO.getAllMatkul();
  }

  @Override
  public HashMap<Integer, Matkul> getMatkul(List<Integer> ids){
    HashMap<Integer, Matkul> matkuls = new HashMap<Integer, Matkul>();
    for(Integer id : ids){
      matkuls.put(id.intValue(), getMatkul(id.intValue()));
    }

    return matkuls;
  }
}
