package umc.spring.web.dto.review;

import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class ReviewRequestDTO {

    @Getter
    public static class RegisterDTO {
        @ExistStore
        Long storeId;
        @NotNull
        Long memberId;
        @NotNull
        String body;
        @DecimalMin("0.0")
        @DecimalMax("5.0")
        Float score;
    }
}
