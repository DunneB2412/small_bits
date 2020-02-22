public class PhysicalObject extends HitBox{
    Force localForce;
    PhysicalObject(Vector position, int w, int h){
        super(position, w, h, 0);
        localForce = new Force(new Vector(0,0), 0, 0);
    }
    protected void tick(Force eForce){
        this.localForce.add(eForce);
        position.add(localForce.momentom);
    }
}