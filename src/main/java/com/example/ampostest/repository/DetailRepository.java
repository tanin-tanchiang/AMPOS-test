package com.example.ampostest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ampostest.entity.Detail;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Long>{
	Detail findByMenuIdAndTitle(Long menu_id, String Title);
	List<Detail> findByMenuId(Long menu_id);
	void deleteByMenuId(Long menu_id);

}
