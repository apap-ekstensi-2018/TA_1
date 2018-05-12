package com.siasisten1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Dosen
{
  private int id;
  private String nip;
  private String nama;
  private List<Matkul> mataKuliahList;
}
