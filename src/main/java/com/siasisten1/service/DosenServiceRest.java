package com.siasisten1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten1.dao.DosenDAO;
import com.siasisten1.model.Dosen;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DosenServiceRest implements DosenService{

  @Autowired
  private DosenDAO DosenDAO;

  @Override
  public Dosen getDosen(String nip) {
    log.info("REST - select Dosen with nip {}",nip);
    return DosenDAO.getDosen(nip);
  }
}
