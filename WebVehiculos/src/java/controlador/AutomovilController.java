package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class AutomovilController {
    private int i=0;
    private int j=0;
    private int n=0;
    private int m=0;
    private int posx=0;
    private int posy=0;
    private String posicion="0,0";
    private String comando="";
    private List lcomandos=new ArrayList();
    private String comandos="";

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public void operacion(){
        boolean estado=true;
        int contador=0;
        while(i<comando.length()){
            if(contador==0){
                try {
                    Integer.parseInt(String.valueOf(comando.charAt(i)));
                } catch (NumberFormatException e) {
                    System.out.println("Error en formato de comando");
                    estado=false;
                    break;
                }
                contador=1;
            }else if(contador==1){
                if(comando.charAt(i)!=','){
                    System.out.println("Error en formato de comando");
                    estado=false;
                    break;
                }
                contador=2;
            }else if(contador==2){
                if(comando.charAt(i)!='N' && comando.charAt(i)!='S' && comando.charAt(i)!='O' && comando.charAt(i)!='E'){
                    System.out.println("Error en formato de comando");
                    estado=false;
                    break;
                }
                contador=3;
            }else if(contador==3){
                if(comando.charAt(i)!=';'){
                    System.out.println("Error en formato de comando");
                    estado=false;
                    break;
                }
                contador=0;
            }
            if(comando.charAt(i)!=';'){
                comandos=comandos+comando.charAt(i);
                if(comandos.length()==3){
                    lcomandos.add(comandos);
                }
            }else{
                j++;
                comandos="";
            }
            i++;
        }
        if(comando.charAt(comando.length()-1)!='N' && comando.charAt(comando.length()-1)!='S' && comando.charAt(comando.length()-1)!='O' && comando.charAt(comando.length()-1)!='E'){
            estado=false;
            System.out.println("Error en formato de comando");
        }
        if(estado){
            for (int k = 0; k < lcomandos.size(); k++) {
                if(lcomandos.get(k).toString().substring(2).equals("N")){
                    posy=posy+Integer.parseInt(lcomandos.get(k).toString().substring(0,1));
                    posicion="("+posx+","+posy+")";
                    if(posy>n){
                        System.out.println("Se ha detenido el avance por salir de los límites.");
                        break;
                    }else{
                        System.out.println("Posición Actual: "+posicion);
                    } 
                }else if(lcomandos.get(k).toString().substring(2).equals("S")){
                    posy=posy-Integer.parseInt(lcomandos.get(k).toString().substring(0,1));
                    posicion="("+posx+","+posy+")";
                    if(posy<0){
                        System.out.println("Se ha detenido el avance por salir de los límites.");
                        break;
                    }else{
                        System.out.println("Posición Actual: "+posicion);
                    }
                }else if(lcomandos.get(k).toString().substring(2).equals("E")){
                    posx=posx+Integer.parseInt(lcomandos.get(k).toString().substring(0,1));
                    posicion="("+posx+","+posy+")";
                    if(posx>m){
                        System.out.println("Se ha detenido el avance por salir de los límites.");
                        break;
                    }else{
                        System.out.println("Posición Actual: "+posicion);
                    }
                }else if(lcomandos.get(k).toString().substring(2).equals("O")){
                    posx=posx-Integer.parseInt(lcomandos.get(k).toString().substring(0,1));
                    posicion="("+posx+","+posy+")";
                    if(posx<0){
                        System.out.println("Se ha detenido el avance por salir de los límites.");
                        break;
                    }else{
                        System.out.println("Posición Actual: "+posicion);
                    }
                }
            }
        }
    }
}
