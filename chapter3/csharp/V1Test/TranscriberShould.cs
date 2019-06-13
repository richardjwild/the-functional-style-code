using System;
using System.Collections.Generic;
using System.Linq;
using NUnit.Framework;
using V1;

namespace V1Test
{
    [TestFixture]
    public class TranscriberShould
    {
        [TestCase("C", "G")]
        [TestCase("G", "C")]
        [TestCase("A", "U")]
        [TestCase("T", "A")]
        [TestCase("ACGTGGTCTTAA", "UGCACCAGAAUU")]
        public void Test1(string input, string expected)
        {
            var dnaSequence = DnaSequence(input);
            var rnaSequence = new Transcriber().Transcribe(dnaSequence);
            Assert.AreEqual(RnaSequence(expected), rnaSequence);
        }

        DnaSequence DnaSequence(string str)
        {
            var sequence = new List<DnaBase>();
            foreach (var c in str) 
                sequence.Add((DnaBase) Enum.Parse(typeof(DnaBase), c.ToString()));
            return new DnaSequence(sequence);
        }
        
        RnaSequence RnaSequence(string str)
        {
            var sequence = new List<RnaBase>();
            foreach (var c in str) 
                sequence.Add((RnaBase) Enum.Parse(typeof(RnaBase), c.ToString()));
            return new RnaSequence(sequence);
        }
    }
}