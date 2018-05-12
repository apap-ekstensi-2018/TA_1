package com.siasisten1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AsdosService {
	List<String> selectMataKuliahDiPegang(int id_MataKuliah);
	boolean isAsdos(@Param("npm") String npm);
	int checkAsdos(@Param("npm") String npm);
	List<Integer> selectMatkulAsdos(@Param("npm") String npm);
}
