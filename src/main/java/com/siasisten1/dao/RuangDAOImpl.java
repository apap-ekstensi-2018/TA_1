package com.siasisten1.dao;

import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.siasisten1.model.Ruang;

@Service
public class RuangDAOImpl implements RuangDAO{

  @Autowired
  private RestTemplate restTemplate;

  private String url = "https://siruang-2.herokuapp.com/api/ruang/";

  @Override
  public Ruang getRuang(int id_ruang) {
    Ruang ruang = restTemplate.getForObject(url + "/view/" + id_ruang, Ruang.class);
    return ruang;
  }
}
