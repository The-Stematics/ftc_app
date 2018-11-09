package org.firstinspires.ftc.teamcode.Year_2018_19.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Year_2018_19.Robot.MecanumDriveRobot;

@Autonomous(name = "TreadDriveAutonomous", group = "AutonomousMode")
//@Disabled

public class MecanumDriveAutonomous extends LinearOpMode
{
    private MecanumDriveRobot robot = new MecanumDriveRobot();

    public void runOpMode() throws InterruptedException
    {
        robot.init(hardwareMap);
        robot.playR2D2Sound(this.hardwareMap.appContext);

        if (gamepad1.x) {robot.state = 1;} //Left Alliance
        if (gamepad1.b) {robot.state = 2;} //Right Alliance

        telemetry.addData("Status", "Robot has initialied!");
        telemetry.addData("State", robot.state);
        telemetry.update();

        waitForStart();
        robot.playBB8Sound(this.hardwareMap.appContext);
        telemetry.addData("Status", "Robot has started!");
        telemetry.update();

        if (robot.state == 1)
        {
            LeftAlliance();
        }
        else if (robot.state == 2)
        {
            RightAlliance();
        }
        else
        {
            LeftAlliance();
        }

        sleep (1000);

        robot.playMusic(this.hardwareMap.appContext);

        while (opModeIsActive())
        {
            //robot.senseGyro();

            telemetry.addData("Left Drive", robot.frontLeftDrive.getPower());
            telemetry.addData("Right Drive", robot.frontRightDrive.getPower());
            telemetry.addData("Left Drive", robot.backLeftDrive.getPower());
            telemetry.addData("Right Drive", robot.backRightDrive.getPower());
            telemetry.addData("State", robot.state);

            /*telemetry.addData("Line Follower Red Color", robot.lineFollower.red());
            telemetry.addData("Line Follower Green Color", robot.lineFollower.green());
            telemetry.addData("Line Follower Blue Color", robot.lineFollower.blue());*/

            /*telemetry.addLine()
                    .addData("dx", formatRate(robot.rates.xRotationRate))
                    .addData("dy", formatRate(robot.rates.yRotationRate))
                    .addData("dz", "%s deg/s", formatRate(robot.rates.zRotationRate));
            telemetry.addData("angle", "%s deg", formatFloat(robot.zAngle));
            telemetry.addData("heading", "%3d deg", robot.heading);
            telemetry.addData("integrated Z", "%3d", robot.integratedZ);
            telemetry.addLine()
                    .addData("rawX", formatRaw(robot.rawX))
                    .addData("rawY", formatRaw(robot.rawY))
                    .addData("rawZ", formatRaw(robot.rawZ));
            telemetry.addLine().addData("z offset", robot.zAxisOffset).addData("z coeff", robot.zAxisScalingCoefficient);
            telemetry.update();*/

            telemetry.update();
        }

        robot.safetyStop();
    }

    private void LeftAlliance() throws InterruptedException
    {
        robot.driveForward(1, 3000);
    }

    private void RightAlliance() throws InterruptedException
    {
        robot.driveForward(0.5f, 2500);
        robot.rotateRight(0.5f, 2500);
        robot.driveForward(0.5f, 4000);
    }

    private String formatRaw(int rawValue) {
        return String.format("%d", rawValue);
    }

    private String formatRate(float rate) {
        return String.format("%.3f", rate);
    }

    private String formatFloat(float rate) {
        return String.format("%.3f", rate);
    }
}
