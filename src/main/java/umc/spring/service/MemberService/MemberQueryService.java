package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

public interface MemberQueryService {

    boolean existMember(Long memberId);
    Page<Review> getReviewList(Long memberId, Integer page);
    Page<MemberMission> getMemberMissionList(Long memberId, MissionStatus status, Integer page);
}
