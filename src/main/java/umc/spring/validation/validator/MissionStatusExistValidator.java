package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.validation.annotation.ExistMissionStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class MissionStatusExistValidator implements ConstraintValidator<ExistMissionStatus, MissionStatus> {

    @Override
    public void initialize(ExistMissionStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionStatus value, ConstraintValidatorContext context) {
        boolean valid = false;

        for (MissionStatus status : MissionStatus.values()){
            if (status.equals(value)){
                valid = true;
                break;
            }
        }

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_MISSION_NOT_STATUS.toString()).addConstraintViolation();
        }
        return valid;
    }
}
