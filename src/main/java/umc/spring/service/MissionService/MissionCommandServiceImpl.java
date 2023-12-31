package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.mission.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService{

    private final MissionRepository missionRepository;

    // 미션 생성하기
    @Override
    public Mission registerMission(MissionRequestDTO.RegisterMissionDTO request) {
        Mission newMission = MissionConverter.toMission(request);

        return missionRepository.save(newMission);
    }
}
