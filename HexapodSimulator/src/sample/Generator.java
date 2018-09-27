package sample;

public class Generator {
    Generator(){
    }


    //Now any I in parameters!
    public CartesianCoordinates createCoordinates(CartesianCoordinates cartesianCoordinates,int i)
    {
      cartesianCoordinates.x = 1;
      cartesianCoordinates.y = 86.5;
      cartesianCoordinates.z = -121.5;
        return cartesianCoordinates;
    }
}
