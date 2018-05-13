package com.siasisten1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten1.dao.AsdosMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsdosServiceImpl implements AsdosService{
	
	@Autowired
	private AsdosMapper asdosMapper;
	  
	@Override
	public List<String> selectMataKuliahDiPegang(int id_MataKuliah) {
		return asdosMapper.selectMataKuliahDiPegang(id_MataKuliah);
	}
	
	@Override
	public boolean isAsdos(String npm) {
		log.info("check if "+ npm +" is asdos");
		if(asdosMapper.isAsdos(npm) > 0)
			return true;
		else
			return false;
	}
	
	@Override
	public int checkAsdos(String npm) {
		return asdosMapper.checkAsdos(npm);
	}
	
	@Override
	public List<Integer> selectMatkulAsdos(String npm){
		return asdosMapper.selectMatkulAsdos(npm);
	}
}
