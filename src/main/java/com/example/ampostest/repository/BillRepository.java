package com.example.ampostest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ampostest.entity.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long>{

	List<Bill> findByMenuId(Long menu_id);

	List<Bill> findByOrderId(Long orderId);

}