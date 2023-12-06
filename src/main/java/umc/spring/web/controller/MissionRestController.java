package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

@Tag(name = "mission", description = "Mission API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    // 미션 등록 API - 가게 정보 없이, 미션 생성 하는 API
    @PostMapping("/")
    @Operation(summary = "미션 추가", description = "RegisterDTO를 통해 미션 생성하기")
    public ApiResponse<MissionResponseDTO.RegisterResultDTO> registerMission(@RequestBody MissionRequestDTO.RegisterDTO request){
        Mission mission = missionCommandService.registerMission(request);
        return ApiResponse.onSuccess(MissionConverter.toRegisterResultDTO(mission));
    }
}
