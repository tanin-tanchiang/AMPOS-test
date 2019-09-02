package com.example.ampostest.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.ampostest.entity.Menu;
import com.example.ampostest.repository.MenuRepository;
import com.example.ampostest.uitils.MenuSpecification;

@Service
public class MenuService {
	private MenuRepository menuRepository;

	public MenuService() {
		
	}
	@Autowired
	public MenuService(MenuRepository menuRepository) {
		super();
		this.menuRepository = menuRepository;
	}
	
	public Page<Menu> retrieveMenu(int page) {
		Pageable pageable = PageRequest.of(-1 + page, 10);
	    return (Page<Menu>) menuRepository.findAll(pageable);
	}
	
	
	public Optional<Menu> retrieveMenu(Long id) {
		Optional<Menu> menuOptional = menuRepository.findById(id);
		if(!menuOptional.isPresent()) {
            return menuOptional;
        }
        return menuOptional;
	}
	
	public List<Menu> FilterMenu(List<MenuSpecification> specs){
		if(specs.size() == 1)
			return menuRepository.findAll(Specification.where(specs.get(0)));
		else if (specs.size() == 2)
			return menuRepository.findAll(Specification.where(specs.get(0)).and(specs.get(0)));
		else if(specs.size() == 3)
			return menuRepository.findAll(Specification.where(specs.get(0)).and(specs.get(0)).and(specs.get(0)));
		else 
			return (List<Menu>) menuRepository.findAll();
	}
		
	public Menu createMenu(@Valid Menu menu) {
        return menuRepository.save(menu);
    }
	
	public Menu updateMenu(Long id, Menu menu) {
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if(!menuOptional.isPresent()) {
            return menuOptional.get();
        }
        menu.setId(id);
        return menuRepository.save(menu);
    }
	
	public boolean deleteMenu(Long id) {
        try {
        	menuRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
	
}
