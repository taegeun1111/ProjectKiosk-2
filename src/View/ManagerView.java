package View;

import Controller.ManagerMenuController;
import Controller.MembershipCardController;
import Controller.UserController;
import model.Manager.Manager;
import model.Manager.MembershipCard;
import model.Repository;
import model.User.MenuCategory;

import java.util.List;

import static Controller.ManagerMenuController.*;
import static Controller.ManagerMenuController.categoryCount;
import static View.UserView.sc;
import static model.Manager.util.Utility.input;
import static model.Repository.incomList;
import static model.Repository.todayMenuList;

public class ManagerView {
    public static Manager manager;
    public static MembershipCardController manageCard;
    public static ManagerMenuController manageMenu;
    public static UserView userView;
    public static UserController userController;

    static {
        userController = new UserController();
        userView = new UserView();
        manager = new Manager();
        manageCard = new MembershipCardController();
        manageMenu = new ManagerMenuController();

    }

    public static void managerStart() {
        managerLogin();
        while (true) {
            choiceManageMenu();
            String managerInputNum = input(">>");
            switch (managerInputNum) {
                case "1":
                    addMenu();
                    break;
                case "2":
                    deleteMenu();
                    break;
                case "3":
                    viewAllMenuList();
//                    System.out.println(manageMenu.viewAllMenu());
                    break;

                case "4":
                    totalSales();
//                    accountBook();
                    break;
                case "5":
                    membershipCard();
                    break;
//                case "6":
//                    membershipCard();
                    //userView.userMemberShip();
//                    break;
                case "0":
                    userView.userMemberShip();
                    break;
                default:
                    System.out.println("잘못입력하셨습니다. 다시 입력해주세요");
            }
        }
    }


    //------------------------------------------------------------------------
    //1.메뉴추가
    private static void addMenu() {
        System.out.println("\n\u001B[33m-------메뉴 추가-------\u001B[0m");
        viewCategory();
        System.out.println("메뉴 카테고리 번호를 입력해주세요");
        String selectCategory = input(">>");
        List<MenuCategory> menuCategoryList = userController.menuCategories(selectCategory);
        String menuName = input("추가할 메뉴명 : ");
        int menuPrice = Integer.parseInt(input("가격 : "));
        String optionMenuName = input("옵션 : ");
        boolean nameCheck = menuCategoryList.stream().anyMatch(rs -> rs.getMenuName().equals(menuName));

        if (nameCheck) {
            System.out.println("\u001B[31m이미 존재하는 메뉴입니다. 초기화면으로 돌아갑니다.\u001B[0m\n");
        } else {
            System.out.println("\n\u001B[33m=================================\u001B[0m");
            System.out.println("\u001B[35m[" + menuName + "]\u001B[0m를 추가에 성공했습니다.");
            System.out.println("\u001B[33m=================================\u001B[0m \n\n");
            sc.nextLine();
            menuCategoryList.add(new MenuCategory(menuName, menuPrice, optionMenuName));
        }
    }

    //2.메뉴 삭제
    private static void deleteMenu() {
        System.out.println("\n\u001B[33m-------메뉴 삭제-------\u001B[0m");
        System.out.println("\u001B[33m메뉴를 조회하고 싶으면 메뉴조회를 입력해주세요.\u001B[0m");
        String deleteMenuName = input("삭제할 메뉴명을 입력해주세요 : ");
        if (deleteMenuName.equals("메뉴조회")) {
            viewAllMenuList();
        } else {
            boolean deleteResult = manageMenu.managerDeleteMenu(deleteMenuName);
            if (deleteResult) {
                System.out.println("\n\u001B[33m=================================\u001B[0m");
                System.out.println("\u001B[35m" + deleteMenuName + "\u001B[0m를 삭제에 성공했습니다.");
                System.out.println("\u001B[33m=================================\u001B[0m");
                sc.nextLine();
            } else {
                System.out.println("\n\u001B[31m일치하는 메뉴가 없습니다. 초기화면으로 돌아갑니다.\u001B[0m\n");

            }
        }
    }

    //3.오늘의 메뉴

    public static void todayMenuView() {
        System.out.println("오늘의 메뉴는 " + "\u001B[35m" + todayMenuList.get(0) + "\u001B[0m로, "
                + "할인율은 " + "\u001B[35m" + todayMenuList.get(1) + "%\u001B[0m,"
                + " 가격은 " + "\u001B[35m" + todayMenuList.get(2) + "원\u001B[0m입니다.");
    }

    //4.가계부
    private static void accountBook() {
    }

    //5.카드 관리
    private static void membershipCard() {
        while (true) {
            System.out.println("\n\u001B[33m=================================\u001B[0m");
            System.out.println("\u001B[36m# 1.\u001B[0m 카드 추가");
            System.out.println("\u001B[36m# 2.\u001B[0m 카드 삭제");
            System.out.println("\u001B[36m# 3.\u001B[0m 카드 전체 조회");
            System.out.println("\u001B[36m# 0.\u001B[0m 이전으로 돌아가기");
            String inputCardNum = input(">>");
            switch (inputCardNum) {
                case "1":
                    addCardMember();
                    break;
                case "2":
                    deleteCardMember();
                    break;
                case "3":
                    System.out.println();
                    viewCardMember();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\u001B[31m정확한 번호를 입력해주세요.\u001B[0m");
            }

        }

    }

    private static void viewCardMember() {
        List<MembershipCard> membershipCardList = manageCard.viewAllMember();
        System.out.println("\u001B[33m======================================================\u001B[0m");
        for (MembershipCard membershipCard : membershipCardList) {
            System.out.println("시리얼 번호:["+membershipCard.getMemberShipSerial()+"], 현재 잔액:["+membershipCard.getMemberShipAmount()+"], 닉네임:["+membershipCard.getMemberShipName()+"]");
        }
        System.out.println("\u001B[33m======================================================\u001B[0m");
        sc.nextLine();
    }

    //메뉴 카테고리별 조회
    private static void viewAllMenuList() {
        while (true) {
            System.out.println("\n\u001B[33m조회할 메뉴 카테고리를 입력해주세요.\u001B[0m");
            viewCategory();
            String selectCategory = input(">>");
            if (selectCategory.equals("1")||selectCategory.equals("2")||selectCategory.equals("3")||selectCategory.equals("4")){
                List<MenuCategory> menuCategoryList = userController.menuCategories(selectCategory);
                System.out.println();
                for (MenuCategory menuCategory : menuCategoryList) {
                    System.out.println("\u001B[35m" + menuCategory.getMenuName() + "\u001B[0m" + " / " + menuCategory.getOptionMenuName() + " / " + menuCategory.getMenuPrice());
                }
            }else {
                System.out.println("\u001B[31m올바른 번호를 입력해주세요.\u001B[0m\n");
                continue;
            }
            System.out.println("====================================");
            System.out.println("\u001B[36m# 1.\u001B[0m 계속 조회하기");
            System.out.println("\u001B[36m# 2.\u001B[0m 이전 메뉴로 돌아가기");
            String inputSearchNum = input(">>");
            while (true) {
                switch (inputSearchNum) {
                    case "1":
                        viewAllMenuList();
                        break;
                    case "2":
                        System.out.println("이전 메뉴로 돌아갑니다.");
                        return;
                    default:
                        System.out.println("\u001B[31m올바른 번호를 입력해주세요.\u001B[0m\n");
                        break;
                }
                break;
            }
            break;
        }
    }


    //5-1.카드 멤버 추가하기
    private static void addCardMember() {
        while (true) {
            String memberShipSerial = input("시리얼 번호 : ");
            int memberShipAmount = Integer.parseInt(input("금액 : "));
            String memberShipName = input("이름 : ");
            //Serial중복 검사
            boolean registered = manageCard.isRegistered(memberShipSerial);
            if (!registered) {
                manageCard.addMember(memberShipSerial, memberShipAmount, memberShipName);
                System.out.println("\n\u001B[33m=================================\u001B[0m");
                System.out.println("\u001B[35m" + memberShipName + "\u001B[0m님이 추가되었습니다.");
                System.out.println("\u001B[33m=================================\u001B[0m");
                sc.nextLine();
//                System.out.printf("\u001B%s\u001B[0m님이 추가되었습니다.\n", memberShipName);
                break;
            } else {
                System.out.println("중복된 시리얼 번호입니다. 다시 입력해주세요!");
            }
        }
    }

    //5-2.카드 멤버 삭제하기
    private static void deleteCardMember() {
        while (true) {
            String deleteSerial = input("삭제할 카드의 시리얼 번호 : ");

            boolean resultDelete = manageCard.deleteMember(deleteSerial);
            if (resultDelete) {

                System.out.println("\n성공적으로 삭제되었습니다!");
                System.out.println("[현재 카드 현황]");
                viewCardMember();
                break;
            } else {
                System.out.println("\u001B[31m삭제에 실패했습니다. 정확한 번호를 입력해주세요.\u001B[0m");
            }
        }
    }

    //----------------------------------------------------------------------
    //첫 진입 화면
    private static void choiceManageMenu() {
        System.out.println("\n\u001B[33m=================================\u001B[0m");
        System.out.println("\u001B[36m# 1.\u001B[0m 메뉴 추가");
        System.out.println("\u001B[36m# 2.\u001B[0m 메뉴 삭제");
        System.out.println("\u001B[36m# 3.\u001B[0m 메뉴 조회");
        System.out.println("\u001B[36m# 4.\u001B[0m 매출 조회");
        System.out.println("\u001B[36m# 5.\u001B[0m 카드 관리");
        System.out.println("\u001B[36m# 0.\u001B[0m 처음 화면으로");


//        System.out.println("1. 메뉴 추가");
//        System.out.println("2. 메뉴 삭제");
//        System.out.println("3. 메뉴 조회");
//        System.out.println("4. 오늘의 메뉴 선택");
//        System.out.println("5. 가계 수입");
//        System.out.println("6. 카드 관리");
//        System.out.println("7. 처음 화면으로");
    }

    //매니저 로그인 검사
    private static void managerLogin() {
        while (true) {
            System.out.println();
            String inputManagerId = input("관리자 아이디를 입력해주세요 : ");
            String inputManagerPw = input("관리자 비밀번호를 입력해주세요 : ");
            if (inputManagerId.equals(manager.getManagerId()) &&
                    inputManagerPw.equals(manager.getManagerPw())) {
                System.out.println("\n\n\u001B[33m=======================================================\u001B[0m");
                System.out.println("\u001B[33m관리자 로그인에 성공했습니다. 관리자 모드를 실행합니다.\u001B[0m");
                System.out.println("\u001B[33m=======================================================\u001B[0m");
                sc.nextLine();

                break;
            } else {
                System.out.println("\u001B[31mId또는 Pw가 틀립니다 다시 확인해주세요\u001B[0m");
            }
        }
    }

    public static void viewCategory() {
        System.out.println("\u001B[36m# 1.\u001B[0m 에스프레소");
        System.out.println("\u001B[36m# 2.\u001B[0m 라떼");
        System.out.println("\u001B[36m# 3.\u001B[0m 프라페&스무디");
        System.out.println("\u001B[36m# 4.\u001B[0m 티&에이드");
    }

    // 총 판매 정보
    private static void totalSales() {

        System.out.println("\n\u001B[33m=======================\u001B[0m");
        System.out.println("카드 충전금액 : \u001B[35m"+incomList.stream().mapToInt(Integer::intValue).sum()+"\u001B[0m원" );
        System.out.println("메뉴 판매금액 : \u001B[35m"+ totalSale()+"\u001B[0m원");
        System.out.println("\u001B[33m=======================\u001B[0m \n");
        sc.nextLine();
    }

}
