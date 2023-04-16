package model.User;
import java.util.Objects;
public class MenuCategory {
    private String menuName;
    private int menuPrice;
    private String optionMenuName;
    private int optionMenuPrice;
    private String status;

    public MenuCategory() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MenuCategory(String menuName, int menuPrice, String optionMenuName, int optionMenuPrice, String status) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.optionMenuName = optionMenuName;
        this.optionMenuPrice = optionMenuPrice;
        this.status = status;
    }

    public MenuCategory(String menuName, int menuPrice, String optionMenuName) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.optionMenuName = optionMenuName;
        this.optionMenuPrice = 500;
        this.status = "hot";
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getOptionMenuPrice() {
        return optionMenuPrice;
    }

    public void setOptionMenuPrice(int optionMenuPrice) {
        this.optionMenuPrice = optionMenuPrice;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getOptionMenuName() {
        return optionMenuName;
    }

    public void setOptionMenuName(String optionMenuName) {
        this.optionMenuName = optionMenuName;
    }

    @Override
    public String toString() {

        return  optionMenuPrice == 500? status+" "+menuName+" ["+optionMenuName+"] "+(menuPrice+optionMenuPrice)+"원" : status+menuName+" "+" \t "+menuPrice+"원";

    }
}