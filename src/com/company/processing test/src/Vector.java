public class Vector{
    public float X, Y;
    public Vector(float x, float y){
        X=x;
        Y=y;
    }
    public void add(Vector other){
        this.X += other.X;
        this.Y += other.Y;
    }
}