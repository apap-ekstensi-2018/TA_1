package com.siasisten1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.mapping.FetchType;

import com.siasisten1.model.Lowongan;

@Mapper
public interface LowonganMapper {
  @Select("SELECT * FROM lowongan")
  @Results(value = {
    @Result(property="id", column="id"),
    @Result(property="opened", column="is_open"),
    @Result(property="jumlah_lowongan", column="jml_lowongan")
  })
  List<Lowongan> getAllLowongan();

  @Select("SELECT * FROM lowongan WHERE id = #{id}")
  @Results(value = {
    @Result(property="id", column="id"),
    @Result(property="opened", column="is_open"),
    @Result(property="jumlah_lowongan", column="jml_lowongan")
  })
  Lowongan findById(int id);

  @Delete("DELETE lowongan WHERE id = #{id}")
  Lowongan deleteById(int id);

  @Insert("INSERT INTO lowongan (id_matkul, is_open, jml_lowongan) VALUES ("
		    + "#{lowongan.id_matkul},"
		    + "#{lowongan.is_open},"
		    + "#{lowongan.jml_lowongan})")
  void insert(@Param("lowongan") Lowongan lowongan);

  @Update("UPDATE lowongan SET "
		    + "id_matkul=#{lowongan.id_matkul},"
		    + "is_open=#{lowongan.is_open},"
		    + "jml_lowongan=#{lowongan.jml_lowongan}"
		    + " WHERE id=#{lowongan.id}")
  void update(@Param("lowongan") Lowongan lowongan);
}
