package com.vanix.easygl.graphics;

import com.vanix.easygl.core.Support;

import java.util.List;
import java.util.Map;

@Support(since = Version.GL43)
public interface ProgramInterface<T extends ProgramResource<T>> {

    int getActiveResources();

    List<T> getResources();

    T getResource(int index);

    default <B> B bindResources(B bean) {
        return bean;
    }

    //region Base interfaces
    interface Named<T extends ProgramResource<T>> extends ProgramInterface<T> {
        T getResource(String name);

        boolean containsResource(String name);

        Map<String, T> getResourcesMap();

        List<String> getResourceNames();
    }

    interface Variable<T extends ProgramResource<T>> extends ProgramInterface<T> {
        int getMaxNumActiveVariables();
    }

    //endregion

}
