package com.hzz.枚举;

public enum Color {

    RED("红色", 1,"颜色"),
    GREEN("绿色", 2,"颜色"),
    BLANK("白色", 3,"颜色"),
    YELLO("黄色", 4,"颜色");


    private String name;
    private int index;
    private String remark;


    Color(String name, int index, String remark) {
        this.name = name;
        this.index = index;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public static void main(String[] args) {

        System.out.println(Color.GREEN);
        System.out.println(Color.BLANK.getRemark());

    }


}