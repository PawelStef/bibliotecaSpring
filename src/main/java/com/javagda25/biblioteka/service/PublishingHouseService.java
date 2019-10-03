package com.javagda25.biblioteka.service;

import com.javagda25.biblioteka.model.PublishingHouse;
import com.javagda25.biblioteka.repository.PublishingHouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublishingHouseService {
    private final PublishingHouseRepository publishingHouseRepository;

    public List<PublishingHouse> getAllHouse() {
       return publishingHouseRepository.findAll();
    }

    public void add(PublishingHouse publishingHouse) {
        publishingHouseRepository.save(publishingHouse);
    }

    public Optional<PublishingHouse> findById(Long phId) {
        return publishingHouseRepository.findById(phId);
    }

    public void remove(PublishingHouse publishingHouse) {
        publishingHouseRepository.delete(publishingHouse);
    }
}
