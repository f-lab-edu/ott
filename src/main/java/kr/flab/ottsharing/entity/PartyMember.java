package kr.flab.ottsharing.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "party_member")
public class PartyMember {
    @Id
    @Column(name = "party_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partyMemberId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "is_leader")
    private boolean isLeader;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_timestamp")
    @CreationTimestamp
    private LocalDateTime createdTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "updated_timestamp")
    @UpdateTimestamp
    private LocalDateTime updatedTime;
}
