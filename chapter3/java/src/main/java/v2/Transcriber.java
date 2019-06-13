package v2;

import common.DnaBase;
import common.DnaSequence;
import common.RnaBase;
import common.RnaSequence;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Transcriber {

    RnaSequence transcribe(DnaSequence sequence) {
        return new RnaSequence(
                sequence.stream()
                        .map(base -> pairOf(base))
                        .collect(toList()));
    }

    private RnaBase pairOf(DnaBase base) {
        switch (base) {
            case C: return RnaBase.G;
            case G: return RnaBase.C;
            case A: return RnaBase.U;
            case T: return RnaBase.A;
        }
        return null;
    }
}
