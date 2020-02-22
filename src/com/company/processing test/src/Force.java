public class Force{
    Vector momentom;
    float rotationPerTick, weight;
    Force(Vector momentom, float rpt, float weight){
        this.momentom = momentom;
        this.rotationPerTick = rpt;
        this.weight = weight;
    }
    public void add(Force other){
        this.momentom.add(other.momentom);
        this.rotationPerTick += other.rotationPerTick;
    }
}