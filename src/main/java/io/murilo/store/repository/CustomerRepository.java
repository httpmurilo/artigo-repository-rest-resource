package io.murilo.store.repository;

import io.murilo.store.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "customer")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByName(@Param("name") String name);
    Optional<Customer> findById(Integer id);
}
