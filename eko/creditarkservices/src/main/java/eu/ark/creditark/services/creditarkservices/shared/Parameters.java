package eu.ark.creditark.services.creditarkservices.shared;

import java.util.Arrays;

public class Parameters {
	public int turnoverDays;
    public double[] mitLgds; // zero entry for uncovered
    public double varMultiplier;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Parameters{");
    sb.append("turnoverDays=").append(turnoverDays);
    sb.append(", mitLgds=").append(Arrays.toString(mitLgds));
    sb.append(", varMultiplier=").append(varMultiplier);
    sb.append('}');
    return sb.toString();
  }
}
