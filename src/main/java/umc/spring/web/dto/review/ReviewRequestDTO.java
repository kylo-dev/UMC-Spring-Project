package umc.spring.web.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class ReviewRequestDTO {

    @Getter
    @Schema(description = "리뷰 작성 DTO")
    public static class RegisterDTO {

        @Schema(description = "맴버 ID")
        @NotNull
        Long memberId;

        @Schema(description = "리뷰 내용")
        @NotNull
        String body;

        @Schema(description = "별점")
        @DecimalMin("0.0")
        @DecimalMax("5.0")
        Float score;
    }
}
