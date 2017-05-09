import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.ml.clustering.DoublePoint;


/**
 * Created by nikita on 09/05/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        CarFinding cf = new CarFinding();


        List<DoublePoint> points = new ArrayList<DoublePoint>();

//        double[] d = new double[2];
//        d[0] = 0.79;
//        d[1] = 1.64;
//        points.add(new DoublePoint(d));
//        d[0] = 8.08;
//        d[1] = 1.49;
//        points.add(new DoublePoint(d));
//
//        d[0] = 3.17;
//        d[1] = 7.67;
//        points.add(new DoublePoint(d));
//
//        d[0] = 12.73;
//        d[1] = 5.72;
//        points.add(new DoublePoint(d));
//
//        d[0] = 14.63;
//        d[1] = 10.05;
//        points.add(new DoublePoint(d));
//
//        d[0] = 18.38;
//        d[1] = 13.02;
//        points.add(new DoublePoint(d));
//
//        d[0] = 7.78;
//        d[1] = 18.52;
//        points.add(new DoublePoint(d));
//
//        d[0] = 4.07;
//        d[1] = 20.52;
//        points.add(new DoublePoint(d));





        String allPoints="";

        try{
            FileInputStream fstream = new FileInputStream("points.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                allPoints=strLine;
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }

        //System.out.println(allPoints);
        String[] strpoints = allPoints.replace("[", "").replace("]", "").split(", ");


        for (int i = 0; i < strpoints.length-1 ; i+=2) {

            double[] d = new double[2];
            d[0] = Double.parseDouble(strpoints[i]);
            d[1] = Double.parseDouble(strpoints[i+1]);

//            System.out.println(d[0]);
//            System.out.println(d[1]);
//            System.out.println();
            points.add(new DoublePoint(d));

        }

        cf.findClasters(points);


    }
}
