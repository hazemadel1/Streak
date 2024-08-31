package com.example.Streak.Data.Segmentation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentationRepository extends CrudRepository<Segmentation, String> {
    Segmentation findByMsisdn(String msisdn);
}
