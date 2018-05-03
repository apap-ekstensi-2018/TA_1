package com.siasisten1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.siasisten1.model.PengajuanModel;

@Mapper
public interface PengajuanMapper {
	
	@Select("select id, id_lowongan, username_mahasiswa, is_accepted from Pengajuan where id = #{id}")
    PengajuanModel selectPengajuan (@Param("id") String id_pengajuan);

    @Select("select id, id_lowongan, username_mahasiswa, is_accepted from Pengajuan")
    List<PengajuanModel> selectAllPengajuan ();

    @Insert("INSERT INTO Pengajuan (id, id_lowongan, username_mahasiswa, is_accepted) VALUES (#{id}, #{id_lowongan}, #{username_mahasiswa}, '2')")
    void addPengajuan (PengajuanModel Pengajuan);
    
    @Delete("DELETE FROM Pengajuan WHERE id = #{id}")
    void deletePengajuan(@Param("id") String id_pengajuan);
    
}
