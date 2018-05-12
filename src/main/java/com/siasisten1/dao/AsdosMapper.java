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
	
	@Select("select * from pengajuan where username_mahasiswa= #{npm} and is_accepted=1")
    PengajuanModel isAsdos(@Param("npm") String npm);
}