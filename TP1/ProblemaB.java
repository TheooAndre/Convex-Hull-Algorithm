import java.util.*;
import java.lang.*;



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
    else if(this.x==p.getX() && this.y==p.getY())
      return Integer.compare(0,0);
    else
      return Integer.compare(this.x,p.getX());
  }

  @Override
  public String toString(){
    return String.format("%s %s\n",x,y);
  }

  public boolean isCCW(Ponto b,Ponto c){
    int result=(b.getX()-x)*(c.getY()-y)-(c.getX()-x)*(b.getY()-y);
    if(result<0)
      return true;
    else
      return false;
  }
}


public class ProblemaB{

  private static ArrayList<Ponto> convexHull(Ponto [] pontos){
    
    Arrays.sort(pontos);
    int p=0;
    int q;
    ArrayList<Ponto> boundary=new ArrayList<Ponto>(pontos.length);
    long startTime = System.nanoTime();
    do{
      boundary.add(pontos[p]);
      q=(p+1)%pontos.length;
      for(int i=0;i<pontos.length;i++){
        if (pontos[p].isCCW(pontos[q],pontos[i]))
            q=i;
      }
      p=q;
    }while(p!=0);
    Collections.sort(boundary);
    long endTime   = System.nanoTime();
    long totalTime = endTime - startTime;
    double seconds = (double)totalTime / 1_000_000_000.0;
    System.out.println("total time of execution: "+seconds);
    return boundary;
  }


    public static void main(String [] args){

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

       long startTime = System.nanoTime();

      for(Ponto p:convexHull(pontos)){
         System.out.print(p);
      }

      
    }
}
