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
  private int jml_lowongan;

  private boolean opened;
  public int jumlah_lowongan;

  public String getStatus() {
    return is_open == 1 ? "Buka" : "Tutup";
  }
}
