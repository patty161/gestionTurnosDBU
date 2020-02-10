package gestionturnos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class BeanImagen{
	private List<String> images;
	
	@PostConstruct
	public void init() {
		images=new ArrayList<String>();
		for (int i=1; i<=12;i++) {
			images.add("dbu"+i+".jpg");
		}
	}
	
	public BeanImagen() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getImages() {
		return images;
	}

}
