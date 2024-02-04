package umc.spring.validation.annotation;

import umc.spring.validation.validator.MissionStatusExistValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionStatusExistValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMissionStatus {

    String message() default "MissionStatus에 존재하는 미션 상태가 아닙니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
