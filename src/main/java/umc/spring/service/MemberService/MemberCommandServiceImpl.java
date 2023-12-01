package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.member.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request) {

        /**
         * Service는 비즈니스 로직에만 집중
         * 따라서, Member를 만드는 작업은 converter에서 작업
         */
        Member newMember = MemberConverter.toMember(request);

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(
                            ()-> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPerferList(foodCategoryList);
        for (MemberPrefer memberPrefer : memberPreferList) {
            memberPrefer.setMember(newMember);
        }

        // membmer의 cascade.all 을 통해 member 저장시 memberPrefer도 같이 저장
        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public MemberMission performMission(MemberRequestDTO.MissionDTO request) {

        // 유저 조회
        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                ()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        // 미션 조회
        Mission findMission = missionRepository.findById(request.getMissionId()).orElseThrow(
                () -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND)
        );

        MemberMission memberMission = MemberMission.builder()
                .member(findMember)
                .mission(findMission)
                .build();

        return memberMissionRepository.save(memberMission);
    }
}
