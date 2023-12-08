package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.MissionRequestDTO;

public interface MissionCommandService {

    Mission registerMission(MissionRequestDTO.RegisterMissionDTO request);
}
