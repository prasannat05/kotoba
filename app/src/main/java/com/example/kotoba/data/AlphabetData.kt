package com.example.kotoba.data

data class KanaCharacter(
    val japanese: String,
    val romaji: String,
    val phonetic: String,
    val type: KanaType
)

enum class KanaType {
    HIRAGANA, KATAKANA, VOCABULARY, SENTENCE
}

data class Chapter(
    val id: Int,
    val name: String,
    val type: KanaType,
    val characters: List<KanaCharacter>
)

object AlphabetData {
    val hiraganaChapters = listOf(
        Chapter(1, "Vowels", KanaType.HIRAGANA, listOf(
            KanaCharacter("あ", "a", "ah", KanaType.HIRAGANA),
            KanaCharacter("い", "i", "ee", KanaType.HIRAGANA),
            KanaCharacter("う", "u", "oo", KanaType.HIRAGANA),
            KanaCharacter("え", "e", "eh", KanaType.HIRAGANA),
            KanaCharacter("お", "o", "oh", KanaType.HIRAGANA)
        )),
        Chapter(2, "K-Row", KanaType.HIRAGANA, listOf(
            KanaCharacter("か", "ka", "ka", KanaType.HIRAGANA),
            KanaCharacter("き", "ki", "kee", KanaType.HIRAGANA),
            KanaCharacter("く", "ku", "koo", KanaType.HIRAGANA),
            KanaCharacter("け", "ke", "keh", KanaType.HIRAGANA),
            KanaCharacter("こ", "ko", "koh", KanaType.HIRAGANA)
        )),
        Chapter(3, "S-Row", KanaType.HIRAGANA, listOf(
            KanaCharacter("さ", "sa", "sah", KanaType.HIRAGANA),
            KanaCharacter("し", "shi", "shee", KanaType.HIRAGANA),
            KanaCharacter("す", "su", "soo", KanaType.HIRAGANA),
            KanaCharacter("せ", "se", "seh", KanaType.HIRAGANA),
            KanaCharacter("そ", "so", "soh", KanaType.HIRAGANA)
        )),
        Chapter(4, "T-Row", KanaType.HIRAGANA, listOf(
            KanaCharacter("た", "ta", "tah", KanaType.HIRAGANA),
            KanaCharacter("ち", "chi", "chee", KanaType.HIRAGANA),
            KanaCharacter("つ", "tsu", "tsoo", KanaType.HIRAGANA),
            KanaCharacter("て", "te", "teh", KanaType.HIRAGANA),
            KanaCharacter("と", "to", "toh", KanaType.HIRAGANA)
        )),
        Chapter(5, "N-Row", KanaType.HIRAGANA, listOf(
            KanaCharacter("な", "na", "nah", KanaType.HIRAGANA),
            KanaCharacter("に", "ni", "nee", KanaType.HIRAGANA),
            KanaCharacter("ぬ", "nu", "noo", KanaType.HIRAGANA),
            KanaCharacter("ね", "ne", "neh", KanaType.HIRAGANA),
            KanaCharacter("の", "no", "noh", KanaType.HIRAGANA)
        )),
        Chapter(6, "H-Row", KanaType.HIRAGANA, listOf(
            KanaCharacter("は", "ha", "hah", KanaType.HIRAGANA),
            KanaCharacter("ひ", "hi", "hee", KanaType.HIRAGANA),
            KanaCharacter("ふ", "fu", "foo", KanaType.HIRAGANA),
            KanaCharacter("へ", "he", "heh", KanaType.HIRAGANA),
            KanaCharacter("ほ", "ho", "hoh", KanaType.HIRAGANA)
        )),
        Chapter(7, "M-Row", KanaType.HIRAGANA, listOf(
            KanaCharacter("ま", "ma", "mah", KanaType.HIRAGANA),
            KanaCharacter("み", "mi", "mee", KanaType.HIRAGANA),
            KanaCharacter("む", "mu", "moo", KanaType.HIRAGANA),
            KanaCharacter("め", "me", "meh", KanaType.HIRAGANA),
            KanaCharacter("も", "mo", "moh", KanaType.HIRAGANA)
        )),
        Chapter(8, "Y-Row", KanaType.HIRAGANA, listOf(
            KanaCharacter("や", "ya", "yah", KanaType.HIRAGANA),
            KanaCharacter("ゆ", "yu", "yoo", KanaType.HIRAGANA),
            KanaCharacter("よ", "yo", "yoh", KanaType.HIRAGANA)
        )),
        Chapter(9, "R-Row", KanaType.HIRAGANA, listOf(
            KanaCharacter("ら", "ra", "rah", KanaType.HIRAGANA),
            KanaCharacter("り", "ri", "ree", KanaType.HIRAGANA),
            KanaCharacter("る", "ru", "roo", KanaType.HIRAGANA),
            KanaCharacter("れ", "re", "reh", KanaType.HIRAGANA),
            KanaCharacter("ろ", "ro", "roh", KanaType.HIRAGANA)
        )),
        Chapter(10, "W-Row & N", KanaType.HIRAGANA, listOf(
            KanaCharacter("わ", "wa", "wah", KanaType.HIRAGANA),
            KanaCharacter("を", "wo", "woh", KanaType.HIRAGANA),
            KanaCharacter("ん", "n", "nn", KanaType.HIRAGANA)
        )),
        Chapter(11, "Dakuten (G, Z, D)", KanaType.HIRAGANA, listOf(
            KanaCharacter("が", "ga", "gah", KanaType.HIRAGANA),
            KanaCharacter("ぎ", "gi", "gee", KanaType.HIRAGANA),
            KanaCharacter("ぐ", "gu", "goo", KanaType.HIRAGANA),
            KanaCharacter("げ", "ge", "geh", KanaType.HIRAGANA),
            KanaCharacter("ご", "go", "goh", KanaType.HIRAGANA),
            KanaCharacter("ざ", "za", "zah", KanaType.HIRAGANA),
            KanaCharacter("じ", "ji", "jee", KanaType.HIRAGANA),
            KanaCharacter("ず", "zu", "zoo", KanaType.HIRAGANA),
            KanaCharacter("ぜ", "ze", "zeh", KanaType.HIRAGANA),
            KanaCharacter("ぞ", "zo", "zoh", KanaType.HIRAGANA),
            KanaCharacter("だ", "da", "dah", KanaType.HIRAGANA),
            KanaCharacter("ぢ", "ji", "jee", KanaType.HIRAGANA),
            KanaCharacter("づ", "zu", "zoo", KanaType.HIRAGANA),
            KanaCharacter("で", "de", "deh", KanaType.HIRAGANA),
            KanaCharacter("ど", "do", "doh", KanaType.HIRAGANA)
        )),
        Chapter(12, "Dakuten (B, P)", KanaType.HIRAGANA, listOf(
            KanaCharacter("ば", "ba", "bah", KanaType.HIRAGANA),
            KanaCharacter("び", "bi", "bee", KanaType.HIRAGANA),
            KanaCharacter("ぶ", "bu", "boo", KanaType.HIRAGANA),
            KanaCharacter("べ", "be", "beh", KanaType.HIRAGANA),
            KanaCharacter("ぼ", "bo", "boh", KanaType.HIRAGANA),
            KanaCharacter("ぱ", "pa", "pah", KanaType.HIRAGANA),
            KanaCharacter("ぴ", "pi", "pee", KanaType.HIRAGANA),
            KanaCharacter("ぷ", "pu", "poo", KanaType.HIRAGANA),
            KanaCharacter("ぺ", "pe", "peh", KanaType.HIRAGANA),
            KanaCharacter("ぽ", "po", "poh", KanaType.HIRAGANA)
        )),
        Chapter(13, "Contracted Sounds (K, S, T)", KanaType.HIRAGANA, listOf(
            KanaCharacter("きゃ", "kya", "kyah", KanaType.HIRAGANA),
            KanaCharacter("きゅ", "kyu", "kyoo", KanaType.HIRAGANA),
            KanaCharacter("きょ", "kyo", "kyoh", KanaType.HIRAGANA),
            KanaCharacter("しゃ", "sha", "shah", KanaType.HIRAGANA),
            KanaCharacter("しゅ", "shu", "shoo", KanaType.HIRAGANA),
            KanaCharacter("しょ", "sho", "shoh", KanaType.HIRAGANA),
            KanaCharacter("ちゃ", "cha", "chah", KanaType.HIRAGANA),
            KanaCharacter("ちゅ", "chu", "choo", KanaType.HIRAGANA),
            KanaCharacter("ちょ", "cho", "choh", KanaType.HIRAGANA)
        )),
        Chapter(14, "Contracted Sounds (N, H, M, R)", KanaType.HIRAGANA, listOf(
            KanaCharacter("にゃ", "nya", "nyah", KanaType.HIRAGANA),
            KanaCharacter("にゅ", "nyu", "nyoo", KanaType.HIRAGANA),
            KanaCharacter("にょ", "nyo", "nyoh", KanaType.HIRAGANA),
            KanaCharacter("ひゃ", "hya", "hyah", KanaType.HIRAGANA),
            KanaCharacter("ひゅ", "hyu", "hyoo", KanaType.HIRAGANA),
            KanaCharacter("ひょ", "hyo", "hyoh", KanaType.HIRAGANA),
            KanaCharacter("みゃ", "mya", "myah", KanaType.HIRAGANA),
            KanaCharacter("みゅ", "myu", "myoo", KanaType.HIRAGANA),
            KanaCharacter("みょ", "myo", "myoh", KanaType.HIRAGANA),
            KanaCharacter("りゃ", "rya", "ryah", KanaType.HIRAGANA),
            KanaCharacter("りゅ", "ryu", "ryoo", KanaType.HIRAGANA),
            KanaCharacter("りょ", "ryo", "ryoh", KanaType.HIRAGANA)
        ))
    )

    val katakanaChapters = listOf(
        Chapter(101, "Vowels", KanaType.KATAKANA, listOf(
            KanaCharacter("ア", "a", "ah", KanaType.KATAKANA),
            KanaCharacter("イ", "i", "ee", KanaType.KATAKANA),
            KanaCharacter("ウ", "u", "oo", KanaType.KATAKANA),
            KanaCharacter("エ", "e", "eh", KanaType.KATAKANA),
            KanaCharacter("オ", "o", "oh", KanaType.KATAKANA)
        )),
        Chapter(102, "K-Row", KanaType.KATAKANA, listOf(
            KanaCharacter("カ", "ka", "ka", KanaType.KATAKANA),
            KanaCharacter("キ", "ki", "kee", KanaType.KATAKANA),
            KanaCharacter("ク", "ku", "koo", KanaType.KATAKANA),
            KanaCharacter("ケ", "ke", "keh", KanaType.KATAKANA),
            KanaCharacter("コ", "ko", "koh", KanaType.KATAKANA)
        )),
        Chapter(103, "S-Row", KanaType.KATAKANA, listOf(
            KanaCharacter("サ", "sa", "sah", KanaType.KATAKANA),
            KanaCharacter("シ", "shi", "shee", KanaType.KATAKANA),
            KanaCharacter("ス", "su", "soo", KanaType.KATAKANA),
            KanaCharacter("セ", "se", "seh", KanaType.KATAKANA),
            KanaCharacter("ソ", "so", "soh", KanaType.KATAKANA)
        )),
        Chapter(104, "T-Row", KanaType.KATAKANA, listOf(
            KanaCharacter("タ", "ta", "tah", KanaType.KATAKANA),
            KanaCharacter("チ", "chi", "chee", KanaType.KATAKANA),
            KanaCharacter("ツ", "tsu", "tsoo", KanaType.KATAKANA),
            KanaCharacter("テ", "te", "teh", KanaType.KATAKANA),
            KanaCharacter("ト", "to", "toh", KanaType.KATAKANA)
        )),
        Chapter(105, "N-Row", KanaType.KATAKANA, listOf(
            KanaCharacter("ナ", "na", "nah", KanaType.KATAKANA),
            KanaCharacter("ニ", "ni", "nee", KanaType.KATAKANA),
            KanaCharacter("ヌ", "nu", "noo", KanaType.KATAKANA),
            KanaCharacter("ネ", "ne", "neh", KanaType.KATAKANA),
            KanaCharacter("ノ", "no", "noh", KanaType.KATAKANA)
        )),
        Chapter(106, "H-Row", KanaType.KATAKANA, listOf(
            KanaCharacter("ハ", "ha", "hah", KanaType.KATAKANA),
            KanaCharacter("ヒ", "hi", "hee", KanaType.KATAKANA),
            KanaCharacter("フ", "fu", "foo", KanaType.KATAKANA),
            KanaCharacter("ヘ", "he", "heh", KanaType.KATAKANA),
            KanaCharacter("ホ", "ho", "hoh", KanaType.KATAKANA)
        )),
        Chapter(107, "M-Row", KanaType.KATAKANA, listOf(
            KanaCharacter("マ", "ma", "mah", KanaType.KATAKANA),
            KanaCharacter("ミ", "mi", "mee", KanaType.KATAKANA),
            KanaCharacter("ム", "mu", "moo", KanaType.KATAKANA),
            KanaCharacter("メ", "me", "meh", KanaType.KATAKANA),
            KanaCharacter("モ", "mo", "moh", KanaType.KATAKANA)
        )),
        Chapter(108, "Y-Row", KanaType.KATAKANA, listOf(
            KanaCharacter("ヤ", "ya", "yah", KanaType.KATAKANA),
            KanaCharacter("ユ", "yu", "yoo", KanaType.KATAKANA),
            KanaCharacter("ヨ", "yo", "yoh", KanaType.KATAKANA)
        )),
        Chapter(109, "R-Row", KanaType.KATAKANA, listOf(
            KanaCharacter("ラ", "ra", "rah", KanaType.KATAKANA),
            KanaCharacter("リ", "ri", "ree", KanaType.KATAKANA),
            KanaCharacter("ル", "ru", "roo", KanaType.KATAKANA),
            KanaCharacter("レ", "re", "reh", KanaType.KATAKANA),
            KanaCharacter("ロ", "ro", "roh", KanaType.KATAKANA)
        )),
        Chapter(110, "W-Row & N", KanaType.KATAKANA, listOf(
            KanaCharacter("ワ", "wa", "wah", KanaType.KATAKANA),
            KanaCharacter("ヲ", "wo", "woh", KanaType.KATAKANA),
            KanaCharacter("ン", "n", "nn", KanaType.KATAKANA)
        )),
        Chapter(111, "Dakuten (G, Z, D)", KanaType.KATAKANA, listOf(
            KanaCharacter("ガ", "ga", "gah", KanaType.KATAKANA),
            KanaCharacter("ギ", "gi", "gee", KanaType.KATAKANA),
            KanaCharacter("グ", "gu", "goo", KanaType.KATAKANA),
            KanaCharacter("ゲ", "ge", "geh", KanaType.KATAKANA),
            KanaCharacter("ゴ", "go", "goh", KanaType.KATAKANA),
            KanaCharacter("ザ", "za", "zah", KanaType.KATAKANA),
            KanaCharacter("ジ", "ji", "jee", KanaType.KATAKANA),
            KanaCharacter("ズ", "zu", "zoo", KanaType.KATAKANA),
            KanaCharacter("ゼ", "ze", "zeh", KanaType.KATAKANA),
            KanaCharacter("ゾ", "zo", "zoh", KanaType.KATAKANA),
            KanaCharacter("ダ", "da", "dah", KanaType.KATAKANA),
            KanaCharacter("ヂ", "ji", "jee", KanaType.KATAKANA),
            KanaCharacter("ヅ", "zu", "zoo", KanaType.KATAKANA),
            KanaCharacter("デ", "de", "deh", KanaType.KATAKANA),
            KanaCharacter("ド", "do", "doh", KanaType.KATAKANA)
        )),
        Chapter(112, "Dakuten (B, P)", KanaType.KATAKANA, listOf(
            KanaCharacter("バ", "ba", "bah", KanaType.KATAKANA),
            KanaCharacter("ビ", "bi", "bee", KanaType.KATAKANA),
            KanaCharacter("ブ", "bu", "boo", KanaType.KATAKANA),
            KanaCharacter("ベ", "be", "beh", KanaType.KATAKANA),
            KanaCharacter("ボ", "bo", "boh", KanaType.KATAKANA),
            KanaCharacter("パ", "pa", "pah", KanaType.KATAKANA),
            KanaCharacter("ピ", "pi", "pee", KanaType.KATAKANA),
            KanaCharacter("ぷ", "pu", "poo", KanaType.KATAKANA),
            KanaCharacter("ペ", "pe", "peh", KanaType.KATAKANA),
            KanaCharacter("ポ", "po", "poh", KanaType.KATAKANA)
        )),
        Chapter(113, "Contracted Sounds (K, S, T)", KanaType.KATAKANA, listOf(
            KanaCharacter("キャ", "kya", "kyah", KanaType.KATAKANA),
            KanaCharacter("キュ", "kyu", "kyoo", KanaType.KATAKANA),
            KanaCharacter("キョ", "kyo", "kyoh", KanaType.KATAKANA),
            KanaCharacter("シャ", "sha", "shah", KanaType.KATAKANA),
            KanaCharacter("シュ", "shu", "shoo", KanaType.KATAKANA),
            KanaCharacter("ショ", "sho", "shoh", KanaType.KATAKANA),
            KanaCharacter("チャ", "cha", "chah", KanaType.KATAKANA),
            KanaCharacter("チュ", "chu", "choo", KanaType.KATAKANA),
            KanaCharacter("チョ", "cho", "choh", KanaType.KATAKANA)
        )),
        Chapter(114, "Extended Katakana", KanaType.KATAKANA, listOf(
            KanaCharacter("ファ", "fa", "fah", KanaType.KATAKANA),
            KanaCharacter("フィ", "fi", "fee", KanaType.KATAKANA),
            KanaCharacter("フェ", "fe", "feh", KanaType.KATAKANA),
            KanaCharacter("フォ", "fo", "foh", KanaType.KATAKANA),
            KanaCharacter("ティ", "ti", "tee", KanaType.KATAKANA),
            KanaCharacter("ディ", "di", "dee", KanaType.KATAKANA),
            KanaCharacter("デュ", "du", "dyoo", KanaType.KATAKANA),
            KanaCharacter("ウィ", "wi", "wee", KanaType.KATAKANA),
            KanaCharacter("ウェ", "we", "weh", KanaType.KATAKANA),
            KanaCharacter("ウォ", "wo", "woh", KanaType.KATAKANA)
        ))
    )

    val vocabularyChapters = listOf(
        Chapter(201, "Greetings", KanaType.VOCABULARY, listOf(
            KanaCharacter("こんにちは", "Hello", "konnichiwa", KanaType.VOCABULARY),
            KanaCharacter("おはよう", "Good morning", "ohayou", KanaType.VOCABULARY),
            KanaCharacter("こんばんは", "Good evening", "konbanwa", KanaType.VOCABULARY),
            KanaCharacter("ありがとう", "Thank you", "arigatou", KanaType.VOCABULARY),
            KanaCharacter("さようなら", "Goodbye", "sayounara", KanaType.VOCABULARY),
            KanaCharacter("おやすみ", "Good night", "oyasumi", KanaType.VOCABULARY)
        )),
        Chapter(202, "Common Objects", KanaType.VOCABULARY, listOf(
            KanaCharacter("ほん", "Book", "hon", KanaType.VOCABULARY),
            KanaCharacter("くるま", "Car", "kuruma", KanaType.VOCABULARY),
            KanaCharacter("ねこ", "Cat", "neko", KanaType.VOCABULARY),
            KanaCharacter("いぬ", "Dog", "inu", KanaType.VOCABULARY),
            KanaCharacter("みず", "Water", "mizu", KanaType.VOCABULARY),
            KanaCharacter("つくえ", "Desk", "tsukue", KanaType.VOCABULARY),
            KanaCharacter("いす", "Chair", "isu", KanaType.VOCABULARY),
            KanaCharacter("でんわ", "Phone", "denwa", KanaType.VOCABULARY),
            KanaCharacter("かばん", "Bag", "kaban", KanaType.VOCABULARY)
        )),
        Chapter(203, "Numbers (1-10)", KanaType.VOCABULARY, listOf(
            KanaCharacter("いち", "One", "ichi", KanaType.VOCABULARY),
            KanaCharacter("に", "Two", "ni", KanaType.VOCABULARY),
            KanaCharacter("さん", "Three", "san", KanaType.VOCABULARY),
            KanaCharacter("よん", "Four", "yon", KanaType.VOCABULARY),
            KanaCharacter("ご", "Five", "go", KanaType.VOCABULARY),
            KanaCharacter("ろく", "Six", "roku", KanaType.VOCABULARY),
            KanaCharacter("なな", "Seven", "nana", KanaType.VOCABULARY),
            KanaCharacter("はち", "Eight", "hachi", KanaType.VOCABULARY),
            KanaCharacter("きゅう", "Nine", "kyuu", KanaType.VOCABULARY),
            KanaCharacter("じゅう", "Ten", "juu", KanaType.VOCABULARY)
        )),
        Chapter(204, "Colors", KanaType.VOCABULARY, listOf(
            KanaCharacter("あか", "Red", "aka", KanaType.VOCABULARY),
            KanaCharacter("あお", "Blue", "ao", KanaType.VOCABULARY),
            KanaCharacter("しろ", "White", "shiro", KanaType.VOCABULARY),
            KanaCharacter("くろ", "Black", "kuro", KanaType.VOCABULARY),
            KanaCharacter("みどり", "Green", "midori", KanaType.VOCABULARY),
            KanaCharacter("きいろ", "Yellow", "kiiro", KanaType.VOCABULARY),
            KanaCharacter("ちゃいろ", "Brown", "chairo", KanaType.VOCABULARY),
            KanaCharacter("むらさき", "Purple", "murasaki", KanaType.VOCABULARY)
        )),
        Chapter(205, "Family", KanaType.VOCABULARY, listOf(
            KanaCharacter("かぞく", "Family", "kazoku", KanaType.VOCABULARY),
            KanaCharacter("おかあさん", "Mother", "okaasan", KanaType.VOCABULARY),
            KanaCharacter("おとうさん", "Father", "otousan", KanaType.VOCABULARY),
            KanaCharacter("おねえさん", "Older Sister", "oneesan", KanaType.VOCABULARY),
            KanaCharacter("おにいさん", "Older Brother", "oniisan", KanaType.VOCABULARY),
            KanaCharacter("いもうと", "Younger Sister", "imouto", KanaType.VOCABULARY),
            KanaCharacter("おとうと", "Younger Brother", "otouto", KanaType.VOCABULARY)
        )),
        Chapter(206, "Food & Drink", KanaType.VOCABULARY, listOf(
            KanaCharacter("ごはん", "Rice / Meal", "gohan", KanaType.VOCABULARY),
            KanaCharacter("パン", "Bread", "pan", KanaType.VOCABULARY),
            KanaCharacter("さかな", "Fish", "sakana", KanaType.VOCABULARY),
            KanaCharacter("にく", "Meat", "niku", KanaType.VOCABULARY),
            KanaCharacter("やさい", "Vegetables", "yasai", KanaType.VOCABULARY),
            KanaCharacter("くだもの", "Fruit", "kudamono", KanaType.VOCABULARY),
            KanaCharacter("たまご", "Egg", "tamago", KanaType.VOCABULARY),
            KanaCharacter("おちゃ", "Green Tea", "ocha", KanaType.VOCABULARY),
            KanaCharacter("コーヒー", "Coffee", "koohii", KanaType.VOCABULARY)
        )),
        Chapter(207, "Places", KanaType.VOCABULARY, listOf(
            KanaCharacter("いえ", "House", "ie", KanaType.VOCABULARY),
            KanaCharacter("がっこう", "School", "gakkou", KanaType.VOCABULARY),
            KanaCharacter("えき", "Station", "eki", KanaType.VOCABULARY),
            KanaCharacter("ぎんこう", "Bank", "ginkou", KanaType.VOCABULARY),
            KanaCharacter("びょういん", "Hospital", "byouin", KanaType.VOCABULARY),
            KanaCharacter("こうえん", "Park", "kouen", KanaType.VOCABULARY),
            KanaCharacter("みせ", "Shop", "mise", KanaType.VOCABULARY)
        )),
        Chapter(208, "Body Parts", KanaType.VOCABULARY, listOf(
            KanaCharacter("からだ", "Body", "karada", KanaType.VOCABULARY),
            KanaCharacter("あたま", "Head", "atama", KanaType.VOCABULARY),
            KanaCharacter("かお", "Face", "kao", KanaType.VOCABULARY),
            KanaCharacter("め", "Eye", "me", KanaType.VOCABULARY),
            KanaCharacter("みみ", "Ear", "mimi", KanaType.VOCABULARY),
            KanaCharacter("はな", "Nose", "hana", KanaType.VOCABULARY),
            KanaCharacter("くち", "Mouth", "kuchi", KanaType.VOCABULARY),
            KanaCharacter("て", "Hand", "te", KanaType.VOCABULARY),
            KanaCharacter("あし", "Leg / Foot", "ashi", KanaType.VOCABULARY)
        )),
        Chapter(209, "Animals", KanaType.VOCABULARY, listOf(
            KanaCharacter("うさぎ", "Rabbit", "usagi", KanaType.VOCABULARY),
            KanaCharacter("とり", "Bird", "tori", KanaType.VOCABULARY),
            KanaCharacter("さる", "Monkey", "saru", KanaType.VOCABULARY),
            KanaCharacter("うま", "Horse", "uma", KanaType.VOCABULARY),
            KanaCharacter("うし", "Cow", "ushi", KanaType.VOCABULARY),
            KanaCharacter("ぶた", "Pig", "buta", KanaType.VOCABULARY),
            KanaCharacter("ぞう", "Elephant", "zou", KanaType.VOCABULARY),
            KanaCharacter("へび", "Snake", "hebi", KanaType.VOCABULARY)
        )),
        Chapter(210, "Professions", KanaType.VOCABULARY, listOf(
            KanaCharacter("いしゃ", "Doctor", "isha", KanaType.VOCABULARY),
            KanaCharacter("かんごし", "Nurse", "kangoshi", KanaType.VOCABULARY),
            KanaCharacter("きょうし", "Teacher", "kyoushi", KanaType.VOCABULARY),
            KanaCharacter("がくせい", "Student", "gakusei", KanaType.VOCABULARY),
            KanaCharacter("けいさつかん", "Police Officer", "keisatsukan", KanaType.VOCABULARY),
            KanaCharacter("ぎんこういん", "Banker", "ginkouin", KanaType.VOCABULARY),
            KanaCharacter("かいしゃいん", "Office Worker", "kaishain", KanaType.VOCABULARY)
        )),
        Chapter(211, "Time & Days", KanaType.VOCABULARY, listOf(
            KanaCharacter("きょう", "Today", "kyou", KanaType.VOCABULARY),
            KanaCharacter("あした", "Tomorrow", "ashita", KanaType.VOCABULARY),
            KanaCharacter("きのう", "Yesterday", "kinou", KanaType.VOCABULARY),
            KanaCharacter("いま", "Now", "ima", KanaType.VOCABULARY),
            KanaCharacter("じ", "O'clock", "ji", KanaType.VOCABULARY),
            KanaCharacter("ふん", "Minute", "fun", KanaType.VOCABULARY),
            KanaCharacter("げつようび", "Monday", "getsuyoubi", KanaType.VOCABULARY),
            KanaCharacter("かようび", "Tuesday", "kayoubi", KanaType.VOCABULARY),
            KanaCharacter("すいようび", "Wednesday", "suiyoubi", KanaType.VOCABULARY),
            KanaCharacter("もくようび", "Thursday", "mokuyoubi", KanaType.VOCABULARY),
            KanaCharacter("きんようび", "Friday", "kinyoubi", KanaType.VOCABULARY),
            KanaCharacter("どようび", "Saturday", "doyoubi", KanaType.VOCABULARY),
            KanaCharacter("にちようび", "Sunday", "nichiyoubi", KanaType.VOCABULARY)
        )),
        Chapter(212, "Nature & Weather", KanaType.VOCABULARY, listOf(
            KanaCharacter("やま", "Mountain", "yama", KanaType.VOCABULARY),
            KanaCharacter("かわ", "River", "kawa", KanaType.VOCABULARY),
            KanaCharacter("うみ", "Sea / Ocean", "umi", KanaType.VOCABULARY),
            KanaCharacter("そら", "Sky", "sora", KanaType.VOCABULARY),
            KanaCharacter("あめ", "Rain", "ame", KanaType.VOCABULARY),
            KanaCharacter("ゆき", "Snow", "yuki", KanaType.VOCABULARY),
            KanaCharacter("かぜ", "Wind", "kaze", KanaType.VOCABULARY),
            KanaCharacter("はれ", "Sunny", "hare", KanaType.VOCABULARY),
            KanaCharacter("くもり", "Cloudy", "kumori", KanaType.VOCABULARY)
        ))
    )

    val sentenceChapters = listOf(
        Chapter(301, "Basic Introductions", KanaType.SENTENCE, listOf(
            KanaCharacter("はじめまして。", "Nice to meet you.", "hajimemashite", KanaType.SENTENCE),
            KanaCharacter("わたしのなまえは ... です。", "My name is ...", "watashi no namae wa ... desu", KanaType.SENTENCE),
            KanaCharacter("よろしくおねがいします。", "Please treat me well.", "yoroshiku onegaishimasu", KanaType.SENTENCE),
            KanaCharacter("どこからきましたか？", "Where are you from?", "doko kara kimashita ka?", KanaType.SENTENCE),
            KanaCharacter("アメリカからきました。", "I came from America.", "amerika kara kimashita", KanaType.SENTENCE),
            KanaCharacter("にほんからきました。", "I came from Japan.", "nihon kara kimashita", KanaType.SENTENCE)
        )),
        Chapter(302, "Daily Conversations", KanaType.SENTENCE, listOf(
            KanaCharacter("おげんきですか？", "How are you?", "o-genki desu ka?", KanaType.SENTENCE),
            KanaCharacter("はい、げんきです。", "Yes, I am fine.", "hai, genki desu", KanaType.SENTENCE),
            KanaCharacter("いまなんじですか？", "What time is it now?", "ima nanji desu ka?", KanaType.SENTENCE),
            KanaCharacter("わかりました。", "I understand.", "wakarimashita", KanaType.SENTENCE),
            KanaCharacter("わかりません。", "I don't understand.", "wakarimasen", KanaType.SENTENCE),
            KanaCharacter("もういちどおねがいします。", "One more time, please.", "mou ichido onegaishimasu", KanaType.SENTENCE),
            KanaCharacter("ちょっとまってください。", "Wait a moment, please.", "chotto matte kudasai", KanaType.SENTENCE),
            KanaCharacter("おねがいします。", "Please.", "onegaishimasu", KanaType.SENTENCE),
            KanaCharacter("どうぞ。", "Here you go / After you.", "douzo", KanaType.SENTENCE)
        )),
        Chapter(303, "Shopping & Eating", KanaType.SENTENCE, listOf(
            KanaCharacter("いくらですか？", "How much is it?", "ikura desu ka?", KanaType.SENTENCE),
            KanaCharacter("これをください。", "I'll take this, please.", "kore o kudasai", KanaType.SENTENCE),
            KanaCharacter("メニューをおねがいします。", "Menu, please.", "menyuu o onegaishimasu", KanaType.SENTENCE),
            KanaCharacter("いただきます。", "Let's eat (Before meal).", "itadakimasu", KanaType.SENTENCE),
            KanaCharacter("ごちそうさまでした。", "Thank you for the meal (After).", "gochisousama deshita", KanaType.SENTENCE),
            KanaCharacter("おいしいです。", "It's delicious.", "oishii desu", KanaType.SENTENCE),
            KanaCharacter("おなかがすきました。", "I am hungry.", "onaka ga sukimashita", KanaType.SENTENCE),
            KanaCharacter("みずをおねがいします。", "Water, please.", "mizu o onegaishimasu", KanaType.SENTENCE)
        )),
        Chapter(304, "Travel & Directions", KanaType.SENTENCE, listOf(
            KanaCharacter("トイレはどこですか？", "Where is the toilet?", "toire wa doko desu ka?", KanaType.SENTENCE),
            KanaCharacter("えきはどこですか？", "Where is the station?", "eki wa doko desu ka?", KanaType.SENTENCE),
            KanaCharacter("まっすぐいってください。", "Go straight, please.", "massugu itte kudasai", KanaType.SENTENCE),
            KanaCharacter("みぎにまがってください。", "Turn right, please.", "migi ni magatte kudasai", KanaType.SENTENCE),
            KanaCharacter("ひだりにまがってください。", "Turn left, please.", "hidari ni magatte kudasai", KanaType.SENTENCE),
            KanaCharacter("ここにとめてください。", "Please stop here.", "koko ni tomete kudasai", KanaType.SENTENCE),
            KanaCharacter("ちかいですか？", "Is it near?", "chikai desu ka?", KanaType.SENTENCE),
            KanaCharacter("とおいですか？", "Is it far?", "tooi desu ka?", KanaType.SENTENCE)
        )),
        Chapter(305, "Common Questions", KanaType.SENTENCE, listOf(
            KanaCharacter("これはなんですか？", "What is this?", "kore wa nan desu ka?", KanaType.SENTENCE),
            KanaCharacter("だれですか？", "Who is it?", "dare desu ka?", KanaType.SENTENCE),
            KanaCharacter("いつですか？", "When is it?", "itsu desu ka?", KanaType.SENTENCE),
            KanaCharacter("なぜですか？", "Why is it?", "naze desu ka?", KanaType.SENTENCE),
            KanaCharacter("どうですか？", "How is it?", "dou desu ka?", KanaType.SENTENCE),
            KanaCharacter("なにをしていますか？", "What are you doing?", "nani o shite imasu ka?", KanaType.SENTENCE)
        )),
        Chapter(306, "Polite Phrases", KanaType.SENTENCE, listOf(
            KanaCharacter("すみません。", "Excuse me / Sorry.", "sumimasen", KanaType.SENTENCE),
            KanaCharacter("ごめんなさい。", "I am sorry.", "gomennasai", KanaType.SENTENCE),
            KanaCharacter("しつれいします。", "Excuse me (entering/leaving).", "shitsurei shimasu", KanaType.SENTENCE),
            KanaCharacter("だいじょうぶです。", "It's okay.", "daijoubu desu", KanaType.SENTENCE),
            KanaCharacter("おめでとうございます！", "Congratulations!", "omedetou gozaimasu", KanaType.SENTENCE),
            KanaCharacter("おげんきで。", "Take care.", "o-genki de", KanaType.SENTENCE)
        )),
        Chapter(307, "Weather & Time", KanaType.SENTENCE, listOf(
            KanaCharacter("いいてんきですね。", "Nice weather, isn't it?", "ii tenki desu ne", KanaType.SENTENCE),
            KanaCharacter("きょうはあついです。", "It's hot today.", "kyou wa atsui desu", KanaType.SENTENCE),
            KanaCharacter("きょうはさむいです。", "It's cold today.", "kyou wa samui desu", KanaType.SENTENCE),
            KanaCharacter("あしたはあめです。", "It will rain tomorrow.", "ashita wa ame desu", KanaType.SENTENCE),
            KanaCharacter("いま、なんじですか？", "What time is it now?", "ima nanji desu ka?", KanaType.SENTENCE),
            KanaCharacter("きょうはなんようびですか？", "What day is it today?", "kyou wa nanyoubi desu ka?", KanaType.SENTENCE)
        )),
        Chapter(308, "Emergency & Help", KanaType.SENTENCE, listOf(
            KanaCharacter("たすけてください！", "Help me, please!", "tasukete kudasai", KanaType.SENTENCE),
            KanaCharacter("きぶんがわるいです。", "I feel sick.", "kibun ga warui desu", KanaType.SENTENCE),
            KanaCharacter("びょういんにいきたいです。", "I want to go to the hospital.", "byouin ni ikitai desu", KanaType.SENTENCE),
            KanaCharacter("けいさつをよんでください。", "Call the police, please.", "keisatsukan o yonde kudasai", KanaType.SENTENCE),
            KanaCharacter("なくしました。", "I lost it.", "nakushimashita", KanaType.SENTENCE),
            KanaCharacter("みちになよいました。", "I am lost (street).", "michi ni mayoimashita", KanaType.SENTENCE)
        )),
        Chapter(309, "Small Talk", KanaType.SENTENCE, listOf(
            KanaCharacter("しゅみはなんですか？", "What is your hobby?", "shumi wa nan desu ka?", KanaType.SENTENCE),
            KanaCharacter("なんさいですか？", "How old are you?", "nansai desu ka?", KanaType.SENTENCE),
            KanaCharacter("どこにすんでいますか？", "Where do you live?", "doko ni sunde imasu ka?", KanaType.SENTENCE),
            KanaCharacter("おなまえは？", "Your name?", "o-namae wa?", KanaType.SENTENCE),
            KanaCharacter("いいですね！", "That's good!", "ii desu ne!", KanaType.SENTENCE),
            KanaCharacter("そうですか。", "I see / Is that so?", "sou desu ka", KanaType.SENTENCE)
        )),
        Chapter(310, "At a Restaurant", KanaType.SENTENCE, listOf(
            KanaCharacter("ちゅうもんをおねがいします。", "I'd like to order, please.", "chuumon o onegaishimasu", KanaType.SENTENCE),
            KanaCharacter("おすすめはなんですか？", "What do you recommend?", "osusume wa nan desu ka?", KanaType.SENTENCE),
            KanaCharacter("おかいけいをおねがいします。", "Check, please.", "okaikei o onegaishimasu", KanaType.SENTENCE),
            KanaCharacter("クレジットカードはつかえますか？", "Do you accept credit cards?", "kurejitto kaado wa tsukaemasu ka?", KanaType.SENTENCE),
            KanaCharacter("えいごのメニューはありますか？", "Is there an English menu?", "eigo no menyuu wa arimasu ka?", KanaType.SENTENCE)
        )),
        Chapter(311, "Shopping Phrases", KanaType.SENTENCE, listOf(
            KanaCharacter("みているだけです。", "I'm just looking.", "miteiru dake desu", KanaType.SENTENCE),
            KanaCharacter("これをみせてください。", "Please show me this.", "kore o misete kudasai", KanaType.SENTENCE),
            KanaCharacter("ほかのいろはありますか？", "Do you have any other colors?", "hoka no iro wa arimasu ka?", KanaType.SENTENCE),
            KanaCharacter("ちょっとたかいです。", "It's a bit expensive.", "chotto takai desu", KanaType.SENTENCE),
            KanaCharacter("やすくなりますか？", "Can you make it cheaper?", "yasukunaru ka?", KanaType.SENTENCE)
        )),
        Chapter(312, "Health & Feelings", KanaType.SENTENCE, listOf(
            KanaCharacter("つかれました。", "I'm tired.", "tsukaremashita", KanaType.SENTENCE),
            KanaCharacter("おなかがいたいです。", "My stomach hurts.", "onaka ga itai desu", KanaType.SENTENCE),
            KanaCharacter("ねむいです。", "I'm sleepy.", "nemui desu", KanaType.SENTENCE),
            KanaCharacter("しあわせです。", "I'm happy.", "shiawase desu", KanaType.SENTENCE),
            KanaCharacter("かなしいです。", "I'm sad.", "kanashii desu", KanaType.SENTENCE)
        ))
    )

    val allChapters = hiraganaChapters + katakanaChapters + vocabularyChapters + sentenceChapters
}
