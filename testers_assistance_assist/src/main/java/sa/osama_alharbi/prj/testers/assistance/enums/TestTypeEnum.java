package sa.osama_alharbi.prj.testers.assistance.enums;

public enum TestTypeEnum {
    UI(1,"UI"),
    NORMAL_API(2,"NORMAL_API");

    public int id;
    public String type;
    TestTypeEnum(int id, String type) {
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString() {
        return type;
    }

    public static TestTypeEnum getTypeById(int typeId){
        for (TestTypeEnum pathSlmType: values()){
            if(pathSlmType.id == typeId){
                return pathSlmType;
            }
        }
        return null;
    }

    public static TestTypeEnum getTypeByType(String type){
        for (TestTypeEnum pathSlmType: values()){
            if(pathSlmType.type.equals(type)){
                return pathSlmType;
            }
        }
        return null;
    }
}
