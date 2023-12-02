package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;


    // 가게에 리뷰 추가 API
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.RegisterResultDTO> registerReview(@PathVariable @ExistStore Long storeId,
                                                                           @RequestBody @Valid ReviewRequestDTO.RegisterDTO request){
        Review review = reviewCommandService.registerReview(storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toRegisterReviewResultDTO(review));
    }

    // 가게에 미션 추가 API
    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDTO.RegisterMissionResultDTO> registerMission(@PathVariable Long storeId,
                                                                                  @RequestBody MissionRequestDTO.RegisterDTO request){
        Mission mission = storeCommandService.registerMission(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toRegisterMissionResultDTO(mission));
    }
}
