package com.siasisten1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten1.dao.LowonganMapper;
import com.siasisten1.model.Lowongan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LowonganDatabase implements LowonganService{
  @Autowired
  private LowonganMapper lowonganMapper;

  @Override
  public Lowongan getLowongan(int id){
    log.info("search lowongan with id {}", id);
    return lowonganMapper.findById(id);
  }

  @Override
  public List<Lowongan> getLowongan(){
    log.info("get all lowongan");
    return lowonganMapper.getAllLowongan();
  }

  @Override
  public List<Lowongan> getBukaLowongan(){
    log.info("get all lowongan available");
    return lowonganMapper.getAllBukaLowongan();
  }
  
  @Override
  public Lowongan delete(int id){
    log.info("delete lowongan with id {}", id);
    return lowonganMapper.deleteById(id);
  }

  @Override
  public void insert(Lowongan lowongan) {
	log.info("insert lowongan to database");
	lowonganMapper.insert(lowongan);
  }

  @Override
  public void update(Lowongan lowongan) {
	log.info("update lowongan dengan id {}", lowongan.getId());
	lowonganMapper.update(lowongan);
	
  }
}
