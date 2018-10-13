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

    public void loop() //Method that runs in a loop when robot starts
    {
        robot.leftDrive.setPower(-gamepad1.left_stick_y); //Controls top left motor
        robot.rightDrive.setPower(-gamepad1.right_stick_y); //Controls bottom left motor

        if (this.gamepad1.x) { //If Gamepad 1 X Button Pressed
            robot.playMusic(this.hardwareMap.appContext); //Plays music
        }
        else if (gamepad1.y) { //If Gamepad 1 Y Button Pressed
            robot.stopMusic(); //Stops music.
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