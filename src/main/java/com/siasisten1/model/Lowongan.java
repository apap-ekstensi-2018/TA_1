package com.siasisten1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Lowongan
{
  public static final String[] LIST_STATUS  = {"Ditutup", "Dibuka"};
  private int id;
  private int id_matkul;
  private int is_open;
  private int jumlah_lowongan;

  private boolean opened;

  public String getStatus() {
    System.out.println(is_open);
    return opened ? "Buka" : "Tutup";
  }

  public int getIdMatkul(){
    return id_matkul;
  }
}
