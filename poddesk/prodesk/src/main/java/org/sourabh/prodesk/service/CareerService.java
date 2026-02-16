package org.sourabh.prodesk.service;

import org.sourabh.prodesk.entity.Career;
import org.sourabh.prodesk.repository.CareerRepository;
import org.springframework.stereotype.Service;

@Service
public class CareerService {

    private final CareerRepository repo;

    public CareerService(CareerRepository repo) {
        this.repo = repo;
    }

    public Career save(Career career) {
        return repo.save(career);
    }
}
