package com.bnbhms.Controller;

import com.bnbhms.Entity.User;
import com.bnbhms.PayLoad.ReviewsDto;
import com.bnbhms.UserService.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping("/add/reviews")
    public ResponseEntity<?> addReview(@RequestBody ReviewsDto reviewsDto,
                                                @RequestParam Long id,
                                                @AuthenticationPrincipal User user)
    {
        ResponseEntity<?> reviewsDto1 = reviewsService.addReview(id, reviewsDto, user);
        return new ResponseEntity<>(reviewsDto1, HttpStatus.OK);

    }
}
