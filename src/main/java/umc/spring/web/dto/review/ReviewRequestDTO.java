package umc.spring.web.dto.review;

import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class RegisterDTO {
        Long storeId;
        Long memberId;
        String body;
        Float score;
    }
}
