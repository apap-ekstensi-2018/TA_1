package com.siasisten1.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.siasisten1.model.PengajuanModel;

@Mapper
public interface AsdosMapper {
	
    @Select("select * from pengajuan where username_mahasiswa= #{npm} and is_accepted=1")
    PengajuanModel isAsdos(@Param("npm") String npm);
}
