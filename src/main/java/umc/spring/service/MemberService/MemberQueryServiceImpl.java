package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 회원이 존재 여부 확인
    @Override
    public boolean existMember(Long memberId) {
        return memberRepository.existsById(memberId);
    }

    // 회원이 작성한 리뷰 조회
    @Override
    public Page<Review> getReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }

    // 내가 진행 중인 미션 조회
    @Override
    public Page<MemberMission> getMemberMissionList(Long memberId, MissionStatus status, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        return memberMissionRepository.findAllByMemberAndStatus(member, status, PageRequest.of(page, 10));
    }
}
