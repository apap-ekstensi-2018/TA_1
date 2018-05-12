package com.siasisten1.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.siasisten1.model.Dosen;

@Service
public class DosenDAOImpl implements DosenDAO{

	  @Autowired
	  private RestTemplate restTemplate;
	  private String url = "https://apap-fasilkom.herokuapp.com/api/dosen";

	  @Override
	  public Dosen getDosen(String nip) {
	    Dosen Dosen = restTemplate.getForObject(url + "/view/nip/" + nip, Dosen.class);
	    return Dosen;
	  }
}
