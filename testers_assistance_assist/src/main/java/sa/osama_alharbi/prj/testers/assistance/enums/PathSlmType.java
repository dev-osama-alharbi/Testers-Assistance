package sa.osama_alharbi.prj.testers.assistance.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum PathSlmType {
    FULL(1,"FULL"),
    ID(2,"ID"),
    NAME(3,"NAME"),
    TAG_NAME(4,"TAG_NAME"),
    CLASS_NAME(5,"CLASS_NAME"),
    XPATH(6,"XPATH"),
    CSS_SELECTOR(7,"CSS_SELECTOR"),
    LINK_TEXT(8,"LINK_TEXT"),
    PARTIAL_LINK_TEXT(9,"PARTIAL_LINK_TEXT");

    public int id;
    public String type;
    PathSlmType(int id, String type) {
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString() {
        return type;
    }

    public static PathSlmType getTypeById(int pathId){
        for (PathSlmType pathSlmType: values()){
            if(pathSlmType.id == pathId){
                return pathSlmType;
            }
        }
        return null;
//        return Arrays.stream(values()).filter(pathSlmType -> pathSlmType.id == pathId).toList().getFirst();
    }

    public static PathSlmType getTypeByType(String type){
        for (PathSlmType pathSlmType: values()){
            if(pathSlmType.type.equals(type)){
                return pathSlmType;
            }
        }
        return null;
//        return Arrays.stream(values()).filter(pathSlmType -> pathSlmType.type.equals(type)).toList().getFirst();
    }
}
