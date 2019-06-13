package v3;

import common.DnaBase;
import common.DnaSequence;
import common.RnaBase;
import common.RnaSequence;

import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Transcriber {

    Map<DnaBase, RnaBase> pairs = Map.of(
            DnaBase.C, RnaBase.G,
            DnaBase.G, RnaBase.C,
            DnaBase.A, RnaBase.U,
            DnaBase.T, RnaBase.A);

    RnaSequence transcribe(DnaSequence sequence) {
        return new RnaSequence(
                sequence.stream()
                        .map(base -> pairs.get(base))
                        .collect(toList()));
    }
}
