package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean existMember(Long memberId) {
        return memberRepository.existsById(memberId);
    }

    @Override
    public Page<Review> getReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }
}
