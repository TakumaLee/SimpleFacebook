package com.github.takumalee.simplefacebook.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.github.takumalee.simplefacebook.entities.Photo;
import com.github.takumalee.simplefacebook.entities.Story;

import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Nijugon on 2015/6/6.
 */
public enum Utils {
    instance,;
    public static final String EMPTY = "";
    public static final String CHARSET_NAME = "UTF-8";
    public Boolean isDebug = true;
    private ObjectMapper mapper;

    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first == null ? EMPTY : first.toString();
        }
        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }
        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static <T> String join(Iterator<T> iterator, String separator, Process<T> process) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        T first = iterator.next();
        if (!iterator.hasNext()) {
            return first == null ? EMPTY : process.process(first);
        }
        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(process.process(first));
        }
        while (iterator.hasNext()) {
            buf.append(separator);
            T obj = iterator.next();
            if (obj != null) {
                buf.append(process.process(obj));
            }
        }
        return buf.toString();
    }

    public static String join(Map<?, ?> map, char separator, char valueStartChar, char valueEndChar) {

        if (map == null) {
            return null;
        }
        if (map.size() == 0) {
            return EMPTY;
        }
        StringBuilder buf = new StringBuilder(256);
        boolean isFirst = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (isFirst) {
                buf.append(entry.getKey());
                buf.append(valueStartChar);
                buf.append(entry.getValue());
                buf.append(valueEndChar);
                isFirst = false;
            } else {
                buf.append(separator);
                buf.append(entry.getKey());
                buf.append(valueStartChar);
                buf.append(entry.getValue());
                buf.append(valueEndChar);
            }
        }

        return buf.toString();
    }

    public static <T> List<T> convert(JSONArray jsonArray, StringConverter<T> converter) {
        List<T> list = new ArrayList<T>();
        if (jsonArray == null || jsonArray.length() == 0) {
            return list;
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(converter.convert(jsonArray.optString(i)));
        }

        return list;
    }

    public static String encodeUrl(Bundle parameters) {
        if (parameters == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String key : parameters.keySet()) {
            Object parameter = parameters.get(key);
            if (!(parameter instanceof String)) {
                continue;
            }

            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            try {
                sb.append(URLEncoder.encode(key, CHARSET_NAME)).append("=").append(URLEncoder.encode(parameters.getString(key), CHARSET_NAME));
            } catch (UnsupportedEncodingException e) {
                Logger.logError(Story.class, "Error enconding URL", e);
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("resource")
    public static String encode(String key, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            mac.init(secretKey);
            byte[] bytes = mac.doFinal(data.getBytes());
            StringBuilder sb = new StringBuilder(bytes.length * 2);
            Formatter formatter = new Formatter(sb);
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
            return sb.toString();
        } catch (Exception e) {
            Logger.logError(Utils.class, "Failed to create sha256", e);
            return null;
        }
    }

    public static List<Bitmap> extractBitmaps(List<Photo> photos) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        for (Photo photo : photos) {
            Parcelable parcelable = photo.getParcelable();
            if (parcelable instanceof Bitmap) {
                bitmaps.add((Bitmap) parcelable);
            }
        }
        return bitmaps;
    }

    public static <T> List<T> createSingleItemList(T t) {
        List<T> list = new ArrayList<T>();
        list.add(t);
        return list;
    }

    public static String getHashKey(Context context) {
        // Add code to print out the key hash
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        return null;
    }

    public void logClaz(Object object, String msg) {
        if (isDebug) {
            Log.d("TAG:", object.getClass().getSimpleName() + "." + new Exception().getStackTrace()[1].getMethodName() + "():" + msg);
        }

    }

    public ObjectMapper getObjectMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();

        }
        return mapper;
    }

    public void printStack() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        System.out.println("[0]" + stack[0]);
        System.out.println("[1]" + stack[1]);
        System.out.println("[2]" + stack[2]);
    }

    public <T> void printObject(T t) {
        System.out.println(writeValueAsString(t));
    }

    public <T> String writeValueAsString(T t) {
        String returnstring = "";
        try {
            returnstring = getObjectMapper().writeValueAsString(t);
            return returnstring;
        } catch (Exception e) {
            e.printStackTrace();
            return returnstring;
        }

    }

    private void getKey(Context context) {

        PackageInfo info;
        try {
            info = context.getPackageManager().getPackageInfo("com.github.takumalee.simplefacebook.test", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String KeyResult = new String(Base64.encode(md.digest(), 0));//String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", KeyResult);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    public void toast(Context context, String msg) {

        SuperToast.create(context, msg, SuperToast.Duration.SHORT, Style.getStyle(Style.GREEN, SuperToast.Animations.FLYIN)).show();
    }

    public String getFacebookSDKVersion() {
        String sdkVersion = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Class<?> cls = classLoader.loadClass("com.facebook.FacebookSdkVersion");
            Field field = cls.getField("BUILD");
            sdkVersion = String.valueOf(field.get(null));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sdkVersion;
    }

    public interface GeneralConverter<T, E> {
        T convert(E e);
    }

    public interface StringConverter<T> extends GeneralConverter<T, String> {
    }

    public interface Process<T> {
        String process(T t);
    }
}
