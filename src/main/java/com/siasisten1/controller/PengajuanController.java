package com.siasisten1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.siasisten1.model.Lowongan;
import com.siasisten1.model.PengajuanModel;
import com.siasisten1.service.LowonganService;
import com.siasisten1.service.PengajuanService;

@Controller
@RequestMapping("pengajuan")
public class PengajuanController {
	
	@Autowired
	PengajuanService pengajuanDAO;

	@Autowired
	LowonganService lowonganDAO;

	
	@RequestMapping("/")
		public String index ()
	    {
	        return "index";
	    }

	    @RequestMapping("/tambah")
	    public String add (Model model)
	    {	
	    	List <Lowongan> lowongan = lowonganDAO.getBukaLowongan();
	    	model.addAttribute("lowongan", lowongan);
	        model.addAttribute("linkSubmit", "/pengajuan/tambah/submit");
	        return "pengajuan/form_tambah_pengajuan";
	    }

	    @RequestMapping(value = "/tambah/submit",method = RequestMethod.POST)
	    public String addSubmit (
	            @RequestParam(value = "id_pengajuan", required = false) String id_pengajuan,
	            @RequestParam(value = "id_lowongan", required = false) String id_lowongan,
	            @RequestParam(value = "username_mahasiswa", required = false) String username_mahasiswa,
	    		@RequestParam(value = "is_accepted", required = false) String status_pengajuan)
	    {
	    	PengajuanModel pengajuan = new PengajuanModel (id_pengajuan, id_lowongan, username_mahasiswa,status_pengajuan);
	        pengajuanDAO.addPengajuan (pengajuan);
	        
	        return "pengajuan/success-add";
	    }

	    @RequestMapping(value = "/view/{id_pengajuan}",method = RequestMethod.GET)
	    public String viewPath (Model model,
	            @PathVariable(value = "id_pengajuan") String id_pengajuan)
	    {
	        PengajuanModel pengajuan = pengajuanDAO.selectPengajuan (id_pengajuan);
	        model.addAttribute("linkSubmit", "/pengajuan/hapus");

	        if (pengajuan != null) {
	            model.addAttribute ("pengajuan", pengajuan);
	            return "pengajuan/viewPengajuan";
	        } else {
	            model.addAttribute ("id_pengajuan", id_pengajuan);
	            return "pengajuan/not-found";
	        }
	    }

	    @RequestMapping(value = "/viewall",method = RequestMethod.GET)
	    public String view (Model model, HttpServletRequest request)
	    {
	    	if(request.isUserInRole("ROLE_MAHASISWA")) {
		        List<PengajuanModel> data_pengajuan = pengajuanDAO.selectAllPengajuan ();
		        model.addAttribute ("data_pengajuan", data_pengajuan);		        
		        
		        return "pengajuan/viewallPengajuan";
	    	} else if(request.isUserInRole("ROLE_DOSEN")) {
		        List<PengajuanModel> data_pengajuan = pengajuanDAO.selectAllPengajuan ();
		        model.addAttribute ("data_pengajuan", data_pengajuan);		        

		        return "pengajuan/viewallPengajuan";
	    	} else {
	    		return "pengajuan/delete";
	    	}
	    }

	    @RequestMapping(value = "/hapus/{id_pengajuan}",method = RequestMethod.GET)
	    public String delete (Model model, @PathVariable(value = "id_pengajuan") String id_pengajuan)
	    {
	        PengajuanModel pengajuan = pengajuanDAO.selectPengajuan (id_pengajuan);
	    	if(pengajuan != null)
	    	{
	    		pengajuanDAO.deletePengajuan(id_pengajuan);
	    		return "pengajuan/delete";
	    	} else {
	    		model.addAttribute("id_pengajuan", id_pengajuan);
	    		return "pengajuan/not-found";
	    	}
	    }
	    
	    @RequestMapping(value = "/hapus",method = RequestMethod.POST)
	    public String deletePost (Model model, 
	    		@RequestParam(value = "id_pengajuan", required = false) String id_pengajuan,
	    		@RequestParam(value = "is_accepted", required = false) String is_accepted)
	    {
	        PengajuanModel pengajuan = pengajuanDAO.selectPengajuan (id_pengajuan);
	    	if(pengajuan != null)
	    	{
	    		if(is_accepted.equals("2")) {
		    		pengajuanDAO.deletePengajuan(id_pengajuan);
		    		return "pengajuan/delete";
	    		}else {
		    		return "pengajuan/is_accepted";
	    		}
	    	} else {
	    		model.addAttribute("id_pengajuan", id_pengajuan);
	    		return "pengajuan/not-found";
	    	}
	    }
	    
}
