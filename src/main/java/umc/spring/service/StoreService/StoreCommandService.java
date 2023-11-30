package umc.spring.service.StoreService;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.store.StoreRequestDTO;

public interface StoreCommandService {

    Store registerStore(StoreRequestDTO.RegisterRegionDTO request);
    void registerMission(StoreRequestDTO.RegisterMissionDTO request);
//    Review registerReview(ReviewRequestDTO.RegisterDTO request);
}
