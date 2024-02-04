package umc.spring.web.dto.mission;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    @Schema(description = "미션 등록 DTO")
    public static class RegisterMissionDTO{

        @Schema(description = "보상 포인트")
        Integer reward;

        @Schema(description = "마감일")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime deadline;

        @Schema(description = "미션 상세 정보")
        @NotNull
        String missionSpec;
    }

}
