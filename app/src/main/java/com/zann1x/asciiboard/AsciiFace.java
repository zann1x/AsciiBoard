package com.zann1x.asciiboard;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AsciiFace {

    /*
    Links for categories and even more faces:

    https://textfac.es/
    https://www.npmjs.com/package/cool-ascii-faces
    https://github.com/dysfunc/ascii-emoji
    http://kaomoji.ru/en/
     */

    public Map<Category, List<String>> asciiFaceMap;

    public AsciiFace(Context context) {
        asciiFaceMap = new TreeMap<>();
        fillCategories(readJsonData(context));
    }

    public AsciiFaceData[] readJsonData(Context context) {
        AsciiFaceData[] asciiFaceData;
        Gson gson = new Gson();
        try (InputStream inputStream = context.getAssets().open("ascii_faces.json")) {
            JsonReader jsonReader = gson.newJsonReader(new InputStreamReader(inputStream));
            asciiFaceData = gson.fromJson(jsonReader, AsciiFaceData[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return asciiFaceData;
    }

    public void fillCategories(AsciiFaceData[] asciiFaceData) {
        asciiFaceMap.clear();

        // TODO improve this approach
        for (Category category : Category.values())
            asciiFaceMap.put(category, new ArrayList<String>());

        for (AsciiFaceData asciiFace : asciiFaceData) {
            for (Category category : asciiFace.categories) {
                asciiFaceMap.get(category).add(asciiFace.face);
            }
        }

        for (Category category : Category.values())
            if (asciiFaceMap.get(category).size() == 0)
                asciiFaceMap.get(category).add("<nothing_found_here>");
    }

}
