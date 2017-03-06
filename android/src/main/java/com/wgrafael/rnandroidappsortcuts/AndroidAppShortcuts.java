package com.wgrafael.rnandroidappsortcuts;

import java.lang.Exception;

import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.net.Uri;
import android.content.Intent;
import android.content.pm.ShortcutManager;
import android.content.pm.ShortcutInfo;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;




public class AndroidAppShortcuts extends ReactContextBaseJavaModule implements NativeModule {
    ReactApplicationContext context;
    ShortcutManager shortcutManager;

    @Override
    public String getName() {
        return "RNAndroidAppShortcuts";
    }

    public AndroidAppShortcuts(ReactApplicationContext reactContext) {
        super(reactContext);

        this.context = reactContext;
        shortcutManager = context.getSystemService(ShortcutManager.class);
    }





    public ShortcutInfo createShortcut(ReadableMap shortcut) {
        String[] requiredKeys = {"id", "shortLabel", "longLabel", "uri"};
        Iterator<String> iterator = Arrays.asList(requiredKeys).iterator();

        while(iterator.hasNext()) {
            String currentKey = iterator.next();
            if(!shortcut.hasKey(currentKey)) {
                String error = String.format("'%s'' it's required in the shortcut object!", currentKey);
                //throw new Exception(error);
            }
        }

        

        ShortcutInfo theShortcut = new ShortcutInfo.Builder(context, shortcut.getString("id"))
            .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse(shortcut.getString("uri"))))
            .setShortLabel(shortcut.getString("shortLabel"))
            .setLongLabel(shortcut.getString("longLabel"))
            .build();

        return theShortcut;
    }

    @ReactMethod
    public void registerAppShourtcut(ReadableMap shortcut) {
        ShortcutInfo theShortcut = createShortcut(shortcut);
        shortcutManager.addDynamicShortcuts(Arrays.asList(theShortcut));
    }

    @ReactMethod
    public void registerAppShourtcuts(ReadableArray shortcuts) {
        List shortcutsList = new ArrayList<ShortcutInfo>();
        int i;
        for(i=0; i < shortcuts.size(); i++) {
            if(shortcuts.getType(i) == ReadableType.Map) {
                shortcutsList.add(createShortcut(shortcuts.getMap(i)));
            } else {
                //throw new Exception("shortcuts should be array of object.");
            }
        }

        shortcutManager.addDynamicShortcuts(shortcutsList);
    }
}