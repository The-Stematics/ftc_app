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
        robot.playR2D2Sound(this.hardwareMap.appContext); //Plays r2d2 Sound
        telemetry.addData("Status", "Robot has initiated!"); //Telemetry initiate message
        telemetry.update(); //Updates telemetry messages
    }

    public void start() //Method that runs when robot starts
    {
        robot.playBB8Sound(this.hardwareMap.appContext); //Plays bb8 sound
        robot.drivePower = 1;
        robot.claw.setPosition(robot.clawPos);
        robot.claw.setPosition(0.3);
        telemetry.addData("Status", "Robot has started!"); //Telemetry start message
        telemetry.update(); //Updates telemetry messages
    }

    public void loop()
    {
        /*                         Drive Motors                              */
        if (gamepad1.dpad_down)
        {
            robot.drivePower = 0.5f;
        }
        if (gamepad1.dpad_up) {
            robot.drivePower = 1;
        }
        robot.leftDrive.setPower(-gamepad1.left_stick_y * robot.drivePower);
        robot.rightDrive.setPower(-gamepad1.right_stick_y * robot.drivePower);




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
            robot.racknPinion.setPower(-1);
        }
        else
        {
            robot.racknPinion.setPower(0);
        }

        if (gamepad1.b)
        {
            robot.racknPinion.setPower(1);
        }
        else
        {
            robot.racknPinion.setPower(0);
        }





        /*                         Claw                              */

        robot.claw.setPosition(robot.clawPos);

        if (gamepad1.left_bumper && robot.clawPos > 0)
        {
            robot.clawPos -= 0.01;
        }

        if (gamepad1.right_bumper && robot.clawPos < 1)
        {
            robot.clawPos += 0.01;
        }





        telemetry.addData("Left Drive", robot.leftDrive.getPower()); //Left Drive Telemetry Message
        telemetry.addData("Right Drive", robot.rightDrive.getPower()); //Right Drive Telemetry Message
        telemetry.addData("Servo Claw Pos Int", robot.clawPos);
        telemetry.addData("Actual Claw", robot.claw.getPosition());

        telemetry.update(); //Updates telemetry messages
    }

    public void stop() //Method that runs when robot stops
    {
        robot.safetyStop(); //Runs safety stop command
        telemetry.addData("Status", "Robot has stopped!"); //Telemetry stop message
        telemetry.update(); //Updates telemetry messages
    }
}

/*
Teja is awarded a prize for being smart and everybody was happy.
The end
Nora lives in a mansion and she rules over everybody and nobody cared.
The end
Jake lives in a treehouse with Fin from 'Adventure Time'
The end
Grace gives grace to everybody and she ruled over Nora and she became goddess she also became a constelation everybody loved her.
The end
Aaron lives with a youtuber named Aphmau [her real name is Jess] and she has 4 million subs everybody shipped them.
The end

*/