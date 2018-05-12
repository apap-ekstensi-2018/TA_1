package com.siasisten1.service;

import java.util.List;

public interface AsdosService {
	List<String> selectMataKuliahDiPegang(int id_MataKuliah);
	boolean isAsdos(String npm);
}
