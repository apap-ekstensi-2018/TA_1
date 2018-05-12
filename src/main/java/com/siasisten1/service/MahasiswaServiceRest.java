package com.siasisten1.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.siasisten1.dao.AsdosMapper;
import com.siasisten1.dao.MahasiswaDAO;
import com.siasisten1.dao.PengajuanMapper;
import com.siasisten1.model.Mahasiswa;
import com.siasisten1.model.Matkul;

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
  
  @Override
  public Mahasiswa getMahasiswaByNPM(String npm) {
	  log.info("REST - select mahasiswa by NPM {}", npm);
	  return mahasiswaDAO.getMahasiswaByNPM(npm);
  }
  
  @Override
  public List<Mahasiswa> getAllMahasiswa(){
	  return  mahasiswaDAO.getAllMahasiswa();
  }
  
//  @Override
//  public HashMap<String, Mahasiswa> getMahasiswa(List<String> npms){
//	  HashMap<String, Mahasiswa> someMahasiswa = new HashMap<String, Mahasiswa>();
//	  for(String npm : npms) {
//		  someMahasiswa.put(npm, getMahasiswaByNPM(npm));
//	  }
//	  
//	  return someMahasiswa;
//  }
}
