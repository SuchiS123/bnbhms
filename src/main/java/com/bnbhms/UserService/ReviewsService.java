package com.bnbhms.UserService;

import com.bnbhms.Entity.Property;
import com.bnbhms.Entity.Reviews;
import com.bnbhms.Entity.User;
import com.bnbhms.PayLoad.ReviewsDto;
import com.bnbhms.Repository.PropertyRepository;
import com.bnbhms.Repository.ReviewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService {

    private ReviewsRepository reviewsRepository;
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;
    public ReviewsService(ReviewsRepository reviewsRepository,
                          PropertyRepository propertyRepository,
                          ModelMapper modelMapper) {
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<?> addReview(Long id, ReviewsDto reviewsDto, User user) {

            Property property = propertyRepository.findById(id).get();
        Reviews byPropertyAndUser = reviewsRepository.findByPropertyAndUser(property, user);

        if(byPropertyAndUser==null) {
            Reviews reviews = mapToEntity(reviewsDto);
            reviews.setProperty(property);
            reviews.setUser(user);
            Reviews savedReviews = reviewsRepository.save(reviews);
            ReviewsDto reviewsDto1 = mapToDto(savedReviews);
            return new ResponseEntity<>(reviewsDto1, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Already Reviewed To This Property ", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public Reviews mapToEntity(ReviewsDto reviewsDto)
    {
        return modelMapper.map(reviewsDto,Reviews.class);
    }

    public ReviewsDto mapToDto(Reviews reviews)
    {
        return modelMapper.map(reviews, ReviewsDto.class);
    }
}
