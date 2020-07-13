package com.advancedandroidstudy.callbacks;

public interface IDataLoadedCallBack<T> {

    void success(T result);

    void failure(T result);
}
