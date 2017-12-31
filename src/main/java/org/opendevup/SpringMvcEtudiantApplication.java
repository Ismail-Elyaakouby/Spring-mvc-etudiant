package org.opendevup;

import java.util.Date;
import java.util.List;

import org.opendevup.dao.EtudiantRepository;
import org.opendevup.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringMvcEtudiantApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(SpringMvcEtudiantApplication.class, args);

		EtudiantRepository dao=ctx.getBean(EtudiantRepository.class);
		//Ajouter quelque etudiant
		/*dao.save(new Etudiant("yassine","yassine.jpg",new Date()));
		dao.save(new Etudiant("mehdi","mehdi.jpg",new Date()));
		System.out.println("---------------------------------");
		//Consulter tous les etudiants
		List<Etudiant> listetudiant=dao.findAll();
		
		for(Etudiant e:listetudiant){
			System.out.println("nom etudiant:"+e.getNom());
			System.out.println("email etudiant:"+e.getEmail());
			System.out.println("nom etudiant:"+e.getDateNaissance());
		}
		//Consulter un etudiant
		Etudiant e=dao.findOne(2L);
		System.out.println(e.getNom()+" "+" "+e.getEmail());*/
	}
}
