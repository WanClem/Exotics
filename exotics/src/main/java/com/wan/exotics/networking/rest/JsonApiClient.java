package com.wan.exotics.networking.rest;

import android.support.annotation.NonNull;

import com.wan.exotics.interfaces.DoneCallback;
import com.wan.exotics.loggers.ExoticsLogger;
import com.wan.exotics.utils.ThreadUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Wan Clem
 */
@SuppressWarnings({"WeakerAccess", "unchecked"})
public class JsonApiClient {

    private static String TAG = JsonApiClient.class.getSimpleName();
    private static String networkInterceptionLogTag = TAG;

    private static MediaType applicationJSONMediaType = MediaType.parse("application/json; charset=utf-8");

    public static void setNetworkInterceptionLogTag(String networkInterceptionLogTag) {
        JsonApiClient.networkInterceptionLogTag = networkInterceptionLogTag;
    }

    public static String getNetworkInterceptionLogTag() {
        return networkInterceptionLogTag;
    }

    private static OkHttpClient.Builder getOkHttpClientBuilder() {
        HttpLoggingInterceptor logging = new
                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                ExoticsLogger.d(getNetworkInterceptionLogTag(), message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .addInterceptor(logging);
    }

    private static Request.Builder getRequestBuilder(HashMap<String, String> headers) {
        final Request.Builder requestBuilder = new Request.Builder();
        if (headers != null) {
            for (String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        return requestBuilder;
    }

    private static OkHttpClient getOkHttpClient() {
        return getOkHttpClientBuilder().build();
    }

    private static Exception spitException(String errorMessage) {
        return new Exception(errorMessage);
    }

    private static void logResponse(String tag, String responseBodyString, int code) {
        ExoticsLogger.d(tag, responseBodyString + ", Response Code =" + code);
    }

    private static int getResponseCode(@NonNull Response response) {
        return response.code();
    }

    private static String getResponseString(@NonNull Response response) throws IOException {
        try {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                return responseBody.string();
            }
            return "";
        } catch (IllegalStateException ignored) {
            return "";
        }
    }

    private static void callBackOnMainThread(final DoneCallback doneCallback, final Object object, final Exception e) {
        ThreadUtils.runOnMain(new Runnable() {
            @Override
            public void run() {
                doneCallback.done(object, e);
            }
        });
    }

}
