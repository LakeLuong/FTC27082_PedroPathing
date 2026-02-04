package org.firstinspires.ftc.teamcode.Autonomous;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


public class Auto extends OpMode {
    private Follower f = null;
    private Poses poses = new Poses();
    private int state = 0;
    private Path start, end;

    private PathChain one, two, three, four, five, six, seven;


    @Override
    public void init() {
        f = Constants.createFollower(hardwareMap) ;
        f.setStartingPose(poses.START_POSE);

    }

    @Override
    public void loop() {

    }

    public void buildPaths() {
        start = new Path(new BezierLine(poses.START_POSE, poses.ALIGN1));
        start.setLinearHeadingInterpolation(poses.START_POSE.getHeading() , poses.ALIGN1.getHeading());

        one = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.ALIGN1, poses.INTAKE1)))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        two = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.INTAKE1, poses.RETURN1)))
                .setLinearHeadingInterpolation(poses.INTAKE1.getHeading(),poses.RETURN1.getHeading())
                .build();

        three = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.RETURN1, poses.ALIGN2)))
                .setLinearHeadingInterpolation(poses.RETURN1.getHeading(),poses.ALIGN2.getHeading())
                .build();

        four = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.ALIGN2, poses.INTAKE2)))
                .setLinearHeadingInterpolation(poses.ALIGN2.getHeading(),poses.INTAKE2.getHeading())
                .build();

        five = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.INTAKE2, poses.RETURN2)))
                .setLinearHeadingInterpolation(poses.RETURN1.getHeading(),poses.ALIGN2.getHeading())
                .build();

        six = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.RETURN2, poses.ALIGN3)))
                .setLinearHeadingInterpolation(poses.RETURN2.getHeading(),poses.ALIGN3.getHeading())
                .build();

        seven = f.pathBuilder()
                .addPath(new Path(new BezierLine(poses.INTAKE3, poses.END_POSE)))
                .setLinearHeadingInterpolation(poses.INTAKE3.getHeading(),poses.END_POSE.getHeading())
                .build();







    }

    public void autoPathUpdates() {
        switch(state) {
            case 0:
                f.followPath(start);



        }


    }




}
