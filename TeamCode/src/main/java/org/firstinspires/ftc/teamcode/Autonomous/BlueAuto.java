package org.firstinspires.ftc.teamcode.Autonomous;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Autonomous

public class BlueAuto extends OpMode {
    private Follower f = null;
    private Poses poses = new Poses();
    private int state = 0;
    private Path start, end;

    private PathChain intakeStrip1, returnStrip1, alignStrip2, intakeStrip2, returnStrip2, alignStrip3, intakeStrip3, END_POSE;

    private enum path {START, INTAKE1, RETURN1, ALIGN2, INTAKE2, RETURN2,  }

    @Override
    public void init() {
        f = Constants.createFollower(hardwareMap) ;
        f.setStartingPose(poses.START_POSE);
        buildPaths();

    }

    @Override
    public void loop() {
        f.update();
        autoPathUpdates();
    }

    public void buildPaths() {
        start = new Path(new BezierLine(poses.START_POSE, poses.ALIGN1));
        start.setLinearHeadingInterpolation(poses.START_POSE.getHeading() , poses.ALIGN1.getHeading());

        intakeStrip1 = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.ALIGN1, poses.INTAKE1)))
                .setConstantHeadingInterpolation(poses.INTAKE1.getHeading())
                .build();

        returnStrip1 = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.INTAKE1, poses.RETURN1)))
                .setLinearHeadingInterpolation(poses.INTAKE1.getHeading(),poses.RETURN1.getHeading())
                .build();

        alignStrip2 = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.RETURN1, poses.ALIGN2)))
                .setLinearHeadingInterpolation(poses.RETURN1.getHeading(),poses.ALIGN2.getHeading())
                .build();

        intakeStrip2 = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.ALIGN2, poses.INTAKE2)))
                .setLinearHeadingInterpolation(poses.ALIGN2.getHeading(),poses.INTAKE2.getHeading())
                .build();

        returnStrip2 = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.INTAKE2, poses.RETURN2)))
                .setLinearHeadingInterpolation(poses.RETURN1.getHeading(),poses.ALIGN2.getHeading())
                .build();

        alignStrip3 = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.RETURN2, poses.ALIGN3)))
                .setLinearHeadingInterpolation(poses.RETURN2.getHeading(),poses.ALIGN3.getHeading())
                .build();

        intakeStrip3 = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.ALIGN3, poses.INTAKE3)))
                .setLinearHeadingInterpolation(poses.ALIGN3.getHeading(),poses.INTAKE3.getHeading())
                .build();

        END_POSE = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.INTAKE3, poses.END_POSE)))
                .setLinearHeadingInterpolation(poses.INTAKE3.getHeading(),poses.END_POSE.getHeading())
                .build();








    }
    public void autoPathUpdates() {

        switch(state) {
            case 0:
                setPathStates(1);
                f.followPath(start);
                break;
            case 1:
                if (!f.isBusy()) {
                    setPathStates(2);
                    f.followPath(intakeStrip1);
                }
                break;
            case 2:
                if (!f.isBusy()) {
                    setPathStates(3);
                    f.followPath(returnStrip1);
                break;
                }
            case 3:
                if (!f.isBusy()) {
                    setPathStates(4);
                    f.followPath(alignStrip2);
                }
                break;
            case 4:
                if (!f.isBusy()) {
                    setPathStates(5);
                    f.followPath(intakeStrip2);
                }
            case 5:
                if (!f.isBusy()) {
                    setPathStates(6);
                    f.followPath(returnStrip2);
                }
            case 6:
                if (!f.isBusy()) {
                    setPathStates(7);
                    f.followPath(alignStrip3);
                }
            case 7:
                if (!f.isBusy()) {
                    setPathStates(8);
                    f.followPath(intakeStrip3);
                }
            break;

            case 8:
                if (!f.isBusy()) {
                    setPathStates(9);
                    f.followPath(end);
                }
            case 9:
                if (!f.isBusy()) {
                    setPathStates(-1);
                }

        }


    }

    public void setPathStates(int nextState){
         state = nextState;

    }




}
