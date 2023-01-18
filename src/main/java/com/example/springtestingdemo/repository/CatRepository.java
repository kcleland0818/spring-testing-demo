package com.example.springtestingdemo.repository;

import com.example.springtestingdemo.entity.Cat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends CrudRepository<Cat, Integer> {
}
