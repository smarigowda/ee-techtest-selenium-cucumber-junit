package com.ee.santosh.pojo.classes;

public class DataItem {
    public String checkoutDate;
    public String checkinDate;
    public String firstname;
    public String lastname;
    public String totalPrice;
    public String deposit;

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getDeposit() {
        return deposit;
    }
}
