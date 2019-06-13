package thefunctionalstyle


class NumeralsRecursive {

    def getNumerals() {
        return [
                [symbol: 'M', value: 1000],
                [symbol: 'CM', value: 900],
                [symbol: 'D', value: 500],
                [symbol: 'CD', value: 400],
                [symbol: 'C', value: 100],
                [symbol: 'XC', value: 90],
                [symbol: 'L', value: 50],
                [symbol: 'XL', value: 40],
                [symbol: 'X', value: 10],
                [symbol: 'IX', value: 9],
                [symbol: 'V', value: 5],
                [symbol: 'IV', value: 4],
                [symbol: 'I', value: 1],
        ]
    }

    String convert(int number) {
        convert(number, 0, '')
    }

    String convert(int remainder, int idx, String roman) {
        if (remainder == 0)
            roman
        else {
            def numeral = numerals[idx]
            if (remainder >= numeral.value)
                convert(remainder - numeral.value, idx, roman.concat(numeral.symbol))
            else
                convert(remainder, idx + 1, roman)
        }
    }
}
