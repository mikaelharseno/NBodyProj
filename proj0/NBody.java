public class NBody {
  public static double readRadius(String directory){
    In stream = new In(directory);
    stream.readLine();
    double radius=stream.readDouble();
    return radius;
  }
  public static Planet[] readPlanets(String directory){
    In stream = new In(directory);
    int i=0;
    int len=stream.readInt();
    Planet[] planets= new Planet[len];
    stream.readDouble();
    while (i<len){
      planets[i]= new Planet(stream.readDouble(),stream.readDouble(),stream.readDouble(),stream.readDouble(),stream.readDouble(),stream.readString());
      i=i+1;
    }
    return planets;
  }
  public static void main(String[] string){
    double T=Double.parseDouble(string[0]);
    double dt=Double.parseDouble(string[1]);
    String filename=string[2];
    String folder="./images/";
    double radius=NBody.readRadius(filename);
    Planet[] planets=NBody.readPlanets(filename);
    StdDraw.setScale(-1*radius, radius);
    StdDraw.clear();
    StdDraw.picture(0,0,"./images/starfield.jpg");
    int len=planets.length;
    double time=0;
    double[] xForces= new double[len];
    double[] yForces= new double[len];
    while (time<T){
      int j=0;
      while (j<len){
        xForces[j]=planets[j].calcNetForceExertedByX(planets);
        yForces[j]=planets[j].calcNetForceExertedByY(planets);
        j=j+1;
      }
      int k=0;
      while (k<len){
        planets[k].update(dt, xForces[k], yForces[k]);
        k=k+1;
      }
      StdDraw.picture(0,0,"./images/starfield.jpg");
      int l=0;
      while(l<len){
        planets[l].draw();
        l=l+1;
      }
      StdDraw.show(10);
      time=time+dt;
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
	    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
