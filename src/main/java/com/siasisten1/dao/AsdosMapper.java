package com.siasisten1.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.siasisten1.model.PengajuanModel;

@Mapper
public interface AsdosMapper {
	@Select("SELECT username_mahasiswa as npm from pengajuan JOIN lowongan ON lowongan.id = id_lowongan WHERE id_matkul = #{id_MataKuliah} AND is_accepted = 1")
	List<String> selectMataKuliahDiPegang(@Param("id_MataKuliah") int id_MataKuliah);
	
	@Select("SELECT id_matkul from pengajuan JOIN lowongan ON lowongan.id = id_lowongan WHERE username_mahasiswa = #{npm} AND is_accepted = 1")
	List<Integer> selectMatkulAsdos(@Param("npm") String npm);
	
	@Select("SELECT count(*) as val from pengajuan where username_mahasiswa = #{npm} and is_accepted=1")
    int isAsdos(@Param("npm") String npm);
	
	@Select("select count(*) as val from pengajuan where username_mahasiswa= #{npm} and is_accepted=1")
    int checkAsdos(@Param("npm") String npm);
}