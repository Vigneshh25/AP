package cricbuzz.entity;

import cricbuzz.enums.WicketType;
import cricbuzz.innings.*;
import cricbuzz.player.Player;

public class Wicket {
    public WicketType wicketType;
    public Player takenBy;
    public OverDetails overDetail;
    public Ball ballDetail;

    public Wicket(WicketType wicketType, Player takenBy, OverDetails overDetail, Ball ballDetail){

        this.wicketType = wicketType;
        this.takenBy = takenBy;
        this.overDetail = overDetail;
        this.ballDetail = ballDetail;
    }

}
