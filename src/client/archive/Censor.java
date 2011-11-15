package client.archive;

// Fully Refactored - gamerx - 14.1.11
// Updated - gamerx - 15.11.11

import client.data.JagexArchive;
import client.data.Stream;

/**
 * Provides handling for text chat censor.
 * Loads strings, etc from cache.
 * This should be removed, as it just loads configuration, but does not use them.
 *
 * @knownas Class34
 * @author gamerx
 */
public final class Censor {

    private static int[] fragmentsEnc;
    private static char[][] badEnc;
    private static byte[][][] badEncByte;
    private static char[][] domainEnc;
    private static char[][] tldList;
    private static int[] tldArray;
    private static final String[] exceptions = {
        "cook", "cook's", "cooks", "seeks", "sheet", "woop", "woops", "faq", "noob", "noobs"
    };

    /**
     * Unpack configuration archive.
     *
     * @param jagexArchive The archive instance.
     */
    public static void loadConfig(JagexArchive jagexArchive) {
        Stream fragmentsEncStream = new Stream(jagexArchive.getDataForName("fragmentsenc.txt"));
        Stream badEncStream = new Stream(jagexArchive.getDataForName("badenc.txt"));
        Stream domainEncStream = new Stream(jagexArchive.getDataForName("domainenc.txt"));
        Stream tldEncStream = new Stream(jagexArchive.getDataForName("tldlist.txt"));
        readValues(fragmentsEncStream, badEncStream, domainEncStream, tldEncStream);
    }

    /**
     * Read values from each stream.
     *
     * @param fragmentsEnc Stream for text fragments.
     * @param badEnc Stream for bad words.
     * @param domainEnc Stream for domains.
     * @param tldEnc Stram for TLD's.
     */
    private static void readValues(Stream fragmentsEnc, Stream badEnc, Stream domainEnc, Stream tldEnc) {
        readBadEnc(badEnc);
        readDomainEnc(domainEnc);
        readFragmentsEnc(fragmentsEnc);
        readTldList(tldEnc);
    }

    private static void readTldList(Stream stream) {
        int length = stream.readDWord();
        tldList = new char[length][];
        tldArray = new int[length];
        for (int id = 0; id < length; id++) {
            tldArray[id] = stream.readUnsignedByte();
            char tld[] = new char[stream.readUnsignedByte()];
            for (int charID = 0; charID < tld.length; charID++) {
                tld[charID] = (char) stream.readUnsignedByte();
            }
            tldList[id] = tld;
        }
    }

    private static void readBadEnc(Stream stream) {
        int length = stream.readDWord();
        badEnc = new char[length][];
        badEncByte = new byte[length][][];
        initBadEnc(stream, badEnc, badEncByte);
    }

    private static void readDomainEnc(Stream stream) {
        int length = stream.readDWord();
        domainEnc = new char[length][];
        initDomainEnc(domainEnc, stream);
    }

    private static void readFragmentsEnc(Stream stream) {
        fragmentsEnc = new int[stream.readDWord()];
        for (int id = 0; id < fragmentsEnc.length; id++) {
            fragmentsEnc[id] = stream.readUnsignedWord();
        }
    }

    private static void initBadEnc(Stream stream, char ac[][], byte badEncComplexArray[][][]) {
        for (int id = 0; id < ac.length; id++) {
            char val[] = new char[stream.readUnsignedByte()];
            for (int charID = 0; charID < val.length; charID++) {
                val[charID] = (char) stream.readUnsignedByte();
            }
            ac[id] = val;
            byte abyte1[][] = new byte[stream.readUnsignedByte()][2];
            for (int l = 0; l < abyte1.length; l++) {
                abyte1[l][0] = (byte) stream.readUnsignedByte();
                abyte1[l][1] = (byte) stream.readUnsignedByte();
            }
            if (abyte1.length > 0) {
                badEncComplexArray[id] = abyte1;
            }
        }
    }

    private static void initDomainEnc(char ac[][], Stream stream) {
        for (int j = 0; j < ac.length; j++) {
            char val[] = new char[stream.readUnsignedByte()];
            for (int k = 0; k < val.length; k++) {
                val[k] = (char) stream.readUnsignedByte();
            }
            ac[j] = val;
        }
    }

    private static void stripNonlegalChars(char chars[]) {
        int writePos = 0;
        for (int readPos = 0; readPos < chars.length; readPos++) {
            if (charIsLegal(chars[readPos])) {
                chars[writePos] = chars[readPos];
            } else {
                chars[writePos] = ' ';
            }
            if (writePos == 0 || chars[writePos] != ' ' || chars[writePos - 1] != ' ') {
                writePos++;
            }
        }
        for (int padPos = writePos; padPos < chars.length; padPos++) {
            chars[padPos] = ' ';
        }
    }

    private static boolean charIsLegal(char c) {
        return c >= ' ' && c <= '\177' || c == ' ' || c == '\n' || c == '\t' || c == '\243' || c == '\u20AC';
    }

    public static String doCensor(String string) {
//        char chars[] = string.toCharArray();
//        stripNonlegalChars(chars);
//        String trimmed = (new String(chars)).trim();
//        chars = trimmed.toLowerCase().toCharArray();
//        String trimmedLowerCase = trimmed.toLowerCase();
//        removeTLDs(chars);
//        processBadEnc(chars);
//        removeEmails(chars);
//        censorLongNumbers(chars);
//        for (int j = 0; j < exceptions.length; j++) {
//            for (int exceptionPos = -1; (exceptionPos = trimmedLowerCase.indexOf(exceptions[j], exceptionPos + 1)) != -1;) {
//                char exceptionChars[] = exceptions[j].toCharArray();
//                System.arraycopy(exceptionChars, 0, chars, exceptionPos, exceptionChars.length);
//            }
//        }
//        restoreOriginalCase(trimmed.toCharArray(), chars);
//        fixCases(chars);
//        return (new String(ac)).trim(); //xxx chat filter, return s to avoid
        return string; 
    }

    private static void restoreOriginalCase(char baseText[], char censoredText[]) {
        for (int pos = 0; pos < baseText.length; pos++) {
            if (censoredText[pos] != '*' && isUpperCaseLetter(baseText[pos])) {
                censoredText[pos] = baseText[pos];
            }
        }
    }

    private static void fixCases(char chars[]) {
        boolean skipToNextUppercase = true;
        for (int pos = 0; pos < chars.length; pos++) {
            char c = chars[pos];
            if (isLetter(c)) {
                if (skipToNextUppercase) {
                    if (isLowerCaseLetter(c)) {
                        skipToNextUppercase = false;
                    }
                } else if (isUpperCaseLetter(c)) {
                    chars[pos] = (char) ((c + 97) - 65);
                }
            } else {
                skipToNextUppercase = true;
            }
        }
    }

    private static void processBadEnc(char text[]) {
        for (int i = 0; i < 2; i++) {
            for (int j = badEnc.length - 1; j >= 0; j--) {
                censorStringIn(badEncByte[j], text, badEnc[j]);
            }
        }
    }

    private static void removeEmails(char ac[]) {
        char textClone[] = ac.clone();
        char atSignString[] = {
            '(', 'a', ')'
        };
        censorStringIn(null, textClone, atSignString);
        char textClone2[] = ac.clone();
        char dotString[] = {
            'd', 'o', 't'
        };
        censorStringIn(null, textClone2, dotString);
        for (int id = domainEnc.length - 1; id >= 0; id--) {
            removeEmailFrom(ac, domainEnc[id], textClone2, textClone);
        }
    }

    private static void removeEmailFrom(char text[], char domain[], char dotRemovedText[], char atSignRemovedText[]) {
        if (domain.length > text.length) {
            return;
        }
        int incrementAmount;
        for (int censorStart = 0; censorStart <= text.length - domain.length; censorStart += incrementAmount) {
            int censorEnd = censorStart;
            int numCharsFound = 0;
            incrementAmount = 1;
            while (censorEnd < text.length) {
                int incCensorEndBy = 0;
                char charAtCensorEnd = text[censorEnd];
                char nextChar = '\0';
                if (censorEnd + 1 < text.length) {
                    nextChar = text[censorEnd + 1];
                }
                if (numCharsFound < domain.length && (incCensorEndBy = checkForCharBasicLeetSpeak(charAtCensorEnd, domain[numCharsFound], nextChar)) > 0) {
                    censorEnd += incCensorEndBy;
                    numCharsFound++;
                    continue;
                }
                if (numCharsFound == 0) {
                    break;
                }
                if ((incCensorEndBy = checkForCharBasicLeetSpeak(charAtCensorEnd, domain[numCharsFound - 1], nextChar)) > 0) {
                    censorEnd += incCensorEndBy;
                    if (numCharsFound == 1) {
                        incrementAmount++;
                    }
                    continue;
                }
                if (numCharsFound >= domain.length || !isNotAlphanumeric(charAtCensorEnd)) {
                    break;
                }
                censorEnd++;
            }
            if (numCharsFound >= domain.length) {
                boolean doCensor = false;
                int test1 = isAtSignBeforeDomain(text, atSignRemovedText, censorStart);
                int test2 = isDotAfterDomain(dotRemovedText, censorEnd - 1, text);
                if (test1 > 2 || test2 > 2) {
                    doCensor = true;
                }
                if (doCensor) {
                    for (int pos = censorStart; pos < censorEnd; pos++) {
                        text[pos] = '*';
                    }
                }
            }
        }
    }

    private static int isAtSignBeforeDomain(char text[], char fakeAtRemoveText[], int domainStart) {
        if (domainStart == 0) {
            return 2;
        }
        for (int pos = domainStart - 1; pos >= 0; pos--) {
            if (!isNotAlphanumeric(text[pos])) {
                break;
            }
            if (text[pos] == '@') {
                return 3;
            }
        }
        int numCensoredChars = 0;
        for (int pos = domainStart - 1; pos >= 0; pos--) {
            if (!isNotAlphanumeric(fakeAtRemoveText[pos])) {
                break;
            }
            if (fakeAtRemoveText[pos] == '*') {
                numCensoredChars++;
            }
        }
        if (numCensoredChars >= 3) {
            return 4;
        }
        return !isNotAlphanumeric(text[domainStart - 1]) ? 0 : 1;
    }

    private static int isDotAfterDomain(char dotRemovedText[], int domainEnd, char text[]) {
        if (domainEnd + 1 == text.length) {
            return 2;
        }
        for (int pos = domainEnd + 1; pos < text.length; pos++) {
            if (!isNotAlphanumeric(text[pos])) {
                break;
            }
            if (text[pos] == '.' || text[pos] == ',') {
                return 3;
            }
        }
        int numCensoredChars = 0;
        for (int pos = domainEnd + 1; pos < text.length; pos++) {
            if (!isNotAlphanumeric(dotRemovedText[pos])) {
                break;
            }
            if (dotRemovedText[pos] == '*') {
                numCensoredChars++;
            }
        }
        if (numCensoredChars >= 3) {
            return 4;
        }
        return !isNotAlphanumeric(text[domainEnd + 1]) ? 0 : 1;
    }

    private static void removeTLDs(char text[]) {
        char textClone[] = (char[]) text.clone();
        char dotString[] = {
            'd', 'o', 't'
        };
        censorStringIn(null, textClone, dotString);
        char textClone2[] = (char[]) text.clone();
        char slashString[] = {
            's', 'l', 'a', 's', 'h'
        };
        censorStringIn(null, textClone2, slashString);
        for (int id = 0; id < tldList.length; id++) {
            removeTldFrom(textClone2, tldList[id], tldArray[id], textClone, text);
        }
    }

    private static void removeTldFrom(char slashRemovedText[], char tld[], int testType, char dotRemovedText[], char rawText[]) {
        if (tld.length > rawText.length) {
            return;
        }
        int incrementAmount;
        for (int pos = 0; pos <= rawText.length - tld.length; pos += incrementAmount) {
            int censorEnd = pos;
            int numCharactersFound = 0;
            incrementAmount = 1;
            while (censorEnd < rawText.length) {
                int incrementCensorEndBy = 0;
                char rawTextChar = rawText[censorEnd];
                char nextChar = '\0';
                if (censorEnd + 1 < rawText.length) {
                    nextChar = rawText[censorEnd + 1];
                }
                if (numCharactersFound < tld.length && (incrementCensorEndBy = checkForCharBasicLeetSpeak(rawTextChar, tld[numCharactersFound], nextChar)) > 0) {
                    censorEnd += incrementCensorEndBy;
                    numCharactersFound++;
                    continue;
                }
                if (numCharactersFound == 0) {
                    break;
                }
                if ((incrementCensorEndBy = checkForCharBasicLeetSpeak(rawTextChar, tld[numCharactersFound - 1], nextChar)) > 0) {
                    censorEnd += incrementCensorEndBy;
                    if (numCharactersFound == 1) {
                        incrementAmount++;
                    }
                    continue;
                }
                if (numCharactersFound >= tld.length || !isNotAlphanumeric(rawTextChar)) {
                    break;
                }
                censorEnd++;
            }
            if (numCharactersFound >= tld.length) {
                boolean censor = false;
                int test1 = isDotBeforeTld(rawText, pos, dotRemovedText);
                int test2 = isSlashAfterTld(rawText, slashRemovedText, censorEnd - 1);
                if (testType == 1 && test1 > 0 && test2 > 0) {
                    censor = true;
                }
                if (testType == 2 && (test1 > 2 && test2 > 0 || test1 > 0 && test2 > 2)) {
                    censor = true;
                }
                if (testType == 3 && test1 > 0 && test2 > 2) {
                    censor = true;
                }
                if (censor) {
                    int start = pos;
                    int end = censorEnd - 1;
                    if (test1 > 2) {
                        if (test1 == 4) {
                            boolean breakOnNextNonCensor = false;
                            for (int p = start - 1; p >= 0; p--) {
                                if (breakOnNextNonCensor) {
                                    if (dotRemovedText[p] != '*') {
                                        break;
                                    }
                                    start = p;
                                } else if (dotRemovedText[p] == '*') {
                                    start = p;
                                    breakOnNextNonCensor = true;
                                }
                            }
                        }
                        boolean breakOnNextNonAlphanumeric = false;
                        for (int i3 = start - 1; i3 >= 0; i3--) {
                            if (breakOnNextNonAlphanumeric) {
                                if (isNotAlphanumeric(rawText[i3])) {
                                    break;
                                }
                                start = i3;
                            } else if (!isNotAlphanumeric(rawText[i3])) {
                                breakOnNextNonAlphanumeric = true;
                                start = i3;
                            }
                        }
                    }
                    if (test2 > 2) {
                        if (test2 == 4) {
                            boolean breakOnNextNonCensor = false;
                            for (int j3 = end + 1; j3 < rawText.length; j3++) {
                                if (breakOnNextNonCensor) {
                                    if (slashRemovedText[j3] != '*') {
                                        break;
                                    }
                                    end = j3;
                                } else if (slashRemovedText[j3] == '*') {
                                    end = j3;
                                    breakOnNextNonCensor = true;
                                }
                            }
                        }
                        boolean breakOnNextNonAlphanumeric = false;
                        for (int k3 = end + 1; k3 < rawText.length; k3++) {
                            if (breakOnNextNonAlphanumeric) {
                                if (isNotAlphanumeric(rawText[k3])) {
                                    break;
                                }
                                end = k3;
                            } else if (!isNotAlphanumeric(rawText[k3])) {
                                breakOnNextNonAlphanumeric = true;
                                end = k3;
                            }
                        }
                    }
                    for (int k2 = start; k2 <= end; k2++) {
                        rawText[k2] = '*';
                    }
                }
            }
        }
    }

    private static int isDotBeforeTld(char rawtext[], int startPos, char slashRemovedText[]) {
        if (startPos == 0) {
            return 2;
        }
        for (int k = startPos - 1; k >= 0; k--) {
            if (!isNotAlphanumeric(rawtext[k])) {
                break;
            }
            if (rawtext[k] == ',' || rawtext[k] == '.') {
                return 3;
            }
        }

        int numCensored = 0;
        for (int pos = startPos - 1; pos >= 0; pos--) {
            if (!isNotAlphanumeric(slashRemovedText[pos])) {
                break;
            }
            if (slashRemovedText[pos] == '*') {
                numCensored++;
            }
        }
        if (numCensored >= 3) {
            return 4;
        }
        return !isNotAlphanumeric(rawtext[startPos - 1]) ? 0 : 1;
    }

    private static int isSlashAfterTld(char rawText[], char slashRemovedText[], int startPos) {
        if (startPos + 1 == rawText.length) {
            return 2;
        }
        for (int j = startPos + 1; j < rawText.length; j++) {
            if (!isNotAlphanumeric(rawText[j])) {
                break;
            }
            if (rawText[j] == '\\' || rawText[j] == '/') {
                return 3;
            }
        }
        int numCensored = 0;
        for (int l = startPos + 1; l < rawText.length; l++) {
            if (!isNotAlphanumeric(slashRemovedText[l])) {
                break;
            }
            if (slashRemovedText[l] == '*') {
                numCensored++;
            }
        }

        if (numCensored >= 5) {
            return 4;
        }
        return !isNotAlphanumeric(rawText[startPos + 1]) ? 0 : 1;
    }

    public static void censorStringIn(byte complexArray[][], char ac[], char ac1[]) {
        if (ac1.length > ac.length) {
            return;
        }
        int j;
        for (int k = 0; k <= ac.length - ac1.length; k += j) {
            int l = k;
            int i1 = 0;
            int j1 = 0;
            j = 1;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            while (l < ac.length && (!flag2 || !flag3)) {
                int k1 = 0;
                char c = ac[l];
                char c2 = '\0';
                if (l + 1 < ac.length) {
                    c2 = ac[l + 1];
                }
                if (i1 < ac1.length && (k1 = checkForCharAdvancedLeetspeak(c2, c, ac1[i1])) > 0) {
                    if (k1 == 1 && isDigit(c)) {
                        flag2 = true;
                    }
                    if (k1 == 2 && (isDigit(c) || isDigit(c2))) {
                        flag2 = true;
                    }
                    l += k1;
                    i1++;
                    continue;
                }
                if (i1 == 0) {
                    break;
                }
                if ((k1 = checkForCharAdvancedLeetspeak(c2, c, ac1[i1 - 1])) > 0) {
                    l += k1;
                    if (i1 == 1) {
                        j++;
                    }
                    continue;
                }
                if (i1 >= ac1.length || !isDigitOrSymbol(c)) {
                    break;
                }
                if (isNotAlphanumeric(c) && c != '\'') {
                    flag1 = true;
                }
                if (isDigit(c)) {
                    flag3 = true;
                }
                l++;
                if ((++j1 * 100) / (l - k) > 90) {
                    break;
                }
            }
            if (i1 >= ac1.length && (!flag2 || !flag3)) {
                boolean flag4 = true;
                if (!flag1) {
                    char c1 = ' ';
                    if (k - 1 >= 0) {
                        c1 = ac[k - 1];
                    }
                    char c3 = ' ';
                    if (l < ac.length) {
                        c3 = ac[l];
                    }
                    byte byte0 = getCharCodeOfSomeSort(c1);
                    byte byte1 = getCharCodeOfSomeSort(c3);
                    if (complexArray != null && charCodesAllowCensoring(byte0, complexArray, byte1)) {
                        flag4 = false;
                    }
                } else {
                    boolean flag5 = false;
                    boolean flag6 = false;
                    if (k - 1 < 0 || isNotAlphanumeric(ac[k - 1]) && ac[k - 1] != '\'') {
                        flag5 = true;
                    }
                    if (l >= ac.length || isNotAlphanumeric(ac[l]) && ac[l] != '\'') {
                        flag6 = true;
                    }
                    if (!flag5 || !flag6) {
                        boolean flag7 = false;
                        int k2 = k - 2;
                        if (flag5) {
                            k2 = k;
                        }
                        for (; !flag7 && k2 < l; k2++) {
                            if (k2 >= 0 && (!isNotAlphanumeric(ac[k2]) || ac[k2] == '\'')) {
                                char ac2[] = new char[3];
                                int j3;
                                for (j3 = 0; j3 < 3; j3++) {
                                    if (k2 + j3 >= ac.length || isNotAlphanumeric(ac[k2 + j3]) && ac[k2 + j3] != '\'') {
                                        break;
                                    }
                                    ac2[j3] = ac[k2 + j3];
                                }

                                boolean flag8 = true;
                                if (j3 == 0) {
                                    flag8 = false;
                                }
                                if (j3 < 3 && k2 - 1 >= 0 && (!isNotAlphanumeric(ac[k2 - 1]) || ac[k2 - 1] == '\'')) {
                                    flag8 = false;
                                }
                                if (flag8 && !charsMatchFragment(ac2)) {
                                    flag7 = true;
                                }
                            }
                        }

                        if (!flag7) {
                            flag4 = false;
                        }
                    }
                }
                if (flag4) {
                    int l1 = 0;
                    int i2 = 0;
                    int j2 = -1;
                    for (int l2 = k; l2 < l; l2++) {
                        if (isDigit(ac[l2])) {
                            l1++;
                        } else if (isLetter(ac[l2])) {
                            i2++;
                            j2 = l2;
                        }
                    }

                    if (j2 > -1) {
                        l1 -= l - 1 - j2;
                    }
                    if (l1 <= i2) {
                        for (int i3 = k; i3 < l; i3++) {
                            ac[i3] = '*';
                        }

                    } else {
                        j = 1;
                    }
                }
            }
        }

    }

    private static boolean charCodesAllowCensoring(byte charCodeOfBeforeStart, byte complexArray[][], byte charCodeOfAtEnd) {
        int arrayPos = 0;
        if (complexArray[arrayPos][0] == charCodeOfBeforeStart && complexArray[arrayPos][1] == charCodeOfAtEnd) {
            return true;
        }
        int len = complexArray.length - 1;
        if (complexArray[len][0] == charCodeOfBeforeStart && complexArray[len][1] == charCodeOfAtEnd) {
            return true;
        }
        do {
            int newSpot = (arrayPos + len) / 2;
            if (complexArray[newSpot][0] == charCodeOfBeforeStart && complexArray[newSpot][1] == charCodeOfAtEnd) {
                return true;
            }
            if (charCodeOfBeforeStart < complexArray[newSpot][0] || charCodeOfBeforeStart == complexArray[newSpot][0] && charCodeOfAtEnd < complexArray[newSpot][1]) {
                len = newSpot;
            } else {
                arrayPos = newSpot;
            }
        } while (arrayPos != len && arrayPos + 1 != len);
        return false;
    }

    private static int checkForCharBasicLeetSpeak(char firstChar, char find, char secondChar) {
        if (find == firstChar) {
            return 1;
        }
        if (find == 'o' && firstChar == '0') {
            return 1;
        }
        if (find == 'o' && firstChar == '(' && secondChar == ')') {
            return 2;
        }
        if (find == 'c' && (firstChar == '(' || firstChar == '<' || firstChar == '[')) {
            return 1;
        }
        if (find == 'e' && firstChar == '\u20AC') {
            return 1;
        }
        if (find == 's' && firstChar == '$') {
            return 1;
        }
        return find != 'l' || firstChar != 'i' ? 0 : 1;
    }

    private static int checkForCharAdvancedLeetspeak(char secondChar, char firstChar, char find) {
        if (find == firstChar) {
            return 1;
        }
        if (find >= 'a' && find <= 'm') {
            if (find == 'a') {
                if (firstChar == '4' || firstChar == '@' || firstChar == '^') {
                    return 1;
                }
                return firstChar != '/' || secondChar != '\\' ? 0 : 2;
            }
            if (find == 'b') {
                if (firstChar == '6' || firstChar == '8') {
                    return 1;
                }
                return (firstChar != '1' || secondChar != '3') && (firstChar != 'i' || secondChar != '3') ? 0 : 2;
            }
            if (find == 'c') {
                return firstChar != '(' && firstChar != '<' && firstChar != '{' && firstChar != '[' ? 0 : 1;
            }
            if (find == 'd') {
                return (firstChar != '[' || secondChar != ')') && (firstChar != 'i' || secondChar != ')') ? 0 : 2;
            }
            if (find == 'e') {
                return firstChar != '3' && firstChar != '\u20AC' ? 0 : 1;
            }
            if (find == 'f') {
                if (firstChar == 'p' && secondChar == 'h') {
                    return 2;
                }
                return firstChar != '\243' ? 0 : 1;
            }
            if (find == 'g') {
                return firstChar != '9' && firstChar != '6' && firstChar != 'q' ? 0 : 1;
            }
            if (find == 'h') {
                return firstChar != '#' ? 0 : 1;
            }
            if (find == 'i') {
                return firstChar != 'y' && firstChar != 'l' && firstChar != 'j' && firstChar != '1' && firstChar != '!' && firstChar != ':' && firstChar != ';' && firstChar != '|' ? 0 : 1;
            }
            if (find == 'j') {
                return 0;
            }
            if (find == 'k') {
                return 0;
            }
            if (find == 'l') {
                return firstChar != '1' && firstChar != '|' && firstChar != 'i' ? 0 : 1;
            }
            if (find == 'm') {
                return 0;
            }
        }
        if (find >= 'n' && find <= 'z') {
            if (find == 'n') {
                return 0;
            }
            if (find == 'o') {
                if (firstChar == '0' || firstChar == '*') {
                    return 1;
                }
                return (firstChar != '(' || secondChar != ')') && (firstChar != '[' || secondChar != ']') && (firstChar != '{' || secondChar != '}') && (firstChar != '<' || secondChar != '>') ? 0 : 2;
            }
            if (find == 'p') {
                return 0;
            }
            if (find == 'q') {
                return 0;
            }
            if (find == 'r') {
                return 0;
            }
            if (find == 's') {
                return firstChar != '5' && firstChar != 'z' && firstChar != '$' && firstChar != '2' ? 0 : 1;
            }
            if (find == 't') {
                return firstChar != '7' && firstChar != '+' ? 0 : 1;
            }
            if (find == 'u') {
                if (firstChar == 'v') {
                    return 1;
                }
                return (firstChar != '\\' || secondChar != '/') && (firstChar != '\\' || secondChar != '|') && (firstChar != '|' || secondChar != '/') ? 0 : 2;
            }
            if (find == 'v') {
                return (firstChar != '\\' || secondChar != '/') && (firstChar != '\\' || secondChar != '|') && (firstChar != '|' || secondChar != '/') ? 0 : 2;
            }
            if (find == 'w') {
                return firstChar != 'v' || secondChar != 'v' ? 0 : 2;
            }
            if (find == 'x') {
                return (firstChar != ')' || secondChar != '(') && (firstChar != '}' || secondChar != '{') && (firstChar != ']' || secondChar != '[') && (firstChar != '>' || secondChar != '<') ? 0 : 2;
            }
            if (find == 'y') {
                return 0;
            }
            if (find == 'z') {
                return 0;
            }
        }
        if (find >= '0' && find <= '9') {
            if (find == '0') {
                if (firstChar == 'o' || firstChar == 'O') {
                    return 1;
                }
                return (firstChar != '(' || secondChar != ')') && (firstChar != '{' || secondChar != '}') && (firstChar != '[' || secondChar != ']') ? 0 : 2;
            }
            if (find == '1') {
                return firstChar != 'l' ? 0 : 1;
            } else {
                return 0;
            }
        }
        if (find == ',') {
            return firstChar != '.' ? 0 : 1;
        }
        if (find == '.') {
            return firstChar != ',' ? 0 : 1;
        }
        if (find == '!') {
            return firstChar != 'i' ? 0 : 1;
        } else {
            return 0;
        }
    }

    private static byte getCharCodeOfSomeSort(char character) {
        if (character >= 'a' && character <= 'z') {
            return (byte) ((character - 97) + 1);
        }
        if (character == '\'') {
            return 28;
        }
        if (character >= '0' && character <= '9') {
            return (byte) ((character - 48) + 29);
        } else {
            return 27;
        }
    }

    private static void censorLongNumbers(char text[]) {
        int nextDigit = 0;
        int nextNonDigit = 0;
        int numInvalidFound = 0;
        int posToCensorFrom = 0;
        while ((nextDigit = getFirstDigit(text, nextNonDigit)) != -1) {
            boolean thereAreAlphanumericCharsBeforeNextDigit = false;
            for (int pos = nextNonDigit; pos >= 0 && pos < nextDigit && !thereAreAlphanumericCharsBeforeNextDigit; pos++) {
                if (!isNotAlphanumeric(text[pos]) && !isDigitOrSymbol(text[pos])) {
                    thereAreAlphanumericCharsBeforeNextDigit = true;
                }
            }

            if (thereAreAlphanumericCharsBeforeNextDigit) {
                numInvalidFound = 0;
            }
            if (numInvalidFound == 0) {
                posToCensorFrom = nextDigit;
            }
            nextNonDigit = getFirstNonDigit(text, nextDigit);
            int valueOfNextAlphaChars = 0;
            for (int pos = nextDigit; pos < nextNonDigit; pos++) {
                valueOfNextAlphaChars = (valueOfNextAlphaChars * 10 + text[pos]) - 48;
            }

            if (valueOfNextAlphaChars > 255 || nextNonDigit - nextDigit > 8) {
                numInvalidFound = 0;
            } else {
                numInvalidFound++;
            }
            if (numInvalidFound == 4) {
                for (int pos = posToCensorFrom; pos < nextNonDigit; pos++) {
                    text[pos] = '*';
                }
                numInvalidFound = 0;
            }
        }
    }

    private static int getFirstDigit(char text[], int startPos) {
        for (int pos = startPos; pos < text.length && pos >= 0; pos++) {
            if (text[pos] >= '0' && text[pos] <= '9') {
                return pos;
            }
        }
        return -1;
    }

    private static int getFirstNonDigit(char text[], int startPos) {
        for (int k = startPos; k < text.length && k >= 0; k++) {
            if (text[k] < '0' || text[k] > '9') {
                return k;
            }
        }
        return text.length;
    }

    private static boolean isNotAlphanumeric(char c) {
        return !isLetter(c) && !isDigit(c);
    }

    private static boolean isDigitOrSymbol(char c) {
        if (c < 'a' || c > 'z') {
            return true;
        }
        return c == 'v' || c == 'x' || c == 'j' || c == 'q' || c == 'z';
    }

    private static boolean isLetter(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isLowerCaseLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    private static boolean isUpperCaseLetter(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private static boolean charsMatchFragment(char chars[]) {
        boolean letterFree = true;
        for (int i = 0; i < chars.length; i++) {
            if (!isDigit(chars[i]) && chars[i] != 0) {
                letterFree = false;
            }
        }

        if (letterFree) {
            return true;
        }
        int code = hashCode(chars);
        int fragmentId = 0;
        int fragmentsLength = fragmentsEnc.length - 1;
        if (code == fragmentsEnc[fragmentId] || code == fragmentsEnc[fragmentsLength]) {
            return true;
        }
        do {
            int i1 = (fragmentId + fragmentsLength) / 2;
            if (code == fragmentsEnc[i1]) {
                return true;
            }
            if (code < fragmentsEnc[i1]) {
                fragmentsLength = i1;
            } else {
                fragmentId = i1;
            }
        } while (fragmentId != fragmentsLength && fragmentId + 1 != fragmentsLength);
        return false;
    }

    public static int hashCode(char chars[]) {
        if (chars.length > 6) {
            return 0;
        }
        int code = 0;
        for (int l = 0; l < chars.length; l++) {
            char c = chars[chars.length - l - 1];
            if (c >= 'a' && c <= 'z') {
                code = code * 38 + ((c - 97) + 1);
            } else if (c == '\'') {
                code = code * 38 + 27;
            } else if (c >= '0' && c <= '9') {
                code = code * 38 + ((c - 48) + 28);
            } else if (c != 0) {
                return 0;
            }
        }
        return code;
    }
}
