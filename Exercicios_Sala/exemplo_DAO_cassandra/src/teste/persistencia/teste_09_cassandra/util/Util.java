package teste.persistencia.teste_09_cassandra.util;

public class Util {

	public static <T> boolean isInArray(T element, T[] v) {
		if(v == null) return false;
		for(T t : v) {
			if(t.equals(element)) return true;
		}
		return false;
	}
	
	public static <T> boolean equalsArrays(T[] a, T[] b) {
		if(a == b) return true;
		if((a == null && b != null) || (a != null && b == null)) return false;
		if(a.length != b.length) return false;
		for(T t : a) {
			if(!isInArray(t, b)) return false;
		}
		return true;
	}
}
