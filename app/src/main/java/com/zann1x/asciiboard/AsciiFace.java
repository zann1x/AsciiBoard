package com.zann1x.asciiboard;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsciiFace {

    /*
    Links for categories and even more faces:

    https://textfac.es/
    https://www.npmjs.com/package/cool-ascii-faces
    https://github.com/dysfunc/ascii-emoji
    http://kaomoji.ru/en/
     */
    public static List<String> asciiFaces = Arrays.asList(
            "( .-. )",
            "( .o.)",
            "( `·´ )",
            "( ° ͜ ʖ °)",
            "( ͡° ͜ʖ ͡°)",
            "( ⚆ _ ⚆ )",
            "( ︶︿︶)",
            "( ﾟヮﾟ)",
            "(\\/)(°,,,°)(\\/)",
            "(¬_¬)",
            "(¬º-°)¬",
            "(¬‿¬)",
            "(°ロ°)☝",
            "(´・ω・)っ",
            "(ó ì_í)",
            "(ʘᗩʘ')",
            "(ʘ‿ʘ)",
            "(͡° ͜ʖ ͡°)",
            "(ಠ_ಠ)",
            "(ಠ‿ಠ)",
            "(ಠ⌣ಠ)",
            "(ಥ_ಥ)",
            "(ಥ﹏ಥ)",
            "(ง ͠° ͟ل͜ ͡°)ง",
            "(ง ͡ʘ ͜ʖ ͡ʘ)ง",
            "(ง •̀_•́)ง",
            "(ง'̀-'́)ง",
            "(ง°ل͜°)ง",
            "(ง⌐□ل͜□)ง",
            "(ღ˘⌣˘ღ)",
            "(ᵔᴥᵔ)",
            "(•ω•)",
            "(•◡•)/",
            "(⊙ω⊙)",
            "(⌐■_■)",
            "(─‿‿─)",
            "(╯°□°）╯",
            "(◕‿◕)",
            "(☞ﾟ∀ﾟ)☞",
            "(❍ᴥ❍ʋ)",
            "(っ◕‿◕)っ",
            "(づ｡◕‿‿◕｡)づ" ,
            "(ノಠ益ಠ)ノ",
            "(ノ・∀・)ノ",
            "(；一_一)",
            "(｀◔ ω ◔´)",
            "(｡◕‿‿◕｡)",
            "(ﾉ◕ヮ◕)ﾉ",
            "=^.^=",
            "t(-.-t)",
            "| (• ◡•)|",
            "~(˘▾˘~)",
            "¬_¬",
            "¯(°_o)/¯",
            "¯\\_(ツ)_/¯",
            "°Д°",
            "ɳ༼ຈل͜ຈ༽ɲ",
            "ʅʕ•ᴥ•ʔʃ",
            "ʕ´•ᴥ•`ʔ",
            "ʕ•ᴥ•ʔ",
            "ʕ◉.◉ʔ",
            "ʕㅇ호ㅇʔ",
            "ʕ；•`ᴥ•´ʔ",
            "ʘ‿ʘ",
            "͡° ͜ʖ ͡°",
            "ζ༼Ɵ͆ل͜Ɵ͆༽ᶘ",
            "Ѱζ༼ᴼل͜ᴼ༽ᶘѰ",
            "ب_ب",
            "٩◔̯◔۶",
            "ಠ_ಠ",
            "ಠoಠ",
            "ಠ~ಠ",
            "ಠ‿ಠ",
            "ಠ⌣ಠ",
            "ಠ╭╮ಠ",
            "ರ_ರ",
            "ง ͠° ل͜ °)ง",
            "๏̯͡๏﴿",
            "༼ ºººººل͟ººººº ༽",
            "༼ ºل͟º ༽",
            "༼ ºل͟º༼",
            "༼ ºل͟º༽",
            "༼ ͡■ل͜ ͡■༽",
            "༼ つ ◕_◕ ༽つ",
            "༼ʘ̚ل͜ʘ̚༽",
            "ლ(´ڡ`ლ)",
            "ლ(́◉◞౪◟◉‵ლ)",
            "ლ(ಠ益ಠლ)",
            "ᔑ•ﺪ͟͠•ᔐ",
            "ᕕ( ᐛ )ᕗ",
            "ᕙ(⇀‸↼‶)ᕗ",
            "ᕙ༼ຈل͜ຈ༽ᕗ",
            "ᶘ ᵒᴥᵒᶅ",
            "‎‎(ﾉಥ益ಥ）ﾉ",
            "≧☉_☉≦",
            "⊙▃⊙",
            "⊙﹏⊙",
            "┌( ಠ_ಠ)┘",
            "╚(ಠ_ಠ)=┐",
            "◉_◉",
            "◔ ⌣ ◔",
            "◔̯◔",
            "◕‿↼",
            "◕‿◕",
            "☉_☉",
            "☜(⌒▽⌒)☞",
            "☼.☼",
            "♥‿♥",
            "⚆ _ ⚆",
            "✌(-‿-)✌",
            "〆(・∀・＠)",
            "ノ( º _ ºノ)",
            "ノ( ゜-゜ノ)",
            "ヽ( ͝° ͜ʖ͡°)ﾉ",
            "ヽ(`Д´)ﾉ",
            "ヽ༼° ͟ل͜ ͡°༽ﾉ",
            "ヽ༼ʘ̚ل͜ʘ̚༽ﾉ",
            "ヽ༼ຈل͜ຈ༽ง",
            "ヽ༼ຈل͜ຈ༽ﾉ",
            "ヽ༼Ὸل͜ຈ༽ﾉ",
            "ヾ(⌐■_■)ノ",
            "꒰･◡･๑꒱",
            "﴾͡๏̯͡๏﴿",
            "｡◕‿◕｡",
            "ʕノ◔ϖ◔ʔノ",
            "ಠ_ರೃ",
            "(ꐦ°᷄д°᷅)",
            "ヘ（。□°）ヘ",
            "૮( ᵒ̌ૢཪᵒ̌ૢ )ა",
            "“ψ(｀∇´)ψ",
            "ಠﭛಠ",
            "(๑>ᴗ<๑)",
            "(۶ꈨຶꎁꈨຶ )۶ʸᵉᵃʰᵎ",
            "٩(•̤̀ᵕ•̤́๑)ᵒᵏᵎᵎᵎᵎ",
            "(✌ﾟ∀ﾟ)☞",
            "ಥ‿ಥ",
            "┬┴┬┴┤  (ಠ├┬┴┬┴",
            "( ˘ ³˘)♥",
            "(•̀o•́)ง",
            "=͟͟͞͞ =͟͟͞͞ ﾍ( ´Д`)ﾉ",
            "\\_(ʘ_ʘ)_/"
    );



    // first steps for implementing categories for the various faces

    public Map<AsciiFaceCategory, List<String>> asciiFaceMap;

    public AsciiFace() {
        asciiFaceMap = new HashMap<>();
    }

    // TODO remove method
    private String readJsonFile(Context context, String filename, String charset) {
        String json;
        try {
            InputStream inputStream = context.getAssets().open(filename);
            int availableBytes = inputStream.available();
            byte[] buffer = new byte[availableBytes];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, charset);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return json;
    }

    public AsciiFaceData[] readJsonData(Context context) {
        AsciiFaceData[] asciiFaceData;
        Gson gson = new Gson();
        try (JsonReader jr = gson.newJsonReader(new InputStreamReader(context.getAssets().open("ascii_faces.json")))) {
            asciiFaceData = gson.fromJson(jr, AsciiFaceData[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return asciiFaceData;
    }

    public void fillCategories(AsciiFaceData[] asciiFaceData) {
        asciiFaceMap.clear();

        // TODO improve this approach
        for (AsciiFaceCategory category : AsciiFaceCategory.values())
            asciiFaceMap.put(category, new ArrayList<String>());

        for (AsciiFaceData asciiFace : asciiFaceData) {
            for (AsciiFaceCategory category : asciiFace.categories) {
                asciiFaceMap.get(category).add(asciiFace.face);
            }
        }

        for (AsciiFaceCategory category : AsciiFaceCategory.values())
            if (asciiFaceMap.get(category).size() == 0)
                asciiFaceMap.get(category).add("nothing found here");

        /*
        fillAngryCategory();
        fillBearCategory();
        fillCryingCategory();
        fillOtherCategory();
        fillShrugCategory();
        fillFlipCategory();
        */
    }

    // TODO remove this early version of the category implementation
    /*
    private void fillAngryCategory() {
        asciiFaceMap.put(AsciiFaceCategory.ANGRY, "ಠ_ಠ");
    }

    private void fillBearCategory() {
        asciiFaceMap.put(AsciiFaceCategory.BEAR, "ʕ•ᴥ•ʔ");
        asciiFaceMap.put(AsciiFaceCategory.BEAR, "ʕᵔᴥᵔʔ");
        asciiFaceMap.put(AsciiFaceCategory.BEAR, "ʕ´•ᴥ•`ʔ");
        asciiFaceMap.put(AsciiFaceCategory.BEAR, "ʅʕ•ᴥ•ʔʃ");
    }

    private void fillCryingCategory() {
        asciiFaceMap.put(AsciiFaceCategory.CRYING, "(ಥ_ʖಥ)");
        asciiFaceMap.put(AsciiFaceCategory.CRYING, "ಥ_ಥ");
        asciiFaceMap.put(AsciiFaceCategory.CRYING, "ಥ﹏ಥ");
    }

    private void fillOtherCategory() {
        asciiFaceMap.put(AsciiFaceCategory.OTHER, "♨_♨");
    }

    private void fillShrugCategory() {
        asciiFaceMap.put(AsciiFaceCategory.SHRUG, "¯\\_(ツ)_/¯");
        asciiFaceMap.put(AsciiFaceCategory.SHRUG, "ʅ（´◔౪◔）ʃ");
        asciiFaceMap.put(AsciiFaceCategory.SHRUG, "¯\\_༼ ಥ ‿ ಥ ༽_/¯");
        asciiFaceMap.put(AsciiFaceCategory.SHRUG, "¯\\_(⊙︿⊙)_/¯");
    }

    private void fillFlipCategory() {
        asciiFaceMap.put(AsciiFaceCategory.FLIP, "(╯°□°）╯︵ ┻━┻");
        asciiFaceMap.put(AsciiFaceCategory.FLIP, "┬─┬ ノ( ゜-゜ノ)");
        asciiFaceMap.put(AsciiFaceCategory.FLIP, "┬─┬⃰͡ (ᵔᵕᵔ͜ )");
        asciiFaceMap.put(AsciiFaceCategory.FLIP, "┻━┻ ︵ヽ(`Д´)ﾉ︵ ┻━┻");
        asciiFaceMap.put(AsciiFaceCategory.FLIP, "(ノಠ ∩ಠ)ノ彡( \\o°o)\\");
    }
    */

}
