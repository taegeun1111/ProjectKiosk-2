package model.Manager;

import model.User.MenuCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MembershipCard {
    private String memberShipSerial; //시리얼 번호
    private int memberShipAmount; //잔액
    private String memberShipName; //닉네임

    private List<MenuCategory> orderCoffe = new ArrayList<>();
    public MembershipCard() {
    }

    public MembershipCard(String memberShipSerial, int memberShipAmount, String memberShipName) {
        this.memberShipSerial = memberShipSerial;
        this.memberShipAmount = memberShipAmount;
        this.memberShipName = memberShipName;
    }

    public MembershipCard(List<MenuCategory> orderCoffe) {
        this.orderCoffe = orderCoffe;
    }

    public List<MenuCategory> getOrderCoffe() {
        return orderCoffe;
    }

    public void setOrderCoffe(List<MenuCategory> orderCoffe) {
        this.orderCoffe = orderCoffe;
    }

    public String getMemberShipSerial() {
        return memberShipSerial;
    }

    public void setMemberShipSerial(String memberShipSerial) {
        this.memberShipSerial = memberShipSerial;
    }

    public int getMemberShipAmount() {
        return memberShipAmount;
    }

    public void setMemberShipAmount(int memberShipAmount) {
        this.memberShipAmount = memberShipAmount;
    }

    public String getMemberShipName() {
        return memberShipName;
    }

    public void setMemberShipName(String memberShipName) {
        this.memberShipName = memberShipName;
    }

    @Override
    public String toString() {
        return "membershipCard{" +
                "memberShipSerial='" + memberShipSerial + '\'' +
                ", memberShipAmount='" + memberShipAmount + '\'' +
                ", memberShipName='" + memberShipName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembershipCard that = (MembershipCard) o;
        return Objects.equals(memberShipSerial, that.memberShipSerial) && Objects.equals(memberShipAmount, that.memberShipAmount) && Objects.equals(memberShipName, that.memberShipName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberShipSerial, memberShipAmount, memberShipName);
    }

    public <E> List<E> getOrderCoffee() {
        return null;
    }

    public Object makeCoffee(String menuinput, String userOptionChice) {
    return null;
    }
}
