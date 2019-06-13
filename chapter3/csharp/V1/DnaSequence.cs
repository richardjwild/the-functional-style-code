using System.Collections.Generic;

namespace V1
{
    public class DnaSequence : List<DnaBase>
    {
        public DnaSequence(List<DnaBase> sequence)
        {
            foreach (var dnaBase in sequence) 
                Add(dnaBase);
        }
    }
}