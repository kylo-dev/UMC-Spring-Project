package umc.spring.web.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class MemberRequestDTO {

    /**
     * 요청 받은 데이터를 Request DTO로 받음
     * @Getter만 있으면 됨
     */
    @Schema(description = "회원가입 DTO")
    @Getter
    public static class JoinDTO{

        @Schema(description = "이름")
        @NotBlank
        String name;

        @Schema(description = "성별")
        @NotNull
        Integer gender;

        @Schema(description = "태어난 년도")
        @NotNull
        Integer birthYear;

        @Schema(description = "태어난 달")
        @NotNull
        Integer birthMonth;

        @Schema(description = "태어난 일")
        @NotNull
        Integer birthDay;

        @Schema(description = "주소")
        @Size(min = 5, max = 12)
        String address;

        @Schema(description = "세부 주소")
        @Size(min = 5, max=12)
        String specAddress;

        /**
         * List<Long>인 이유
         * 음식 카테고리를 조회하는 API를 호출하고,
         * 그 API 결과에서 음식 카테고리의 id 값을 전달함
         */
        @Schema(description = "선호하는 음식들")
        @ExistCategories
        List<Long> preferCategory;
    }

    @Getter
    @Schema(description = "회원 미션 등록 DTO")
    public static class MissionDTO{

        @Schema(description = "맴버 ID")
        @NotNull
        Long memberId;

        @Schema(description = "미션 ID")
        @NotNull
        Long missionId;
    }
}
