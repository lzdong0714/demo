package degsin.model.factory;

public enum ProductSpecies {
//    ONE , TWO , THREE;
//    public static final String
    SURFACE_WATER_lEVE_ONE("01010101","地表水环境功能区 I 类区"),
    SURFACE_WATER_lEVE_TWO("01010102","地表水环境功能区 II 类区"),
    SURFACE_WATER_lEVE_THREE("01010103","地表水环境功能区 III 类区");

    private final String code;

    private final String desc;

    private ProductSpecies(final String code,final String desc){
        this.code = code;
        this.desc = desc;
    }

    //获取数值
    public static String getValue(String code_input){
        ProductSpecies[] productSpecies = values();
        for (ProductSpecies species:productSpecies){
            if(species.code.equals(code_input)){
                return species.desc;
            }
        }
        return "";
    }
}
