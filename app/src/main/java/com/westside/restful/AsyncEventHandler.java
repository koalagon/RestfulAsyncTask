package com.westside.restful;

public interface AsyncEventHandler {
    void onAsyncTaskStarted(int message);
    void onAsyncTaskFinished(int message, String result);
}
