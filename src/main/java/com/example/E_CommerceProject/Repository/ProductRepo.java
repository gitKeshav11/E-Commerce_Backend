package com.example.E_CommerceProject.Repository;

import com.example.E_CommerceProject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo  extends JpaRepository<Product,Long> {
}
