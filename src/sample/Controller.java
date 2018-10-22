package sample;

 class Controller {
        private static boolean RobotActive = false;

     void startSimulation() throws InterruptedException {
         if (RobotActive == false) RobotActive = true;
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
try {
    Thread.sleep(300);
}
catch (Exception e){
    System.err.println(e);}

            if (RobotActive==false) break;
            i++;
        }
    }

    void stopSimulation(){
         RobotActive = false;
    }
}
