package com.siasisten1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Lowongan
{
  public static final String[] LIST_STATUS  = {"Ditutup", "Dibuka"};
  private int id;
  private int id_matkul;
  private int opened;
  private int jumlah_lowongan;
  private Timestamp deleted_at;

  public String getStatus() {
    System.out.println(opened);
    return opened==1 ? "Buka" : "Tutup";
  }

  public int getIdMatkul(){
    return id_matkul;
  }
}
