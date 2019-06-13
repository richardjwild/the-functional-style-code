package v1;

import common.DnaSequence;
import common.RnaBase;
import common.RnaSequence;

import java.util.ArrayList;

public class Transcriber {

    RnaSequence transcribe(DnaSequence sequence) {
        var out = new ArrayList<RnaBase>();
        for (var dna : sequence) {
            RnaBase rna = null;
            switch (dna) {
                case C: rna = RnaBase.G;
                    break;
                case G: rna = RnaBase.C;
                    break;
                case A: rna = RnaBase.U;
                    break;
                case T: rna = RnaBase.A;
                    break;
            }
            out.add(rna);
        }
        return new RnaSequence(out);
    }

}
