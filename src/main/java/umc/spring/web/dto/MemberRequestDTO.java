package umc.spring.web.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    /**
     * 요청 받은 데이터를 Request DTO로 받음
     * @Getter만 있으면 됨
     */
    @Getter
    public static class JoinDTO{
        String name;
        Integer gender;
        Integer birthYear;
        Integer birthMonth;
        Integer birthDay;
        String address;
        String specAddress;

        /**
         * List<Long>인 이유
         * 음식 카테고리를 조회하는 API를 호출하고,
         * 그 API 결과에서 음식 카테고리의 id 값을 전달함
         */
        List<Long> preferCategory;
    }
}
