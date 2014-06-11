import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;







import javax.swing.JComboBox;

import com.mysql.jdbc.Statement;

public class DB {
	Connection conexion = null; //maneja la conexión
	Statement instruccion = null;// instrucción de consulta
	ResultSet conjuntoResultados = null;// maneja los resultados
	private JComboBox<Coche> coches;
	
	
	public DB(JComboBox coches) {
		// TODO Auto-generated constructor stub
				crearconexion();
				this.coches=coches;
	}
	
	public void crearconexion() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			// establece la conexión a la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/parking","root","tonphp");
			
			}catch( SQLException excepcionSql ){
			excepcionSql.printStackTrace();
			}// fin de catch
			catch( ClassNotFoundException noEncontroClase )
			{
				noEncontroClase.printStackTrace();
			}// fin de catch
	}
	
	public void leerCoches(){
		try{
			// crea objeto Statement para consultar la base de datos
			instruccion = (Statement) conexion.createStatement();
			// consulta la base de datos
			conjuntoResultados = instruccion.executeQuery("SELECT Id, matric, horentrada, horsalida, prec, observ FROM controlcoches");
			//Mostrar por pantalla
			while (conjuntoResultados.next())
			{
				System.out.println("id="+conjuntoResultados.getObject("Id")+
					      ", matricula="+conjuntoResultados.getObject("matric"));						 
					   Coche equip=new Coche 	((int)conjuntoResultados.getObject("Id"),
							   					(String)conjuntoResultados.getObject("matric"),
							   					(int)conjuntoResultados.getObject("horentrada"),
							   					(int)conjuntoResultados.getObject("horsalida"),
							   					(int)conjuntoResultados.getObject("prec"),
							   					(String)conjuntoResultados.getObject("observ"));
					  coches.addItem(equip);								
			}
			conjuntoResultados.close();

	}catch( SQLException excepcionSql ){
	excepcionSql.printStackTrace();}
}
	
	public int insertarCoche(String matricula, int horaEntrada, int horaSalida, int precio){
		// crea objeto Statement para consultar la base de datos
		try {
			instruccion = (Statement) conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//inserción en base de datos
		try{
			String sql="INSERT INTO `parking`.`controlcoches` (`Matricula`, `HoraEntrada`, `HoraSalida`, `Precio`) VALUES ("
														+"'"+matricula+"', '"+horaEntrada+"', '"+horaSalida+"', '"+precio+"');";
			instruccion.executeUpdate(sql);
			
			//PARA GUARDAR EL ID
			sql = "SELECT * FROM controlcoches";
			conjuntoResultados = instruccion.executeQuery(sql);
			int ID=1;
			//Mostrar por pantalla
			while (conjuntoResultados.next())
			{
				ID=(int)conjuntoResultados.getObject("ID");
			}
			conjuntoResultados.close();	
			return ID;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
			}
		}	
}
