package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Image;

import java.util.Optional;

@Repository
public interface UserImagesRepository extends JpaRepository<Image,Long>{


    Optional<Image> findByToken(String token);
}
