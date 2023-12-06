package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;

public interface MemberQueryService {

    boolean existMember(Long memberId);
    Page<Review> getReviewList(Long memberId, Integer page);
}
