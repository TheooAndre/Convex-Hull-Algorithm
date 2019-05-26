import java.util.*;
import java.lang.*;

//Ordenar Lista
//scan

class Ponto implements Comparable<Ponto>{
  private final int x;
  private final int y;

  public Ponto(int x, int y){
    this.x = x;
    this.y = y;
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public boolean igualdade(Ponto p){

    if(p.getX() == this.x && p.getY() == this.y)
      return true;
    else
      return false;
  }
  
  @Override
  public int compareTo(Ponto p){
    if(this.x==p.getX())
      return Integer.compare(this.y,p.getY());
    else
      return Integer.compare(this.x,p.getX());
  }

  @Override
  public String toString(){
    return String.format("%s %s\n",x,y);
  }
}



//a=y2-y1, b=x1-x2,c=x1y2-y1x2.
//ax+by=c
class Linha{
  private final int a;
  private final int b;
  private final int c;
  private final Ponto p1;
  private final Ponto p2;

  public Linha(Ponto p1,Ponto p2){
    a = p2.getY()-p1.getY();
    b = p1.getX()-p2.getX();
    c = p1.getX()*p2.getY()-p1.getY()*p2.getX();
    this.p1=p1;
    this.p2=p2;
  }

  public int compare(Ponto p){
   int result=a*p.getX()+b*p.getY();
   if(result > c){
    return -1;
   }
   else if(result < c){
    return 1;
   }
   else
    return 0;
   }

  public Ponto getP1(){
    return p1;
  }

  public Ponto getP2(){
    return p2;
  }
}


public class ProblemaA {

    static boolean BoundaryCheck(Ponto [] pontos,Linha l){

      int lado=0;
      int c=0;
      for(Ponto p:pontos){
        if(!p.igualdade(l.getP1()) && !p.igualdade(l.getP2())){
          c++;
          if(c==1)
            lado=l.compare(p);
          else{
            if(l.compare(p)!=lado)
              return false;
          }
        }
      }
      return true;
    }

    public static void main(String[] args) {
      //Input
       
       Scanner sc = new Scanner(System.in);
       int c = Integer.parseInt(sc.nextLine());
       String aux;
       ArrayList<Ponto> pontos_aux= new ArrayList<Ponto>();
       for(int i=0;i < c;i++){
         aux=sc.nextLine();
         Ponto p_aux=new Ponto(Integer.parseInt(aux.split(" ")[0]),Integer.parseInt(aux.split(" ")[1]));
         pontos_aux.add(p_aux);
       }

       Ponto [] pontos=new Ponto[pontos_aux.size()];
       pontos=pontos_aux.toArray(pontos);
      

  

     //Lista
    
      ArrayList<Ponto> vertices=new ArrayList<Ponto>();
      //Loop
      
      long startTime = System.nanoTime();
      for(int i=0; i<pontos.length;i++){
        for(int j=i+1; j<pontos.length;j++){
          //Criar com os dois pontos
          Linha l1=new Linha(pontos[i],pontos[j]);
          if(BoundaryCheck(pontos,l1)){
            if(vertices.isEmpty()){
              vertices.add(pontos[i]);
              vertices.add(pontos[j]);
            }
            for(int k=0; k<vertices.size();k++){
              if(vertices.get(k).igualdade(pontos[i]))
                break;
              else if(k==vertices.size()-1){
                vertices.add(pontos[i]);
              }
              for(int s=0; s<vertices.size();s++){

                if(vertices.get(s).igualdade(pontos[j]))
                  break;
                else if(s==vertices.size()-1){
                  vertices.add(pontos[j]);
                }
            }
          }
          }
        }
      }
      
      Collections.sort(vertices);
      
      for(int i = 0; i < vertices.size(); i++) {  
           System.out.print(vertices.get(i));
      }  
      long endTime   = System.nanoTime();
      long totalTime = endTime - startTime;
      double seconds = (double)totalTime / 1_000_000_000.0;  
      System.out.println("total time of execution: "+seconds);
    }
}
