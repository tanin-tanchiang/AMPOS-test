package com.example.ampostest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ampostest.entity.Bill;
import com.example.ampostest.entity.Menu;
import com.example.ampostest.service.BillService;
import com.example.ampostest.service.MenuService;
import com.example.ampostest.uitils.BillCreateRequest;
import com.example.ampostest.uitils.BillResponseList;
import com.example.ampostest.uitils.BillWrapper;



@RestController
@RequestMapping("/bill")
public class BillController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private BillService billService;
	
	@GetMapping()
	public List<BillResponseList> getBill() {
		List<BillWrapper> billList = billService.retrieveBill();
		return billService.converseMenuIdToName(billList);
	}
	
	@PostMapping()
    public ResponseEntity<Object> creatBill(@Valid @RequestBody BillCreateRequest billCreateRequest) {
		Optional<Menu> menu = menuService.retrieveMenu(billCreateRequest.getMenu_id());
		
		Bill bill = new Bill(billCreateRequest.getOrderId(), menu.get(), billCreateRequest.getQuantities());
		Bill createBill = billService.createBill(bill);
        URI location = ServletUriComponentsBuilder
	    			.fromCurrentRequest()
	    			.path("/{id}")
	    			.buildAndExpand(createBill.getId())
	    			.toUri();
    		return ResponseEntity.created(location).build();

    }
	
}
