public class ColidableRectangle extends PhysicalObject{

    ColidableRectangle(int w, int h, Vector position){
        super(position, w, h);
    }


    public void show(Force eForce){
        tick(eForce);
        //fill(red,green,blue);
        rect(topLeft().X, topLeft().Y, 50, 50);
        if(collideWall()){
            wallPhase();
        }
    }

    private void wallPhase(){
        rect(topLeft().X+width, topLeft().Y, 50, 50);
        if(bottomRight().X+width<width){
            move(position.X+width, position.Y);
        }
        rect(topLeft().X, topLeft().Y+height, 50, 50);
        if(bottomRight().Y+height<height){
            move(position.X, position.Y+height);
        }
        rect(topLeft().X, topLeft().Y-height, 50, 50);
        if(topLeft().Y-height>0){
            move(position.X, position.Y-height);
        }
        rect(topLeft().X-width, topLeft().Y, 50, 50);
        if(topLeft().X-width>0){
            move(position.X-width, position.Y);
        }
        rect(topLeft().X+width, topLeft().Y+height, 50, 50);
        if(bottomRight().X+width<width&&bottomRight().Y+height<height){
            move(position.X+width, position.Y+height);
        }
        rect(topLeft().X+width, topLeft().Y-height, 50, 50);
        if(bottomRight().X+width<width&&topLeft().Y-height>0){
            move(position.X+width, position.Y-height);
        }
        rect(topLeft().X-width, topLeft().Y+height, 50, 50);
        if(topLeft().X-width>0&&bottomRight().Y+height<height){
            move(position.X-width, position.Y+height);
        }
        rect(topLeft().X-width, topLeft().Y-height, 50, 50);
        if(topLeft().X-width>0&&topLeft().Y-height>0){
            move(position.X-width, position.Y-height);
        }
}
