package model.Manager;

import Controller.ManagerMenuController;
import Controller.MembershipCardController;
import model.User.MenuCategory;
import model.User.MenuCategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static model.Repository.*;
import static model.Repository.cafeAdelist;

public class Test {
    public static void test() {
        List<MenuCategory> cafeEspressolist = MenuCategoryRepository.cafeEspressolist;
    }

    public static void main(String[] args) {
      ManagerMenuController mmc = new ManagerMenuController();
        MembershipCardController mc = new MembershipCardController();
//        String deleteSerial = "bbbb";
//        System.out.println(mc.viewAllMember());
//        for (int i = 0; i <card.size() ; i++) {
//            if (card.get(i).getMemberShipSerial().equals(deleteSerial)){
//                card.remove(i);
//            }
//        }
//        Utility.makeLine();
//        System.out.println(mc.viewAllMember());
        boolean aaaa = mc.isRegistered("aaaa");
        System.out.println("aaaa = " + aaaa);
        System.out.println("1");

//        List<MenuCategory> allMenuCategoryList = new ArrayList<>();
//        allMenuCategoryList.addAll(cafeEspressolist);
//        allMenuCategoryList.addAll(cafeLattelist);
//        allMenuCategoryList.addAll(cafeSmoothielist);
//        allMenuCategoryList.addAll(cafeAdelist);
//
//        System.out.println("allMenuCategoryList = " + allMenuCategoryList);

    }
}

