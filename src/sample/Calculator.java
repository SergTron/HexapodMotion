package sample;

import static java.lang.Math.*;


public class Calculator {
    private double px;
    private double py;
    private double pz;
    private double pr;
    private double c;
    private double alfa;
    private double beta;
    public final static double L1= 37;
    public final static double L2= 57;
    public final static double L3= 160;
    public final static double A = 10;
    NormalCoordinates normalCoordinates = new NormalCoordinates();


    public NormalCoordinates calculate(CartesianCoordinates coordinates){
        px = coordinates.x;
        py = coordinates.y;
        pz = coordinates.z;

        pr = sqrt (px*px + py*py);
        c = sqrt (Math.pow(pz-A,2) + Math.pow(pr-L1,2));
        alfa = acos((Math.pow(L3,2) - Math.pow(L2,2) - Math.pow(c,2)) / (-2*L2*c));
        beta = atan ((pz-A) / (pr-L1));

        // infinity case
      //  if (px!=0) normalCoordinates.angle1 = atan (py/px);
      //  if (px!=0)
            normalCoordinates.angle1 = atan (px/py);
      //else
         // normalCoordinates.angle1= Math.PI/2;

        if (pr-L1 >= 0)
            normalCoordinates.angle2 = alfa + beta;
        else
            normalCoordinates.angle2 = alfa - (Math.PI - beta);

        normalCoordinates.angle3 = acos( (Math.pow(c,2) - Math.pow(L2,2) - Math.pow(L3,2)) / (2 * L2 * L3) );

        return normalCoordinates;
    }

    public NormalCoordinates calculateDeg(CartesianCoordinates coordinates) {
        px = coordinates.x;
        py = coordinates.y;
        pz = coordinates.z;

        pr = sqrt (px*px + py*py);
        c = sqrt (Math.pow(pz-A,2) + Math.pow(pr-L1,2));
        alfa = acos((Math.pow(L3,2) - Math.pow(L2,2) - Math.pow(c,2)) / (-2*L2*c));
        beta = atan ((pz-A) / (pr-L1));

        // infinity case
        //  if (px!=0) normalCoordinates.angle1 = atan (py/px);
        //  if (px!=0)
        normalCoordinates.angle1 = atan (px/py);
        //else
        // normalCoordinates.angle1= Math.PI/2;

        if (pr-L1 >= 0)
            normalCoordinates.angle2 = alfa + beta;
        else
            normalCoordinates.angle2 = alfa - (Math.PI - beta);

        normalCoordinates.angle3 = acos( (Math.pow(c,2) - Math.pow(L2,2) - Math.pow(L3,2)) / (2 * L2 * L3) );

        normalCoordinates.angle1=normalCoordinates.angle1 * 180 / Math.PI;
        normalCoordinates.angle2=normalCoordinates.angle2 * 180 / Math.PI;
        normalCoordinates.angle3=normalCoordinates.angle3 * 180 / Math.PI;
        return normalCoordinates;
    }
}
