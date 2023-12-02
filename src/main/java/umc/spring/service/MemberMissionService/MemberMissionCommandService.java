package umc.spring.service.MemberMissionService;

public interface MemberMissionCommandService {

    boolean exsistMemberAndMissionOnStatus(Long memberId, Long missionId);
}
