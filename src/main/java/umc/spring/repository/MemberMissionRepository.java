package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.mapping.MemberMission;

import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);


    @Query("SELECT COUNT(mm) > 0 FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.mission.id = :missionId " +
            "AND mm.status = 'CHALLENGING'")
    boolean existsByMemberIdAndMissionIdAndStatus(@Param("memberId") Long memberId, @Param("missionId") Long missionId);
}