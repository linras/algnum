package projekt3;

public abstract class RealNumber<T> {
	//public abstract RealNumber();
	public abstract T add(T a);
	public abstract T add(long a);
	public abstract T sub(T a);
	public abstract T sub(long a);
	public abstract T mul(T a);
	public abstract T mul(long a);
	public abstract T div(T a);
	public abstract T div(long a);
	public abstract T inv();
	public abstract int eq(T a);
	public abstract T abs();
	public abstract T[][] returnNew(int pion, int poziom);
}