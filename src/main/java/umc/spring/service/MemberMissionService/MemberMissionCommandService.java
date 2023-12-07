package umc.spring.service.MemberMissionService;

import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionCommandService {

    boolean existMemberAndMissionOnStatus(Long memberId, Long missionId);
    MemberMission completeMissionStatus(Long memberId, Long missionId);
}
