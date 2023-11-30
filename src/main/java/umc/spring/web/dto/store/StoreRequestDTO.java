package umc.spring.web.dto.store;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class RegisterRegionDTO{
        String name;
        String address;
        Long regionId;
    }
}
