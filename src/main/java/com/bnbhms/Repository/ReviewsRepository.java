package com.bnbhms.Repository;

import com.bnbhms.Entity.Property;
import com.bnbhms.Entity.Reviews;
import com.bnbhms.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    Reviews findByPropertyAndUser(Property property,User user);
}