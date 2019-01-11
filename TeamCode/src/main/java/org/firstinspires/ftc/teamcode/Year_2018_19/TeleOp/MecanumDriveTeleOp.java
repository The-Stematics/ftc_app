package org.firstinspires.ftc.teamcode.Year_2018_19.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Year_2018_19.Robot.MecanumDriveRobot;

@TeleOp(name="MecanumDriveTeleOp", group="TeleOpMode")
//@Disabled

public class MecanumDriveTeleOp extends OpMode
{
    //Creates robot hardware.
    private MecanumDriveRobot robot = new MecanumDriveRobot();

    //Robot initiates hardware and components.
    public void init()
    {
        robot.init(hardwareMap);
        robot.playR2D2Sound(this.hardwareMap.appContext);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Status", "Robot has initiated!");
        telemetry.update();
    }

    //Robot starts.
    public void start()
    {
        robot.playBB8Sound(this.hardwareMap.appContext);
        telemetry.addData("Status", "Robot has started!");
        telemetry.update();
        //robot.teamMarkerDropper.setPosition(1);
    }

    //Called repeatedly after robot starts.
    public void loop()
    {
        double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y); //Added -
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4; //Added -
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        robot.frontLeftDrive.setPower(v1);
        robot.frontRightDrive.setPower(v2);
        robot.backLeftDrive.setPower(v3);
        robot.backRightDrive.setPower(v4);

        ////////////////////////////////////////////////////////////////////////////////////////////
        if (gamepad1.left_bumper)
        {
            robot.boxSlammer.setPower(1);
        }
        if (gamepad1.right_bumper)
        {
            robot.boxSlammer.setPower(-1);
        }
        robot.boxSlammer.setPower(0);

        robot.boxArm.setPower(gamepad1.left_trigger + -gamepad1.right_trigger);
        robot.secondaryArmMotor.setPower(gamepad1.left_trigger + -gamepad1.right_trigger);


        /*if (this.gamepad1.x) {
            robot.playMusic(this.hardwareMap.appContext);
        }
        else if (gamepad1.y) {
            robot.stopMusic();
        }*/

        telemetry.addData("Front Left Drive", robot.frontLeftDrive.getPower());
        telemetry.addData("Front Right Drive", robot.frontRightDrive.getPower());
        telemetry.addData("Back Left Drive", robot.backLeftDrive.getPower());
        telemetry.addData("Back Right Drive", robot.backRightDrive.getPower());

        /*telemetry.addData("Gyro Sensor X", robot.gyroSensor.rawX());
        telemetry.addData("Gyro Sensor Y", robot.gyroSensor.rawY());
        telemetry.addData("Gyro Sensor Z", robot.gyroSensor.rawZ());

        telemetry.addData("Line Follower Red Color", robot.lineFollower.red());
        telemetry.addData("Line Follower Green Color", robot.lineFollower.green());
        telemetry.addData("Line Follower Blue Color", robot.lineFollower.blue());*/

        telemetry.update();
    }

    //Robot ends.
    public void stop()
    {
        robot.safetyStop();
        telemetry.addData("Status", "Robot has stopped!");
        telemetry.update();
    }
}