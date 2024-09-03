package com.dinidu.samplecrud.dto;

public class Customer {
    private String CustID;
    private String CustTitle;
    private String CustName;
    private String DOB;
    private double salary;
    private String CustAddress;
    private String City;
    private String Province;
    private String PostalCode;

    public Customer(String custID, String custTitle, String custName, String dob, String province, String custAddress, String city, double salary, String postalCode) {
        CustID = custID;
        CustTitle = custTitle;
        CustName = custName;
        DOB = dob;
        this.salary = salary;
        CustAddress = custAddress;
        City = city;
        Province = province;
        PostalCode = postalCode;
    }


    public String getCustID() {
        return CustID;
    }

    public void setCustID(String custID) {
        CustID = custID;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        CustAddress = custAddress;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustTitle() {
        return CustTitle;
    }

    public void setCustTitle(String custTitle) {
        CustTitle = custTitle;
    }

    @Override
    public String toString() {
        return "customer{" +
                "CustID='" + CustID + '\'' +
                ", CustTitle='" + CustTitle + '\'' +
                ", CustName='" + CustName + '\'' +
                ", DOB='" + DOB + '\'' +
                ", salary=" + salary +
                ", CustAddress='" + CustAddress + '\'' +
                ", City='" + City + '\'' +
                ", Province='" + Province + '\'' +
                ", PostalCode='" + PostalCode + '\'' +
                '}';
    }
}
