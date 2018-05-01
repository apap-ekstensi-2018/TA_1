package com.siasisten1.dao;

import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.siasisten1.model.Matkul;

@Service
public class MatkulDAOImpl implements MatkulDAO{

  @Autowired
  private RestTemplate restTemplate;

  private String url = "https://apap-fasilkom.herokuapp.com/api/matkul";

  @Override
  public Matkul getMatkul(int id_matkul) {
    Matkul matkul = restTemplate.getForObject(url + "/view/id/" + id_matkul, Matkul.class);
    return matkul;
  }

  @Override
  public List<Matkul> getAllMatkul() {
    ResponseEntity <Matkul[]> response = restTemplate.getForEntity(url + "/viewall", Matkul[].class);
    return Arrays.asList(response.getBody());
  }
}
