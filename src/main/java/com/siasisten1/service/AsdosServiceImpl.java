package com.siasisten1.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.siasisten1.dao.AsdosMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsdosServiceImpl implements AsdosService{
	
	@Autowired
	private AsdosMapper asdosMapper;

	@Override
	public boolean isAsdos(String npm) {
		// TODO Auto-generated method stub
		log.info("check if "+ npm +" is asdos");
		if(asdosMapper.isAsdos(npm) !=null)
			return true;
		else
			System.out.println("masukkkkkksiniiiii=====");
			return false;
	}
	
}
