package umc.spring.web.dto.store;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StoreRequestDTO {

    @Getter
    public static class RegisterRegionDTO{
        @Size(min = 3, max = 10)
        String name;
        @Size(min = 5, max=12)
        String address;
        @NotNull
        Long regionId;
    }

    @Getter
    public static class RegisterMissionDTO{
        @NotNull
        Long storeId;  // 기존 가게의 Id
        @NotNull
        Long missionId; // 추가할 미션의 Id
    }
}
