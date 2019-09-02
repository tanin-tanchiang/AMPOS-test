package com.example.ampostest.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.ampostest.entity.Bill;
import com.example.ampostest.repository.BillRepository;
import com.example.ampostest.uitils.BillResponse;
import com.example.ampostest.uitils.BillResponseList;
import com.example.ampostest.uitils.BillWrapper;

@Service
public class BillService {

	@Autowired
    private BillRepository billRepository;
		
	public List<BillResponseList> converseMenuIdToName(List<BillWrapper> billList){
		List<BillResponseList> newlist = new ArrayList<BillResponseList>();
		for (BillWrapper billWrapper : billList) {
			List<Bill> bills= billWrapper.getOrder();
			List<BillResponse> converserList = coverseterBill(bills);
			newlist.add(new BillResponseList(converserList, billWrapper.getTotalPrice()));
		}
		return newlist;
	}
	
	private List<BillResponse> coverseterBill(List<Bill> bills){
		List<BillResponse> cenverseterBill = new ArrayList<BillResponse>();
		for(Bill bill:bills) {
			cenverseterBill.add(new BillResponse(bill.getMenu().getName(), bill.getOrderId(), bill.getQuantities()));
		}
		return cenverseterBill;
	}

	public List<BillWrapper> retrieveBill(){
		List<Bill> billList = (List<Bill>) billRepository.findAll();
		List<BillWrapper> billWrapperList = new ArrayList<BillWrapper>();
		for(Bill bill:billList) {
			List<Bill> order = retrieveBillByOrder(bill.getOrderId());
			int totalprice = calculatePrice(order);
			BillWrapper billWrapper = new BillWrapper(order, totalprice);
			billWrapperList.add(billWrapper);
		}
		
		Set<BillWrapper> set = new HashSet<BillWrapper>(billWrapperList);
		List<BillWrapper> billWrapperListResult = new ArrayList<BillWrapper>(set);
		return billWrapperListResult;
	}
	
	private int calculatePrice(List<Bill> billList) {
		int totalPrice = 0;
		for(Bill bill:billList) {
			totalPrice += bill.getQuantities() * bill.getMenu().getPrice();
		}
		return totalPrice;
	}
	public List<Bill> retrieveBillByOrder(Long order_id){
		List<Bill> billList = (List<Bill>) billRepository.findByOrderId(order_id);
		return billList;
	}
	
	public Optional<Bill> retrieveBill(Long id) {
    	Optional<Bill> bill =  billRepository.findById(id);
    	return bill;
    }
	
	public Bill createBill(@Valid Bill bill) {
        return billRepository.save(bill);
    }
	
	public Bill updateBill(Long id, Bill bill, String status) {
		if(status == "update") {
			Optional<Bill> billOptional = billRepository.findById(id);
			if(!billOptional.isPresent()) {
	            return billOptional.get();
	        }
	        return billRepository.save(bill);
		}
		else if(status == "add") {
			return billRepository.save(bill);
		}
		
		else if (status == "remove") {
			if(deleteBill(id)) {
				return null;
			}
		}
		return null;

    }
	
	public boolean deleteBill(Long id) {
        try {
        	billRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
	
}
