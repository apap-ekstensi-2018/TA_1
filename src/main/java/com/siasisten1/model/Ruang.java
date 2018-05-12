package com.siasisten1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ruang {
  private int id;
  private String nama;
  private int kapasitas;
}
