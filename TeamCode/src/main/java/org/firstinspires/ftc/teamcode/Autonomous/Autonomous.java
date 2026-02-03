package org.firstinspires.ftc.teamcode.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.nio.file.Path;

public class Autonomous extends OpMode {
    private Follower f = null;

    private int state = 0;

    private Path start, end;
    private PathChain one;


    @Override
    public void init() {
        f = Constants.createFollower(hardwareMap);
        f.setStartingPose(startPos);

    }

    @Override
    public void loop() {

    }

    public void buildPaths() {
        start = new Path(new BezierLine(startPos, endPos));
    }

    public void autoPathUpdates() {
        switch(state) {
            case 0:

        }


    }




}
