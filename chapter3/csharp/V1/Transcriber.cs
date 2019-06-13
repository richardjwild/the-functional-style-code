using System.Linq;

namespace V1
{
    public class Transcriber
    {
        private RnaBase PairOf(DnaBase dnaBase)
        {
            switch (dnaBase)
            {
                case DnaBase.A: return RnaBase.U;
                case DnaBase.C: return RnaBase.G;
                case DnaBase.G: return RnaBase.C;
                case DnaBase.T: return RnaBase.A;
                default: return RnaBase.A;
            }
        }

        public RnaSequence Transcribe(DnaSequence dnaSequence)
        {
            return new RnaSequence(
                dnaSequence
                    .Select(dna => PairOf(dna))
                    .ToList());
        }
    }
}