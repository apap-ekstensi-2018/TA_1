package com.siasisten1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Slf4j
public class Lowongan
{
  private int id;
  private int id_matkul;
  private boolean is_open;
  private int jml_lowongan;
}
