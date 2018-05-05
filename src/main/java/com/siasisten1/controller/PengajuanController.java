package com.siasisten1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.siasisten1.model.PengajuanModel;
import com.siasisten1.service.PengajuanService;

@Controller
@RequestMapping("pengajuan")
public class PengajuanController {
	
	@Autowired
	PengajuanService pengajuanDAO;
	
	 @RequestMapping("/")
	    public String index ()
	    {
	        return "index";
	    }

	    @RequestMapping("/tambah")
	    public String add ()
	    {
	        return "pengajuan/form_tambah_pengajuan";
	    }


	    @RequestMapping("/tambah/submit")
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
//
//
//	    @RequestMapping("/Pengajuan/view")
//	    public String view (Model model,
//	            @RequestParam(value = "npm", required = false) String npm)
//	    {
//	        PengajuanModel Pengajuan = PengajuanDAO.selectPengajuan (npm);
//
//	        if (Pengajuan != null) {
//	            model.addAttribute ("Pengajuan", Pengajuan);
//	            return "view";
//	        } else {
//	            model.addAttribute ("npm", npm);
//	            return "not-found";
//	        }
//	    }

	    @RequestMapping(value = "/view/{id_pengajuan}",method = RequestMethod.GET)
	    public String viewPath (Model model,
	            @PathVariable(value = "id_pengajuan") String id_pengajuan)
	    {
	        PengajuanModel pengajuan = pengajuanDAO.selectPengajuan (id_pengajuan);

	        if (pengajuan != null) {
	            model.addAttribute ("pengajuan", pengajuan);
	            return "pengajuan/viewPengajuan";
	        } else {
	            model.addAttribute ("id_pengajuan", id_pengajuan);
	            return "pengajuan/not-found";
	        }
	    }


	    @RequestMapping(value = "/viewall",method = RequestMethod.GET)
	    public String view (Model model)
	    {
	        List<PengajuanModel> data_pengajuan = pengajuanDAO.selectAllPengajuan ();
	        model.addAttribute ("data_pengajuan", data_pengajuan);

	        return "pengajuan/viewallPengajuan";
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
	    
	    @RequestMapping(value = "/hapus/",method = RequestMethod.POST)
	    public String deletePost (Model model, 
	    		@RequestParam(value = "id_pengajuan", required = false) String id_pengajuan,
	    		@RequestParam(value = "is_accepted", required = false) String is_accepted)
	    {
	        PengajuanModel pengajuan = pengajuanDAO.selectPengajuan (id_pengajuan);
	    	if(pengajuan != null)
	    	{
	    		if(is_accepted.equals("2")) {
		    		pengajuanDAO.deletePengajuan(id_pengajuan);
		    		return "delete";
	    		}else {
		    		return "is_accepted";
	    		}
	    	} else {
	    		model.addAttribute("id_pengajuan", id_pengajuan);
	    		return "pengajuan/not-found";
	    	}
	    }
	    
//	    @RequestMapping("/Pengajuan/update/{npm}")
//	    public String update (Model model, @PathVariable(value = "npm") String npm)
//	    {
//	    	PengajuanModel Pengajuan = PengajuanDAO.selectPengajuan(npm);
//	    	if(Pengajuan == null)
//	    	{
//	    		model.addAttribute("npm",npm);
//	    		return "not-found";
//	    	}
//	    	model.addAttribute ("Pengajuan", Pengajuan);      
//	        return "form-update";   
//		}
//
//		@RequestMapping(value = "/Pengajuan/update/submit", method = RequestMethod.POST )
//		public String updateSubmit(@ModelAttribute PengajuanModel Pengajuan)
//		{
//			PengajuanDAO.updatePengajuan (Pengajuan);
//		  
//		    return "success-update";   }


}
