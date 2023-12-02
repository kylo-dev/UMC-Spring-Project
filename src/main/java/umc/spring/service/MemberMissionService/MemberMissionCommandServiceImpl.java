package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean exsistMemberAndMissionOnStatus(Long memberId, Long missionId) {
        return memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(memberId, missionId);
    }
}
