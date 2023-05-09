package com.weprode.nero.contact.utils;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.ExpiryPolicy;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

public class CacheUtil {

    static Cache<String, String> cache;

    static {
        // We store in cache for half an hour (30 minutes)
        // Create a cache configuration with the desired settings
        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10000, EntryUnit.ENTRIES)
                                .offheap(1, MemoryUnit.GB))
                .withExpiry(new ExpiryPolicy<>() {
                    @Override
                    public Duration getExpiryForCreation(String s, String s2) {
                        return Duration.of(30, ChronoUnit.MINUTES);
                    }

                    @Override
                    public Duration getExpiryForAccess(String s, Supplier<? extends String> supplier) {
                        return Duration.of(30, ChronoUnit.MINUTES);
                    }

                    @Override
                    public Duration getExpiryForUpdate(String s, Supplier<? extends String> supplier, String s2) {
                        return Duration.of(30, ChronoUnit.MINUTES);
                    }
                })
                .build();

        try {
            CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                    .withCache("myCache", cacheConfiguration)
                    .build(true);

            // Create a cache instance with the desired name
            cache = cacheManager.getCache("myCache", String.class, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkCache(String key) {
        if (cache == null) {
            return false;
        }
        return key != null && !key.equals("") && cache.containsKey(key);
    }

    public static String getObjectFromCache(String key) {
        if (!checkCache(key)) {
            return null;
        }

        // Retrieve the object from the cache using the key
        return cache.get(key);
    }

    public static void removeObjectFromCache (String key) {
        if (checkCache(key)) {
            cache.remove(key);
        }
    }

    public static void storeObjectIntoCache(String key, String value){
        // Store an object in the cache with a given key
        cache.put(key, value);
    }

}
