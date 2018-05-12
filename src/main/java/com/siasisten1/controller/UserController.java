package com.siasisten1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siasisten1.service.AsdosService;
import com.siasisten1.service.DosenService;
import com.siasisten1.service.MahasiswaService;
import com.siasisten1.service.MatkulService;
import com.siasisten1.model.PengajuanModel;
import com.siasisten1.service.MahasiswaService;
import com.siasisten1.service.PengajuanService;
import com.siasisten1.model.Dosen;
import com.siasisten1.model.Lowongan;
import com.siasisten1.model.Mahasiswa;
import com.siasisten1.model.Matkul;

@Controller
public class UserController {
	
		@Autowired
		AsdosService asdosDAO;
		
		@Autowired
		MahasiswaService mahasiswaDAO;
		
		@Autowired
		MatkulService matkulDAO;
		
		@Autowired
		DosenService dosenDAO;
		
	 	@RequestMapping("/")
	 	 public String index (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException 
	    {
	 		if (authentication != null) {
				response.sendRedirect("/pengajuan/viewall");
			}
				
			return "login";
	    }

	 	@RequestMapping("login")
		public String login(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
			if (authentication != null) {
				response.sendRedirect("/pengajuan/viewall");
			}
			
			return "login";
		}

		@RequestMapping("admin")
		public String admin() {
			return "admin";
		}
		
		@RequestMapping("/mata-kuliah/{id_mataKuliah}")
		public String getAsdosMataKuliah(Model model, 
				@PathVariable(value = "id_mataKuliah") int id_mataKuliah) throws IOException
		{	
			List<String> listNPM = asdosDAO.selectMataKuliahDiPegang(id_mataKuliah);
			List<Mahasiswa> listMahasiswa = new ArrayList<>();
						
			
			for(int i = 0 ; i < listNPM.size() ; i++) {
				listMahasiswa.add(mahasiswaDAO.getMahasiswaByNPM(listNPM.get(i)));
			}
			
			model.addAttribute("listMahasiswa", listMahasiswa);
			return "asdos/daftarasdos";
		}
		
		@RequestMapping("/daftarmatkul")
		public String getMatkul(Model model,HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException 
		{
			if(request.isUserInRole("ROLE_ADMIN")){
				Dosen dosen = dosenDAO.getDosen(request.getRemoteUser());
				List<Matkul> matkulsResult = dosen.getMataKuliahList();
				model.addAttribute("matkuls", matkulsResult);
				return "asdos/daftarmatkul";
			}else {
				List<Integer> ids = asdosDAO.selectMatkulAsdos(request.getRemoteUser());
				List<Matkul> matkuls = matkulDAO.getMatkul();
				List<Matkul> matkulsResult = new ArrayList<>();
				for(int id : ids) {
					for(Matkul m : matkuls) {
						if(m.getId() == id) {
							matkulsResult.add(m);
						}
					}
				}
			
				model.addAttribute("matkuls", matkulsResult);
				return "asdos/daftarmatkul";
			}
		}
}
