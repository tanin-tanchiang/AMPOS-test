package com.example.ampostest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ampostest.entity.Bill;
import com.example.ampostest.entity.Detail;
import com.example.ampostest.entity.Menu;
import com.example.ampostest.exception.MenuNotFoundException;
import com.example.ampostest.service.BillService;
import com.example.ampostest.service.MenuService;
import com.example.ampostest.uitils.BillCreateRequest;
import com.example.ampostest.uitils.BillResponse;
import com.example.ampostest.uitils.BillResponseList;
import com.example.ampostest.uitils.BillUpdateWrapper;
import com.example.ampostest.uitils.BillWrapper;
import com.example.ampostest.uitils.MenuWrapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;



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
	
	@GetMapping("/{id}")
	public BillResponseList getBill(@PathVariable Long id) {
		BillWrapper billWrapper = billService.retrieveBillById(id);
		if(billWrapper == null) {
			throw new MenuNotFoundException("id-" + id);
		}
		List<BillWrapper> billList = new ArrayList<BillWrapper>();
		billList.add(billWrapper);
		List<BillResponseList> billResponseList = billService.converseMenuIdToName(billList);
		BillResponseList billResponse = billResponseList.get(0);
		return billResponse;
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
	
	@PostMapping("/{id}")
	@ApiOperation(value = "Update bill")
    public ResponseEntity<?> updateBill(@Valid @RequestBody List<BillUpdateWrapper> body) {
		for(BillUpdateWrapper billUpdateWrapper:body) {
			Optional<Menu> menu = menuService.retrieveMenu(billUpdateWrapper.getBillCreateRequest().getMenu_id());
			Bill bill = new Bill(billUpdateWrapper
						.getBillCreateRequest()
						.getOrderId(),
						menu.get(),
						billUpdateWrapper
						.getBillCreateRequest()
						.getQuantities());
			Bill updateBill = billService.updateBill(bill, billUpdateWrapper.getStatus());
			if(updateBill == null) {
	        	throw new MenuNotFoundException("id-" + bill);
	        }
		}
        return ResponseEntity.ok().build();
    }
	
	
	
}
