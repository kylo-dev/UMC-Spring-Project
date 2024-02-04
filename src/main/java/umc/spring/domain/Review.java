package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImageList;

    //== 연관관계 메서드 ==//
    public void setMember(Member member){
        this.member = member;
        member.getReviewList().add(this);
    }

    public void setStore(Store store){
        this.store = store;
        store.getReviewList().add(this);
    }
}
