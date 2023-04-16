package model;

import model.Manager.MembershipCard;
import model.User.MenuCategory;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    public static List<MembershipCard> card;
    public static List<MenuCategory> cafeEspressolist;
    public static List<MenuCategory> cafeLattelist;
    public static List<MenuCategory> cafeSmoothielist;
    public static List<MenuCategory> cafeAdelist;
    public static List<MenuCategory> saleList;
    public static List<String> todayMenuList;
    public static List<Integer> incomList;
    static {
        card = new ArrayList<>();
        card.add(new MembershipCard("aaaa",10000,"홍길동"));
        card.add(new MembershipCard("bbbb",20000,"김일리"));
        card.add(new MembershipCard("cccc",30000,"김이이"));
        card.add(new MembershipCard("dddd",40000,"이삼이"));

        cafeEspressolist = new ArrayList<>();
        cafeEspressolist.add(new MenuCategory("아메리카노", 3000, "샷추가",500,"all"));
        cafeEspressolist.add(new MenuCategory("허니아메리카노", 3000, "샷추가",500,"all"));
        cafeEspressolist.add(new MenuCategory("콜드브루", 3000, "돌체추가",500,"all"));
        cafeEspressolist.add(new MenuCategory("디카페인아메리카노", 3000, "샷추가",500,"all"));
        cafeEspressolist.add(new MenuCategory("디카페인콜드브루", 3000, "샷추가",500,"all"));

        cafeLattelist = new ArrayList<>();
        cafeLattelist.add(new MenuCategory("카페라떼", 3500, "저지방우유",500,"all"));
        cafeLattelist.add(new MenuCategory("헤이즐넛라뗴", 3500, "시럽추가",500,"all"));
        cafeLattelist.add(new MenuCategory("녹차샷라떼", 3500, "저지방우유",500,"all"));
        cafeLattelist.add(new MenuCategory("바닐라라떼", 3500, "시럽추가",500,"all"));
        cafeLattelist.add(new MenuCategory("꿀라떼", 3500, "저지방우유",500,"all"));

        cafeSmoothielist = new ArrayList<>();
        cafeSmoothielist.add(new MenuCategory("밀크쉐이크", 5000, "우유추가",500,"ice"));
        cafeSmoothielist.add(new MenuCategory("딸기쉐이크", 5000, "딸기추가",500,"ice"));
        cafeSmoothielist.add(new MenuCategory("초코쉐이크", 5000, "초코칩추가",500,"ice"));
        cafeSmoothielist.add(new MenuCategory("딸기스무디", 5000, "딸기칩추가",500,"ice"));
        cafeSmoothielist.add(new MenuCategory("오레오초코프라페", 5000, "초코칩추가",500,"ice"));

        cafeAdelist = new ArrayList<>();
        cafeAdelist.add(new MenuCategory("유자티", 4500, "유자추가",500,"all"));
        cafeAdelist.add(new MenuCategory("유자에이드", 4500, "유자추가",500,"ice"));
        cafeAdelist.add(new MenuCategory("자몽티", 4500, "자몽추가",500,"all"));
        cafeAdelist.add(new MenuCategory("자몽에이드", 4500, "자몽추가",500,"ice"));
        cafeAdelist.add(new MenuCategory("오미자에이드", 4500, "오미자추가",500,"ice"));

        saleList = new ArrayList<>();
        todayMenuList = new ArrayList<>();
        incomList = new ArrayList<>();
    }
}