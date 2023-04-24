package model.Player.Tool;

public class Torch extends Tool{
    private boolean have_torch = false; // false
    private boolean torch_new_day = false; // false

    // getters and setters:
    // have torch:
    public boolean isHave_torch() {
        return have_torch;
    }
    public void setHave_torch(boolean have_torch) {
        this.have_torch = have_torch;
    }
    // torch new day:
    public boolean isTorch_new_day() {
        return torch_new_day;
    }
    public void setTorch_new_day(boolean torch_new_day) {
        this.torch_new_day = torch_new_day;
    }
}
