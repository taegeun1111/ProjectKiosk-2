package Controller;


import model.Manager.MembershipCard;
import model.User.MenuCategory;


import java.util.ArrayList;
import java.util.List;

import static model.Repository.*;

public class UserController {
    public static List<MenuCategory> cartList;

    static {
        cartList = new ArrayList<>();
    }

    // 시리얼 번호를 입력 받아서 해당 멤버쉽카드 정보를 반환하는 기능
    public MembershipCard checkSerialNumber(String inputSerialNumber) {
        for (MembershipCard membershipCard : card) {
            if (membershipCard.getMemberShipSerial().equals(inputSerialNumber)) {
                return membershipCard;
            }
        }
        return null;
    }

    // 충전금액을 입력하면 충전해주고 현재잔액을 반환하는 기능
    public void pointCharge(MembershipCard userCard, String inputNum) {
        switch (inputNum) {
            case "1":
                userCard.setMemberShipAmount(userCard.getMemberShipAmount() + 10000);
                incomList.add(10000);
                break;
            case "2":
                userCard.setMemberShipAmount(userCard.getMemberShipAmount() + 20000);
                incomList.add(20000);
                break;
            case "3":
                userCard.setMemberShipAmount(userCard.getMemberShipAmount() + 30000);
                incomList.add(30000);
                break;
        }
    }

    // 카테고리를 입력받아 해당 카테고리를 반환해주는 기능
    public List<MenuCategory> menuCategories(String inputNum) {
        switch (inputNum) {
            case "1":
                return cafeEspressolist;
            case "2":
                return cafeLattelist;
            case "3":
                return cafeSmoothielist;
            case "4":
                return cafeAdelist;
        }
        return null;
    }


    // 현재 장바구니 보여줌
    public List<MenuCategory> shoppingCart() {
        List<MenuCategory> m = new ArrayList<>();
        return m;

    }

    // 카테고리와 메뉴를 입력받아 해당 메뉴객체 반환해주는 기능
    public MenuCategory menuReturn(String inputCategoryNumber, String inputMenuName) {

        List<MenuCategory> m = menuCategories(inputCategoryNumber);

        for (MenuCategory ab1 : m) {
            if (ab1.getMenuName().equals(inputMenuName)) {
                return ab1;
            }
        }
        return null;
    }

    // 장바구니 넣기

    /**
     * @param menuCategoryNumber 메뉴 카테고리 번호 1.에스프레소 2.라떼 3.스모디 4.에이드
     * @param menuName           선택한 메뉴 이름
     * @param choiceOption       번호 0.아이스 1.핫 2.아이스
     * @param inputStatus        번호 1.옵션추가 2.옵션추가안함
     * @return 카트 객체 반환
     */
    public List<MenuCategory> LastOrder(String menuCategoryNumber, String menuName, String choiceOption, String inputStatus) {
        //메뉴이름, 메뉴가격, 옵션명 옵션가격 상태
        MenuCategory m = menuReturn(menuCategoryNumber, menuName);
        if (inputStatus.equals("1") && choiceOption.equals("1")) {
            cartList.add(new MenuCategory(m.getMenuName(), m.getMenuPrice(), m.getOptionMenuName(), m.getOptionMenuPrice(), "ICE"));
            return cartList;
        } else if (inputStatus.equals("1") && choiceOption.equals("2")) {
            cartList.add(new MenuCategory(m.getMenuName(), m.getMenuPrice(), m.getOptionMenuName(), m.getOptionMenuPrice(), "HOT"));
            return cartList;
        } else if (inputStatus.equals("2") && choiceOption.equals("1")) {
            cartList.add(new MenuCategory(m.getMenuName(), m.getMenuPrice(), "", 0, "ICE"));
            return cartList;
        } else if (inputStatus.equals("2") && choiceOption.equals("2")) {
            cartList.add(new MenuCategory(m.getMenuName(), m.getMenuPrice(), "", 0, "HOT"));
            return cartList;
        }

        return null;
    }
}

// 값을 주면 해당 값을 쇼핑 카트에 기입하는 기능

// 옵션과 메뉴 객체를 입력받아 쇼핑카드에 넣는 기능
//    public List<ShoppingCart> addCart(String input3, MenuCategory m1) {
//        System.out.println("testtest");
//        System.out.println(m1.getMenuName());
//        if(input3.equals("1")) {
//            shoppingCartList.add(new ShoppingCart(m1.getMenuName(), m1.getMenuPrice(), m1.getOptionMenuName(), "핫", 500));
//            return shoppingCartList;
//        }else {
//            shoppingCartList.add(new ShoppingCart(m1.getMenuName(), m1.getMenuPrice(), m1.getOptionMenuName(), "핫", 0));
//            return shoppingCartList;
//        }
//    }


// 장바구니 객체를 입력받고 삭제할 메뉴 번호를 입력받아서 삭제해주는 장바구니 객체를 반환하는 기능

// 결제 후 최종영수증 출력해주는 기능

// 장바구니 입력받아서 결제하는 기능(영수증)
