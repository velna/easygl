package com.vanix.easygl.graphics;

import com.vanix.easygl.core.Support;

import javax.annotation.Nullable;
import java.util.List;

@Support(since = Version.GL43)
public interface ProgramInterface<T extends ProgramResource<T>> {

    int getActiveResources();

    List<T> getResources();

    @Nullable
    T getResource(int index);

    default <B> B bindResources(B bean) {
        return bean;
    }

    //region Base interfaces
    interface Named<T extends ProgramResource<T>> extends ProgramInterface<T> {
        @Nullable
        T getResource(String name);
    }

    interface Variable<T extends ProgramResource<T>> extends ProgramInterface<T> {
        int getMaxNumActiveVariables();
    }

    interface SubroutineUniform<T extends ProgramResource<T>> extends Named<T> {
        int getMaxNumCompatibleSubroutines();
    }
    //endregion

}
