package com.siasisten1.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siasisten1.model.Mahasiswa;
import com.siasisten1.response.ResponseTransfer;
import com.siasisten1.service.AsdosService;
import com.siasisten1.service.MahasiswaService;

@RestController
public class AsdosRestController {
	@Autowired
	AsdosService asdosDAO;
	
	@Autowired
	MahasiswaService mahasiswaDAO;
	
	@RequestMapping(value="/asisten-dosen/cek-status/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseTransfer isAsdos (@PathVariable(value = "id", required = true) int id)
    {
		Mahasiswa mahasiswa = mahasiswaDAO.getMahasiswaById(id);
		if(mahasiswa != null) {
			if(asdosDAO.isAsdos(mahasiswa.getNpm()))
				return new ResponseTransfer(HttpStatus.OK.value(),true);
			else
				return new ResponseTransfer(HttpStatus.NOT_FOUND.value(),false);
		}
		else
			return new ResponseTransfer(HttpStatus.NOT_FOUND.value(),false);
    }
}
