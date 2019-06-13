package thefunctionalstyle

import spock.lang.Specification


class RomanSpec extends Specification {

    def "convert numbers to roman numerals"(int number, String expected) {
        def numerals = new Numerals()

        expect:
        numerals.convert(number) == expected

        where:
        number | expected
        1      | "I"
        2      | "II"
        3      | "III"
        4      | "IV"
        5      | "V"
        6      | "VI"
        9      | "IX"
        27     | "XXVII"
        48     | "XLVIII"
        59     | "LIX"
        93     | "XCIII"
        141    | "CXLI"
        163    | "CLXIII"
        402    | "CDII"
        575    | "DLXXV"
        911    | "CMXI"
        1024   | "MXXIV"
        3000   | "MMM"
    }
}