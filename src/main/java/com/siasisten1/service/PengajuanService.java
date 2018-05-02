package com.siasisten1.service;

import java.util.List;

import com.siasisten1.model.PengajuanModel;

public interface PengajuanService {
	
	PengajuanModel selectPengajuan (String id_pengajuan);
	
	List<PengajuanModel> selectAllPengajuan();
	
	void addPengajuan(PengajuanModel pengajuan);
	
	void deletePengajuan(String id_pengajuan);

}
