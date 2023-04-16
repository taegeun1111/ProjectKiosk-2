package Controller;

import model.Manager.MembershipCard;

import java.util.List;

import static model.Repository.card;

public class MembershipCardController {

    //5-1.카드 멤버 추가하기
    public List<MembershipCard> addMember(String memberShipSerial, int memberShipAmount, String memberShipName){
        card.add(new MembershipCard(memberShipSerial,memberShipAmount,memberShipName));
        return card;
    }

    //5-2.카드 멤버 삭제하기 : 시리얼 넘버로 삭제할 수 있음
    public boolean deleteMember(String deleteSerial){
        //삭제할 serial번호의 인덱스 넘버를 탐색
        for (int i = 0; i <card.size() ; i++) {
            if (card.get(i).getMemberShipSerial().equals(deleteSerial)){
                card.remove(i);
                return true;
            }
        }
        return false;
    }

    //5-3.카드 멤버 전체조회하기
    public List<MembershipCard> viewAllMember(){
        return card;
    }
    //5+a 중복검사
    public boolean isRegistered(String Serial){
        boolean result = card.stream()
                .anyMatch(se -> se.getMemberShipSerial().equals(Serial));
        return result;
    }







}
