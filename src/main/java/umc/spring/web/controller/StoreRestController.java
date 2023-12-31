package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

import javax.validation.Valid;

@Tag(name = "store", description = "Store API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;
    private final ReviewCommandService reviewCommandService;

    // 가게에 리뷰 추가 API
    @PostMapping("/{storeId}/reviews")
    @Operation(summary = "리뷰 작성", description = "RegisterDTO를 통해 가게에 리뷰 작성하기")
    public ApiResponse<ReviewResponseDTO.RegisterResultDTO> registerReview(@PathVariable @ExistStore Long storeId,
                                                                           @RequestBody @Valid ReviewRequestDTO.RegisterReviewDTO request){
        Review review = reviewCommandService.registerReview(storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toRegisterReviewResultDTO(review));
    }

    // 가게에 미션 추가 API
    @PostMapping("/{storeId}/missions")
    @Operation(summary = "가게 미션 추가", description = "RegisterDTO를 통해 가게에 미션 추가하기")
    public ApiResponse<StoreResponseDTO.RegisterMissionResultDTO> registerMission(@PathVariable Long storeId,
                                                                                  @RequestBody MissionRequestDTO.RegisterMissionDTO request){
        Mission mission = storeCommandService.registerMission(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toRegisterMissionResultDTO(mission));
    }

    // 리뷰 목록 조회
    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query string 으로 page 번호를 주세요.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "올바른 STOREID가 아닙니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "PAGE 번호는 1이상의 수입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다.!"),
            @Parameter(name = "page", description = "리뷰의 페이지 번호, query string 입니다.!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page - 1);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(reviewList));
    }
}
