package Controller;

import model.Repository;
import model.User.MenuCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static model.Repository.*;

public class ManagerMenuController {
    /**
     * 1. 메뉴 추가
     *
     * @param selectCategory : 메뉴 추가 버튼에서 카테고리 입력
     *                       menuName, menuPrice, optionMenuName : 생성자
     * @return : boolean
     */
    public boolean managerAddMenu(String selectCategory, String menuName, int menuPrice, String optionMenuName) {
        switch (selectCategory) {
            case "1":
                cafeEspressolist.add(new MenuCategory(menuName, menuPrice, optionMenuName));
                return true;
            case "2":
                cafeLattelist.add(new MenuCategory(menuName, menuPrice, optionMenuName));
                return true;
            case "3":
                cafeSmoothielist.add(new MenuCategory(menuName, menuPrice, optionMenuName));
                return true;
            case "4":
                cafeAdelist.add(new MenuCategory(menuName, menuPrice, optionMenuName));
                return true;
            default:
                return false;
        }
    }

    /**
     * 1+a. 전체 메뉴 출력
     *
     * @return : <List> allMenuCategoryList
     */
    public List viewAllMenu() {
        List<List> allMenuCategoryList = new ArrayList<>();
        allMenuCategoryList.add(cafeEspressolist);
        allMenuCategoryList.add(cafeLattelist);
        allMenuCategoryList.add(cafeSmoothielist);
        allMenuCategoryList.add(cafeAdelist);
        return allMenuCategoryList;
    }


    /**
     * 2. 메뉴 삭제
     *
     * @param menuName : 메뉴명만 받아서 true,false확인 후 for문으로 해당 메뉴 삭제
     * @return
     */
    public boolean managerDeleteMenu(String menuName) {
        boolean espressoResult = cafeEspressolist.stream()
                .anyMatch(menu -> menu.getMenuName().equals(menuName));
        boolean latteResult = cafeLattelist.stream()
                .anyMatch(menu -> menu.getMenuName().equals(menuName));
        boolean smoothieResult = cafeSmoothielist.stream()
                .anyMatch(menu -> menu.getMenuName().equals(menuName));
        boolean adeResult = cafeAdelist.stream()
                .anyMatch(menu -> menu.getMenuName().equals(menuName));

        if (espressoResult) {
            for (int i = 0; i < cafeEspressolist.size(); i++) {
                int indexOf = cafeEspressolist.get(i).getMenuName().indexOf(menuName);
                if (indexOf == 0) {
                    cafeEspressolist.remove(i);
                    return true;
                }
            }
        } else if (latteResult) {
            for (int i = 0; i < cafeLattelist.size(); i++) {
                int indexOf = cafeLattelist.get(i).getMenuName().indexOf(menuName);
                if (indexOf == 0) {
                    cafeLattelist.remove(i);
                    return true;
                }
            }
        } else if (smoothieResult) {
            for (int i = 0; i < cafeSmoothielist.size(); i++) {
                int indexOf = cafeSmoothielist.get(i).getMenuName().indexOf(menuName);
                if (indexOf == 0) {
                    cafeSmoothielist.remove(i);
                    return true;
                }
            }
        } else if (adeResult) {
            for (int i = 0; i < cafeAdelist.size(); i++) {
                int indexOf = cafeAdelist.get(i).getMenuName().indexOf(menuName);
                if (indexOf == 0) {
                    cafeAdelist.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 3.오늘의 메뉴
     */
    public static void todayMenu() {
        List<MenuCategory> allMenuCategoryList = new ArrayList<>();
        allMenuCategoryList.addAll(cafeEspressolist);
        allMenuCategoryList.addAll(cafeLattelist);
        allMenuCategoryList.addAll(cafeSmoothielist);
        allMenuCategoryList.addAll(cafeAdelist);

        //Math.ramdom() * (y -x +1) + x

        //할인율 1~20%까지
        int randomDiscountNum = (int) (Math.random() * 20) + 1;
        int randomMenuNum = (int) (Math.random() * allMenuCategoryList.size());
        String randomMenuPrice = "";
        //랜덤숫자(길이)만큼 출력 -> 이름을 반환 -> 그 이름에 맞는 메뉴를 찾고 setPrice;
        String randomMenu = allMenuCategoryList.get(randomMenuNum).getMenuName();
        //오늘의 메뉴와 할인율을 String으로 반환


        /**
         * 각 배열에 randomMenu와 같은 메뉴명이 있는지 확인
         * 각각의 boolean을 받아 setMenuPrice실행
         */
        boolean espressoResult = cafeEspressolist.stream()
                .anyMatch(menu -> menu.getMenuName().equals(randomMenu));
        boolean latteResult = cafeLattelist.stream()
                .anyMatch(menu -> menu.getMenuName().equals(randomMenu));
        boolean smoothieResult = cafeSmoothielist.stream()
                .anyMatch(menu -> menu.getMenuName().equals(randomMenu));
        boolean adeResult = cafeAdelist.stream()
                .anyMatch(menu -> menu.getMenuName().equals(randomMenu));

        if (espressoResult) {
            for (int i = 0; i < cafeEspressolist.size(); i++) {
                int indexOf = cafeEspressolist.get(i).getMenuName().indexOf(randomMenu);
                if (indexOf == 0) {
                    cafeEspressolist
                            .get(i).setMenuPrice((int) (cafeEspressolist.get(i).getMenuPrice() - (cafeEspressolist.get(i).getMenuPrice() * (randomDiscountNum * 0.01))));
                    randomMenuPrice = String.valueOf(cafeEspressolist.get(i).getMenuPrice());
                }
            }
        } else if (latteResult) {
            for (int i = 0; i < cafeLattelist.size(); i++) {
                int indexOf = cafeLattelist.get(i).getMenuName().indexOf(randomMenu);
                if (indexOf == 0) {
                    cafeLattelist
                            .get(i).setMenuPrice((int) (cafeLattelist.get(i).getMenuPrice() - (cafeLattelist.get(i).getMenuPrice() * (randomDiscountNum * 0.01))));
                    randomMenuPrice = String.valueOf(cafeLattelist.get(i).getMenuPrice());
                }
            }
        } else if (smoothieResult) {
            for (int i = 0; i < cafeSmoothielist.size(); i++) {
                int indexOf = cafeSmoothielist.get(i).getMenuName().indexOf(randomMenu);
                if (indexOf == 0) {
                    cafeSmoothielist
                            .get(i).setMenuPrice((int) (cafeSmoothielist.get(i).getMenuPrice() - (cafeSmoothielist.get(i).getMenuPrice() * (randomDiscountNum * 0.01))));
                    randomMenuPrice = String.valueOf(cafeSmoothielist.get(i).getMenuPrice());
                }
            }
        } else if (adeResult) {
            for (int i = 0; i < cafeAdelist.size(); i++) {
                int indexOf = cafeAdelist.get(i).getMenuName().indexOf(randomMenu);
                if (indexOf == 0) {
                    cafeAdelist
                            .get(i).setMenuPrice((int) (cafeAdelist.get(i).getMenuPrice() - (cafeAdelist.get(i).getMenuPrice() * (randomDiscountNum * 0.01))));
                    randomMenuPrice = String.valueOf(cafeAdelist.get(i).getMenuPrice());
                }
            }
        }

        Repository.todayMenuList.add(randomMenu);
        Repository.todayMenuList.add(String.valueOf(randomDiscountNum));
        Repository.todayMenuList.add(randomMenuPrice);
    }


    // 총 판매금액 리턴
    public static int totalSale() {
        int total = 0;
        if (saleList.size() > 0) {
            for (MenuCategory m : saleList) {
                total += m.getMenuPrice();
                total += m.getOptionMenuPrice();
            } return total;
        }
        return 0;
    }

    // 총 판매 카티고리별 카운팅 List로 리턴 (에스프레소, 라떼, 스무디, 에이드 순)
    public static List<Integer> categoryCount() {

        int espressoCount = 0;
        int latteCount = 0;
        int smoothieCount = 0;
        int adeCount = 0;
        List<Integer> categoryCnt = null;
        if (saleList.size() > 0) {
            List<String> saleListGetMenuName = saleList.stream().map(d -> d.getMenuName()).collect(Collectors.toList());

            List<String> espressoGetMentName = cafeEspressolist.stream().map(d -> d.getMenuName()).collect(Collectors.toList());
            List<String> latteGetMentName = cafeLattelist.stream().map(d -> d.getMenuName()).collect(Collectors.toList());
            List<String> smoothieGetMentName = cafeSmoothielist.stream().map(d -> d.getMenuName()).collect(Collectors.toList());
            List<String> adeGetMentName = cafeAdelist.stream().map(d -> d.getMenuName()).collect(Collectors.toList());

            for (String m : saleListGetMenuName) {
                if (espressoGetMentName.contains(m)) {
                    espressoCount++;
                }
            }

            for (String m : saleListGetMenuName) {
                if (latteGetMentName.contains(m)) {
                    latteCount++;
                }
            }

            for (String m : saleListGetMenuName) {
                if (smoothieGetMentName.contains(m)) {
                    smoothieCount++;
                }
            }

            for (String m : saleListGetMenuName) {
                if (adeGetMentName.contains(m)) {
                    adeCount++;
                }
            }

            categoryCnt = List.of(
                    espressoCount,
                    latteCount,
                    smoothieCount,
                    adeCount
            );

            return categoryCnt;
        }return null;

    }

    // 총 판매 옵션 가격 리턴
    public static int totalOptionPrice(){
        if (saleList.size() > 0) {
            int total = 0;
            for (MenuCategory m : saleList) {
                total += m.getOptionMenuPrice();
            }
            return total;
        }return 0;
    }

}



