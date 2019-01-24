package sample;

// One oslculation cycle (whole circle) has 4 sub-cycles
// each of them describes x and z coordinates increasing and decreasing:
// in 1 subCycle x - up, z - up;
// in 2 subCycle x - up, z - down;
// in 3 subCycle x - down, z - down;
// in 4 subCycle x - down, z - up;

public class Generator {
    CartesianCoordinates cartesianCoordinates = new CartesianCoordinates();

    private  int subCycleNumber = 1;
    private float iterationStep = 0.1f;

    double limiterMaxX = 10.0;
    double limiterMinX = -10.0;
    double limiterMaxZ = -116.0;
    double limiterMinZ = -126.0;

    int multiplierX = 1;
    int multiplierZ = 1;

    Generator(){
        cartesianCoordinates.x = 0;
        cartesianCoordinates.y = 86.5;
        cartesianCoordinates.z = -121;
    }

    //Basically x get it Limiter two times slower that Z
    // x should get from 0 to 10 (10 steps with iterationStep = 1 .
    // In the same time Z should get from -121 to -116 (5 steps with iterationStep = 1)
    //this why for Z coordinate iterationStep are divided by 2 in calculations;
    public CartesianCoordinates getCoordinates() {
      cartesianCoordinates.x = cartesianCoordinates.x + (iterationStep * multiplierX);
      cartesianCoordinates.y = 86.5;
      cartesianCoordinates.z = cartesianCoordinates.z + (iterationStep/2 * multiplierZ);
      updateMultipliers();

      return cartesianCoordinates;
    }

    private  void updateMultipliers() {
        if (cartesianCoordinates.x >= limiterMaxX) multiplierX = -1;
        else if (cartesianCoordinates.x <= limiterMinX) multiplierX = 1;
        if (cartesianCoordinates.z >= limiterMaxZ) multiplierZ = -1;
        else if (cartesianCoordinates.z <= limiterMinZ) multiplierZ = 1;
    }
}
