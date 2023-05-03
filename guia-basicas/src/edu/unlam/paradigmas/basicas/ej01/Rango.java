package edu.unlam.paradigmas.basicas.ej01;



import java.lang.Integer;


public class Rango implements Comparable<Rango>
{
    private final int inicio;
    private final int fin;
    private String tipoInicio;
    private String tipoFin;
    
    public static Rango crearIntervaloAbierto(int ini, int fin){
        Rango rango = new Rango(ini,fin);
        rango.tipoInicio=rango.tipoFin="abierto";
        
        return rango;
    }
    
    public static Rango crearIntervaloCerrado(int ini, int fin){
        Rango rango = new Rango(ini,fin);
        rango.tipoInicio=rango.tipoFin="cerrado";
        
        return rango;
    }
    
    public static Rango crearIntervaloAbiertoCerrado(int ini, int fin){
        Rango rango = new Rango(ini,fin);
        rango.tipoInicio="abierto";
        rango.tipoFin="cerrado";
        
        return rango;
    }
    
    public static Rango crearIntervaloCerradoAbierto(int ini, int fin){
        Rango rango = new Rango(ini,fin);
        rango.tipoInicio="cerrado";
        rango.tipoFin="abierto";
        
        return rango;
    }

    private Rango(int ini, int fin){
        this.inicio = ini;
        this.fin = fin;
    }
    
    public boolean numeroEnRango(int num){
        boolean dentroLimiteInferior = this.tipoInicio.equals("abierto") ? num > this.inicio  : num >= this.inicio;
        boolean dentroLimiteSuperior = this.tipoFin.equals("abierto") ? num < this.fin  : num <= this.fin;
        
        return dentroLimiteInferior && dentroLimiteSuperior;
    }
    
    
    
    
    public boolean rangoEnRango(Rango otro) {
        boolean dentroLimiteInferior=this.inicio <= otro.inicio;
        
        if(this.tipoInicio.equals("abierto") && otro.tipoInicio.equals("cerrado"))
            dentroLimiteInferior=this.inicio < otro.inicio;
        
        
        boolean dentroLimiteSuperior=this.fin >= otro.fin;;
        if(this.tipoFin.equals("abierto") && otro.tipoFin.equals("cerrado"))
            dentroLimiteSuperior=this.fin > otro.fin;
        
        
        return dentroLimiteInferior && dentroLimiteSuperior;
    }
    
    public boolean hayInterseccion(Rango otro) {
    	 /*
        Sabiendo que para intersectar dos rangos A y B.
        el maximo de A debe ser mayor al Minimo de B, y 
        a su vez el máximo de B debe ser mayor al Minimo de A
    */
    	
    	boolean comparacion1 = this.fin >= otro.inicio;
       if(this.tipoFin.equals("abierto") && otro.tipoInicio.equals("cerrado"))
           comparacion1 = this.fin > otro.inicio;
           
       boolean comparacion2 = otro.fin >= this.inicio;
       if(this.tipoInicio.equals("abierto") && otro.tipoFin.equals("cerrado"))
           comparacion1 = otro.fin > this.inicio;
           

       return comparacion1 && comparacion2;
    }
    
    public Rango interseccion(Rango otro){
       
 
        int inicio, fin;
        String tipoInicio, tipoFin;

    	
        //calculo el inicio mas grande de los dos rangos
    	if(this.inicio>=otro.inicio)
    		inicio=this.inicio;
    	else
    		inicio=otro.inicio;
    	
    	if(this.inicio!=otro.inicio)
    		tipoInicio= this.tipoInicio == "cerrado" || otro.tipoInicio =="cerrado"?
    				"cerrado" : "abierto";
    	else
    		tipoInicio= this.tipoInicio == "abierto" || otro.tipoInicio =="abierto"?
    				"abierto" : "cerrado";
    	
    	
    	//valido que no haya un desborde en el nuevo inicio
    	if(inicio > this.fin || inicio > otro.fin)
    		return Rango.crearIntervaloAbierto(0, 0);
    	if(inicio == this.fin && this.tipoFin == "abierto" ||
    			inicio == otro.fin && otro.tipoFin == "abierto")
    		return Rango.crearIntervaloAbierto(0, 0);
    	
    		
    	
    	//calculo el fin mas chico de los dos rangos
    	if(this.fin<=otro.fin)
    		fin=this.fin;
    	else
    		fin=otro.fin;
    	if(this.fin!=otro.fin)
    		tipoFin= this.tipoFin == "cerrado" || otro.tipoFin =="cerrado"?
    				"cerrado" : "abierto";
    	else
    		tipoFin= this.tipoFin == "abierto" || otro.tipoFin =="abierto"?
    				"abierto" : "cerrado";
    	
    	//valido que no haya un desborde en el nuevo fin
    	if(fin < this.inicio || fin < otro.inicio)
    		return Rango.crearIntervaloAbierto(0, 0);
    	if(fin == this.inicio && this.tipoInicio == "abierto" ||
    			fin == otro.inicio && otro.tipoInicio == "abierto")
    		return Rango.crearIntervaloAbierto(0, 0);
    	
    	
        //retorno el rango obtenido
        Rango resultado = new Rango(inicio,fin);
        resultado.tipoInicio=tipoInicio;
        resultado.tipoFin=tipoFin;
        
        
        return resultado;
    }
    
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
			
		Rango otro = (Rango) obj;
		if (this.inicio != otro.inicio || !this.tipoInicio.equals(otro.tipoInicio))
			return false;
		if (this.fin != otro.fin || !this.tipoFin.equals(otro.tipoFin))
			return false;
			
		return true;
	}
	
	@Override
	public String toString() {
		
		return new StringBuilder()
		        .append(this.tipoInicio.equals("abierto")? "(" : "[")
		        .append(this.inicio + ", ")
		        .append(this.fin)
		        .append(this.tipoFin.equals("abierto")? ")" : "]")
		        .toString();
		        
	}
	
	@Override
	 public int compareTo (Rango otro){
		 
		 int resultado = Integer.compare(this.inicio,otro.inicio);
		 
		 if(resultado==0)
		    resultado = Integer.compare(this.fin,otro.fin);
		    
		 if(resultado==0){
		     if(!this.tipoInicio.equals(otro.tipoInicio) && this.tipoInicio.equals("cerrado"))
		        resultado=-1;
		    else if(!this.tipoInicio.equals(otro.tipoInicio) && this.tipoInicio.equals("abierto"))
		        resultado=1;
		 }
		 
		 
		 if(resultado==0){
		     if(!this.tipoFin.equals(otro.tipoInicio) && this.tipoFin.equals("cerrado"))
		        resultado=-1;
		    else if(!this.tipoFin.equals(otro.tipoFin) && this.tipoFin.equals("abierto"))
		        resultado=1;
		 }
		    
		                
		 
		 return resultado;
	 }
	 
	 public static Rango RangoInfinito(){
	     /*
	        Este metodo retorna un rango que contiene a todos
	        los otros posibles rangos que se puedan crear con 
	        esta clase
	     */
	     
	     return crearIntervaloAbierto(Integer.MIN_VALUE,Integer.MAX_VALUE);
	     
	 }
	 
	 public static Rango sumarRango(Rango ran1, Rango ran2){
	     Rango nue = new Rango(ran1.inicio,ran2.fin);
	     nue.tipoInicio=ran1.tipoInicio;
	     nue.tipoFin=ran2.tipoFin;
	     
	     return nue;
	 }
	 
	 public Rango rangoPorEscalar(int k){
	    Rango nue = new Rango(this.inicio*k,this.fin*k);
	    nue.tipoInicio=this.tipoInicio;
	    nue.tipoFin=this.tipoFin;
	    
	    return nue;
	 }
    
	
}
