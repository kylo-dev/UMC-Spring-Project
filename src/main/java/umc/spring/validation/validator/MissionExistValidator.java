package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;V
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.member.MemberRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, MemberRequestDTO.MissionDTO> {

    private final MemberMissionCommandService memberMissionCommandService;

    @Override
    public void initialize(ExistMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberRequestDTO.MissionDTO request, ConstraintValidatorContext context) {
        boolean isValid = memberMissionCommandService.exsistMemberAndMissionOnStatus(request.getMemberId(), request.getMissionId());

        if(isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_STATUS.toString()).addConstraintViolation();
        }

        return !isValid;
    }
}
