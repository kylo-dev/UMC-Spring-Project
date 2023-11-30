package umc.spring.web.dto.mission;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class RegisterDTO{
        Integer reward;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime deadline;
        String missionSpec;
    }

}
