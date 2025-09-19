package com.vanix.easygl.graphics;

import com.vanix.easygl.commons.IntEnum;
import com.vanix.easygl.core.Handle;
import com.vanix.easygl.core.HandleArray;
import com.vanix.easygl.core.Support;
import com.vanix.easygl.core.meta.MetaSystem;

import javax.annotation.Nullable;

public interface Query<T extends Query<T>> extends Handle {
    State STATE = MetaSystem.Graphics.of(State.class);

    T begin();

    T end();

    int getIntResult();

    @Support(since = Version.GL44)
    int getIntResultNoWait();

    long getLongResult();

    @Support(since = Version.GL44)
    long getLongResultNoWait();

    boolean isResultAvailable();

    static SampleQuery ofSample(SampleType type) {
        return MetaHolder.SampleQuery.create(type);
    }

    static HandleArray<SampleQuery> ofSample(SampleType type, int n) {
        return MetaHolder.SampleQuery.createArray(n, type);
    }

    @SuppressWarnings("unchecked")
    static <T extends Query<T>> T of(Target<T> target) {
        return (T) switch (target) {
            case SampleType sampleType -> ofSample(sampleType);
            case IndexType indexType -> ofIndex(indexType);
            case TimerType timerType -> ofTimer(timerType);
        };
    }

    static IndexQuery ofIndex(IndexType type) {
        return MetaHolder.IndexQuery.create(type);
    }

    static HandleArray<IndexQuery> ofIndex(IndexType type, int n) {
        return MetaHolder.IndexQuery.createArray(n, type);
    }

    static TimerQuery ofTimer(TimerType type) {
        return MetaHolder.TimerQuery.create(type);
    }

    static HandleArray<TimerQuery> ofTimer(int n) {
        return MetaHolder.TimerQuery.createArray(n);
    }

    enum Mode implements IntEnum {
        QueryWait("QUERY_WAIT"),
        QueryNoWait("QUERY_NO_WAIT"),
        QueryByRegionWait("QUERY_BY_REGION_WAIT"),
        QueryByRegionNoWait("QUERY_BY_REGION_NO_WAIT"),
        @Support(since = Version.GL45)
        QueryWaitInverted("QUERY_WAIT_INVERTED"),
        @Support(since = Version.GL45)
        QueryNoWaitInverted("QUERY_NO_WAIT_INVERTED"),
        @Support(since = Version.GL45)
        QueryByRegionWaitInverted("QUERY_BY_REGION_WAIT_INVERTED"),
        @Support(since = Version.GL45)
        QueryByRegionNoWaitInverted("QUERY_BY_REGION_NO_WAIT_INVERTED");
        private final int value;

        Mode(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    interface State {
        @Nullable
        <T extends Query<T>> T getCurrentQuery(Target<T> target);

        @Nullable
        IndexQuery getCurrentQuery(IndexType target, int index);

        int getCounterBits(Target<?> target);

        int getCounterBits(IndexType target, int index);
    }

    sealed interface Target<T extends Query<T>> extends IntEnum permits SampleType, IndexType, TimerType {

    }

    enum SampleType implements Target<SampleQuery> {
        SamplesPassed("SAMPLES_PASSED"),
        AnySamplesPassed("ANY_SAMPLES_PASSED"),
        @Support(since = Version.GL43)
        AnySamplesPassedConservative("ANY_SAMPLES_PASSED_CONSERVATIVE");
        //        TimeElapsed("TIME_ELAPSED");
        private final int value;

        SampleType(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    interface SampleQuery extends Query<SampleQuery> {
        SampleQuery beginConditionalRender(Mode mode);

        SampleQuery endConditionalRender();
    }

    enum IndexType implements Target<IndexQuery> {
        PrimitivesGenerated("PRIMITIVES_GENERATED"),
        TransformFeedbackPrimitivesWritten("TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN");
        private final int value;

        IndexType(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    interface IndexQuery extends Query<IndexQuery> {
        IndexQuery begin(int index);

        IndexQuery end(int index);
    }

    enum TimerType implements Target<TimerQuery> {
        TimeElapsed("TIME_ELAPSED");
        private final int value;

        TimerType(String id) {
            this.value = MetaSystem.Graphics.queryInt(id);
        }

        @Override
        public int value() {
            return value;
        }
    }

    interface TimerQuery extends Query<TimerQuery> {
        void count();
    }

}
