//package com.crowderia.udayasri.androidhivetest.util;
//
//import android.graphics.Bitmap;
//import android.util.LruCache;
//
//import com.android.volley.toolbox.ImageLoader.ImageCache;
//
///**
// * Created by UdayaSri on 12/19/15.
// */
//public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache {
//
//    public static int getDefaultLruCacheSize() {
//        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//        final int cacheSize = maxMemory / 8;
//
//        return cacheSize;
//    }
//
//    public LruBitmapCache() {
//        this(getDefaultLruCacheSize());
//    }
//
//    /**
//     * @param sizeInKiloBytes for caches that do not override {@link #sizeOf}, this is
//     *                the maximum number of entries in the cache. For all other caches,
//     *                this is the maximum sum of the sizes of the entries in this cache.
//     */
//    public LruBitmapCache(int sizeInKiloBytes) {
//        super(sizeInKiloBytes);
//    }
//
//    @Override
//    protected int sizeOf(String key, Bitmap value) {
//        return value.getRowBytes() * value.getHeight() / 1024;
//    }
//
//    @Override
//    public Bitmap getBitmap(String url) {
//        return get(url);
//    }
//
//    @Override
//    public void putBitmap(String url, Bitmap bitmap) {
//        put(url, bitmap);
//    }
//}
