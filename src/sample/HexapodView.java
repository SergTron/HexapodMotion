package sample;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private Point3D pointL11Start = new Point3D(70,0,-50);
    private Point3D pointL11End = new Point3D(70,-30,-100);
    private Point3D pointL12Start = new Point3D(70,-30,-100);
    private Point3D pointL12End = new Point3D(70,80,-100);

    private Point3D pointL21Start = new Point3D(0,0,-50);
    private Point3D pointL21End = new Point3D(0,-30,-100);
    private Point3D pointL22Start = new Point3D(0,-30,-100);
    private Point3D pointL22End = new Point3D(0,80,-100);

    private Point3D pointL31Start = new Point3D(-70,0,-50);
    private Point3D pointL31End = new Point3D(-70,-30,-100);
    private Point3D pointL32Start = new Point3D(-70,-30,-100) ;
    private Point3D pointL32End = new Point3D(-70,80,-100);

    private Point3D pointR11Start = new Point3D(70,0,50);
    private Point3D pointR11End = new Point3D(70,-30,100);
    private Point3D pointR12Start = new Point3D(70,-30,100) ;
    private Point3D pointR12End = new Point3D(70,80,100);

    private Point3D pointR21Start = new Point3D(0,0,50);
    private Point3D pointR21End = new Point3D(0,-30,100);
    private Point3D pointR22Start = new Point3D(0,-30,100);
    private Point3D pointR22End = new Point3D(0,80,100);

    private Point3D pointR31Start = new Point3D(-70,0,50);
    private Point3D pointR31End = new Point3D(-70,-30,100);

    private Point3D pointR32Start = new Point3D(-70,-30,100);
    private Point3D pointR32End = new Point3D(-70,80,100);

    private Cylinder legL11 = new Cylinder();
    private Cylinder legL12 = new Cylinder();
    private Cylinder legL21 = new Cylinder();
    private Cylinder legL22 = new Cylinder();
    private Cylinder legL31 = new Cylinder();
    private Cylinder legL32 = new Cylinder();
    private Cylinder legR11 = new Cylinder();
    private Cylinder legR12 = new Cylinder();
    private Cylinder legR21 = new Cylinder();
    private Cylinder legR22 = new Cylinder();
    private Cylinder legR31 = new Cylinder();
    private Cylinder legR32 = new Cylinder();

    Rotate motionRLegL11 = new Rotate(0, pointL11End.subtract(pointL11Start));
    Rotate motionRLegL21 = new Rotate(0, pointL21End.subtract(pointL21Start));
    Rotate motionRLegL31 = new Rotate(0, pointL31End.subtract(pointL31Start));
    Rotate motionRLegR11 = new Rotate(0, pointR11End.subtract(pointR11Start));
    Rotate motionRLegR21 = new Rotate(0, pointR21End.subtract(pointR21Start));
    Rotate motionRLegR31 = new Rotate(0, pointR31End.subtract(pointR31Start));

    Translate motionTLegL11 = new Translate();
  //  Translate motionTLegL12 = new Translate();
    Translate motionTLegL21 = new Translate();
   // Translate motionTLegL22 = new Translate();
    Translate motionTLegL31 = new Translate();
   // Translate motionTLegL32 = new Translate();
    Translate motionTLegR11 = new Translate();
   // Translate motionTLegR12 = new Translate();
    Translate motionTLegR21 = new Translate();
   // Translate motionTLegR22 = new Translate();
    Translate motionTLegR31 = new Translate();
   // Translate motionTLegR32 = new Translate();


    Group groupBox = new Group();
    Group groupLegs1 = new Group();
    Group groupLegs2 = new Group();
    Group groupRobot = new Group();
    Group groupButoon = new Group();
    Group mainGroup = new Group();
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;

    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creating Left legs
         legL11 = createConnection(pointL11Start ,pointL11End);
         legL12 = createConnection(pointL12Start ,pointL12End);

         legL21 = createConnection(pointL21Start,pointL21End);
         legL22 = createConnection(pointL22Start,pointL22End);

         legL31 = createConnection(pointL31Start,pointL31End);
         legL32 = createConnection(pointL32Start,pointL32End);

        //Creating Right Legs
         legR11 = createConnection(pointR11Start,pointR11End);
         legR12 = createConnection(pointR12Start,pointR12End);

         legR21 = createConnection(pointR21Start,pointR21End);
         legR22 = createConnection(pointR22Start,pointR22End);

         legR31 = createConnection(pointR31Start,pointR31End);
         legR32 = createConnection(pointR32Start,pointR32End);

        //Creating Robot Base construction
        Box box = new Box(210,30,100);
        box.setTranslateX(0);
        box.setTranslateY(0);
        box.setTranslateZ(0);

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setSpecularColor(Color.ORANGE);
        redMaterial.setDiffuseColor(Color.RED);
        box.setMaterial(redMaterial);


        //Creating play button
        Button playButton = new Button("Play");
        playButton.setLayoutX(300);
        playButton.setLayoutY(250);

        //Creating stop button
        Button stopButton = new Button("Stop");
        stopButton.setLayoutX(250);
        stopButton.setLayoutY(250);


        groupBox.getChildren().addAll(box);
        groupLegs1.getChildren().addAll(legL11,legL12,legR21,legR22,legL31,legL32);
        groupLegs2.getChildren().addAll(legR11,legR12,legL21,legL22,legR31,legR32);

        legL11.getTransforms().addAll(motionRLegL11,motionTLegL11);
     //   legL12.getTransforms().add(motionTLegL12);
        legL21.getTransforms().addAll(motionRLegL21,motionTLegL21);
      //  legL22.getTransforms().add(motionTLegL22);
        legL31.getTransforms().addAll(motionRLegL31,motionTLegL31);
      //  legL32.getTransforms().add(motionTLegL32);
        legR11.getTransforms().addAll(motionRLegR11,motionTLegR11);
      //  legR12.getTransforms().add(motionTLegR12);
        legR21.getTransforms().addAll(motionRLegR21,motionTLegR21);
       // legR22.getTransforms().add(motionTLegR22);
        legR31.getTransforms().addAll(motionRLegR31,motionTLegR31);
      //  legR32.getTransforms().add(motionTLegR32);
        groupRobot.getChildren().addAll(groupBox,groupLegs1,groupLegs2);


        groupButoon.getChildren().addAll(playButton, stopButton);

        mainGroup.getChildren().addAll(groupRobot,groupButoon);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1300);
        camera.setFieldOfView(35);
        camera.setNearClip(1);
        camera.setFarClip(3000);


        Scene scene = new Scene(mainGroup, WIDTH, HEIGHT,true);
        scene.setFill(Color.AZURE);
        scene.setCamera(camera);

        initMouseControl(groupRobot, scene);

        primaryStage.setTitle("Hexapod Robot 3D");
        primaryStage.setScene(scene);
        primaryStage.show();


        playButton.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws Exception {

                    controller.setStatus(true);
                    //Rules to continue
                    // Coordinates c  = controller.calculateCoordinate();
                    NormalCoordinates coord = controller.getNewCoordinatesAngle();
                    System.out.println(coord.angle1);

                    //New logic
                    //Step 1
                    //Step 1 Group1
                    motionRLegL11.angleProperty().set(motionRLegL11.angleProperty().getValue()+30);
                    legL11.setTranslateX(legL11.getTranslateX() + 13);
                    legL11.setTranslateY(legL11.getTranslateY() + 5);
                    legL11.setTranslateZ(legL11.getTranslateZ() + 6);
                    legL12.setTranslateX(legL12.getTranslateX() + 26);
                    legL12.setTranslateY(legL12.getTranslateY() + 3);
                    legL12.setTranslateZ(legL12.getTranslateZ() + 6);
                    legL12.setHeight(legL12.getHeight() - 7);

                    motionRLegR21.angleProperty().set(motionRLegR21.angleProperty().getValue()-30);
                    legR21.setTranslateX(legR21.getTranslateX() + 13);
                    legR21.setTranslateY(legR21.getTranslateY() + 5);
                    legR21.setTranslateZ(legR21.getTranslateZ() - 5);
                    legR22.setTranslateX(legR22.getTranslateX() + 26);
                    legR22.setTranslateY(legR22.getTranslateY() + 3);
                    legR22.setTranslateZ(legR22.getTranslateZ() - 5);
                    legR22.setHeight(legR22.getHeight() - 7);

                    motionRLegL31.angleProperty().set(motionRLegL31.angleProperty().getValue()+30);
                    legL31.setTranslateX(legL31.getTranslateX() + 13);
                    legL31.setTranslateY(legL31.getTranslateY() + 5);
                    legL31.setTranslateZ(legL31.getTranslateZ() + 6);
                    legL32.setTranslateX(legL32.getTranslateX() + 26);
                    legL32.setTranslateY(legL32.getTranslateY() + 3);
                    legL32.setTranslateZ(legL32.getTranslateZ() + 6);
                    legL32.setHeight(legL32.getHeight() - 7);

                    Thread.sleep(2000);
                    //Second step
                    //Group1
                    motionRLegL11.angleProperty().set(motionRLegL11.angleProperty().getValue() - 60);
                    legL11.setTranslateX(legL11.getTranslateX() - 26);
                    legL12.setTranslateX(legL12.getTranslateX() - 52);

                    motionRLegR21.angleProperty().set(motionRLegR21.angleProperty().getValue() + 60);
                    legR21.setTranslateX(legR21.getTranslateX() - 26);
                    legR22.setTranslateX(legR22.getTranslateX() - 52);

                    motionRLegL31.angleProperty().set(motionRLegL31.angleProperty().getValue() - 60);
                    legL31.setTranslateX(legL31.getTranslateX() - 26);
                    legL32.setTranslateX(legL32.getTranslateX() - 52);
                    //Group 2
                    legR12.setHeight(legR12.getHeight() - 7);
                    legL22.setHeight(legL22.getHeight() - 7);
                    legR32.setHeight(legR32.getHeight() - 7);
                    motionRLegR11.angleProperty().set(motionRLegR11.angleProperty().getValue()-30);
                    legR11.setTranslateX(legR11.getTranslateX() + 13);
                    legR11.setTranslateY(legR11.getTranslateY() + 5);
                    legR11.setTranslateZ(legR11.getTranslateZ() - 5);
                    legR12.setTranslateX(legR12.getTranslateX() + 26);
                    legR12.setTranslateY(legR12.getTranslateY() + 3);
                    legR12.setTranslateZ(legR12.getTranslateZ() - 5);

                    motionRLegL21.angleProperty().set(motionRLegL21.angleProperty().getValue()+30);
                    legL21.setTranslateX(legL21.getTranslateX() + 13);
                    legL21.setTranslateY(legL21.getTranslateY() + 5);
                    legL21.setTranslateZ(legL21.getTranslateZ() + 6);
                    legL22.setTranslateX(legL22.getTranslateX() + 26);
                    legL22.setTranslateY(legL22.getTranslateY() + 3);
                    legL22.setTranslateZ(legL22.getTranslateZ() + 6);


                    motionRLegR31.angleProperty().set(motionRLegR31.angleProperty().getValue()-30);
                    legR31.setTranslateX(legR31.getTranslateX() + 13);
                    legR31.setTranslateY(legR31.getTranslateY() + 5);
                    legR31.setTranslateZ(legR31.getTranslateZ() - 5);
                    legR32.setTranslateX(legR32.getTranslateX() + 26);
                    legR32.setTranslateY(legR32.getTranslateY() + 3);
                    legR32.setTranslateZ(legR32.getTranslateZ() - 5);
                    Thread.sleep(2000);
                    int counter = 2;
                    // while (!controller.getStatus()) {
                    while (controller.getStatus()) {
                        //Step 2,4,6....

                        if (counter % 2 == 0) {
                            //Group 1
                            motionRLegL11.angleProperty().set(motionRLegL11.angleProperty().getValue() + 60);
                            legL11.setTranslateX(legL11.getTranslateX() + 26);
                            legL12.setTranslateX(legL12.getTranslateX() + 52);

                            motionRLegR21.angleProperty().set(motionRLegR21.angleProperty().getValue() - 60);
                            legR21.setTranslateX(legR21.getTranslateX() + 26);
                            legR22.setTranslateX(legR22.getTranslateX() + 52);

                            motionRLegL31.angleProperty().set(motionRLegL31.angleProperty().getValue() + 60);
                            legL31.setTranslateX(legL31.getTranslateX() + 26);
                            legL32.setTranslateX(legL32.getTranslateX() + 52);

                            //Group 2
                            motionRLegR11.angleProperty().set(motionRLegR11.angleProperty().getValue()+60);
                            legR11.setTranslateX(legR11.getTranslateX() - 26);
                            legR12.setTranslateX(legR12.getTranslateX() - 52);

                            motionRLegL21.angleProperty().set(motionRLegL21.angleProperty().getValue()-60);
                            legL21.setTranslateX(legL21.getTranslateX() - 26);
                            legL22.setTranslateX(legL22.getTranslateX() - 52);

                            motionRLegR31.angleProperty().set(motionRLegR31.angleProperty().getValue()+60);
                            legR31.setTranslateX(legR31.getTranslateX() - 26);
                            legR32.setTranslateX(legR32.getTranslateX() - 52);
                            counter++;
                            Thread.sleep(2000);

                        } else {
                            //Group 1
                            motionRLegL11.angleProperty().set(motionRLegL11.angleProperty().getValue() - 60);
                            legL11.setTranslateX(legL11.getTranslateX() - 26);
                            legL12.setTranslateX(legL12.getTranslateX() - 52);

                            motionRLegR21.angleProperty().set(motionRLegR21.angleProperty().getValue() + 60);
                            legR21.setTranslateX(legR21.getTranslateX() - 26);
                            legR22.setTranslateX(legR22.getTranslateX() - 52);

                            motionRLegL31.angleProperty().set(motionRLegL31.angleProperty().getValue() - 60);
                            legL31.setTranslateX(legL31.getTranslateX() - 26);
                            legL32.setTranslateX(legL32.getTranslateX() - 52);

                            //Group 2
                            motionRLegR11.angleProperty().set(motionRLegR11.angleProperty().getValue()-60);
                            legR11.setTranslateX(legR11.getTranslateX() + 26);
                            legR12.setTranslateX(legR12.getTranslateX() + 52);

                            motionRLegL21.angleProperty().set(motionRLegL21.angleProperty().getValue()+60);
                            legL21.setTranslateX(legL21.getTranslateX() + 26);
                            legL22.setTranslateX(legL22.getTranslateX() + 52);

                            motionRLegR31.angleProperty().set(motionRLegR31.angleProperty().getValue()-60);
                            legR31.setTranslateX(legR31.getTranslateX() + 26);
                            legR32.setTranslateX(legR32.getTranslateX() + 52);
                            counter++;
                            Thread.sleep(2000);
                        }
                    }

                    resetCoordinates();
                    return null;
                }
            };
           new Thread(task).start();
        });

        stopButton.setOnAction(event -> {
            controller.stopSimulation();
         });
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

    void resetCoordinates(){
        motionRLegL11.angleProperty().set(0);
        motionRLegL21.angleProperty().set(0);
        motionRLegL31.angleProperty().set(0);
        motionRLegR11.angleProperty().set(0);
        motionRLegR21.angleProperty().set(0);
        motionRLegR31.angleProperty().set(0);
        legL11.setTranslateX(pointL11End.getX()-pointL11Start.getX());
        legL12.setTranslateX(pointL12End.getX()-pointL12Start.getX());
        legL21.setTranslateX(pointL21End.getX()-pointL21Start.getX());
        legL22.setTranslateX(pointL22End.getX()-pointL22Start.getX());
        legL31.setTranslateX(pointL31End.getX()-pointL31Start.getX());
        legL32.setTranslateX(pointL32End.getX()-pointL32Start.getX());
        legR11.setTranslateX(pointR11End.getX()-pointR11Start.getX());
        legR12.setTranslateX(pointR12End.getX()-pointR12Start.getX());
        legR21.setTranslateX(pointR21End.getX()-pointR21Start.getX());
        legR22.setTranslateX(pointR22End.getX()-pointR22Start.getX());
        legR31.setTranslateX(pointR31End.getX()-pointR31Start.getX());
        legR32.setTranslateX(pointR32End.getX()-pointR32Start.getX());

    }
    public static void main(String[] args) {
        launch(args);
           }
           //JavaFx isn't able to draw Line3D based on coordinates x1,y1,z1 and x2,y2,z2
           // this function is realising this functionality
    private Cylinder createConnection(Point3D origin, Point3D target) {
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