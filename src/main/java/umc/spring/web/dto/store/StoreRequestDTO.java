package umc.spring.web.dto.store;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class RegisterRegionDTO{
        String name;
        String address;
        Long regionId;
    }

    @Getter
    public static class RegisterMissionDTO{
        Long storeId;  // 기존 가게의 Id
        Long missionId; // 추가할 미션의 Id
    }
}
