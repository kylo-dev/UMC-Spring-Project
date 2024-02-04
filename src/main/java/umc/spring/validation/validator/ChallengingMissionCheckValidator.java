package umc.spring.validation.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.validation.annotation.CheckChallengingMission;
import umc.spring.web.dto.member.MemberRequestDTO;

@Component
@RequiredArgsConstructor
public class ChallengingMissionCheckValidator implements ConstraintValidator<CheckChallengingMission, MemberRequestDTO.MissionDTO> {

    private final MemberMissionQueryService memberMissionQueryService;

    @Override
    public void initialize(CheckChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberRequestDTO.MissionDTO request, ConstraintValidatorContext context) {
        boolean valid = memberMissionQueryService.existMemberAndMissionOnStatus(request.getMemberId(), request.getMissionId());

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_MISSION_NOT_CHALLENGING.toString()).addConstraintViolation();
        }
        return valid;
    }
}
