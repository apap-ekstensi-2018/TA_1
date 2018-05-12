package com.siasisten1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PengajuanModel {
	private String id;
	private int id_lowongan;
	private String username_mahasiswa,is_accepted;
	private String namaMatkul;
	private int id_matkul;
}
