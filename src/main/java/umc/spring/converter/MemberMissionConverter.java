package umc.spring.converter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberResponseDTO.ChallengingMissionPreViewDTO toChallengingMissionPreViewDTO(MemberMission memberMission){

        return MemberResponseDTO.ChallengingMissionPreViewDTO.builder()
                .missionId(memberMission.getMission().getId())
                .reward(memberMission.getMission().getReward())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .deadline(memberMission.getMission().getDeadline())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.ChallengingMissionPreViewListDTO toChallengingMissionPreViewListDTO(Page<MemberMission> memberMissionList){

        List<MemberResponseDTO.ChallengingMissionPreViewDTO> result = memberMissionList.stream()
                .map(MemberMissionConverter::toChallengingMissionPreViewDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.ChallengingMissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(result.size())
                .missionList(result)
                .build();
    }

}
