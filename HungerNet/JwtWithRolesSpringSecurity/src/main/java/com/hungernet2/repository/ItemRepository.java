package com.hungernet2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.hungernet2.model.entity.*;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Override
	Optional<Item> findById(Integer integer);

	List<Item> findByMenu(Menu menu);
}

