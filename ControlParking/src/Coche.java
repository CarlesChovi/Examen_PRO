
public class Coche {

	private int id;
	private String matricula;
	private int horaentrada;
	private int horasalida;
	private int precio;
	private String observaciones;
	
	public Coche() {
		
		id=0;
		matricula="";
		horaentrada=0;
		horasalida=0;
		precio=0;
		observaciones="";

	}
	
	public Coche(int Id, String matric, int horentrada, int horsalida, int prec, String observ) {
		
		id=Id;
		matricula=matric;
		horaentrada=horentrada;
		horasalida=horsalida;
		precio=prec;
		observaciones=observ;
	}
	
	public void setid(int id){
		this.id=id;
	}
	public void setmatr(String a){
		this.matricula=a;
	}
	public void setentrada(int b){
		this.horaentrada=b;
	}
	public void setsalida(int c){
		this.horasalida=c;
	}
	public void setprecio(int d){
		this.precio=d;
	}
	public void setobserv(String e){
		this.observaciones=e;
	}
	
	public int getid(){
		return id;
	}
	public String getmatr(){
		return matricula;
	}
	public int getentrada(){
		return horaentrada;
	}
	public int getsalida(){
		return horasalida;
	}
	public int getprecio(){
		return precio;
	}
	public String getobserv(){
		return observaciones;
	}
	public String toString(){
		return this.matricula;
	}
}
