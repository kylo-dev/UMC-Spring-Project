package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.member.MemberRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, MemberRequestDTO.MissionDTO> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(ExistMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberRequestDTO.MissionDTO request, ConstraintValidatorContext context) {
        boolean isValid = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(request.getMemberId(), request.getMissionId());

        if(isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_STATUS.toString()).addConstraintViolation();
        }

        return !isValid;
    }
}