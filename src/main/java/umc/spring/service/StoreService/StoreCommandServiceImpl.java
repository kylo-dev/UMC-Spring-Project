package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.repository.*;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.store.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Store registerStore(Long regionId, StoreRequestDTO.RegisterRegionDTO request) {
        // DTO -> Entity
        Store newStore = StoreConverter.toStore(request);

        // 기존의 Region 조회
        Region region = regionRepository.findById(regionId).orElseThrow(
                () -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND)
        );
        // 연관관계 설정
        newStore.setRegion(region);

        return storeRepository.save(newStore);
    }

    @Override
    @Transactional
    public Mission registerMission(Long storeId, MissionRequestDTO.RegisterDTO request) {

        // 추가할 가게 조회
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)
        );

        Mission newMission = MissionConverter.toMission(request);

        // 연관관계 설정 - 더티 체킹으로 업데이트 처리
        newMission.setStore(store);
        return missionRepository.save(newMission);
    }

    @Override
    public boolean exsistStore(Long storeId) {
        return storeRepository.existsById(storeId);
    }
}
