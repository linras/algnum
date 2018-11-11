package projekt2;

import java.util.concurrent.ThreadLocalRandom;
import projekt2.FloatT;
import projekt2.DoubleT;
import projekt2.Rational;

public class Main {
	
	public static int precision = 65536;
	public static String nanoToSeconds(long nanos) {
		return ""+nanos/1000000000l+"."+(nanos/1000000l)%1000;
	}
	public static String nanoToMicro(long nanos) {
		return ""+nanos/1000000l+"."+(nanos/1000l)%1000;
	}
	
	public static Rational[][] generateRandomValues(int size){
		Rational[][] ret = new Rational[size][size];
		for (int i = 0; i < size*size; i++) {
			ret[i/size][i%size] = new Rational(ThreadLocalRandom.current().nextInt(-precision, precision-1),precision);
		}
		return ret;
	}
	public static Rational[][] generateRandomValues(int pion, int poziom){
		Rational[][] ret = new Rational[pion][poziom];
		for (int i = 0; i < pion; i++) {
			for(int j=0; j<poziom; j++)
				ret[i][j] = new Rational(ThreadLocalRandom.current().nextInt(-precision, precision-1),precision);
		}
		return ret;
	}
	public static Rational[][] generate(int pion, int poziom){
		Rational[][] ret = new Rational[pion][poziom];
		for (int i = 0; i < pion; i++) {
			for(int j=0; j<poziom; j++)
				ret[i][j] = new Rational(0,1);
		}
		return ret;
	}
	//public static DoubleT[][] rationalToDoubleT(Rational[][] r, int size){
		
	//}

	public static void main(String ...strings ) {
		long dt0,dt1,dt2,lastNano;
		int size = 6;
        System.out.print("generating...");
		Rational[][] A = generateRandomValues(size);
		Rational[][] X = generateRandomValues(size, 1);
		Rational[][] B = generate(size, 1);
        System.out.print("done\n");
		
		Matrix<Rational> Ar = new Matrix<Rational>(A,size);
		Matrix<Rational> Xr = new Matrix<Rational>(X, size, 1);
		Matrix<Rational> Br = new Matrix<Rational>(B, size, 1);

        System.out.print("multiplu A * X...");
		Br.mul(Ar,Xr);
        System.out.print("done\n");
		
		DoubleT[][] Ad1 = new DoubleT[size][size];
		DoubleT[][] Bd1 = new DoubleT[size][1];
		DoubleT[][] Xd = new DoubleT[size][1];
		FloatT[][] Af1 = new FloatT[size][size];
		FloatT[][] Bf1 = new FloatT[size][1];
		FloatT[][] Xf = new FloatT[size][1];
		for(int i = 0; i < size*size; i++) {
			Ad1[i/size][i%size]=new DoubleT(A[i/size][i%size].asDouble());
			Af1[i/size][i%size]=new FloatT(A[i/size][i%size].asFloat());
		}
		for(int i = 0; i < size; i++) {
			Bd1[i][0]=new DoubleT(B[i][0].asDouble());
			Bf1[i][0]=new FloatT(B[i][0].asFloat());
		}
		for(int i = 0; i < size; i++) {
			Xd[i][0]=new DoubleT(X[i][0].asDouble());
			Xf[i][0]=new FloatT(X[i][0].asFloat());
		}
		
		
		Matrix<DoubleT> Ad = new Matrix<>(Ad1, size);
		Matrix<DoubleT> Bd = new Matrix<>(Bd1, size, 1);
		Matrix<FloatT> Af = new Matrix<>(Af1, size);
		Matrix<FloatT> Bf = new Matrix<>(Bf1, size, 1);
		System.out.println("Ar "+Ar.toString());
		
		lastNano = System.nanoTime();
		//Af.gaussianElimination(Bf);
		dt0 = System.nanoTime()-lastNano;
		System.out.println("FloatT: "+nanoToMicro(dt0)+"ms");
		
		lastNano = System.nanoTime();
		//Ad.gaussianElimination(Bd);
		dt1 = System.nanoTime()-lastNano;
		System.out.println("DoubleT: "+nanoToMicro(dt1)+"ms");
		
		lastNano = System.nanoTime();
		Ar.fullGaussianElimination(Br);
		//Ar.partialGaussianElimination(Br);
		//Ar.gaussianElimination(Br);
		dt2 = System.nanoTime()-lastNano;
		System.out.println("Rational: "+nanoToMicro(dt2)+"ms");
		
		
		//Ar.gaussianElimination(Br);
		
		//System.out.println("Ar "+Ar.toString());
		System.out.println("Xr "+Xr.toString());
		System.out.println("Br "+Br.toString());
		/*for(int i=0; i<size;i++) {
				System.out.println(Xd[i][0].sub(Bd.elems[i][0]));
			
		}*/
		/*System.out.println(Ar.toString());
		Ar.gaussianElimination(Br);
		System.out.println(Ar.toString());
		System.out.println(Xr.toString());
		System.out.println(Br.toString());*/
	}
}