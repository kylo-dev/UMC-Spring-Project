package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean existMemberAndMissionOnStatus(Long memberId, Long missionId) {
        return memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(memberId, missionId);
    }

    @Override
    @Transactional
    @Modifying(clearAutomatically = true)
    public MemberMission completeMissionStatus(Long memberId, Long missionId) {

        // 미션이 회원에게 등록 되었는지 확인하기
        boolean result = memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);
        if (!result){
            throw new MissionHandler(ErrorStatus.MISSION_NOT_BELONGS_TO_MEMBER);
        }

        // 진행 중인 미션 가져오기 (조회)
        MemberMission findMemberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId);

        if (findMemberMission.getStatus() != MissionStatus.CHALLENGING){
            throw new MissionHandler(ErrorStatus.MEMBER_MISSION_NOT_CHALLENGING);
        }
        findMemberMission.changeStatus(MissionStatus.COMPLETE);
        return findMemberMission;
    }
}
