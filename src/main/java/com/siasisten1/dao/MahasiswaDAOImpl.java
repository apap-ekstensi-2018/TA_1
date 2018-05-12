package com.siasisten1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.siasisten1.model.Mahasiswa;

@Service
public class MahasiswaDAOImpl implements MahasiswaDAO{

  @Autowired
  private RestTemplate restTemplate;

  private String url = "https://apap-fasilkom.herokuapp.com/api/mahasiswa";

  @Override
  public Mahasiswa getMahasiswaById(int id) {
    Mahasiswa mahasiswa = restTemplate.getForObject(url + "/view/id/" + id, Mahasiswa.class);
    return mahasiswa;
  }

}
