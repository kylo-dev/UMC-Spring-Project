package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;

    // 특정 지역에 가게 추가 - API
    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.RegisterResultDTO> register(@RequestBody StoreRequestDTO.RegisterRegionDTO request){
        Store store = storeCommandService.registerStore(request);
        return ApiResponse.onSuccess(StoreConverter.toRegisterResultDTO(store));
    }

    @PatchMapping("/missions")
    public ApiResponse<StoreResponseDTO.RegisterMissionResultDTO> registerMission(@RequestBody StoreRequestDTO.RegisterMissionDTO request){
        storeCommandService.registerMission(request);
        return ApiResponse.onSuccess(StoreConverter.toRegisterMissionResultDTO(request));
    }

    @PatchMapping("/reviews")
    public ApiResponse<ReviewResponseDTO.RegisterResultDTO> registerReview(@RequestBody @Valid ReviewRequestDTO.RegisterDTO request){
        Review review = reviewCommandService.registerReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toRegisterReviewResultDTO(review));
    }
}
