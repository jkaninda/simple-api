package com.jkaninda.simpleapi.repository;

import com.jkaninda.simpleapi.entity.Book;
import com.jkaninda.simpleapi.entity.Fake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//Fake , for application health check
@Repository
public interface FakeRepository extends CrudRepository<Fake, String> {
}
