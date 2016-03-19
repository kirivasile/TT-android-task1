package com.github.kirivasile.tt_android_task1;

/**
 * Created by Kirill on 19.03.2016.
 */
public class Converter {
    private static String[] ones = {
        "одна", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"
    };
    private static String[] teen = {
        "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };
    private static String[] tens = {
        "temp", "десять", "двадцать", "тридцать", "сорок", "пятдесят", "шестьдесят", "семьдесят",
        "восемьдесят", "девяносто"
    };
    private static String[] hundreds = {
        "temp", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот",
        "восемьсот", "девятьсот"
    };

    public String convert(int number) {
        if (number > 1000000) {
            return null;
        }
        if (number == 1000000) {
            return "один миллион";
        }
        StringBuilder result = new StringBuilder();
        int rest = number / 1000;
        if (rest != 0) {
            result.append(convertSmall(rest, true));
            if (rest % 10 == 1) {
                result.append(" тысяча ");
            } else if (rest % 10 < 5) {
                result.append(" тысячи ");
            } else {
                result.append(" тысяч ");
            }
        }
        result.append(convertSmall(number % 1000, false));
        return result.toString();
    }

    private String convertSmall(int number, boolean isFemale) {
        if (number > 1000) {
            return null;
        }
        if (number == 0) {
            return "ноль";
        }
        StringBuilder result = new StringBuilder();
        int hundredNum = number / 100;
        if (hundredNum != 0) {
            result.append(hundreds[hundredNum] + " ");
        }
        int tenNum = number / 10 % 10;
        int oneNum = number % 10;
        if (tenNum == 1) {
            result.append(teen[oneNum]);
            return result.toString();
        }
        if (tenNum != 0) {
            result.append(tens[tenNum] + " ");
        }
        if (oneNum == 1 && isFemale) {
            result.append(ones[0]);
        }
        if (oneNum != 0) {
            result.append(ones[oneNum]);
        }
        return result.toString();
    }
}
