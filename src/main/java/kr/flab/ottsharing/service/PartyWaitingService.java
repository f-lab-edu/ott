package kr.flab.ottsharing.service;

import kr.flab.ottsharing.entity.Party;
import kr.flab.ottsharing.entity.PartyMember;
import kr.flab.ottsharing.entity.PartyWaiting;
import kr.flab.ottsharing.entity.User;
import kr.flab.ottsharing.repository.PartyMemberRepository;
import kr.flab.ottsharing.repository.PartyWaitingRepository;
import kr.flab.ottsharing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartyWaitingService {

    private final PartyWaitingRepository waitRepo;
    private final PartyMemberRepository memberRepo;
    private final UserRepository userRepo;

    public boolean cheackWaitingPersonExist(){
        return waitRepo.existsBy();
    }

    public Integer putPartyMember(Party enrolledparty){

        Iterable<PartyWaiting> top3Waits = waitRepo.findTop3ByOrderByCreatedTimeAsc();
        int memberNumber = 0;
        for (PartyWaiting top3Wait : top3Waits) {
            PartyMember member = PartyMember.builder().user(top3Wait.getUser()).party(enrolledparty).build();
            memberRepo.save(member);
            waitRepo.deleteById(top3Wait.getWaitingId());
            memberNumber++;
        }

        return memberNumber;
    }

    public void putWaitingList(String waitmemberId){
        // User Repository 변경으로 인해 코드 정상적으로 동작하지 않음
        /*
        User waitmember = userRepo.getById(waitmemberId);
        PartyWaiting member = PartyWaiting.builder().user(waitmember).build();
        waitRepo.save(member);
        */
    }
}
