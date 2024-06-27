package com.haisely.community.Repository.Impl;

import com.haisely.community.Entity.Image;
import com.haisely.community.Repository.ImageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageJpaRepository extends JpaRepository<Image, Integer>, ImageRepository {
}
