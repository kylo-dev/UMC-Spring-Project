package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    // Entity -> DTO
    public static StoreResponseDTO.RegisterResultDTO toRegisterResultDTO(Store store){
        return StoreResponseDTO.RegisterResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.RegisterRegionDTO request) {

        // Region 연관관계 설정해주어야 함.
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }
}
