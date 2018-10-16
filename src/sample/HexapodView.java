package sample;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class HexapodView extends Application {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private double anchorX;
    private double anchorY;

    private double anchorAngleX = 0;
    private double anchorAngleY = 0;

    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Cylinder legL11 = createConnection(new Point3D(70,0,-50),new Point3D(70,-30,-100));
        Cylinder legL12 = createConnection(new Point3D(70,-30,-100),new Point3D(70,80,-100));

        Cylinder legL21 = createConnection(new Point3D(0,0,-50),new Point3D(0,-30,-100));
        Cylinder legL22 = createConnection(new Point3D(0,-30,-100),new Point3D(0,80,-100));

        Cylinder legL31 = createConnection(new Point3D(-70,0,-50),new Point3D(-70,-30,-100));
        Cylinder legL32 = createConnection(new Point3D(-70,-30,-100),new Point3D(-70,80,-100));

        Cylinder legR11 = createConnection(new Point3D(70,0,50),new Point3D(70,-30,100));
        Cylinder legR12 = createConnection(new Point3D(70,-30,100),new Point3D(70,80,100));

        Cylinder legR21 = createConnection(new Point3D(0,0,50),new Point3D(0,-30,100));
        Cylinder legR22 = createConnection(new Point3D(0,-30,100),new Point3D(0,80,100));

        Cylinder legR31 = createConnection(new Point3D(-70,0,50),new Point3D(-70,-30,100));
        Cylinder legR32 = createConnection(new Point3D(-70,-30,100),new Point3D(-70,80,100));

        Box box = new Box(210,30,100);
        box.setTranslateX(0);
        box.setTranslateY(0);
        box.setTranslateZ(0);

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setSpecularColor(Color.ORANGE);
        redMaterial.setDiffuseColor(Color.RED);
        box.setMaterial(redMaterial);

        Group group = new Group();
        group.getChildren().addAll(box,
                legL11,legL12,legL21,legL22,legL31,legL32,
                legR11,legR12,legR21,legR22,legR31,legR32);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1300);
        camera.setFieldOfView(35);
        camera.setNearClip(1);
        camera.setFarClip(3000);



        Scene scene = new Scene(group, WIDTH, HEIGHT,true);
        scene.setFill(Color.AZURE);
        scene.setCamera(camera);

        initMouseControl(group,scene);

        primaryStage.setTitle("Hexapod Robot 3D");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void initMouseControl(Group group, Scene scene) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());

        });
    }
    public static void main(String[] args) {
        launch(args);
           }


    public Cylinder createConnection(Point3D origin, Point3D target) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);


        Cylinder line = new Cylinder(5, height);

        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        return line;
    }
}