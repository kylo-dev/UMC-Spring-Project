package umc.spring.web.controller;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    // 미션 등록 API - 가게 정보 없이, 미션 생성 하는 API
    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.RegisterResultDTO> registerMission(@RequestBody MissionRequestDTO.RegisterDTO request){
        Mission mission = missionCommandService.registerMission(request);
        return ApiResponse.onSuccess(MissionConverter.toRegisterResultDTO(mission));
    }
}
