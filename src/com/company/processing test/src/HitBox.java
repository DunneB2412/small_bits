public class HitBox{
    final int OWidth, OHeight, IFraims;
    Vector position;

    HitBox(Vector position, int width, int height, int fraims){
        this.position = position;
        this.OWidth = width;
        this.OHeight = height;
        this.IFraims = fraims;
    }

    public Boolean collideWall(){
        if(position.X<OWidth/2 || position.X>width-(OWidth/2)
                || position.Y<OHeight/2 || position.Y>height-(OHeight/2)){
            return true;
        }
        return false;
    }
    public boolean collide(HitBox other){
        if(topLeft().X < other.bottomRight().X &&
                bottomRight().X > other.topLeft().X &&
                topLeft().Y < other.bottomRight().Y &&
                bottomRight().Y > other.topLeft().Y){
            return true;
        }
        return false;
    }
    public Vector topLeft(){
        return new Vector(position.X-(OWidth/2), position.Y-(OHeight/2));
    }
    public Vector bottomRight(){
        return new Vector(position.X+(OWidth/2), position.Y+(OHeight/2));
    }
    public void move(float newX, float newY){
        position.X = newX;
        position.Y = newY;
    }
}