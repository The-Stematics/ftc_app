package org.firstinspires.ftc.teamcode.Year_2018_19.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
        telemetry.addData("Status", "Robot has initiated!");
        telemetry.update();
    }

    //Robot starts.
    public void start()
    {
        robot.playBB8Sound(this.hardwareMap.appContext);
        telemetry.addData("Status", "Robot has started!");
        telemetry.update();
    }

    //Called repeatedly after robot starts.
    public void loop()
    {
        if (gamepad1.left_bumper) //If left bumper pressed
        {
            //Power low
            robot.drivePower = 0.5;
        }
        if (gamepad1.right_bumper) //If right bumper pressed
        {
            //Power high
            robot.drivePower = 1;
        }

        if (gamepad1.left_stick_y <= -0.5) //If left stick forward
        {
            //robot.drivePower = gamepad1.left_stick_y;
            //Move forward
            robot.frontLeftDrive.setPower(robot.drivePower);
            robot.frontRightDrive.setPower(robot.drivePower);
            robot.backLeftDrive.setPower(robot.drivePower);
            robot.backRightDrive.setPower(robot.drivePower);
        }
        if (gamepad1.left_stick_y >= 0.5) //If left stick backward
        {
            //robot.drivePower = gamepad1.left_stick_y;
            //Move backward
            robot.frontLeftDrive.setPower(-robot.drivePower);
            robot.frontRightDrive.setPower(-robot.drivePower);
            robot.backLeftDrive.setPower(-robot.drivePower);
            robot.backRightDrive.setPower(-robot.drivePower);
        }

        if (gamepad1.left_stick_x <= -0.5) //If left stick left
        {
            //robot.drivePower = gamepad1.left_stick_x;
                    //Strafe left
            robot.frontLeftDrive.setPower(robot.drivePower);
            robot.frontRightDrive.setPower(-robot.drivePower);
            robot.backLeftDrive.setPower(-robot.drivePower);
            robot.backRightDrive.setPower(robot.drivePower);
        }
        if (gamepad1.left_stick_x >= 0.5) //If left stick right
        {
            //robot.drivePower = gamepad1.left_stick_x;
            //Strafe right
            robot.frontLeftDrive.setPower(-robot.drivePower);
            robot.frontRightDrive.setPower(robot.drivePower);
            robot.backLeftDrive.setPower(robot.drivePower);
            robot.backRightDrive.setPower(-robot.drivePower);
        }

        if(gamepad1.right_stick_x <= -0.5) //If right stick left
        {
            //robot.drivePower = gamepad1.right_stick_x;
            //Rotate left
            robot.frontLeftDrive.setPower(robot.drivePower);
            robot.frontRightDrive.setPower(-robot.drivePower);
            robot.backLeftDrive.setPower(robot.drivePower);
            robot.backRightDrive.setPower(-robot.drivePower);
        }
        if (gamepad1.right_stick_x >= 0.5) //If right stick right
        {
            //robot.drivePower = gamepad1.right_stick_x;
            //Rotate right
            robot.frontLeftDrive.setPower(-robot.drivePower);
            robot.frontRightDrive.setPower(robot.drivePower);
            robot.backLeftDrive.setPower(-robot.drivePower);
            robot.backRightDrive.setPower(robot.drivePower);
        }

        robot.frontLeftDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
        robot.backRightDrive.setPower(0);

        if (this.gamepad1.x) {
            robot.playMusic(this.hardwareMap.appContext);
        }
        else if (gamepad1.y) {
            robot.stopMusic();
        }

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