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
import com.example.ampostest.entity.Menu;
import com.example.ampostest.repository.BillRepository;
import com.example.ampostest.uitils.BillResponse;
import com.example.ampostest.uitils.BillResponseList;
import com.example.ampostest.uitils.BillWrapper;

@Service
public class BillService {

	@Autowired
    private BillRepository billRepository;
		
	private List<BillResponse> coverseterBill(List<Bill> bills){
		List<BillResponse> cenverseterBill = new ArrayList<BillResponse>();
		for(Bill bill:bills) {
			cenverseterBill.add(new BillResponse(bill.getMenu().getName(), bill.getOrderId(), bill.getQuantities()));
		}
		return cenverseterBill;
	}
	
	private boolean checkDupicate(List<BillWrapper> billWrapperList ,Long order_id) {
		for(BillWrapper billWrapper:billWrapperList) {
			if(billWrapper.getOrder().get(0).getOrderId() == order_id) {
				return true;
			}
		}
		return false;
	}
	
	private int calculatePrice(List<Bill> billList) {
		int totalPrice = 0;
		for(Bill bill:billList) {
			totalPrice += bill.getQuantities() * bill.getMenu().getPrice();
		}
		return totalPrice;
	}
	
	public List<BillResponseList> converseMenuIdToName(List<BillWrapper> billList){
		List<BillResponseList> newlist = new ArrayList<BillResponseList>();
		for (BillWrapper billWrapper : billList) {
			List<Bill> bills= billWrapper.getOrder();
			List<BillResponse> converserList = coverseterBill(bills);
			newlist.add(new BillResponseList(converserList, billWrapper.getTotalPrice()));
		}
		return newlist;
	}
	
	public List<BillWrapper> retrieveBill(){
		List<Bill> billList = (List<Bill>) billRepository.findAll();
		List<BillWrapper> billWrapperList = new ArrayList<BillWrapper>();
		for(Bill bill:billList) {
			List<Bill> order = retrieveBillByOrder(bill.getOrderId());
			int totalprice = calculatePrice(order);
			BillWrapper billWrapper = new BillWrapper(order, totalprice);
			if(!checkDupicate(billWrapperList, bill.getOrderId()))
				billWrapperList.add(billWrapper);
		}
		return billWrapperList;
	}
	
	public List<Bill> retrieveBillByOrder(Long order_id){
		List<Bill> billList = (List<Bill>) billRepository.findByOrderId(order_id);
		return billList;
	}
	
 	public BillWrapper retrieveBillById(Long order_id) {
		List<Bill> order = retrieveBillByOrder(order_id);
		int totalprice = calculatePrice(order);
		BillWrapper billWrapper = new BillWrapper(order, totalprice);
		
		return billWrapper;
 	}
	public Optional<Bill> retrieveBill(Long id) {
    	Optional<Bill> bill =  billRepository.findById(id);
    	return bill;
    }
	
	public Bill createBill(@Valid Bill bill) {
        return billRepository.save(bill);
    }
		
	public Bill updateBill(Bill bill, String status) {
		Bill forUpdateBill =  billRepository.findByOrderIdAndMenuId(bill.getOrderId(), bill.getMenu().getId());
		if(status.equals("update")) {
			bill.setId(forUpdateBill.getId());
	        return billRepository.save(bill);
		}
		else if(status.equals("add")) {
			return billRepository.save(bill);
		}
		
		else if (status.equals("remove")) {
			if(deleteBill(forUpdateBill.getId())) {
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
