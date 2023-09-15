package com.bookstore.bookstore.dto;

public class BookAddDto {

    private String dTitle;

    private String ddescription;

   // private String dcoverImageURL;

    private int dpageCount;
    private String dfirstName;
    private String dLastName;

    private String dCategory;
    private String dLanguage;

    public String getDdescription() {
        return ddescription;
    }

    public void setDdescription(String ddescription) {
        this.ddescription = ddescription;
    }


    public int getDpageCount() {
        return dpageCount;
    }

    public void setDpageCount(int dpageCount) {
        this.dpageCount = dpageCount;
    }

    public String getdTitle() {
        return dTitle;
    }

    public void setdTitle(String dTitle) {
        this.dTitle = dTitle;
    }

    public String getDfirstName() {
        return dfirstName;
    }

    public void setDfirstName(String dfirstName) {
        this.dfirstName = dfirstName;
    }

    public String getdLastName() {
        return dLastName;
    }

    public void setdLastName(String dLastName) {
        this.dLastName = dLastName;
    }

    public String getdCategory() {
        return dCategory;
    }

    public void setdCategory(String dCategory) {
        this.dCategory = dCategory;
    }

    public String getdLanguage() {
        return dLanguage;
    }

    public void setdLanguage(String dLanguage) {
        this.dLanguage = dLanguage;
    }
}
