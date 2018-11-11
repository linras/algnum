package projekt2;

import java.util.Arrays;

import projekt2.Rational;
import projekt2.RealNumber;

public class Matrix<T extends RealNumber<T>> { 
	public T[][] elems;
	public int pion, poziom;
	
	public Matrix(T[][] elems, int size) {
		this.elems = elems;
		this.pion = size;
		this.poziom = size;
	}
	public Matrix(T[][] elems, int pion, int poziom) {
		this.elems = elems;
		this.pion = pion;
		this.poziom = poziom;
	}
	public Matrix(int pion, int poziom) {
		this.pion = pion;
		this.poziom = poziom;
	}
	@Override
	public String toString() {
		String ret ="Matrix<"+elems[0][0].getClass().getSimpleName()+">["+pion+"]["+poziom+"] = \n";
		for (int i = 0; i < pion; i++) {
			for(T elem : elems[i]) {
				ret+="\t"+elem.toString();
			}
			ret+="\n";
		}
		return ret ;
	}
	public void print() {
		
	}
	public void mul(Matrix<T> A, Matrix<T> B) {
		for(int i=0; i<A.getPion(); i++) {
			for(int j=0; j<B.getPoziom(); j++) {
				for(int k=0; k<A.getPoziom();k++) {
					//System.out.println(i+" "+j+" "+k+": ");
					//System.out.print(A.elems[i][k] + " * "+ B.elems[k][j]+ " = ");
					this.elems[i][j]=this.elems[i][j].add(A.elems[i][k].mul(B.elems[k][j]));
					//System.out.println(this.elems[i][j]);
				}
				//System.out.println(this.elems[i][j]);
			}
		}
	}
	
	
	public void swapRow(int i1, int i2, Matrix<T> X)
    {
		T temp;
        for (int i=0; i<pion; i++)
        {
            temp = this.elems[i2][i];
            this.elems[i2][i] = this.elems[i1][i];
            this.elems[i1][i] = temp;
        }
        temp = X.elems[i2][0];
        X.elems[i2][0] = X.elems[i1][0];
        X.elems[i1][0] = temp;
    }

    public void swapColumn(int i1, int i2)
    {
        for (int i=0; i<poziom; i++)
        {
            T temp = this.elems[i][i2];
            this.elems[i][i2] = this.elems[i][i1];
            this.elems[i][i1] = temp;
        }
    }
    
    public void gaussianElimination(Matrix<T> X)
    {
        System.out.print("bottom...");
    	//Matrix<T> A = this.merge(X);
        this.eliminateBottomTriangle(X, 0);
        System.out.print("done\ntop...");
        //System.out.println(this.toString());
        this.eliminateTopTriangle(X, pion-1);
        System.out.print("done\nconvert...");
        //System.out.println(X.toString());
        this.convert(X);
        System.out.print("done\n");
    }
    
    public void partialGaussianElimination(Matrix<T> X)
    {
        System.out.print("bottom...");
    	//Matrix<T> A = this.merge(X);
        this.eliminatePartialBottomTriangle(X, 0);
        System.out.print("done\ntop...");
        //System.out.println(this.toString());
        this.eliminateTopTriangle(X, pion-1);
        System.out.print("done\nconvert...");
        //System.out.println(X.toString());
        this.convert(X);
        System.out.print("done\n");
    }
    
    public void fullGaussianElimination(Matrix<T> X)
    {
        System.out.print("bottom...");
    	//Matrix<T> A = this.merge(X);
        this.eliminateFullBottomTriangle(X, 0);
        System.out.print("done\ntop...");
        //System.out.println(this.toString());
        this.eliminateTopTriangle(X, pion-1);
        System.out.print("done\nconvert...");
        //System.out.println(X.toString());
        this.convert(X);
        System.out.print("done\n");
    }
    
	
	private void convert(Matrix<T> X) {
		for(int i=0; i<this.pion; i++) {
			X.elems[i][0] = X.elems[i][0].div(elems[i][i]);
		}
		
	}
	
	private int getMaxInColumn(int level) {
		T max = this.elems[level][level];
		int index = level;
			for(int i = level+1; i < this.pion; i++) {
				if(this.elems[i][level].abs().eq(max.abs())==1) {
					max = this.elems[i][level];
					index = i;
				}
			}
			System.out.println("Max = "+max);
		return index;
	}
	
	private void getMaxAndSwitch(int level, Matrix<T> X) {
		T max = this.elems[level][level];
		int i1 = level, i2=level;
		//petla glowna zmienia kolumny
		for(int j=level; j<this.pion; j++) {
			//idzie sobie po kolumnie
			for(int i = level; i < this.pion; i++) {
				if(this.elems[i][j].abs().eq(max.abs())==1) {
					max = this.elems[i][j];
					i1 = i;
					i2 = j;
				}
			}
		}
		this.swapRow(i1, i2, X);
		this.swapColumn(i1, i2);
		System.out.println("Max = "+max+" i1:"+i1+" i2:"+i2);
	}
	
	
	private void eliminatePartialBottomTriangle(Matrix<T> X, int level) {
		//level to wiersz od którego zacynam eliminacje. zeruje kolumne w dol od elems[level][level]
			if(level == (this.pion-1)) 
				return;
			int i1 = this.getMaxInColumn(level);
			if(i1 != level) {
				this.swapRow(i1, level, X);
			}
			//ide po kolumnie:
			for (int i = level+1; i < this.pion; i++) {
				T a = null;
				for(int j= level; j< this.poziom; j++) {
					if(a==null)
						a = elems[i][j].div(elems[level][level]);
					//elems[i][j] = elems[i][j].sub((elems[level][j].mul(a)).div(elems[level][level]));//elems[i][level]=a
					elems[i][j] = elems[i][j].sub(elems[level][j].mul(a));
				}
				//System.out.print("X["+i+"][0]="+X.elems[i][0]+"-"+X.elems[level][0]+"*"+a+"=");
				X.elems[i][0] = X.elems[i][0].sub(X.elems[level][0].mul(a));
				a = null;
				//System.out.println(X.elems[i][0]);
				//X.elems[i][0] = X.elems[i][0].sub(X.elems[level][0].mul(a).div(elems[level][level]));
			}
			this.eliminatePartialBottomTriangle(X, level+1);
	}
	
	private void eliminateFullBottomTriangle(Matrix<T> X, int level) {
		//level to wiersz od którego zacynam eliminacje. zeruje kolumne w dol od elems[level][level]
			if(level == (this.pion-1)) 
				return;
			this.getMaxAndSwitch(level, X);
			
			//ide po kolumnie:
			for (int i = level+1; i < this.pion; i++) {
				T a = null;
				for(int j= level; j< this.poziom; j++) {
					if(a==null)
						a = elems[i][j].div(elems[level][level]);
					//elems[i][j] = elems[i][j].sub((elems[level][j].mul(a)).div(elems[level][level]));//elems[i][level]=a
					elems[i][j] = elems[i][j].sub(elems[level][j].mul(a));
				}
				//System.out.print("X["+i+"][0]="+X.elems[i][0]+"-"+X.elems[level][0]+"*"+a+"=");
				X.elems[i][0] = X.elems[i][0].sub(X.elems[level][0].mul(a));
				a = null;
				//System.out.println(X.elems[i][0]);
				//X.elems[i][0] = X.elems[i][0].sub(X.elems[level][0].mul(a).div(elems[level][level]));
			}
			this.eliminateFullBottomTriangle(X, level+1);
	}
	
	private void eliminateBottomTriangle(Matrix<T> X, int level) {
		//level to wiersz od którego zacynam eliminacje. zeruje kolumne w dol od elems[level][level]
			if(level == (this.pion-1)) 
				return;
			//ide po kolumnie:
			for (int i = level+1; i < this.pion; i++) {
				T a = null;
				for(int j= level; j< this.poziom; j++) {
					if(a==null)
						a = elems[i][j].div(elems[level][level]);
					//elems[i][j] = elems[i][j].sub((elems[level][j].mul(a)).div(elems[level][level]));//elems[i][level]=a
					elems[i][j] = elems[i][j].sub(elems[level][j].mul(a));
				}
				//System.out.print("X["+i+"][0]="+X.elems[i][0]+"-"+X.elems[level][0]+"*"+a+"=");
				X.elems[i][0] = X.elems[i][0].sub(X.elems[level][0].mul(a));
				a = null;
				//System.out.println(X.elems[i][0]);
				//X.elems[i][0] = X.elems[i][0].sub(X.elems[level][0].mul(a).div(elems[level][level]));
			}
			this.eliminateBottomTriangle(X, level+1);
	}
	private void eliminateTopTriangle(Matrix<T> X, int level) {
		//level to wiersz od którego zaczynam eliminacje. zeruje kolumne w gore od elems[level][level]
			if(level == 0) 
				return;
			
			//ide po kolumnie:
			for (int i = level-1; i >=0; i--) {
				T a = null;
				for(int j= level; j >=0; j--) {
					if(a==null)
						a = elems[i][j].div(elems[level][level]);
					//elems[i][j] = elems[i][j].sub((elems[level][j].mul(a)).div(elems[level][level]));//elems[i][level]=a
					elems[i][j] = elems[i][j].sub(elems[level][j].mul(a));
				}
				X.elems[i][0] = X.elems[i][0].sub(X.elems[level][0].mul(a));
				a = null;
				//X.elems[i][0] = X.elems[i][0].sub(X.elems[level][0].mul(a).div(elems[level][level]));
			}
			this.eliminateTopTriangle(X, level-1);
		}
		
	
    /*public int maxInColumn(int selected)
    {
        // set selected row as current max
        var currentMaxRowIndex = selected;
        var currentMax = this.elems[selected][selected];

        // check each row below selected row
        for(int i=selected; i<pion; i++)
        {
            if (this.elems[i][selected] > currentMax)
            {
                currentMax = this.elems[i][selected];
                currentMaxRowIndex = i;
            }
        }

        return currentMaxRowIndex;
    }*/
	
	
	public T[][] getElems() {
		return elems;
	}
	public void setElems(T[][] elems) {
		this.elems = elems;
	}
	public int getPion() {
		return pion;
	}
	public void setPion(int pion) {
		this.pion = pion;
	}
	public int getPoziom() {
		return poziom;
	}
	public void setPoziom(int poziom) {
		this.poziom = poziom;
	}
}