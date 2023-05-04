package com.weprode.nero.contact;

import com.whirlycott.cache.Cache;
import com.whirlycott.cache.CacheManager;

public class CacheUtil {

    static Cache whirlycache = null;

    static{
        try {
            whirlycache = CacheManager.getInstance().getCache();
        } catch (com.whirlycott.cache.CacheException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkCache(String pKey) {
        if (whirlycache == null) {
            return false;
        }
        return pKey != null && !pKey.equals("");
    }

    public static Object getObjectFromCache(String key){
        if (!checkCache(key)) {
            return null;
        }
        return whirlycache.retrieve(key);
    }

    public static Object removeObjectFromCache (String key) {
        if (!checkCache(key)) {
            return null;
        }
        return whirlycache.remove(key);
    }

    public static void storeObjectIntoCache(String key,Object object,long expireTime){
        if (!checkCache(key)) {
            return;
        }
        if (expireTime < 0) {
            // Cache object with no expire date
            whirlycache.store(key,object);
        } else {
            // Cache object with expire date, this object will cleanup automatically after expire date
            whirlycache.store(key,object,expireTime);
        }
    }

}
