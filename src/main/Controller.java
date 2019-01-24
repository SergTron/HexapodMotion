package main;

 class Controller {
     private static boolean robotActive = false; //main flag which relate to buttons in user interface
     Generator generator = new Generator();
     Calculator calculator = new Calculator();

     //Simple way to getCoordinates
     CartesianCoordinates getNewCoordinates() throws InterruptedException {

        int i = 0;
        CartesianCoordinates cartesianCoordinates = new CartesianCoordinates();
        NormalCoordinates normalCoordinates = new NormalCoordinates();

         if (robotActive ==true) {
             cartesianCoordinates = generator.getCoordinates();
             System.out.println("x=" + cartesianCoordinates.x + " y=" + cartesianCoordinates.y + " z=" + cartesianCoordinates.z);
         }

         return cartesianCoordinates;

     }


     NormalCoordinates getNewCoordinatesAngle() throws InterruptedException {

         int i = 0;
         CartesianCoordinates cartesianCoordinates = new CartesianCoordinates();
         NormalCoordinates normalCoordinates = new NormalCoordinates();
         NormalCoordinates normalCoordinatesDeg = new NormalCoordinates();

         if (robotActive ==true) {
             cartesianCoordinates = generator.getCoordinates();
             normalCoordinates = calculator.calculate(cartesianCoordinates);
             normalCoordinatesDeg = calculator.calculateDeg(cartesianCoordinates);

         }

         return normalCoordinates;

     }


    Boolean getStatus() {
         return robotActive;
    }
    void setStatus(Boolean status) {
         robotActive = status;
    }
     void stopSimulation() {
         robotActive = false;
     }

}
