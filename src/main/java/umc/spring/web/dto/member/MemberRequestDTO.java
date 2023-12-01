package umc.spring.web.dto.member;

import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistMission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class MemberRequestDTO {

    /**
     * 요청 받은 데이터를 Request DTO로 받음
     * @Getter만 있으면 됨
     */
    @Getter
    public static class JoinDTO{
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max=12)
        String specAddress;
        /**
         * List<Long>인 이유
         * 음식 카테고리를 조회하는 API를 호출하고,
         * 그 API 결과에서 음식 카테고리의 id 값을 전달함
         */
        @ExistCategories
        List<Long> preferCategory;
    }

    @Getter
    @ExistMission
    public static class MissionDTO{
        @NotNull
        Long memberId;

        @NotNull
        Long missionId;
    }
}
