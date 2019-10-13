public class Planet{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  public Planet(double xP, double yP, double xV, double yV, double m, String img){
    xxPos=xP;
    yyPos=yP;
    xxVel=xV;
    yyVel=yV;
    mass=m;
    imgFileName=img;
  }
  public Planet(Planet p){
    this(p.xxPos,p.yyPos,p.xxVel,p.yyVel,p.mass,p.imgFileName);
  }
  public double calcDistance(Planet p2){
    double dist_x=this.xxPos-p2.xxPos;
    double dist_y=this.yyPos-p2.yyPos;
    double dist_squared=(dist_x*dist_x)+(dist_y*dist_y);
    return Math.sqrt(dist_squared);
  }
  public double calcForceExertedBy(Planet p2){
    double G=6.67*0.00000000001;
    double dist=this.calcDistance(p2);
    return G*this.mass*p2.mass/(dist*dist);
  }
  public double calcForceExertedByX(Planet p2){
    double F=this.calcForceExertedBy(p2);
    double cos=(p2.xxPos-this.xxPos)/this.calcDistance(p2);
    return F*cos;
  }
  public double calcForceExertedByY(Planet p2){
    double F=this.calcForceExertedBy(p2);
    double sin=(p2.yyPos-this.yyPos)/this.calcDistance(p2);
    return F*sin;
  }
  public double calcNetForceExertedByX(Planet[] planets){
    int len=planets.length;
    int i=0;
    double totalX=0;
    while (i<len){
      if (this.equals(planets[i])){
        i=i+1;
      }
      else {
        totalX=totalX+this.calcForceExertedByX(planets[i]);
        i=i+1;
      }
    }
    return totalX;
  }
  public double calcNetForceExertedByY(Planet[] planets){
    int len=planets.length;
    int i=0;
    double totalY=0;
    while (i<len){
      if (this.equals(planets[i])){
        i=i+1;
      }
      else {
        totalY=totalY+this.calcForceExertedByY(planets[i]);
        i=i+1;
      }
    }
    return totalY;
  }
  public void update(double time, double fx, double fy){
    double ax=fx/this.mass;
    double ay=fy/this.mass;
    this.xxVel=this.xxVel+ax*time;
    this.yyVel=this.yyVel+ay*time;
    this.xxPos=this.xxPos+this.xxVel*time;
    this.yyPos=this.yyPos+this.yyVel*time;
  }
  public void draw(){
    String directory="./images/";
    StdDraw.picture(this.xxPos,this.yyPos,directory+this.imgFileName);
  }
}
