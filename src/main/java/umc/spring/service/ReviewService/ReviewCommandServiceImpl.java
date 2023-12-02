package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.review.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review registerReview(Long storedId, ReviewRequestDTO.RegisterDTO request) {

        Review newReview = ReviewConverter.toReview(request);

        // 리뷰 작성자 조회
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        // 리뷰 등록할 가게 조회
        Store store = storeRepository.findById(storedId).orElseThrow(
                () -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)
        );

        newReview.setMember(member);
        newReview.setStore(store);

        return reviewRepository.save(newReview);
    }
}
