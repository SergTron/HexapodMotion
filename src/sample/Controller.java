package sample;

 class Controller {

     void startSimulation() {
        int i = 0;

        Generator generator = new Generator();
        Calculator calculator = new Calculator();
        CartesianCoordinates cartesianCoordinates = new CartesianCoordinates();
        NormalCoordinates normalCoordinates = new NormalCoordinates();

        while (i <= 30){
            cartesianCoordinates = generator.getCoordinates();
            System.out.println("x="+cartesianCoordinates.x +" y="+ cartesianCoordinates.y +" z="+ cartesianCoordinates.z);

            normalCoordinates = calculator.calculate(cartesianCoordinates);
            System.out.println("angle1="+normalCoordinates.angle1*180/Math.PI+" angle2="+normalCoordinates.angle2*180/Math.PI+" angle3="+normalCoordinates.angle3*180/Math.PI);
            i++;
        }
    }
}
