package v1;

import common.DnaBase;
import common.DnaSequence;
import common.RnaBase;
import common.RnaSequence;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;

class TranscriberShould {

    @Test
    void transcribe_dna_to_rna_v1() {
        verify(
                new DnaSequence(
                        asList(
                                DnaBase.A,
                                DnaBase.C,
                                DnaBase.G,
                                DnaBase.T,
                                DnaBase.G,
                                DnaBase.G,
                                DnaBase.T,
                                DnaBase.C,
                                DnaBase.T,
                                DnaBase.T,
                                DnaBase.A,
                                DnaBase.A)
                ),
                new RnaSequence(
                        asList(
                                RnaBase.U,
                                RnaBase.G,
                                RnaBase.C,
                                RnaBase.A,
                                RnaBase.C,
                                RnaBase.C,
                                RnaBase.A,
                                RnaBase.G,
                                RnaBase.A,
                                RnaBase.A,
                                RnaBase.U,
                                RnaBase.U)
                ));
    }

    private void verify(DnaSequence dnaSequence, RnaSequence expected) {
        var transcriber = new Transcriber();
        assertThat(transcriber.transcribe(dnaSequence), Matchers.is(expected));
    }
}