package sample;

public class Generator {
    CartesianCoordinates cartesianCoordinates = new CartesianCoordinates();
    // One oscilator cycle (whole circle) has 4 sub-cycles
    // each of them describes x and z coordinates increasing and decreasing:
    // in 1 subCycle x - up, z - up;
    // in 2 subCycle x - up, z - down;
    // in 3 subCycle x - down, z - down;
    // in 4 subCycle x - down, z - up;
    private  int subCycleNumber = 1;
    private float iterationStep = 0.002f;
    //TODO Create possibility to change iteration step value

    double limiterMaxX = 10.0;
    double limiterMinX = -10.0;
    double limiterMaxZ = -116.0;
    double limiterMinZ = -127.0;
    int multiplierX = 1;
    int multiplierZ = 1;

    Generator(){
        cartesianCoordinates.x = 0;
        cartesianCoordinates.y = 86.5;
        cartesianCoordinates.z = -121.7;
    }

    public CartesianCoordinates getCoordinates() {
      updateMultipliers();
      cartesianCoordinates.x = cartesianCoordinates.x + (iterationStep * multiplierX);
      cartesianCoordinates.y = 86.5;
      cartesianCoordinates.z = cartesianCoordinates.z + (iterationStep * multiplierZ);
        return cartesianCoordinates;
    }

    private  void updateMultipliers() {
    if (cartesianCoordinates.x >= limiterMaxX) multiplierX = -1;
    else if (cartesianCoordinates.x <= limiterMinX) multiplierX = 1;
    if (cartesianCoordinates.z >= limiterMaxZ) multiplierZ = -1;
    else if (cartesianCoordinates.z <= limiterMinZ) multiplierZ = 1;
    }

}
