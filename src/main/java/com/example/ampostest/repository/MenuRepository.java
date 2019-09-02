package com.example.ampostest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.ampostest.entity.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long>, PagingAndSortingRepository<Menu, Long>, JpaSpecificationExecutor<Menu>{
	Page<Menu> findAll(Pageable pageable);
}
