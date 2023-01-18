package com.example.springtestingdemo.repository;

import com.example.springtestingdemo.entity.Rescue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RescueRepository extends CrudRepository<Rescue, Integer> {

    Optional<Rescue> findRescueByName(String name);
}
