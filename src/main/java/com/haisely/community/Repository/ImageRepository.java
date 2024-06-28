package com.haisely.community.Repository;

import com.haisely.community.Entity.Image;

import java.util.Optional;

public interface ImageRepository {
    Optional<Image> findById(int id);
}
