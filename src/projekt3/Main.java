package projekt3;

public class Main {
	public static String nanoToMili(long nanos) {
		return ""+nanos/1000000l+"."+(nanos/1000l)%1000;
	}
	public static void main(String[] args) {
		int iloscAgentow = 50;
		//for(; iloscAgentow<30; iloscAgentow+=10) {
			
		System.out.print(iloscAgentow);
		
		EquationMatrix eq = new EquationMatrix(iloscAgentow);
		double[][] G = eq.generateGMatrix();
		double[][] X = eq.generateXVector();
		int size = G.length;
		double[][] C = new double[G.length][1];
		DoubleT[][] Gg = new DoubleT[G.length][G.length];
		DoubleT[][] Cg = new DoubleT[C.length][1];
		DoubleT[][] Xg = new DoubleT[X.length][1];
		double[][] G2 = new double[G.length][G[0].length];
		double[][] X2 = new double[X.length][X[0].length];
		for(int i = 0; i < size*size; i++) {
			Gg[i/size][i%size]=(new DoubleT(G[i/size][i%size]));
			G2[i/size][i%size] = G[i/size][i%size];
		}
		for(int i = 0; i < size; i++) {
			Cg[i][0]=(new DoubleT(1.0));
			Xg[i][0]=(new DoubleT(X[i][0]));
			X2[i][0]=X[i][0];
		}
//		Matrix<DoubleT> GM = new Matrix<>(Gg, size);
//		Matrix<DoubleT> CM = new Matrix<>(Cg, size, 1);
		

		Iter gaussSeidel = new Iter(G2.length, G2, X2, null);
		Iter jacobs = new Iter(G.length, G, X, null);
		
		//GM.partialGaussianElimination(CM);
		//long dt0,dt1,lastNano;
		
		gaussSeidel.gaussWTransform();
		jacobs.jacobiWOTransform();
		
		X = jacobs.getW();
		X2 = gaussSeidel.getW();
		
		int n = 0;
		double sum=0;
		for(int nY = 0; nY <= iloscAgentow; nY++) {
			for (int nN = 0; nN <= iloscAgentow - nY; nN++) {
				double fromIter = X[n][0];
				double fromMC = MonteCarlo.simulationNTimes(iloscAgentow, nY, nN, 10000); ;
				
				//System.out.println(String.format("P_%d,%d:\tJ: %.3f\tMC: %.3f\tE: %.3f",
				//		nY, nN, fromIter, fromMC, fromIter-fromMC));
				
				sum+= Math.pow(fromIter-fromMC, 2);
				
				n++;
			}
		}
		sum=Math.sqrt(sum);
		System.out.println("Mean error="+sum+" for N="+iloscAgentow);
		
//		lastNano = System.nanoTime();
//		gaussSeidel.gaussWTransform();
//		dt1 = System.nanoTime()-lastNano;
//		System.out.print(" Czas GS: "+nanoToMili(dt1)+"ms");
//		
//		lastNano = System.nanoTime();
//		jacobs.jacobiWOTransform();
//		dt0 = System.nanoTime()-lastNano;
//		System.out.print(" Czas J: "+nanoToMili(dt0)+"ms");
		
		
		
		
		
		System.out.println();
		}
//		double[][] W = jacobs.getW();
//		for (int i = 0; i < W.length; i++) {
//			//System.out.println(String.format("P_%s=", eq.getStateString(i))+W[i][0]);
//			System.out.println(W[i][0]+" vs "+CM.elems[i][0]);
//		}
	//}
}
