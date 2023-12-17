package umc.spring.service.MemberMissionService;

import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionCommandService {
    MemberMission completeMissionStatus(Long memberId, Long missionId);

    MemberMission completeMissionStatus(Long memberMissionId);
}
