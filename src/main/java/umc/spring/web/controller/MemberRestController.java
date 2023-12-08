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
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.*;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.review.ReviewResponseDTO;

import javax.validation.Valid;

@Tag(name = "member", description = "Member API") // API 그룹 설정
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/")
    @Operation(summary = "회원 가입", description = "JoinDTO를 통해 회원가입 진행")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/missions")
    @Operation(summary = "맴버 미션 추가", description = "진행 중인 미션이 아니라면 미션 추가하기")
    public ApiResponse<MemberResponseDTO.MissionResultDTO> missionPerform(@RequestBody @ExistChallengingMission MemberRequestDTO.MissionDTO request){
        MemberMission memberMission = memberCommandService.performMission(request);
        return ApiResponse.onSuccess(MemberConverter.toMissionResultDTO(memberMission));
    }

    // 내가 작성한 리뷰 목록 API
    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "각 회원이 작성한 리뷰의 목록을 조회하는 API이며, 페이징을 포함합니다. query string으로 page 번호를 주세요")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "올바른 MEMBERID가 아닙니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "PAGE 번호는 1이상의 수입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다.!"),
            @Parameter(name = "page", description = "리뷰의 페이지 번호, query string 입니다.!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = memberQueryService.getReviewList(memberId, page-1);

        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(reviewList));
    }

    // 내가 진행중인 미션 목록 API - Parameter로 Enum Class를 받을 수 있음
    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행중인 미션 목록 API", description = "각 회원의 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query string으로 status, page 를 주세요")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "올바른 MEMBERID가 아닙니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER_MISSION4001", description = "올바른 미션 상태가 아닙니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "PAGE 번호는 1이상의 수입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다.!"),
            @Parameter(name = "status", description = "미션 상태, query string 입니다.!"),
            @Parameter(name = "page", description = "리뷰의 페이지 번호, query string 입니다.!")
    })
    public ApiResponse<MemberResponseDTO.ChallengingMissionPreViewListDTO> getMissionChallenging(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                                                 @ExistMissionStatus @RequestParam(name = "status") MissionStatus status,
                                                                                                 @CheckPage @RequestParam(name = "page") Integer page){
        Page<MemberMission> memberMissionList = memberQueryService.getMemberMissionList(memberId, status, page -1);

        return ApiResponse.onSuccess(MemberMissionConverter.toChallengingMissionPreViewListDTO(memberMissionList));
    }

    // 진행중인 미션 -> 진행 완료로 변경하기
    @PatchMapping("/{memberId}/missions/{missionId}")
    @Operation(summary = "진행 중인 미션 상태 진행 완료 상태로 바꾸는 API", description = "회원이 수행한 미션을 진행 완료 상태로 변경합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "올바른 MEMBERID가 아닙니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "올바른 MISSIONID가 아닙니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다.!"),
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다.!")
    })
    public ApiResponse<MemberResponseDTO.MissionChangeResultDTO> completeMission(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                                 @ExistMission @PathVariable(name = "missionId") Long missionId){

        MemberMission memberMission = memberMissionCommandService.completeMissionStatus(memberId, missionId);
        return ApiResponse.onSuccess(MemberConverter.toMissionChangeResultDTO(memberMission));
    }

}