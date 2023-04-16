package model.User;

import java.util.ArrayList;
import java.util.List;

public class MenuCategoryRepository {
    public static List<MenuCategory> cafeEspressolist;
    private static List<MenuCategory> cafeLattelist;
    private static List<MenuCategory> cafeSmoothielist;
    private static List<MenuCategory> cafeAdelist;

    static {
        cafeEspressolist = new ArrayList<>();
        cafeEspressolist.add(new MenuCategory("아메리카노", 3000, "샷추가"));
        cafeEspressolist.add(new MenuCategory("허니아메리카노", 3000, "샷추가"));
        cafeEspressolist.add(new MenuCategory("콜드브루", 3000, "샷추가"));
        cafeEspressolist.add(new MenuCategory("디카페인아메리카노", 3000, "샷추가"));
        cafeEspressolist.add(new MenuCategory("디카페인콜드브루", 3000, "샷추가"));

        cafeLattelist = new ArrayList<>();
        cafeLattelist.add(new MenuCategory("카페라떼", 3500, "저지방우유"));
        cafeLattelist.add(new MenuCategory("헤이즐넛라뗴", 3500, "저지방우유"));
        cafeLattelist.add(new MenuCategory("녹차샷라떼", 3500, "저지방우유"));
        cafeLattelist.add(new MenuCategory("바닐라라떼", 3500, "저지방우유"));
        cafeLattelist.add(new MenuCategory("꿀라떼", 3500, "저지방우유"));

        cafeSmoothielist = new ArrayList<>();
        cafeSmoothielist.add(new MenuCategory("밀크쉐이크", 5000, "초코칩추가"));
        cafeSmoothielist.add(new MenuCategory("딸기쉐이크", 5000, "초코칩추가"));
        cafeSmoothielist.add(new MenuCategory("초코쉐이크", 5000, "초코칩추가"));
        cafeSmoothielist.add(new MenuCategory("딸기스무디", 5000, "초코칩추가"));
        cafeSmoothielist.add(new MenuCategory("오레오초코프라페", 5000, "초코칩추가"));

        cafeAdelist = new ArrayList<>();
        cafeAdelist.add(new MenuCategory("유자티", 4500, "사이즈업"));
        cafeAdelist.add(new MenuCategory("유자에이드", 4500, "사이즈업"));
        cafeAdelist.add(new MenuCategory("자몽티", 4500, "사이즈업"));
        cafeAdelist.add(new MenuCategory("자몽에이드", 4500, "사이즈업"));
        cafeAdelist.add(new MenuCategory("오미자에이드", 4500, "사이즈업"));
    }
}
