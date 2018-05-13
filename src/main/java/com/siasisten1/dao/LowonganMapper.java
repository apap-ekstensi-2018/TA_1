package com.siasisten1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import com.siasisten1.model.Lowongan;

@Mapper
public interface LowonganMapper {
  @Select("SELECT * FROM lowongan WHERE deleted_at IS NULL")
  @Results(value = {
    @Result(property="id", column="id"),
    @Result(property="opened", column="opened"),
    @Result(property="jumlah_lowongan", column="jumlah_lowongan")
  })
  List<Lowongan> getAllLowongan();

  @Select("SELECT * FROM lowongan WHERE opened = 1 AND deleted_at IS NULL")
  List<Lowongan> getAllBukaLowongan();

  @Select("SELECT * FROM lowongan WHERE id = #{id}")
  @Results(value = {
    @Result(property="id", column="id"),
    @Result(property="id_matkul", column="id_matkul"),
    @Result(property="opened", column="opened"),
    @Result(property="jumlah_lowongan", column="jumlah_lowongan")
  })
  Lowongan findById(int id);

  @Insert("INSERT INTO lowongan (id_matkul, opened, jumlah_lowongan) VALUES ("
		    + "#{lowongan.id_matkul},"
		    + "#{lowongan.opened},"
		    + "#{lowongan.jumlah_lowongan})")
  void insert(@Param("lowongan") Lowongan lowongan);

  @Update("UPDATE lowongan SET "
		    + "id_matkul=#{lowongan.id_matkul},"
		    + "opened=#{lowongan.opened},"
		    + "jumlah_lowongan=#{lowongan.jumlah_lowongan}"
		    + " WHERE id=#{lowongan.id}")

  void update(@Param("lowongan") Lowongan lowongan);

  @Delete("UPDATE lowongan SET deleted_at=CURRENT_TIMESTAMP() WHERE id=#{id}")
  void delete(@Param("id") int id);

  @Select("select * from lowongan where id_matkul= #{id_matkul}")
  Lowongan isMatkulDosen(@Param("id_matkul") int id_matkul);

}
