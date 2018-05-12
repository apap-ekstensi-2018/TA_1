package com.siasisten1.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.log.Log;
import com.siasisten1.dao.DosenDAO;
import com.siasisten1.model.Dosen;
import com.siasisten1.model.Lowongan;
import com.siasisten1.model.Matkul;
import com.siasisten1.model.PengajuanModel;
import com.siasisten1.service.DosenService;
import com.siasisten1.service.LowonganService;
import com.siasisten1.service.MatkulService;
import com.siasisten1.service.PengajuanService;

@Controller
@RequestMapping("pengajuan")
public class PengajuanController {
	
	@Autowired
	PengajuanService pengajuanDAO;

	@Autowired
	LowonganService lowonganDAO;
	

	@Autowired
	MatkulService matkulDAO;
	
	@Autowired
	DosenService dosenDAO;

	
	 @RequestMapping("/")
	    public String index ()
	    {
	        return "index";
	    }

	    @RequestMapping("/tambah")
	    public String add (Model model)
	    {	
	    	List <Lowongan> lowongan = lowonganDAO.getBukaLowongan();
	        List<Matkul> matkuls = matkulDAO.getMatkul();

	        Map<Integer, Matkul> listMatkul = new HashMap<Integer, Matkul>();

	        for(Matkul m : matkuls){
	          listMatkul.put(new Integer(m.getId()), m);
	        }

	    	
	        model.addAttribute("matkuls", listMatkul);
	    	model.addAttribute("lowongan", lowongan);
	        model.addAttribute("linkSubmit", "/pengajuan/tambah/submit");
	        return "pengajuan/form_tambah_pengajuan";
	    }

	    @RequestMapping(value = "/cici",method = RequestMethod.POST)
	    public String cici (Model model)
	    {	
	        return "pengajuan/form_tambah_pengajuan";
	    }


	    @RequestMapping(value = "/tambah/submit",method = RequestMethod.POST)
	    public String addSubmit (
	            @RequestParam(value = "id_pengajuan", required = false) String id_pengajuan,
	            @RequestParam(value = "id_lowongan", required = false) int id_lowongan,
	            @RequestParam(value = "username_mahasiswa", required = false) String username_mahasiswa,
	    		@RequestParam(value = "is_accepted", required = false) String status_pengajuan)
	    {
	        PengajuanModel checkPengajuan = pengajuanDAO.checkPengajuan(id_lowongan, username_mahasiswa);
	        
	        if(checkPengajuan!=null)
	        {
	        	return "pengajuan/pengajuan-failed";
	        }
	        else
	        {
	        	 PengajuanModel pengajuan = new PengajuanModel (id_pengajuan, id_lowongan, username_mahasiswa,status_pengajuan, "", 0);
	        	 pengajuanDAO.addPengajuan (pengajuan);
	        	 return "pengajuan/success-add";
	        }
	    }

	    @RequestMapping(value = "/view/{id_pengajuan}",method = RequestMethod.GET)
	    public String viewPath (Model model,
	            @PathVariable(value = "id_pengajuan") String id_pengajuan)
	    {
	        model.addAttribute("linkSubmit", "/pengajuan/hapus");
	    	PengajuanModel pengajuan = pengajuanDAO.selectPengajuan (id_pengajuan);

	        if (pengajuan != null) {
		    	String namaMatkul;
	            model.addAttribute ("pengajuan", pengajuan);
		        
	            Lowongan lowongan = lowonganDAO.getLowongan(pengajuan.getId_lowongan());
		        Matkul matkul = matkulDAO.getMatkul(lowongan.getIdMatkul());

		        
		        namaMatkul = matkul.getNama_matkul();
		        pengajuan.setNamaMatkul(namaMatkul);

		        return "pengajuan/viewPengajuan";
	        } else {
	            model.addAttribute ("id_pengajuan", id_pengajuan);
	            return "pengajuan/not-found";
	        }
	    }


	    @RequestMapping(value = "/viewall",method = RequestMethod.GET)
	    public String view (Model model, HttpServletRequest request)
	    {
	        model.addAttribute("reviewLink", "/pengajuan/review");

	        String namaMatkul;
	        int idMatkul;
        	List<PengajuanModel> data_pengajuan = new ArrayList<PengajuanModel>();

	        if(request.isUserInRole("ROLE_LECTURE")) {
	        	data_pengajuan = pengajuanDAO.selectAllPengajuan();
	        	Dosen dosen = dosenDAO.getDosen(request.getRemoteUser());

	        	if(dosen != null)
	        	{
		        	List<Matkul> data_matkul = dosen.getMataKuliahList();
		        	List<PengajuanModel> dp = new ArrayList<PengajuanModel>();
		        	
		        	for(PengajuanModel pengajuan : data_pengajuan)
			        {
				        Lowongan lowongan = lowonganDAO.getLowongan(pengajuan.getId_lowongan());
				        Matkul matkul = matkulDAO.getMatkul(lowongan.getIdMatkul());
				        
				        namaMatkul = matkul.getNama_matkul();
				        idMatkul = matkul.getId();
				        pengajuan.setNamaMatkul(namaMatkul);
				        pengajuan.setId_matkul(idMatkul);
				        
		        		for(Matkul m: data_matkul) {
		        			if(pengajuan.getId_matkul() == m.getId()) {
		        				dp.add(pengajuan);
		        			}
	        			}
			        }
		        	
		        	model.addAttribute("data_pengajuan",dp);
	        	}
	        	model.addAttribute("data_pengajuan",data_pengajuan);
	        	return "pengajuan/viewallPengajuanDosen";
	        }

	        if(request.isUserInRole("ROLE_STUDENT")) {
		        data_pengajuan = pengajuanDAO.selectAllPengajuanMahasiswa(request.getRemoteUser());

		        for(PengajuanModel pengajuan : data_pengajuan)
		        {
			        Lowongan lowongan = lowonganDAO.getLowongan(pengajuan.getId_lowongan());
			        Matkul matkul = matkulDAO.getMatkul(lowongan.getIdMatkul());
			        
			        namaMatkul = matkul.getNama_matkul();
			        pengajuan.setNamaMatkul(namaMatkul);
		        }
		        
		        model.addAttribute ("data_pengajuan", data_pengajuan);

		        return "pengajuan/viewallPengajuan";
	        }
	        return "pengajuan/not-found";

	        
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
	    
	    
	    @RequestMapping(value = "/review", method = RequestMethod.POST)
	    public String reviewPengajuan(Model model, 
	    		@RequestParam(value = "id_pengajuan", required = true) String id_pengajuan, 
	    		@RequestParam(value = "reviewValue", required = true) int reviewValue)
	    {
	    		PengajuanModel pengajuan = pengajuanDAO.selectPengajuan(id_pengajuan);
	    		if(pengajuan != null)
	    		{
	    			pengajuanDAO.updatePengajuan(id_pengajuan, reviewValue);
	    		    System.out.println("Rev Val :" + reviewValue);

	    		    if(reviewValue == 1) {
	    		    		model.addAttribute("id_pengajuan", id_pengajuan);
	    		    		return "pengajuan/accepted";
	    		    }else {
	    		    		model.addAttribute("id_pengajuan", id_pengajuan);
	    		    		return "pengajuan/rejected";
	    		    }
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
