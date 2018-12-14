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

        ////////////////////////////////////////////////////////////////////////////////////////////

        /*if (gamepad1.left_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) { // Drive forward given the user ain't trying to strafe
            robot.frontLeftDrive.setPower(-gamepad1.left_stick_y);
            robot.frontRightDrive.setPower(-gamepad1.right_stick_y);
            robot.backLeftDrive.setPower(-gamepad1.left_stick_y);
            robot.backRightDrive.setPower(-gamepad1.right_stick_y);
        }
        if (gamepad1.right_stick_x >= 0.1 && gamepad1.left_stick_x > -0.1) //Strafing right
        {
            //robot.frontLeftDrive.setPower(gamepad1.left_stick_x);
            robot.frontRightDrive.setPower(1 * gamepad1.left_stick_x);
            robot.backLeftDrive.setPower(-1 * gamepad1.left_stick_x);
            //robot.backRightDrive.setPower(gamepad1.left_stick_x);
        }
        if (gamepad1.left_stick_x <= -0.1 && gamepad1.right_stick_x < 0.1) //Strafing left
        {
            robot.frontLeftDrive.setPower(1*gamepad1.right_stick_x);
           // robot.frontRightDrive.setPower(gamepad1.right_stick_x);
           // robot.backLeftDrive.setPower(gamepad1.right_stick_x);
            robot.backRightDrive.setPower(-1*gamepad1.right_stick_x);
        }*/


        robot.frontLeftDrive.setPower(v1);
        robot.frontRightDrive.setPower(v2);
        robot.backLeftDrive.setPower(v3);
        robot.backRightDrive.setPower(v4);

        ////////////////////////////////////////////////////////////////////////////////////////////
        if (gamepad1.left_bumper)
        {
            robot.boxSlammer.setPower(-1);
        }
        if (gamepad1.right_bumper)
        {
            robot.boxSlammer.setPower(1);
        }
        robot.boxSlammer.setPower(0);


        /*if (gamepad1.left_trigger >= 0.5)
        {
            robot.boxStorage.setPower(-1);
        }
        if (gamepad1.right_trigger >= 0.5)
        {
            robot.boxStorage.setPower(1);
        }
        robot.boxStorage.setPower(0);*/
        robot.boxArm.setPower(-gamepad1.left_trigger + gamepad1.right_trigger);

        if (gamepad1.y)
        {
            robot.hanger.setPower(1);
        }
        if(gamepad1.a)
        {
            robot.hanger.setPower(-1);
        }
        robot.hanger.setPower(0);

        if (gamepad1.x)
        {
            robot.hopper.setPower(-1);
        }
        if (gamepad1.b)
        {
            robot.hopper.setPower(1);
        }
        robot.hopper.setPower(0);

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