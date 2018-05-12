package com.siasisten1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten1.dao.MahasiswaDAO;
import com.siasisten1.model.Mahasiswa;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceRest implements MahasiswaService{

  @Autowired
  private MahasiswaDAO mahasiswaDAO;

  @Override
  public Mahasiswa getMahasiswaById(int id) {
    log.info("REST - select mahasiswa with id {}",id);
    Mahasiswa mahasiswa = mahasiswaDAO.getMahasiswaById(id);
    	return mahasiswa;
  }
}
