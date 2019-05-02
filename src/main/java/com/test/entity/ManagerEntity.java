package com.test.entity;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/4/29.
 */
@Entity
@Table(name = "manager", schema = "canteen", catalog = "")
public class ManagerEntity {
    private int managerId;
    private String managerName;
    private String managerPassword;

    @Id
    @Column(name = "manager_id", nullable = false)
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "manager_name", nullable = false, length = 30)
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Basic
    @Column(name = "manager_password", nullable = false, length = 30)
    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerEntity that = (ManagerEntity) o;

        if (managerId != that.managerId) return false;
        if (managerName != null ? !managerName.equals(that.managerName) : that.managerName != null) return false;
        if (managerPassword != null ? !managerPassword.equals(that.managerPassword) : that.managerPassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = managerId;
        result = 31 * result + (managerName != null ? managerName.hashCode() : 0);
        result = 31 * result + (managerPassword != null ? managerPassword.hashCode() : 0);
        return result;
    }
}
