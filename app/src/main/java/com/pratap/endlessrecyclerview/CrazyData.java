package com.pratap.endlessrecyclerview;

import java.io.Serializable;

public class CrazyData implements Serializable {

    private static final long serialVersionUID = 1L;


    String DealId;
    String DealTitle;
    String DealPrice;
    String DealDiscount;
    String BulletDescription;
    String FolderName;
    String AccountsTitle;
    String RegularPrice;
    String SignupStartingDate;
    String SignupClosingDate;
    String IsShowStartingDate;
    String QuantityRestrict;
    String QtnAfterBooking;
    String ShortDescription;
    String ProfileId;
    String CompanyName;

    public CrazyData(String dealId, String dealTitle, String dealPrice, String dealDiscount, String bulletDescription, String folderName, String accountsTitle, String regularPrice, String signupStartingDate, String signupClosingDate, String isShowStartingDate, String quantityRestrict, String qtnAfterBooking, String shortDescription, String profileId, String companyName) {
        DealId = dealId;
        DealTitle = dealTitle;
        DealPrice = dealPrice;
        DealDiscount = dealDiscount;
        BulletDescription = bulletDescription;
        FolderName = folderName;
        AccountsTitle = accountsTitle;
        RegularPrice = regularPrice;
        SignupStartingDate = signupStartingDate;
        SignupClosingDate = signupClosingDate;
        IsShowStartingDate = isShowStartingDate;
        QuantityRestrict = quantityRestrict;
        QtnAfterBooking = qtnAfterBooking;
        ShortDescription = shortDescription;
        ProfileId = profileId;
        CompanyName = companyName;
    }

    public CrazyData(String string){
        DealTitle = string;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDealId() {
        return DealId;
    }

    public void setDealId(String dealId) {
        DealId = dealId;
    }

    public String getDealTitle() {
        return DealTitle;
    }

    public void setDealTitle(String dealTitle) {
        DealTitle = dealTitle;
    }

    public String getDealPrice() {
        return DealPrice;
    }

    public void setDealPrice(String dealPrice) {
        DealPrice = dealPrice;
    }

    public String getDealDiscount() {
        return DealDiscount;
    }

    public void setDealDiscount(String dealDiscount) {
        DealDiscount = dealDiscount;
    }

    public String getBulletDescription() {
        return BulletDescription;
    }

    public void setBulletDescription(String bulletDescription) {
        BulletDescription = bulletDescription;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public String getAccountsTitle() {
        return AccountsTitle;
    }

    public void setAccountsTitle(String accountsTitle) {
        AccountsTitle = accountsTitle;
    }

    public String getRegularPrice() {
        return RegularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        RegularPrice = regularPrice;
    }

    public String getSignupStartingDate() {
        return SignupStartingDate;
    }

    public void setSignupStartingDate(String signupStartingDate) {
        SignupStartingDate = signupStartingDate;
    }

    public String getSignupClosingDate() {
        return SignupClosingDate;
    }

    public void setSignupClosingDate(String signupClosingDate) {
        SignupClosingDate = signupClosingDate;
    }

    public String getIsShowStartingDate() {
        return IsShowStartingDate;
    }

    public void setIsShowStartingDate(String isShowStartingDate) {
        IsShowStartingDate = isShowStartingDate;
    }

    public String getQuantityRestrict() {
        return QuantityRestrict;
    }

    public void setQuantityRestrict(String quantityRestrict) {
        QuantityRestrict = quantityRestrict;
    }

    public String getQtnAfterBooking() {
        return QtnAfterBooking;
    }

    public void setQtnAfterBooking(String qtnAfterBooking) {
        QtnAfterBooking = qtnAfterBooking;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public String getProfileId() {
        return ProfileId;
    }

    public void setProfileId(String profileId) {
        ProfileId = profileId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
}