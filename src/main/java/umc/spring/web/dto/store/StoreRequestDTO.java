package umc.spring.web.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import jakarta.validation.constraints.Size;

public class StoreRequestDTO {

    @Getter
    @Schema(description = "지역에 가게 등록 DTO")
    public static class RegisterRegionDTO{

        @Schema(description = "가게 이름")
        @Size(min = 3, max = 10)
        String name;

        @Schema(description = "가게 주소")
        @Size(min = 5, max=12)
        String address;
    }
}
