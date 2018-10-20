package org.firstinspires.ftc.teamcode.Year_2018_19.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Year_2018_19.Robot.TreadDriveRobot;

@TeleOp(name="TreadDriveTeleOp", group="TeleOpMode") //Makes Program Initiation
//@Disabled //Disables program initiation, if you wish for the program to not show up.

public class TreadDriveTeleOp extends OpMode //OpMode class
{

    private TreadDriveRobot robot = new TreadDriveRobot(); //Gets Tread Drive Robot program

    public void init() //Method that runs when robot initiates.
    {
        robot.init(hardwareMap); //Initiates hardware map method from Tread Drive Robot program
        robot.playR2D2Sound(this.hardwareMap.appContext); //Plays R2D2 Sound
        telemetry.addData("Status", "Robot has initiated!"); //Telemetry initiate message
        telemetry.update(); //Updates telemetry messages
    }

    public void start() //Method that runs when robot starts
    {
        robot.playBB8Sound(this.hardwareMap.appContext); //Plays BB8 sound
        telemetry.addData("Status", "Robot has started!"); //Telemetry start message
        telemetry.update(); //Updates telemetry messages
    }

    public void loop()
    {
        /*                         Drive Motors                              */
        if (gamepad1.left_stick_button)
        {
            robot.drivePower = 0.5f;
        }
        if (gamepad1.right_stick_button) {
            robot.drivePower = 1;
        }
        robot.leftDrive.setPower(-gamepad1.left_stick_y /* robot.drivePower*/);
        robot.rightDrive.setPower(-gamepad1.right_stick_y /* robot.drivePower*/);




        /*                         Music                              */

        if (gamepad1.x) { //If Gamepad 1 X Button Pressed
            robot.playMusic(this.hardwareMap.appContext); //Plays music
        }
        if (gamepad1.y) { //If Gamepad 1 Y Button Pressed
            robot.stopMusic(); //Stops music.
        }





        /*                         Shoulder Arm                              */
        robot.shoulderArm.setPower(-gamepad1.left_trigger);
        robot.shoulderArm.setPower(gamepad1.right_trigger);




        /*                         Rack and Pinion                              */

        if (gamepad1.a)
        {
            robot.racknPinion.setPower(-0.5);
        }
        else
        {
            robot.racknPinion.setPower(0);
        }

        if (gamepad1.b)
        {
            robot.racknPinion.setPower(0.5);
        }
        else
        {
            robot.racknPinion.setPower(0);
        }





        /*                         Claw                              */

        if (gamepad1.left_bumper)
        {
            robot.claw.setPosition(0); //Go down
        }

        if (gamepad1.right_bumper)
        {
            robot.claw.setPosition(0.3); //Go up
        }






        telemetry.addData("Left Drive", robot.leftDrive.getPower()); //Left Drive Telemetry Message
        telemetry.addData("Right Drive", robot.rightDrive.getPower()); //Right Drive Telemetry Message

        telemetry.update(); //Updates telemetry messages
    }

    public void stop() //Method that runs when robot stops
    {
        robot.safetyStop(); //Runs safety stop command
        telemetry.addData("Status", "Robot has stopped!"); //Telemetry stop message
        telemetry.update(); //Updates telemetry messages
    }
}