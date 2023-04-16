package View;

import Controller.ManagerMenuController;
import Controller.UserController;
import model.Manager.MembershipCard;
import model.Repository;
import model.User.MenuCategory;


import java.util.*;
import java.util.stream.Collectors;

import static Controller.ManagerMenuController.todayMenu;
import static Controller.UserController.cartList;
import static View.ManagerView.*;
import static model.Manager.util.Utility.input;
import static model.Manager.util.Utility.makeLine;
import static model.Repository.*;

public class UserView {
    public static UserController user;
    public MembershipCard userCard;
    public static ManagerMenuController MMC;
    public static Scanner sc;

    static {
        sc = new Scanner(System.in);
        user = new UserController();
        MMC = new ManagerMenuController();
    }


    public void userMemberShip() {

        // 시리얼 번호를 입력받아 접속
        System.out.println();
        System.out.println("\u001B[33m[STARBUCKS Membership Order]\u001B[0m");
        System.out.println("안녕하세요 스타벅스입니다. 멤버쉽 키오스크를 이용해주셔서 감사합니다\n");
        while (true) {

            String userSerialLogin = input("Serial 번호를 입력해주세요 :");
            userCard = user.checkSerialNumber(userSerialLogin);
            if(userSerialLogin.equals("관리자")){
                managerStart();
            }
            else if (userCard != null) {
                System.out.printf("\n\u001B[34m%s님\u001B[0m 안녕하세요.현재 잔액은\u001B[34m %d원\u001B[0m 입니다. ",
                        userCard.getMemberShipName(), userCard.getMemberShipAmount());
                break;
            } else {
                System.out.println("\u001B[31mSerial 번호가 틀립니다 다시 확인해주세요\u001B[0m");
            }
        }
        pointCharge();
    }


    // 주문하기
    public void userOrder() {
        // 메뉴 화면
        while (true) {
            System.out.println("\n\u001B[33m[STARBUCKS Membership Order]\u001B[0m");
            System.out.println("\u001B[36m# 1.\u001B[0m 메뉴 보기");
            System.out.println("\u001B[36m# 2.\u001B[0m 이전으로 가기");
            System.out.println("\u001B[36m# 3.\u001B[0m 장바구니 가기");
            String inputUserMenuChoice = input(">>");
            switch (inputUserMenuChoice) {
                case "1":
                    mainOrder();
                    break;
                case "2":
//                    userMemberShip(); // 시리얼 넘버 입력으로 넘어감
                    pointCharge();
                    break;
                case "3":
                    viewCart();
                    break;
                default:
                    makeLine();
                    makeLine();
                    System.out.println(" \u001B[31m잘못된 입력입니다 다시 입력해주세요. \u001B[0m\n");
            }
        }
    }

    // 메뉴 보기
    public void mainOrder() {
        while (true) {
            // 카테고리 선택
            System.out.println("\n\u001B[33m[STARBUCKS Membership Order]\u001B[0m");
//            System.out.println(); 오늘의 메뉴 추가
            ManagerView.todayMenuView();
            viewCategory();
            System.out.println("\u001B[36m# 5.\u001B[0m 오늘의 메뉴 바로 주문하기");
            System.out.println("\u001B[36m# 6.\u001B[0m 장바구니로 가기");
            System.out.println("\u001B[36m# 0.\u001B[0m 이전으로 가기");
            System.out.println("\u001B[33m==============================\u001B[0m");
            System.out.println("메뉴 카테고리 번호를 입력해주세요");
            String inputNum = input(">>");

            switch (inputNum) {
                case "1":
                case "2":
                case "3":
                case "4":
                    System.out.println("\u001B[33m\n======== Cafe Menu ========\u001B[0m");
                    user.menuCategories(inputNum).stream()
                            .forEach(x -> System.out.println(x.getMenuName() + " [" + x.getMenuPrice() + "]원"));

                    break;
                case "5":

                    String todayMenuName = todayMenuList.get(0);
                    boolean espressoResult = cafeEspressolist.stream()
                            .anyMatch(menu -> menu.getMenuName().equals(todayMenuName));
                    boolean latteResult = cafeLattelist.stream()
                            .anyMatch(menu -> menu.getMenuName().equals(todayMenuName));
                    boolean smoothieResult = cafeSmoothielist.stream()
                            .anyMatch(menu -> menu.getMenuName().equals(todayMenuName));
                    boolean adeResult = cafeAdelist.stream()
                            .anyMatch(menu -> menu.getMenuName().equals(todayMenuName));
                    if (espressoResult){
                        inputNum="1";
                    }else if(latteResult){
                        inputNum="2";
                    }else if(smoothieResult){
                        inputNum="3";
                    }else if(adeResult){
                        inputNum="4";
                    }
                    List<MenuCategory> menuCategoryList = user.menuCategories(inputNum);
                    suborderIng(inputNum,todayMenuName,menuCategoryList);
                    return;
                case "6":
                    viewCart();
                    break;
                case "0":
                    userOrder();
                default:
                    System.out.println(" \u001B[31m잘못된 입력입니다 다시 입력해주세요. \u001B[0m\n");
                    continue;
            }
            subOrder(inputNum);

        }
    }

    private void subOrder(String inputnum1) {
        System.out.println("\u001B[33m============================\u001B[0m");
        while (true) {
            System.out.println("메뉴명을 입력 해주세요");
            String inputNum2 = input(">>");
            List<MenuCategory> menuCategoryList = user.menuCategories(inputnum1);
            /////////////////////////////////////////////////////////////////////////
            if (user.menuReturn(inputnum1, inputNum2) == null) {
                System.out.println(" \u001B[31m잘못된 입력입니다 다시 입력해주세요. \u001B[0m\n");
                mainOrder();
            }
            ///////////////////////////////////////////////////////////////////////////
            suborderIng(inputnum1, inputNum2, menuCategoryList);
        }
    }

    //오늘의 메뉴때문에 분리
    private void suborderIng(String inputnum1, String inputNum2, List<MenuCategory> menuCategoryList) {
        String menuName = user.menuReturn(inputnum1, inputNum2).getOptionMenuName();
        List<String> collect = menuCategoryList.stream().map(d -> d.getMenuName()).collect(Collectors.toList());
        if (collect.stream().anyMatch(d -> d.contains(inputNum2)) && menuCategoryList.stream().anyMatch(d -> d.getStatus().contains("ice"))) {
            while (true) {
//                System.out.println(menuName); // text == 샷추가 / 옵션이름
                System.out.println("\n선택하신 메뉴의 추가옵션 -> " + "\u001B[35m[" + menuName + "]\u001B[0m");
                System.out.println("옵션을 추가하시겠습니까?(+500원)");
                System.out.println("\u001B[36m# 1.\u001B[0m 추가 \u001B[36m# 2.\u001B[0m 추가안함");
                String inputNum5 = input(">>");
                if (inputNum5.equals("1") || inputNum5.equals("2")) {
                    subOrder2(inputnum1, inputNum2, "1", inputNum5);
                }
            }
        } else if (collect.stream().anyMatch(d -> d.contains(inputNum2)) && menuCategoryList.stream().anyMatch(d -> d.getStatus().contains("all"))) {
            while (true) {
                System.out.println("\nHOT,ICE를 선택해주세요");
                System.out.println("\u001B[36m# 1.\u001B[0m ICE\n\u001B[36m# 2.\u001B[0m HOT");
                String inputNum = input(">>");
                switch (inputNum) {
                    case "1":
                    case "2":
                        while (true) {
                            System.out.println("\n선택하신 메뉴의 추가옵션 -> " + "\u001B[35m[" + menuName + "]\u001B[0m");
                            System.out.println("옵션을 추가하시겠습니까?(+500원)");
                            System.out.println("\u001B[36m# 1.\u001B[0m 추가\n\u001B[36m# 2.\u001B[0m 추가안함");
                            String inputNum6 = input(">>");
                            if (inputNum6.equals("1") || inputNum6.equals("2")) {
                                subOrder2(inputnum1, inputNum2, inputNum, inputNum6);
                            }
                        }

                    default:
                        System.out.println(" \u001B[31m잘못된 입력입니다 다시 입력해주세요. \u001B[0m\n");
                }
            }
        }
    }


    //마지막 옵션

    /**
     * @param menuCategoryNumber 메뉴 카테고리 번호 1.에스프레소 2.라떼 3.스모디 4.에이드
     * @param menuName           선택한 메뉴 이름
     * @param choiceOption       번호 1.핫 2.아이스
     */
    private void subOrder2(String menuCategoryNumber, String menuName, String choiceOption, String inputNum) {


        switch (inputNum) {
            case "1":
            case "2":
                user.LastOrder(menuCategoryNumber, menuName, choiceOption, inputNum);
                //주문 후 주문한 메뉴
                System.out.println("\n\n====================================================");
                System.out.printf("주문하신 \u001B[35m%s\u001B[0m가 주문이 완료되었습니다.\n",
                        menuName);
                stop();
                ResultPage();
                break;
            default:
                makeLine();
                makeLine();
                System.out.println(" \u001B[31m잘못된 입력입니다 다시 입력해주세요. \u001B[0m\n");
        }
    }

    private void ResultPage() {
        while (true) {
            System.out.println("\n\u001B[36m# 1.\u001B[0m 장바구니 \n\u001B[36m# 2.\u001B[0m 결제하기\n\u001B[36m# 3.\u001B[0m 메뉴추가");
            String inputNum = input(">>");
            switch (inputNum) {
                case "1":
                    viewCart();
                    break;
                case "2":
                    if (cartList.size() > 0 ) {
                        payment();
                        userMemberShip();
                    }
                    System.out.println("주문 내역이 없습니다");
                    break;
                case "3":
                    mainOrder();
                    break;
                default:
                    System.out.println(" \u001B[31m잘못된 입력입니다 다시 입력해주세요. \u001B[0m\n");
            }
        }
    }

    private void viewCart() {
        System.out.println("\u001B[33m\n=========== Shopping Cart ===========\u001B[0m");
        int i = 0;
        if (cartList.size() > 0) {
            List<MenuCategory> cartList = UserController.cartList;
            for (MenuCategory menuCategory : cartList) {
                i++;
                System.out.println(i + ". " + menuCategory.toString());
            }

        } else {
            System.out.println("\u001B[31m현재 장바구니가 비어있습니다.");
            userOrder();
        }

        while (true) {
            System.out.println("\u001B[33m=====================================\u001B[0m");
            System.out.println("\n\u001B[36m# 1.\u001B[0m 결제하기\n\u001B[36m# 2.\u001B[0m 메뉴추가\n\u001B[36m# 3.\u001B[0m 메뉴취소");
            String inputNum = input(">>");
            switch (inputNum) {
                case "1":
                    if (cartList.size() > 0) {
                        payment();
                        userMemberShip();
                    }
                    System.out.println("주문 내역이 없습니다");
//                    break;
                case "2":
                    mainOrder();
                    break;
                case "3":
                    while (true) {
                        System.out.println("취소 할 메뉴의 번호를 입력해주세요");
                        String inputNum1 = input(">>");
                        int intInputNum1 = 0;
                        try {
                            intInputNum1 = Integer.parseInt(inputNum1);
                        } catch(NumberFormatException e) {
                            System.out.println("잘못된 입력입니다");
                            continue;
                        }
                        // 카트사이즈가 0이 아니면 실행
                        // 입력값이 카트 사이즈보다 같거나 작았을때 실행
                        // 입력값이 저 위랑 다른 값이 오면 널포인트 인셉션이 발생함


                        if (cartList.size() != 0 && cartList.size() >= intInputNum1) {
                            deleteCart(inputNum1);
                            viewCart();
                        } else {
                            System.out.println("잘못된 입력입니다.");
                            continue;
                        }


                        break;

                    }
                default:
                    System.out.println(" \u001B[31m잘못된 입력입니다 다시 입력해주세요. \u001B[0m\n");
                    continue;
            }
            continue;
        }

    }

    // 카드충전 or 주문하기
    public void pointCharge() {
        out:
        while (true) {
            System.out.println();
            System.out.println("\n\u001B[36m# 1.\u001B[0m 카드충전하기\n\u001B[36m# 2.\u001B[0m 주문하기\n\u001B[36m# 3.\u001B[0m 처음화면");
            String userChoice = input(">>");
            switch (userChoice) {
                case "1":
                    memberShipCardCharge();
                    continue;
                case "2":
                    userOrder();
                    break out;
                case "3":
                    userMemberShip();
                default:
                    System.out.println("\u001B[31m번호를 다시 입력해주세요\u001B[0m");
            }
        }

    }

    //카드 충전하는 기능
    public void memberShipCardCharge() {
        while (true) {
            System.out.println("\n충전하실 금액을 선택해주세요");
            System.out.println("\u001B[36m# 1.\u001B[0m 10000원");
            System.out.println("\u001B[36m# 2.\u001B[0m 20000원");
            System.out.println("\u001B[36m# 3.\u001B[0m 30000원");
            String chargeNum = input("#번호를 입력해주세요 : ");
            if (chargeNum.equals("1") || chargeNum.equals("2") || chargeNum.equals("3")) {
                user.pointCharge(userCard, chargeNum);
                System.out.println("\n\n====================================================");
                System.out.printf("충전이 완료되었습니다 충전 후 잔액은\u001B[35m %d원\u001B[0m 입니다\n", userCard.getMemberShipAmount());
                stop();
                break;

            } else
                makeLine();
            makeLine();
            System.out.println("\u001B[31m잘못 입력하셨습니다.\u001B[0m\n");

        }
    }

    // 가격계산 0414 3:22
    private void payment() {
        int sum = 0;

        ArrayList<String> cartListMenuName = new ArrayList<>();
        System.out.println("\u001B[33m\n======== Payment Screen ========\u001B[0m");
        System.out.println("\u001B[33m메뉴/옵션/가격\u001B[0m");
            for (MenuCategory menuCategory : cartList) {
                if (menuCategory.getOptionMenuName()=="") {
                    menuCategory.setOptionMenuName("옵션없음");
                }
                sum += menuCategory.getMenuPrice();
                sum += menuCategory.getOptionMenuPrice();
                System.out.println(menuCategory.getStatus()
                        + " " + menuCategory.getMenuName() + " / "
                        + menuCategory.getOptionMenuName() + " / "
                        + (menuCategory.getOptionMenuPrice()
                        + menuCategory.getMenuPrice()));

        }

        System.out.println("\u001B[33m=================================\u001B[0m");
        // 장바구니 총금액
        System.out.println("\t\t\t\t\u001B[35m총 결제금액:\u001B[0m" + sum);
        System.out.println("\u001B[33m=================================\u001B[0m \n\n");

        if (sum > userCard.getMemberShipAmount()) {
            while (true){
            System.out.println("\n잔액이 부족합니다");
            System.out.println("번호를 눌러주세요");
            System.out.println("# 1. 포인트 충전하기 2. 취소");
            String inputn = input(">>");
            switch (inputn) {
                case "1":
                    memberShipCardCharge();
                    ResultPage();
                    break;
                case "2":
                    userMemberShip();
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
            }
            }
        } else {
            userCard.setMemberShipAmount(userCard.getMemberShipAmount() - sum);
            // 결제가 완료되면 영수증을 저장소의 saleList에 저장
            List<MenuCategory> receiptLlist = Repository.saleList;
            List<MenuCategory> cartList1 = cartList;
            for (MenuCategory menuCategory : cartList1) {
                receiptLlist.add(menuCategory);
            }
        }

        // 쇼핑카트 초기화
        cartList.clear();
//        System.out.println(userCard.getMemberShipAmount()); // 잔액
        System.out.println("\u001B[33m이용해주셔서 감사합니다.\n추가로 이용하실려면 ENTER키를 눌러주세요\n\u001B[0m");

        sc.nextLine();
    }

    // 번호입력하면 해당 메뉴 삭제
    private void deleteCart(String inputNum) {
        int n = Integer.parseInt(inputNum);
        cartList.remove(n - 1);
    }

    public void stop() {
        System.out.print("====================================================\n");
        sc.nextLine();
    }
}