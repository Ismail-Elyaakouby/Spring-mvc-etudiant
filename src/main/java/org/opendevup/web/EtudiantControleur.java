package org.opendevup.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.opendevup.dao.EtudiantRepository;
import org.opendevup.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import scala.remote;

@Controller
/*@RequestMapping(value="/Etudiant")*/
public class EtudiantControleur {
@Autowired
	private EtudiantRepository etudiantRepository;

    @Value("${dir.images}")
    private String dirimage;
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String Index(Model model,@RequestParam(name="page",defaultValue="0") int page,@RequestParam(name="motcle",defaultValue="")String mc){
		
		Page<Etudiant> pageEtudiants=etudiantRepository.chercherEtudiant("%"+mc+"%", new PageRequest(page, 5));
		model.addAttribute("pageEtudiants",pageEtudiants);
		int[] pages=new int[pageEtudiants.getTotalPages()];
		for(int i=0;i<pages.length;i++) pages[i]=i;
		model.addAttribute("motcle",mc);	
		model.addAttribute("page", page);
		model.addAttribute("pages", pages);
		

		return"etudiants";
	}
	@RequestMapping(value="/FormEtudiant",method=RequestMethod.GET)
	public String formEtudiant(Model model){
		model.addAttribute("etudiant", new Etudiant());
		return "FormEtudiant";
	}
	@RequestMapping(value="/SaveEtudiant",method=RequestMethod.POST)
	public String SaveEtudiant(@Valid Etudiant et,BindingResult bindingResult
			,@RequestParam(name="picture")MultipartFile file) throws  IOException{
       
		if(bindingResult.hasErrors()){
			return "FormEtudiant";
		}
		
		if(!(file.isEmpty())){et.setPhoto(file.getOriginalFilename());
		etudiantRepository.save(et);
          System.out.println(et.getDateNaissance());
			System.out.println("----------------------");
			System.out.println(file.getOriginalFilename());
			et.setPhoto(file.getOriginalFilename());
			//file.transferTo(new File(System.getProperty("user.home")+"/scolarite/"+et.getId()));
			file.transferTo(new File(dirimage+et.getId()));
		}
		
	
		return "redirect:index";
	}
	@RequestMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws  IOException{
		File f=new File(dirimage+id);
		System.out.println(dirimage+id);
		System.out.println(IOUtils.toByteArray(new FileInputStream(f)));
		return IOUtils.toByteArray(new FileInputStream(f));	
	}
	@RequestMapping(value="/edit")
	public String EditEtudiant(Model model,Long id){
		Etudiant et=etudiantRepository.getOne(id);
		model.addAttribute("etudiant", et);
		return "EditEtudiant";
	}
	@RequestMapping(value="/supprimer")
	public String SpprimerEtudiant(Long id){
		
		etudiantRepository.delete(id);;
		
		return "redirect:index";
	}	
	
}
