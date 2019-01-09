package br.com.imood.util;

public final class ValidationUtils {

    private ValidationUtils(){}

    /**
     * Verify if any element in the collection is null
     * @param objects elements
     * @return true if any element in the collection is null
     */
    public static boolean isNull(Object... objects){
        for(Object object : objects){
            if(object == null){
                return true;
            }
        }
        return false;
    }

}
