package com.ind.SmartExpenseTracker.repository;

import com.ind.SmartExpenseTracker.entity.Report;
import org.apache.el.stream.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.YearMonth;

public interface ReportRepository extends MongoRepository<Report, String> {
    Optional<Report> findByUserIdAndPeriod(String userId, YearMonth period);
}

