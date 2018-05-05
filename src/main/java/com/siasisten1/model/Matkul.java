package com.siasisten1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Matkul
{
  private int id;
  private String kode_matkul;
  private String nama_matkul;
  private List<Dosen> dosenList;
}
