package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoodRatingsTest {
    @Test
    public void test() {
        String[] foods = new String[]{"vhbibydfop","ggtdlawxup","lslcltacc","bqgkfcy","xxowyabq","ezswv","ewgvsuyvpo","zgochpey","epiaxxiv","idpdiqlcp","wqgrbvme","wqxbxeovwe","iduzk","ahemklz","ah","rvuqnlydxu","dldqmpheob","lzeqwfb","ykwjc","dtrgnrcqr","cwcgevzzz","zejbynj","ivhdsnjpho","gsyewq","ekqhtiijrj","cthwbt","gnbhjyhcil","kkthw","ktiqokzl","zzroi","cjwapjnzql","hfum","jrwby","amhfyno","bj","fszije","nbkpptir","dgzmoxhhoe","bnisfskgxi","uepypxnsy","afdz","tdctkeg","lfsohpzmk","ewwqult","iynyddno","yyroyjzdg","mcpea","lzafjh","jdvcxidtm","qvkyqmupz","kjop","girgfkaib","qecvnaxpsm","itx","xhjmq","lwwbdxkz","chthxuhex","tghokrfica","vrmjibxv","oipnttbz","yyfvlwvj","erbtnw","yezfiom","ismarfqbuv","vmfxlqbxwa","pcwparohxf","bpdrndbv","owgjjitjfd","fdwmxhli","kffetdxvfx","ruahmg","aphbsbkimb","nbkdfkgl","rxehcqcrwu","kxwlfxbe","qukxd","tewgnjuxnm","jxfqwqe","mdgwiok","tbkmnx","klzznopcn","vjbcvddw","yzhawtmpwp","fhjichpi","tapig","glfbwolsoa","xklqlzm"};
        String[] c = new String[]{"kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh","kggxajkarh"};
        int[] rates = new int[]{146,146,428,619,731,483,598,619,950,446,630,556,894,911,537,359,863,995,417,702,689,667,502,691,896,582,111,319,182,953,131,372,563,963,300,42,829,187,676,509,259,688,897,614,392,170,392,1,134,79,995,371,886,430,72,830,871,758,247,333,128,987,659,933,640,198,638,585,825,904,729,214,974,935,581,985,85,987,51,174,301,635,148,492,200,275,101};
        FoodRatings foodRatings = new FoodRatings(foods, c, rates);
        foodRatings.highestRated("kggxajkarh");
    }
}