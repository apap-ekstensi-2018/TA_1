package com.siasisten1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.siasisten1.model.PengajuanModel;

@Mapper
public interface PengajuanMapper {
	
	@Select("select id, id_lowongan, username_mahasiswa, is_accepted from Pengajuan where id = #{id}")
    PengajuanModel selectPengajuan (@Param("id") String id_pengajuan);

    @Select("select id, id_lowongan, username_mahasiswa, is_accepted from Pengajuan")
    List<PengajuanModel> selectAllPengajuan ();

    @Select("select id, id_lowongan, username_mahasiswa, is_accepted from Pengajuan where username_mahasiswa = #{username_mahasiswa}")
    List<PengajuanModel> selectAllPengajuanMahasiswa (@Param("username_mahasiswa") String username_mahasiswa);
    
    @Insert("INSERT INTO Pengajuan (id_lowongan, username_mahasiswa, is_accepted) VALUES (#{id_lowongan}, #{username_mahasiswa}, '2')")
    void addPengajuan (PengajuanModel Pengajuan);
    
    @Delete("DELETE FROM Pengajuan WHERE id = #{id}")
    void deletePengajuan(@Param("id") String id_pengajuan);
    
    @Update("UPDATE PENGAJUAN SET "
			+ "is_accepted=#{reviewValue}"
			+ " WHERE id = #{id}")
    void updatePengajuan(@Param("id") String id_pengajuan, @Param("reviewValue") int reviewValue);

    @Select("select id, id_lowongan, username_mahasiswa, is_accepted from Pengajuan where id_lowongan = #{id_lowongan} and username_mahasiswa = #{username_mahasiswa}")
	PengajuanModel checkPengajuan(@Param("id_lowongan") int id_lowongan,@Param("username_mahasiswa") String username_mahasiswa);

}
