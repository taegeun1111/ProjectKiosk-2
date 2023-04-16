package View;

import Controller.ManagerMenuController;

import static model.Manager.util.Utility.input;

public class Main {
    public static void main(String[] args) {
        ManagerMenuController.todayMenu();
        UserView view = new UserView();
        view.userMemberShip();
    }
}
