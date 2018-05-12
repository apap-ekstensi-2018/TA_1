package com.siasisten1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.siasisten1.dao.PengajuanMapper;
import com.siasisten1.model.Dosen;
import com.siasisten1.model.PengajuanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PengajuanServiceImpl implements PengajuanService{
	
	@Autowired
	private PengajuanMapper pengajuanMapper;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public PengajuanModel selectPengajuan(String id_pengajuan) {
		log.info("select pengajuan with id {}",id_pengajuan);
		return pengajuanMapper.selectPengajuan(id_pengajuan);
	}

	@Override
	public List<PengajuanModel> selectAllPengajuan() {
		// TODO Auto-generated method stub
		log.info("select all pengajuan");
		return pengajuanMapper.selectAllPengajuan();
	}

	@Override
	public List<PengajuanModel> selectAllPengajuanMahasiswa(String username_mahasiswa) {
		// TODO Auto-generated method stub
		log.info("select all pengajuan by mahasiswa");
		return pengajuanMapper.selectAllPengajuanMahasiswa(username_mahasiswa);
	}

	@Override
	public void addPengajuan(PengajuanModel pengajuan) 
	{
		pengajuanMapper.addPengajuan(pengajuan);
	}

	@Override
	public void deletePengajuan(String id_pengajuan) {
		// TODO Auto-generated method stub
		log.info("pengajuan "+ id_pengajuan +" deleted");
		pengajuanMapper.deletePengajuan(id_pengajuan);			
	}
	
	@Override
	public void updatePengajuan(String id_pengajuan, int reviewValue)
	{
		log.info("Review "+ id_pengajuan + " With Result "+ reviewValue);
		pengajuanMapper.updatePengajuan(id_pengajuan, reviewValue);
	}

	@Override
	public PengajuanModel checkPengajuan(int id_lowongan,String username_mahasiswa) {
		log.info("check pengajuan with id lowongan {}",id_lowongan);
		return pengajuanMapper.checkPengajuan(id_lowongan,username_mahasiswa);
	}

}
