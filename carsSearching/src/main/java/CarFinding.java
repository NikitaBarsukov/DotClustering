import org.apache.commons.math3.ml.clustering.*;
import org.apache.commons.math3.ml.clustering.DoublePoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CarFinding {

    public static void findClasters(List<DoublePoint> carPoints) throws IOException {

        List<CarData> datalist= new ArrayList<CarData>();


        //загружаем картинку
        File file = new File("car.jpg");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);




        DBSCANClusterer<org.apache.commons.math3.ml.clustering.DoublePoint> dbscan
                = new DBSCANClusterer<DoublePoint>(10,6);

        List<org.apache.commons.math3.ml.clustering.Cluster<DoublePoint>> cluster = dbscan.cluster(carPoints);

        double maxx=0;
        double maxy=0;

        double minx=0;
        double miny=0;



        System.out.println(carPoints);
        for (int i=0; i<cluster.size(); i++) {
            System.out.println("Cluster " + i);
            //ищем низ квадрата
            for (DoublePoint db : cluster.get(i).getPoints()){

                double[] xy=db.getPoint();

                if (xy[0]>maxx)
                    maxx=xy[0];
                if (xy[1]>maxy)
                    maxy=xy[1];


                System.out.println(xy[0]);
                System.out.println(xy[1]);
                System.out.println();

            }
            System.out.println("The lowest right dot of cluster is x: "+maxx+" y: " +maxy);


            minx=maxx;
            miny=maxy;

            //ищем верх квадрата
            for (DoublePoint db : cluster.get(i).getPoints()) {
                double[] xy=db.getPoint();

                if(xy[0]<minx)
                    minx=xy[0];
                if(xy[1]<miny)
                    miny=xy[1];


            }
            System.out.println("The upper left dot of cluster is x: "+minx+" y: " +miny);


            //режем картинку;
            double width=maxx-minx;
            double height=maxy-miny;

            System.out.println(image.getHeight()+" --"+ height);

            //System.out.println(maxx+width+"----"+image.getWidth());

            //если вдруг выходим за рамки то упираемся в границу, а не кидаем исключение
            if(maxx+width>image.getWidth())
                maxx=image.getWidth()-width-1;

            BufferedImage croppedCar = image.getSubimage((int)minx, (int)miny,(int)width , (int)height);
            File outputfile = new File("result/car"+String.valueOf(i)+".jpg");
            ImageIO.write(croppedCar, "jpg", outputfile);

            double centerPointX=(maxx+(maxx+width))/2;
            double centerPointY=(maxy+(maxy+height))/2;

            datalist.add(new CarData(croppedCar,centerPointX,centerPointY));
            
            maxx=maxy=miny=miny=0;
        }

    }


}
