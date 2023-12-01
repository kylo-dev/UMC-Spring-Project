package umc.spring.web.dto.review;

import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class RegisterDTO {
        @ExistStore
        Long storeId;
        Long memberId;
        String body;
        Float score;
    }
}
