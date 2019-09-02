package com.example.ampostest.controller;



import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ampostest.entity.Detail;
import com.example.ampostest.entity.Menu;
import com.example.ampostest.exception.MenuNotFoundException;
import com.example.ampostest.service.DetailService;
import com.example.ampostest.service.MenuService;
import com.example.ampostest.uitils.MenuSpecification;
import com.example.ampostest.uitils.MenuWrapper;
import com.example.ampostest.uitils.SearchCriteria;



@RestController
@RequestMapping("/menus")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private DetailService detailService;
		
	@GetMapping("/page/{page}")
	public List<MenuWrapper> getMenu (@PathVariable int page) {
		Page<Menu> menuList =  menuService.retrieveMenu(page);
		List<MenuWrapper> menuWrapperList = new ArrayList();
		for(Menu menu:menuList) {
			List<String> detail = detailService.retrieveDetail(menu.getId());
			MenuWrapper menuWrapper = new MenuWrapper(
					menu.getName(), 
					menu.getDescription(),
					menu.getImage(),
					menu.getPrice(),
					detail);
			menuWrapperList.add(menuWrapper);
		}
		return menuWrapperList;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getMenu(@PathVariable Long id) {
		Optional<Menu> menu = menuService.retrieveMenu(id);
		if(menu == null) {
			throw new MenuNotFoundException("id-" + id);
		}
		List<String> detail = detailService.retrieveDetail(id);
		MenuWrapper menuWrapper = new MenuWrapper(
				menu.get().getName(), 
				menu.get().getDescription(),
				menu.get().getImage(),
				menu.get().getPrice(),
				detail);
		return ResponseEntity.ok(menuWrapper);
	}
	
	@GetMapping("/filter/keyword")
	public List<MenuWrapper> getMenu (@RequestParam Map<String,String> allParams) {
		List<MenuSpecification> specs = new ArrayList<MenuSpecification>();
		String filterDetail = null;
		if(allParams.containsKey("detail")) {
			filterDetail = allParams.get("detail");
		}
		for(Map.Entry<String, String> param: allParams.entrySet()) {
			MenuSpecification spec = new MenuSpecification(new SearchCriteria(param.getKey(), ":", param.getValue()));
			specs.add(spec);
		}
		List<Menu> menuList =  menuService.FilterMenu(specs);
		List<Menu> filterList = new ArrayList<Menu>();
		if(!filterDetail.isEmpty()) {
			List<Detail> filterDetailList = detailService.filterDetail(filterDetail);
			for(Menu menu:menuList) {
				for(Detail detail:filterDetailList) {
					if(detail.getMenu().getId() == menu.getId()) {
						filterList.add(menu);
					}
				}
			}
		}
		else filterList = menuList;
		List<MenuWrapper> menuWrapperList = new ArrayList();
		for(Menu menu:filterList) {
			List<String> detail = detailService.retrieveDetail(menu.getId());
			MenuWrapper menuWrapper = new MenuWrapper(
					menu.getName(), 
					menu.getDescription(),
					menu.getImage(),
					menu.getPrice(),
					detail);
			menuWrapperList.add(menuWrapper);
		}
		return menuWrapperList;
	}

	@PostMapping()
    public ResponseEntity<Object> createMenu(@Valid @RequestBody MenuWrapper body) {
		Menu menu = new Menu(
				body.getName(),
				body.getDescription(),
				body.getImage(),
				body.getPrice());
		Menu createdMenu = menuService.createMenu(menu);
        for(String title :  body.getAdditionalDetails()) {
        	Detail detail = new Detail(createdMenu, title);
        	Detail createddetail = detailService.createDetail(detail);
        } 
		
        URI location = ServletUriComponentsBuilder
	    			.fromCurrentRequest()
	    			.path("/{id}")
	    			.buildAndExpand(createdMenu.getId())
	    			.toUri();
    		return ResponseEntity.created(location).build();
    }
	
	@PostMapping("/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable Long id, @Valid @RequestBody MenuWrapper body) {
		Menu menu = new Menu(
				body.getName(),
				body.getDescription(),
				body.getImage(),
				body.getPrice());
        Menu updateMenu = menuService.updateMenu(id, menu);
        if(updateMenu == null) {
        	throw new MenuNotFoundException("id-" + id);
        }
        for(String title :  body.getAdditionalDetails()) {
        	Optional<Detail> updateDetail = detailService.updateDetail(updateMenu, title);
        } 
        return ResponseEntity.ok().build();
    }
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteMenu (@PathVariable Long id) {
		String message = "";
		if(detailService.deleteDetal(id))
		if(menuService.deleteMenu(id)) {
			message = "Delete Success !" ;
		}
		else {
			message = "Delete Fail !" ;
		}
		return ResponseEntity.ok(message);
	}
	
	
	
}
