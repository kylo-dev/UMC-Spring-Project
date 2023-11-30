package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.repository.*;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.store.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Store registerStore(StoreRequestDTO.RegisterRegionDTO request) {
        // DTO -> Entity
        Store newStore = StoreConverter.toStore(request);

        // 기존에 저장된 Region 조회
        Long regionId = request.getRegionId();
        Region region = regionRepository.findById(regionId).orElseThrow(
                () -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND)
        );
        // 연관관계 설정
        newStore.setRegion(region);

        return storeRepository.save(newStore);
    }

    @Override
    @Transactional
    public void registerMission(StoreRequestDTO.RegisterMissionDTO request) {

        // 추가할 가게 조회
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(
                () -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)
        );

        // 등록할 미션 조회
        Mission mission = missionRepository.findById(request.getMissionId()).orElseThrow(
                ()-> new MissionHandler(ErrorStatus.MISSON_NOT_FOUND)
        );

        // 연관관계 설정 - 더티 체킹으로 업데이트 처리
        mission.setStore(store);
    }

}
