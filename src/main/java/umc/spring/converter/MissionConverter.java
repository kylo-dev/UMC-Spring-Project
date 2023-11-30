package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissionConverter {

    public static MissionResponseDTO.RegisterResultDTO toRegisterResultDTO(Mission mission){
        return MissionResponseDTO.RegisterResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.RegisterDTO request){

        /**
         * Mission 생성하기
         * 추후에, Store에서 등록하거나, Member가 수행함
         */
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();
    }
}
