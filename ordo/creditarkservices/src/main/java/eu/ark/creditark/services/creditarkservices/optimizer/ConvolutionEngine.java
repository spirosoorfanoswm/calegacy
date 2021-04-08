package eu.ark.creditark.services.creditarkservices.optimizer;

public class ConvolutionEngine {
	
	public class LossEntry
    {
        public double loss;
        public double probability;
    }

    private class AdjustedEntry {
        public int band;
        public double probability;
    }
    
    private double exposureUnit;
    private double[] source;
    private double[] destination;
    private int vectorLength;
    private int maxOrder = 0;

    public ConvolutionEngine(int vectorLength, double exposureUnit)
    {
        this.vectorLength = vectorLength;
        this.exposureUnit = exposureUnit;
        source = new double[vectorLength];
        destination = new double[vectorLength];
        
    }
    
    public double[] getLossDistribution() {
    	return source;
    }

    public void InitVectors()
    {
        maxOrder = 0;
        for (int i = 0; i < vectorLength; i++)
        {
            source[i] = 0;
            destination[i] = 0;
        }
    }

    public void AddEntry(LossEntry[] lossDistribution)
    {
        int i;

        // Adjust entry to fit in buckets
        AdjustedEntry[] distrib = new AdjustedEntry[lossDistribution.length + 1];

        distrib[0].band = 0;
        distrib[0].probability = 0;

        for (i = 0; i < lossDistribution.length; i++)
        {
            double bandUnits = Math.ceil(lossDistribution[i].loss / exposureUnit);
            double expLoss = lossDistribution[i].probability * lossDistribution[i].loss;
            distrib[i + 1].probability = lossDistribution[i].loss == 0 ?
                lossDistribution[i].probability : expLoss / (bandUnits * exposureUnit);
            distrib[i + 1].band = (int)bandUnits;
            distrib[0].probability += lossDistribution[i].probability - distrib[i + 1].probability;
        }

        boolean firstEntry = (maxOrder == 0);

        for (i = 0; i <= maxOrder; i++) destination[i] = 0; // Crear destination polynomial
   
        for (i = 0; i < distrib.length; i++)
        {
            if (firstEntry)
            {
                source[distrib[i].band] += distrib[i].probability;
                if (distrib[i].band > maxOrder) maxOrder = distrib[i].band;
            }
            else
            {
                int lastOrder = maxOrder;
                for (int j = 0; j <= maxOrder; j++)
                {
                    int order = distrib[i].band + j;
                    destination[order] += source[j] * distrib[i].probability;
                    if (order > lastOrder) lastOrder = order;
                }
                if (lastOrder > maxOrder) maxOrder = lastOrder;
            }
        }
        if (firstEntry) return;
        // Swap source and destination
        double[] buf = source;
        source = destination;
        destination = buf;
    }
}
