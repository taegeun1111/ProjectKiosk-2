package model.Manager;

import java.util.Objects;

public class Manager {
    /**
     * 매니저는 하나의 계정
     * id : admin, pw : 1111
     */
    private String ManagerId = "admin";
    private String ManagerPw = "1111";

    public Manager(String managerId, String managerPw) {
        ManagerId = managerId;
        ManagerPw = managerPw;
    }

    public Manager() {
        ManagerId = "admin";
        ManagerPw = "1111";
    }

    public String getManagerId() {
        return ManagerId;
    }

    public void setManagerId(String managerId) {
        ManagerId = managerId;
    }

    public String getManagerPw() {
        return ManagerPw;
    }

    public void setManagerPw(String managerPw) {
        ManagerPw = managerPw;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "ManagerId='" + ManagerId + '\'' +
                ", ManagerPw='" + ManagerPw + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ManagerId, ManagerPw);
    }
}
