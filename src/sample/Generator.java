package sample;

public class Generator {
    CartesianCoordinates cartesianCoordinates = new CartesianCoordinates();
    // One oscilator cycle (whole circle) has 4 sub-cycles
    // each of them describes x and z coordinates increasing and decreasing:
    // in 1 subCycle x - up, z - up;
    // in 2 subCycle x - up, z - down;
    // in 3 subCycle x - down, z - down;
    // in 4 subCycle x - down, z - up;
    int subCycleNumber = 1;
    float iterationStep = 0.002f;

    double LimiterMaxX = 10.0;
    double LimiterMinX = -10.0;
    double LimiterMaxZ = -116.0;
    double LimiterMinZ = -127.0;

    Generator(){
        cartesianCoordinates.x = 0;
        cartesianCoordinates.y = 86.5;
        cartesianCoordinates.z = 0;
    }


    public CartesianCoordinates setCoordinates() {
        if
      cartesianCoordinates.x += iterationStep;
      cartesianCoordinates.y = 86.5;
      cartesianCoordinates.z += iterationStep ;
        return cartesianCoordinates;
    }
}
