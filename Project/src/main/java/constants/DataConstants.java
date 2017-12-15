package main.java.constants;

import one.util.streamex.IntStreamEx;

public final class DataConstants {
    private DataConstants(){
        throw new UnsupportedOperationException("Illegal access to private constructor!");
    }

    // Positive strings
    private final static String CYRILLIC_CHARACTERS   = "АБВГЭЮЯ";
    private final static String DIFFERENT_CASES       = "aAzZgG";
    private final static String ONE_CHARACTER         = "H";
    private final static String NUMERIC_STRING        = "150";
    private final static String HTML_INJECTION_STRING = "<p\\>";
    private final static String EMPTY_STRING          = "";
    private final static String SPACE_STRING          = " ";
    private final static String CHARS_256_STRING = "ЎўЋ¤Ґ¦§Ё©Є«¬\u00AD®Ї°±Ііґµ¶·ё┬№є»јЅѕїАБВГДЕЖЗИЙКЛМНОПРСТУФХ" +
        "ЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]" +
        "^_`abcdefghijklmnopqrstuvwxyz{|}ЂЃ‚ѓ„…†‡€‰Љ‹ЊЌЋЏђ‘’“”•–—™љ›њќћџ☺☻♥♢♧♤•◘○◙♂♀♪♫☼▻◅↕¶§▒▓│┤╡" +
        "╢╖╕╣║╗╝╜╛┐└";
    public final static String[] REQ_POSITIVE_STRS = {ONE_CHARACTER, CYRILLIC_CHARACTERS, NUMERIC_STRING,
        DIFFERENT_CASES, HTML_INJECTION_STRING, CHARS_256_STRING};
    public final static String[] NO_REQ_POSITIVE_STRS = {EMPTY_STRING, SPACE_STRING, ONE_CHARACTER,
        CYRILLIC_CHARACTERS, NUMERIC_STRING, DIFFERENT_CASES, HTML_INJECTION_STRING, CHARS_256_STRING};
    public final static String[] NO_REQ_512_POSITIVE_STRS = {EMPTY_STRING, SPACE_STRING, ONE_CHARACTER,
        CYRILLIC_CHARACTERS, NUMERIC_STRING, DIFFERENT_CASES, HTML_INJECTION_STRING,
        CHARS_256_STRING.concat(CHARS_256_STRING)};

    // Negative strings
    private final static String CHARS_257_STRING = CHARS_256_STRING + "┬";
    public final static String[] REQ_NEGATIVE_STRS = {EMPTY_STRING, SPACE_STRING, CHARS_257_STRING};
    public final static String[] NO_REQ_NEGATIVE_STRS = {CHARS_257_STRING};
    public final static String[] NO_REQ_512_NEGATIVE_STRS = {CHARS_257_STRING.concat(CHARS_256_STRING)};

    // Positive paths
    private final static String  DOS_FILE_PATH    = "C:\\Olympus\\Autotest\\File_path\\.hgingnore";
    private final static String  PRODOS_FILE_PATH = "Olympus\\Autotest\\File_path\\.hgingnore";
    private final static String  ADMIN_SHARA_PATH = "C$\\Olympus\\Autotest\\File_path";
    private final static String CHARS_256_PATH = "ff\\Olympus\\Autotest\\File_path\\hgingnoreЎўЋ¤Ґ¦§Ё©Є¬®Ї°±Ііґµ¶ё┬" +
        "№єјЅѕїАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя!#$%&()+,-.0123456789;=@ABCDEFGHIJKL" +
        "MNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{}ЂЃ‚ѓ„…†‡€‰Љ‹ЊЌЋЏђ•–—™љ›њќћџ☺☻♥♢♧♤•◘○◙♂♀♪♫☼";
    private final static String CHARS_257_PATH = CHARS_256_PATH + "$";
    public final static String[] POSITIVE_PATHS   = {CYRILLIC_CHARACTERS, DIFFERENT_CASES, ONE_CHARACTER,
        NUMERIC_STRING, DOS_FILE_PATH, PRODOS_FILE_PATH, ADMIN_SHARA_PATH, CHARS_256_PATH};
    public final static String[] NOT_DOS_POSITIVE_PATHS   = {CYRILLIC_CHARACTERS, DIFFERENT_CASES, ONE_CHARACTER,
        NUMERIC_STRING, PRODOS_FILE_PATH, ADMIN_SHARA_PATH, CHARS_256_PATH.concat(CHARS_256_PATH)};

    // Negative paths
    private final static String  BACKSLASH_CHAR   = "\\";
    private final static String  GREATER_CHAR     = ">";
    private final static String  LES_CHAR         = "<";
    private final static String  COLON_CHAR       = ":";
    private final static String  QUOTES_CHAR      = "\"";
    private final static String  LOGIC_OR_CHAR    = "|";
    private final static String  ASTERISK_CHAR    = "*";
    private final static String  QUESTION_MARK    = "?";
    private final static String  DOUBLE_BACKSLASH_PATH   = "C:\\Olympus\\Autotest\\\\";
    public final static String[] NEGATIVE_PATHS = {BACKSLASH_CHAR, GREATER_CHAR, LES_CHAR, COLON_CHAR, QUOTES_CHAR,
        LOGIC_OR_CHAR, ASTERISK_CHAR, QUESTION_MARK, DOUBLE_BACKSLASH_PATH, CHARS_257_PATH};
    public final static String[] NOT_DOS_NEGATIVE_PATHS = {BACKSLASH_CHAR, GREATER_CHAR, LES_CHAR, COLON_CHAR, QUOTES_CHAR,
        LOGIC_OR_CHAR, ASTERISK_CHAR, QUESTION_MARK, DOUBLE_BACKSLASH_PATH, DOS_FILE_PATH,
        CHARS_256_PATH.concat(CHARS_257_PATH)};

    // Urls
    private final static String NUMS_URL = "/0123456789/";
    private final static String CHARS_64_URL = "/azqwsxcderfvbgtyhnmjuiklopaZQWSXCDERFVBGTYHNMJUIKLOP@%_+.~#?&=d";
    private final static String CHARS_512_URL = IntStreamEx.range(8).boxed()
        .map(num -> CHARS_64_URL)
        .joining();

    private final static String  DOUBLE_SLASH_URL   = "/Olympus/Autotest//";
    private final static String CHARS_513_URL = CHARS_512_URL + "/";
    public final static String[] POSITIVE_URLS   = {DIFFERENT_CASES, ONE_CHARACTER, NUMS_URL, CHARS_512_URL};
    public final static String[] NEGATIVE_URLS = {EMPTY_STRING, SPACE_STRING, BACKSLASH_CHAR, GREATER_CHAR, LES_CHAR,
        COLON_CHAR, QUOTES_CHAR, LOGIC_OR_CHAR, ASTERISK_CHAR, CYRILLIC_CHARACTERS, DOUBLE_SLASH_URL,
        CHARS_513_URL};

    // Positive textArea's strings
    private final static String CHARS_16384_STRING = IntStreamEx.range(64).boxed()
        .map(num -> CHARS_256_STRING)
        .joining();
    public final static String[] REQ_POSITIVE_AREA_STRS = {ONE_CHARACTER, CYRILLIC_CHARACTERS, NUMERIC_STRING,
        DIFFERENT_CASES, HTML_INJECTION_STRING, CHARS_16384_STRING};

    // Negative textArea's strings
    private final static String CHARS_16385_STRING = CHARS_16384_STRING + "f";
    public final static String[] REQ_NEGATIVE_AREA_STRS = {EMPTY_STRING, SPACE_STRING, CHARS_16385_STRING};

    // Numeric values
    private static Long NEGATIVE_NUM  = -1L;
    private static Long ZERO_NUM      = 0L;
    private static Long ONE_NUM       = 1L;
    private static Long SIMPLE_NUM    = 93L;
    private static Long HUNDRER_NUM   = 100L;
    private static Long HUNDRER_PLUS_NUM   = 101L;
    private static Long BYTE_NUM      = 255L;
    private static Long BYTE_PLUS_NUM = 256L;
    private static Long TWO_BYTE_NUM  = 65535L;
    private static Long TWO_BYTE_PLUS_NUM = 65536L;
    private static Long FOUR_BYTE_NUM = 4294967295L;
    private static Long FOUR_BYTE_PLUS_NUM = 4294967296L;
    private static Long BIG_NUM = 333496734343296L;

    private static String MINUS_NUM = "-";
    private static String PLUS_NUM = "+";
    private static String DOT_NUM = ".";
    private static String COMMA_NUM = ",";
    private static String LES_ONE_NUM = "0.958";
    private static String EIGHT_BYTE_NUM = "18446744073709553664";
    private static String EIGHT_BYTE_PLUS_NUM = "18446744073709553665";
    private static String DOUBLE_NUM = "65.245";


    public static Long[] PERCENT_POSITIVE_NUMS = {ONE_NUM, SIMPLE_NUM, HUNDRER_NUM};
    public static Long[] PERCENT_NEGATIVE_NUMS = {ZERO_NUM, NEGATIVE_NUM, HUNDRER_PLUS_NUM, BIG_NUM};
    public static Long[] BYTE_POSITIVE_NUMS = {ONE_NUM, SIMPLE_NUM, BYTE_NUM};
    public static Long[] BYTE_NEGATIVE_NUMS = {NEGATIVE_NUM, ZERO_NUM, BYTE_PLUS_NUM, BIG_NUM};
    public static Long[] TWO_BYTE_POSITIVE_NUMS = {ONE_NUM, SIMPLE_NUM, TWO_BYTE_NUM};
    public static Long[] TWO_BYTE_NEGATIVE_NUMS = {NEGATIVE_NUM, ZERO_NUM, TWO_BYTE_PLUS_NUM, BIG_NUM};
    public static Long[] FOUR_BYTE_POSITIVE_NUMS = {ONE_NUM, SIMPLE_NUM, FOUR_BYTE_NUM};
    public static Long[] FOUR_BYTE_NIL_POSITIVE_NUMS = {ZERO_NUM, ONE_NUM, SIMPLE_NUM, FOUR_BYTE_NUM};
    public static Long[] FOUR_BYTE_NEGATIVE_NUMS = {NEGATIVE_NUM, ZERO_NUM, FOUR_BYTE_PLUS_NUM, BIG_NUM};
    public static Long[] FOUR_BYTE_NIL_NEGATIVE_NUMS = {NEGATIVE_NUM, FOUR_BYTE_PLUS_NUM, BIG_NUM};
    public static Long[] EIGHT_BYTE_NEGATIVE_NUMS = {NEGATIVE_NUM, ZERO_NUM};

    public static String[] POSITIVE_NUM_STRS = {DOUBLE_NUM};
    public static String[] EIGHT_BYTE_POSITIVE_STRS = {DOUBLE_NUM, EIGHT_BYTE_NUM};
    public static String[] NEGATIVE_NUM_STRS = {MINUS_NUM, PLUS_NUM, DOT_NUM, COMMA_NUM, LES_ONE_NUM, DIFFERENT_CASES};
    public static String[] NIL_NEGATIVE_NUM_STRS = {MINUS_NUM, PLUS_NUM, DOT_NUM, COMMA_NUM, DIFFERENT_CASES};
    public static String[] EIGHT_BYTE_NEGATIVE_STRS = {MINUS_NUM, PLUS_NUM, DOT_NUM, COMMA_NUM, LES_ONE_NUM,
        DIFFERENT_CASES, EIGHT_BYTE_PLUS_NUM};

    // Positive registry strings
    private final static String CHARS_255_STRING = CHARS_256_STRING.replaceFirst(".$", "");
    private final static String CHARS_16383_STRING = CHARS_16384_STRING.replaceFirst(".$", "");
    private final static String DWORD_STRING = IntStreamEx.range(4).boxed()
        .map(num -> CHARS_16384_STRING)
        .joining();
    private final static String SEMI_DWORD_STRING = CHARS_16384_STRING.concat(CHARS_16384_STRING);

    public final static String[] REGISTRY_KEY_POSITIVE_STRS = {ONE_CHARACTER, CYRILLIC_CHARACTERS, NUMERIC_STRING,
        DIFFERENT_CASES, HTML_INJECTION_STRING, CHARS_255_STRING};
    public final static String[] REGISTRY_VALUE_NAME_POSITIVE_STRS = {EMPTY_STRING, SPACE_STRING, ONE_CHARACTER,
        CYRILLIC_CHARACTERS, NUMERIC_STRING, DIFFERENT_CASES, HTML_INJECTION_STRING, CHARS_16383_STRING};
    public final static String[] REGISTRY_VALUE_POSITIVE_STRS = {EMPTY_STRING, SPACE_STRING, ONE_CHARACTER,
        CYRILLIC_CHARACTERS, NUMERIC_STRING, DIFFERENT_CASES, HTML_INJECTION_STRING, SEMI_DWORD_STRING};

    // Negative registry strings

    public final static String[] REGISTRY_KEY_NEGATIVE_STRS = {EMPTY_STRING, SPACE_STRING, BACKSLASH_CHAR,
        CHARS_256_STRING};
    public final static String[] REGISTRY_VALUE_NAME_NEGATIVE_STRS = {CHARS_16384_STRING};
    public final static String[] REGISTRY_VALUE_NEGATIVE_STRS = {DWORD_STRING};
}