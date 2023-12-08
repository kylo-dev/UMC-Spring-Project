package umc.spring.service.MemberMissionService;

public interface MemberMissionQueryService {

    boolean existMemberAndMissionOnStatus(Long memberId, Long missionId);
}
