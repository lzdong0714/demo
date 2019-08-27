package com.example.base;

public enum EnumTest {
    //字段与枚举同名，获取类似map的功能
    UNDEFINE(0,"UNDEFINE"),
    CIRCLE(1,"CIRCLE"),
    POLYGON(2,"POLYGON");


    private final int typeCode;

    private final String typeName;

    EnumTest(final int typeCode,final String typeName){
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public static String getShapeDesc(int typeCode){
        EnumTest[] shapeEnums = values();
        for(EnumTest shapeEnum:shapeEnums){
            if(shapeEnum.typeCode==typeCode){
                return shapeEnum.typeName;
            }
        }
        return "UNDEFINE";
    }

    public static void main(String[] args) {
        int type = 3;
        EnumTest[] values = EnumTest.values();

        for(EnumTest test :values){
            System.out.println(test.typeName+" "+test.ordinal());


        }

        String name = EnumTest.getShapeDesc(type);
        switch (EnumTest.valueOf(EnumTest.class,name)){
            case POLYGON:
                System.out.println("POLYGON");
                break;
            case CIRCLE:
                System.out.println("CIRCLE");
                break;
            default:
                System.out.println("default");
        }
    }
}
