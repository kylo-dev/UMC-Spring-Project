package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionRestController {

    private final StoreCommandService storeCommandService;

    // 특정 지역에 가게 추가 API
    @PostMapping("/{regionId}/stores")
    public ApiResponse<StoreResponseDTO.RegisterResultDTO> register(@PathVariable Long regionId,
                                                                    @RequestBody StoreRequestDTO.RegisterRegionDTO request){
        Store store = storeCommandService.registerStore(regionId, request);
        return ApiResponse.onSuccess(StoreConverter.toRegisterResultDTO(store));
    }
}
