package com.siasisten1.service;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten1.dao.RuangDAO;
import com.siasisten1.model.Ruang;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RuangServiceRest implements RuangService{

  @Autowired
  private RuangDAO ruangDAO;

  @Override
  public Ruang getRuang(int id) {
    log.info("REST - select ruang with id {}", id);
    return ruangDAO.getRuang(id);
  }
}
