package com.mps.dynamodb;

/**
 * @author manvendrasingh
 * @since 2022-May-23
 * <p>
 * </p>
 **/
public interface KaleidoscopeDataRepo {

    public void save(KaleidoscopeData kaleidoscopeData);
    public KaleidoscopeData load(KaleidoscopeData partialObject);

}
