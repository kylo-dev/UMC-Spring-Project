package umc.spring.web.dto.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class StoreRequestDTO {

    @Getter
    public static class RegisterRegionDTO{

        @Size(min = 3, max = 10)
        String name;

        @Size(min = 5, max=12)
        String address;
    }
}
