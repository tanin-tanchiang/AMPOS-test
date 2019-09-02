package com.example.ampostest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.ampostest.entity.Detail;
import com.example.ampostest.entity.Menu;
import com.example.ampostest.repository.DetailRepository;

@Service
public class DetailService {
	@Autowired
    private DetailRepository detailRepository;
	
    public List<String> retrieveDetail(Long menu_id) {
    	List<Detail> details =  detailRepository.findByMenuId(menu_id);
    	List<String> title = new ArrayList<String>();
    	for(Detail detail :  details) {
        	title.add(detail.getTitle());
        } 
    	return title;
    }
    
    public List<Detail> filterDetail(String title) {
    	List<Detail> details =  detailRepository.findByTitle(title);
    	return details;
    }
    
    public Detail retrieveDetailWithTitle(Long menu_id, String title) {
    	return detailRepository.findByMenuIdAndTitle(menu_id, title);
    }
    
    public Detail createDetail(@Valid Detail detail) {
        return detailRepository.save(detail);
    }
    
    public Optional<Detail> updateDetail(Menu menu, String title) {
        Detail detail = retrieveDetailWithTitle(menu.getId(), title);
        if(detail != null)
        	return Optional.of(detail);
        return Optional.of(detailRepository.save(new Detail(menu, title)));
    }

	public boolean deleteDetal(Long menu_id) {
		boolean result = false;
		List<String> details = retrieveDetail(menu_id);
		for(var detail :  details) {
			Detail detailToDelete = retrieveDetailWithTitle(menu_id, detail);
			try {
				detailRepository.deleteById(detailToDelete.getId());
				result =  true;
	        } catch (EmptyResultDataAccessException e) {
	        	result =  false;
	        }
		}
		return result;
	}
    
    
    
    
}
