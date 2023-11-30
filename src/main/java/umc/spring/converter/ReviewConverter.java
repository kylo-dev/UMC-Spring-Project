package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static ReviewResponseDTO.RegisterResultDTO toRegisterReviewResultDTO(Review review){

        return ReviewResponseDTO.RegisterResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static Review toReview(ReviewRequestDTO.RegisterDTO request){

        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }
}
