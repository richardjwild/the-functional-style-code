using System.Collections.Generic;

namespace V1
{
    public class RnaSequence : List<RnaBase>
    {
        public RnaSequence(List<RnaBase> sequence)
        {
            foreach (var rnaBase in sequence) 
                Add(rnaBase);
        }
    }
}