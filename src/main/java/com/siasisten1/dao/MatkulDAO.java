package com.siasisten1.dao;

import java.util.List;

import com.siasisten1.model.Matkul;

public interface MatkulDAO {
	Matkul getMatkul(int id);
	List<Matkul> getAllMatkul();

}
