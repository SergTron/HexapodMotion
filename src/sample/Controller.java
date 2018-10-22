package sample;

 class Controller {
     private static boolean robotActive = false;
     Generator generator = new Generator();
     Calculator calculator = new Calculator();
     Controller(){
         robotActive = true;

     }

     //Simple way to getCoordinates
     CartesianCoordinates getNewCoordinates() throws InterruptedException {

        int i = 0;


        CartesianCoordinates cartesianCoordinates = new CartesianCoordinates();
        NormalCoordinates normalCoordinates = new NormalCoordinates();

         if (robotActive ==true) {
             cartesianCoordinates = generator.getCoordinates();
             System.out.println("x=" + cartesianCoordinates.x + " y=" + cartesianCoordinates.y + " z=" + cartesianCoordinates.z);
//
//             normalCoordinates = calculator.calculate(cartesianCoordinates);
//             System.out.println("angle1=" + normalCoordinates.angle1 * 180 / Math.PI + " angle2=" + normalCoordinates.angle2 * 180 / Math.PI + " angle3=" + normalCoordinates.angle3 * 180 / Math.PI);
         }
         return cartesianCoordinates;

     }


     //TODO
//     CartesianCoordinates getNewCoordinatesWithAngles() throws InterruptedException {
//         if (robotActive == false) robotActive = true;
//        int i = 0;
//
//        Generator generator = new Generator();
//        Calculator calculator = new Calculator();
//        CartesianCoordinates cartesianCoordinates = new CartesianCoordinates();
//        NormalCoordinates normalCoordinates = new NormalCoordinates();
//
//         if (robotActive ==true) {
//             cartesianCoordinates = generator.getCoordinates();
//             System.out.println("x=" + cartesianCoordinates.x + " y=" + cartesianCoordinates.y + " z=" + cartesianCoordinates.z);
//
//             normalCoordinates = calculator.calculate(cartesianCoordinates);
//             System.out.println("angle1=" + normalCoordinates.angle1 * 180 / Math.PI + " angle2=" + normalCoordinates.angle2 * 180 / Math.PI + " angle3=" + normalCoordinates.angle3 * 180 / Math.PI);
//         }
//         return cartesianCoordinates;
//
//     }

    void stopSimulation(){
         robotActive = false;
    }

    Boolean getStatus() {
         return robotActive;
    }
    void setStatus(Boolean status){
         robotActive = status;
    }
}
