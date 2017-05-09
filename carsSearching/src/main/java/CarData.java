import java.awt.image.BufferedImage;

/**
 * Created by nikita on 09/05/2017.
 */
public class CarData {

    CarData(BufferedImage i,double x,double y){
        this.carImage=i;
        this.centerPointX=x;
        this.centerPointY=y;
    }
    BufferedImage carImage;
    double centerPointX;
    double centerPointY;

}
