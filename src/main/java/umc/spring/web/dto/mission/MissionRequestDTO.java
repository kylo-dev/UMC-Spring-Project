package umc.spring.web.dto.mission;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class RegisterDTO{

        Integer reward;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime deadline;

        @NotNull
        String missionSpec;
    }

}
