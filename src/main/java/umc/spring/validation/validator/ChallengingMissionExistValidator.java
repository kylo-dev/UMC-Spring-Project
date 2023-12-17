package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.validation.annotation.ExistChallengingMission;
import umc.spring.web.dto.member.MemberRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
@RequiredArgsConstructor
public class ChallengingMissionExistValidator implements ConstraintValidator<ExistChallengingMission, MemberRequestDTO.MissionDTO> {

    private final MemberMissionQueryService memberMissionQueryService;

    @Override
    public void initialize(ExistChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberRequestDTO.MissionDTO request, ConstraintValidatorContext context) {
        boolean valid = memberMissionQueryService.existMemberAndMissionOnStatus(request.getMemberId(), request.getMissionId());

        if(valid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_STATUS.toString()).addConstraintViolation();
        }

        return !valid;
    }
}
