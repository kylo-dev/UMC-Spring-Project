package umc.spring.service.StoreService;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.store.StoreRequestDTO;

public interface StoreCommandService {

    Store registerStore(Long regionId, StoreRequestDTO.RegisterRegionDTO request);
    Mission registerMission(Long storeId, MissionRequestDTO.RegisterDTO request);
    boolean exsistStore(Long storeId);
}
