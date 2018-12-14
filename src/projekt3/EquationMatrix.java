package projekt3;

import java.util.HashMap;
import java.util.Map.Entry;

public class EquationMatrix {
	private int N; // ilosc agentow
	private HashMap<String, Integer> map; // mapa wartosci
	private int nP; // ilosc par;
	private int size;
	
	public EquationMatrix(int iloscAgentow) {
		N = iloscAgentow;
		size = binomial(N+2,2);	//binomial coefficient dwumian newtona
		nP = binomial(N,2);
		generateMap();
	}
	
	// musi sie wykonac raz po utworzeniu obiektu EquationMatrix
	private void generateMap() {
		map = new HashMap<String,Integer>();
		int n = 0;
		// wszystkie prawdopodobienstwa
		for(int nY = 0; nY <= N; nY++) {
			for (int nN = 0; nN <= N - nY; nN++) {
				map.put(nY+"."+nN,n);
				//System.out.println("\'"+nY+"."+nN+"\'->"+n);
				n++;
			}
		}
	}
	
	public double[][] generateGMatrix(){
		double[][] ret = new double[size][size];
		
		int n = 0;
		for(int nY = 0; nY <= N; nY++) {
			for (int nN = 0; nN <= N - nY; nN++) {
				int nU = N - nY - nN;
				
				if(nN!=0 && nU!=0) {
					ret[n][map.get((nY)+"."+(nN+1))]=((double)nN*nU)/nP;
					//System.out.println("ret["+n+"]["+map.get((nY)+"."+(nN+1))+"]="+ret[n][map.get((nY)+"."+(nN+1))]);
				}
				if(nY!=0 && nU!=0) {
					ret[n][map.get((nY+1)+"."+(nN))]=((double)nY*nU)/nP;
					//System.out.println("ret["+n+"]["+map.get((nY+1)+"."+(nN))+"]="+ret[n][map.get((nY+1)+"."+(nN))]);
				}
				if(nY!=0 && nN!=0) {
					ret[n][map.get((nY-1)+"."+(nN-1))]=((double)nY*nN)/nP;
					//System.out.println("ret["+n+"]["+map.get((nY-1)+"."+(nN-1))+"]="+ret[n][map.get((nY-1)+"."+(nN-1))]);
				}
				ret[n][map.get((nY)+"."+(nN))]=((double)nP - nN*nU - nY*nU - nY*nN)/nP;
				//System.out.println("ret["+n+"]["+map.get((nY)+"."+(nN))+"]="+ret[n][map.get((nY)+"."+(nN))]);
				
				
				n++;
			}
		}
		return ret;
	}
	public double[][] generateXVector(){
		double[][] ret = new double[size][1];
		ret[size-1][0]=1;
		return ret;
	}
	
	public String getStateString(int index) {
		String ret = "ERR";
		for(Entry<String,Integer> e : map.entrySet()) {
			if(e.getValue()==index) {
				ret = e.getKey();
				break;
			}
		}
		return ret;
	}
	public int getStateIndex(int nY, int nN) {
		int ret = -1;
		if(map.containsKey(nY+"."+nN)) {
			ret = map.get(nY+"."+nN);
		}
		return ret;
	}
	

	private static int binomial(int n, int k)
    {
        if (k>n-k)
            k=n-k;

        int b=1;
        for (int i=1, m=n; i<=k; i++, m--)
            b=b*m/i;
        return b;
    }
	
	public static void main(String[] args) {
		EquationMatrix eq = new EquationMatrix(3);
		double[][] G = eq.generateGMatrix();
		for (int i = 0; i < G.length; i++) {
			double[] ds = G[i];
			for (int j = 0; j < ds.length; j++) {
				System.out.print(String.format("%.1f ", ds[j]));
			}
			System.out.println();
		}
		double[][] X = eq.generateXVector();
		for (int i = 0; i < X.length; i++) {
			double[] ds = X[i];
			for (int j = 0; j < ds.length; j++) {
				System.out.print(String.format("%.1f ", ds[j]));
			}
			System.out.println();
		}
	}
}
