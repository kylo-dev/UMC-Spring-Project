package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.ErrorReasonDTO;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;

@Tag(name = "mission", description = "Mission API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    // 미션 등록 API - 가게 정보 없이, 미션 생성 하는 API
    @PostMapping("/")
    @Operation(summary = "미션 추가", description = "RegisterDTO를 통해 미션 생성하기")
    public ApiResponse<MissionResponseDTO.RegisterResultDTO> registerMission(@RequestBody MissionRequestDTO.RegisterMissionDTO request){
        Mission mission = missionCommandService.registerMission(request);
        return ApiResponse.onSuccess(MissionConverter.toRegisterResultDTO(mission));
    }

    // 특정 가게의 미션 목록
    @GetMapping("/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 API", description = "특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query string으로 page 번호를 주세요")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "올바른 STOREDID를 주세요.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "PAGE 번호는 1이상의 수입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다.!"),
            @Parameter(name = "page", description = "리뷰의 페이지 번호, query string 입니다.!")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page){
        Page<Mission> missionList = missionQueryService.getMissionList(storeId, page - 1);
        return ApiResponse.onSuccess(MissionConverter.toMissionPreViewListDTO(missionList));
    }
}
