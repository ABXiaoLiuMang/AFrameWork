package commonsdk.mmkv.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.tencent.mmkv.MMKV;

import java.util.Set;

import timber.log.Timber;

/**
 * MMKVUtils
 */
public final class MMKVUtils {

    private static MMKV INSTANCE;

    private MMKVUtils() {
    }

    public static void init(Context context) {
        String rootDir = MMKV.initialize(context);
        INSTANCE = MMKV.defaultMMKV();
        Timber.d("------------mmkv root-------: " + rootDir);
    }

    public static void put(String key, boolean value) {
        INSTANCE.encode(key, value);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return INSTANCE.decodeBool(key, defaultValue);
    }

    public static void put(String key, int value) {
        INSTANCE.encode(key, value);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return INSTANCE.decodeInt(key, defaultValue);
    }

    public static void put(String key, long value) {
        INSTANCE.encode(key, value);
    }

    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static long getLong(String key, long defaultValue) {
        return INSTANCE.decodeLong(key, defaultValue);
    }

    public static void put(String key, float value) {
        INSTANCE.encode(key, value);
    }

    public static float getFloat(String key) {
        return getFloat(key, 0.0F);
    }

    public static float getFloat(String key, float defaultValue) {
        return INSTANCE.decodeFloat(key, defaultValue);
    }

    public static void put(String key, double value) {
        INSTANCE.encode(key, value);
    }

    public static double getDouble(String key) {
        return getDouble(key, 0.0D);
    }

    public static double getDouble(String key, double defaultValue) {
        return INSTANCE.decodeDouble(key, defaultValue);
    }

    public static void put(String key, String value) {
        INSTANCE.encode(key, value);
    }

    public static String getString(String key) {
        return getString(key, (String) null);
    }

    public static String getString(String key, String defaultValue) {
        return INSTANCE.decodeString(key, defaultValue);
    }

    public static void put(String key, Set<String> value) {
        INSTANCE.encode(key, value);
    }

    public static Set<String> getStringSet(String key) {
        return getStringSet(key, (Set) null);
    }

    public static Set<String> getStringSet(String key, Set<String> defaultValue) {
        return INSTANCE.decodeStringSet(key, defaultValue);
    }

    public static void put(String key, byte[] value) {
        INSTANCE.encode(key, value);
    }

    public static byte[] decodeBytes(String key) {
        return INSTANCE.decodeBytes(key);
    }

    public static String[] getAllKeys() {
        return INSTANCE.allKeys();
    }

    public static long getCount() {
        return INSTANCE.count();
    }

    public static long getTotalSize() {
        return INSTANCE.totalSize();
    }

    public static void clearAll() {
        INSTANCE.clearAll();
    }

    public static void sync() {
        INSTANCE.sync();
    }

    public static boolean containsKey(String key) {
        return INSTANCE.containsKey(key);
    }

    public static void removeValueForKey(String key) {
        INSTANCE.removeValueForKey(key);
    }

    public static void removeValuesForKeys(String[] keys) {
        INSTANCE.removeValuesForKeys(keys);
    }

    public static void importFromSP(SharedPreferences sharedPreferences) {
        INSTANCE.importFromSharedPreferences(sharedPreferences);
    }
}
