package com.siasisten1.service;

import java.util.List;

import com.siasisten1.model.Dosen;
import com.siasisten1.model.PengajuanModel;

public interface PengajuanService {
	
	PengajuanModel selectPengajuan (String id_pengajuan);
	
	PengajuanModel checkPengajuan(int id_lowongan,String username_mahasiswa);
	
	List<PengajuanModel> selectAllPengajuan();

	List<PengajuanModel> selectAllPengajuanMahasiswa(String username);
		
	void addPengajuan(PengajuanModel pengajuan);
	
	void deletePengajuan(String id_pengajuan);
	
	void updatePengajuan(String id_pengajuan, int reviewValue);
}
