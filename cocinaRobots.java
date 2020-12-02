import java.util.Scanner;

public class cocinaRobots{
    public static void main(String[] args) {
	int num,c=1,ingrediente=1,i;
	int[] robot = new int[50];
	int[] accion = new int[50];
	boolean f=true,bat,comidaCompletada=true;
	boolean[] exito = new boolean[50];
	
	Robot [] robotArray = new Robot [3];//array de objetos de robot
	robotArray[0] = new Chef("Cheff robot",100);
	robotArray[1] = new Mini("Mini robot 1",100);
	robotArray[2] = new Mini("Mini robot 2",100);
	
	Scanner sc= new Scanner(System.in);
	
	System.out.println("");
	bat=((Chef) robotArray[0] ).batteryFull();//check battery con el uso de interface
	((Chef) robotArray[0] ).checkBattery(bat);
	bat=((Mini) robotArray[1] ).batteryFull();
	((Mini) robotArray[1] ).checkBattery(bat);
	bat=((Mini) robotArray[2] ).batteryFull();
	((Mini) robotArray[2] ).checkBattery(bat);
	
		do{
			System.out.print("\n----------------------------------"+//menu 1
							"\nAccion: "+ c +
							"\nQue robot desea usar:"+ 
							"\n1.Robot Cheff"+
							"\n2.Mini Robot 1 (Cuchillos)"+
							"\n3.Mini Robot 2 (Manos Calientes)"+
							"\n\nIngresa numero: ");
			robot[c-1] = sc.nextInt();
			
			System.out.print("\nQue accion desea realizar:"+ //menu 2
							"\n1.Preparar ingredinete: "+ ingrediente + 
							"\n2.Cocinar ingredientes:" +
							"\n3.Servir"+
							"\n\nIngresa numero: ");
			accion[c-1] = sc.nextInt();
			num=accion[c-1];
							
							switch(num){  //switch case para el menu
							case 1: if(robot[c-1]==1){ exito[c-1]=robotArray[robot[c-1]-1].preparar(ingrediente,robot[c-1]);//realiza la accion de preparar ingrediente, si se puede hacer regresa un true en el vector exito[]												
													ingrediente++;//suma uno al contador de ingrediente
													}
										else{
											if(robot[c-1]==2 || robot[c-1]==3){ exito[c-1]=robotArray[robot[c-1]-1].preparar(ingrediente,robot[c-1]);//realiza la accion de preparar ingrediente, si se puede hacer regresa un true en el vector exito[]	
																				if(exito[c-1]==true) ingrediente++;//si completo la accion suma una al contador de ingresiente
											}
												else {System.out.println("Robot no existente"); c--;}//si elige un opcion no existente regresa un digito en el contador 
										}
								break;
							case 2:if(robot[c-1]==1) exito[c-1]=robotArray[robot[c-1]-1].cocinar(robot[c-1]);//realiza la accion de cocinar, si se puede hacer regresa un true en el vector exito[]
										else{
											if(robot[c-1]==2 || robot[c-1]==3) exito[c-1]=robotArray[robot[c-1]-1].cocinar(robot[c-1]);//realiza la accion de cocinar, si se puede hacer regresa un true en el vector exito[]
												else{ System.out.println("Robot no existente"); c--; }//si elige un opcion no existente regresa un digito en el contador 
										}
								break;
							case 3:	if(robot[c-1]==1){ exito[c-1]=robotArray[robot[c-1]-1].servir(robot[c-1]);//realiza la accion de servir, si se puede hacer regresa un true en el vector exito[]
													f=false;//convierte la bandera de salida a falsa
													}
										else{
											if(robot[c-1]==2 || robot[c-1]==3){ exito[c-1]=robotArray[robot[c-1]-1].servir(robot[c-1]);//realiza la accion de servir, si se puede hacer regresa un true en el vector exito[]
																			f=false;//convierte la bandera de salida a falsa	
																			}
												else {System.out.println("Robot no existente"); c--;}//si elige un opcion no existente regresa un digito en el contador 
										}
								break;
							default: System.out.println("\n--Ingresa una de las opciones--\n");
									c--;
						}
			c++;
		}while(f==true); //mientras que la bandera de salida sea verdadera	
	
	System.out.println("-----------------------");
	for(i=0;i<c-1;i++) {Acciones.ordenAcciones(robot[i],accion[i],exito[i],i);//ciclo para mostrar las acciones realizadas en orden, y si se realizaron con exito
		if(i>0){
			comidaCompletada=exito[i];//si un robot no pudo realizar su tarea, iguala la variable a false
			if(comidaCompletada==true){				
				if(accion[i]<accion[i-1]) comidaCompletada=false; //si no se realizaron las tareas en el procedimiento correcto, iguala la variable a false
			}
		}
	}
	
	if(comidaCompletada==true) System.out.println("\n||Hurra! El platillo estaba delicioso||");
		else System.out.println("\n||Alguna tarea fallo, la cominada no se pudo completar||");
	}	
}

interface Battery{//interface para revisar la bateria
	public boolean batteryFull();
	public void checkBattery(boolean bat);
}

abstract class Robot{//clase padre abstacta
	String name;
	int battery;
	
	public Robot(String name, int battery){
		this.name = name;
		this.battery = battery;
	}
	
	public abstract boolean preparar(int ingrediente,int r);
	
	public abstract boolean cocinar(int r);
	
	public abstract boolean servir(int r);
}

class Chef extends Robot implements Battery{//clase para robot cheff
	
	public Chef(String name, int battery){
		super(name,battery);
	}
	
	public boolean preparar(int ingrediente,int r){
		System.out.println("Se preparao con exito el ingrediente: "+ ingrediente);
		return true;
	}
	
	public boolean cocinar(int r){
		System.out.println("Se cocino con exito.");
		return true;
	}
	
	public boolean servir(int r){
		System.out.println("Se sirvio con exito la comida.");
		return true;
	}
	
	public boolean batteryFull(){
		if(battery>0) return true;
			else return false;
	}

	public void checkBattery(boolean bat){
		if(bat==true) System.out.println(name +" tiene bateria con "+ battery + "% de carga, esta listo para usarse.");
			else System.out.println(name +" tiene bateria con carga suficiente para usarse");
	}
}

class Mini extends Robot implements Battery{//clase para los mini robots
	
	public Mini(String name, int battery){
		super(name,battery);
	}
	
	public boolean preparar(int ingrediente,int r){//si el robot 2 realizo la tarea, regresa true
		if(r==2){
			System.out.println("Se prepraro con exito el ingrediente: "+ ingrediente + ".");
			return true;
		}
			else{
				System.out.println("Robot "+ r +" no es  capaz de preparar el ingrediente.");
				return false;
			}			
	}
	
	public boolean cocinar(int r){//si el robot 3 realizo la tarea, regres true
		if(r==3){
			System.out.println("Se cocino con exito.");
			return true;
		}
			else{
				System.out.println("Robot "+ r +" no es capaz de cocinar.");
				return false;
			}
	}
	
	public boolean servir(int r){//robot mini ni puede servir, regresa false
		System.out.println("Robot " + r +" no capaz de servir la comida.");
		return false;
	}
	
	public boolean batteryFull(){
		if(battery>0) return true;
			else return false;
	}

	public void checkBattery(boolean bat){
		if(bat==true) System.out.println(name +" tiene bateria con "+ battery + "% de carga, esta listo para usarse.");
			else System.out.println(name +" no tiene bateria con carga sufiente para usarse.");
	}
}

class Acciones{//clase 	que muestra la accione realizada
	
	public static void ordenAcciones(int robot, int accion, boolean exito,int i){
		String name="", tarea="";
		i++;
		
		switch(robot){//dependiendo del numero que este en el vector, genera un nombre en la variable
			case 1: name="Cheff"; break;
			case 2: name="Mini 2"; break;
			case 3: name="Mini 3"; break;
		}
		
		switch(accion){//dependiendo del numero del vector, genera una tarea char en la variable
			case 1: tarea=": Preparar ingrediente."; break;
			case 2: tarea=": Cocinar."; break;
			case 3: tarea=": Servir la comida."; break;
		}
		System.out.println("\nAccion: "+ i +
							"\nRobot "+ name + " " + tarea); 
							
		if(exito==true) System.out.println("La tarea se realizo con exito.");
			else System.out.println("--Tarea no se pudo realizar--");
						
	}
}