package org.firstinspires.ftc.teamcode.Year_2018_19.Robot;

import android.content.Context;
import android.media.MediaPlayer;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.R;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class MecanumDriveRobot
{
    //Drivetrain Subsystem
    public DcMotor frontLeftDrive;
    public DcMotor frontRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor backRightDrive;

    //Arm Subsystem
    public DcMotor boxArm;
    public DcMotor boxSlammer;

    public Servo teamMarkerDropper;

    //public GyroSensor gyroSensor;
    //public ColorSensor lineFollower;

    private MediaPlayer mediaPlayer = null;
    public int state = 0;

    //The hardware data.

    //Set up commands here.
    public void init(HardwareMap hwMap)    //Hardware and components initiates.
    {
        frontLeftDrive = hwMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hwMap.get(DcMotor.class, "frontRightDrive");
        backLeftDrive = hwMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hwMap.get(DcMotor.class, "backRightDrive");

        boxArm = hwMap.get(DcMotor.class, "boxArm");
        boxSlammer = hwMap.get(DcMotor.class, "boxSlammer");
        teamMarkerDropper = hwMap.get(Servo.class, "teamMarkerDropper");
        //gyroSensor = hwMap.get(GyroSensor.class, "gyroSensor");
        //lineFollower = hwMap.get(ColorSensor.class, "lineFollower");

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);

        /*frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        gyroSensor.calibrate();
        lineFollower.enableLed(true);*/
    }

    public void safetyStop() //Safely stops all motors and other running components.
    {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        //lineFollower.enableLed(false);
        stopMusic();
    }

    public void playMusic(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.music);
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void playBB8Sound(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.bb8);
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }

    public void playR2D2Sound (Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.r2d2);
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }

    public void driveForward (float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        frontLeftDrive.setPower(drivePower);
        frontRightDrive.setPower(drivePower);
        backLeftDrive.setPower(drivePower);
        backRightDrive.setPower(drivePower);
        sleep(timeInMilliseconds);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void driveBackward (float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        frontLeftDrive.setPower(-drivePower);
        frontRightDrive.setPower(-drivePower);
        backLeftDrive.setPower(-drivePower);
        backRightDrive.setPower(-drivePower);
        sleep(timeInMilliseconds);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void strafeLeft (float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        frontLeftDrive.setPower(drivePower);
        frontRightDrive.setPower(-drivePower);
        backLeftDrive.setPower(drivePower);
        backRightDrive.setPower(-drivePower);
        sleep(timeInMilliseconds);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void strafeRight (float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        frontLeftDrive.setPower(drivePower);
        frontRightDrive.setPower(-drivePower);
        backLeftDrive.setPower(-drivePower);
        backRightDrive.setPower(drivePower);
        sleep(timeInMilliseconds);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void rotateLeft (float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        frontLeftDrive.setPower(-drivePower);
        frontRightDrive.setPower(drivePower);
        backLeftDrive.setPower(-drivePower);
        backRightDrive.setPower(drivePower);
        sleep(timeInMilliseconds);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void rotateRight (float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        frontLeftDrive.setPower(drivePower);
        frontRightDrive.setPower(-drivePower);
        backLeftDrive.setPower(drivePower);
        backRightDrive.setPower(-drivePower);
        sleep(timeInMilliseconds);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }

    public void hangerDown(float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        boxArm.setPower(-drivePower); //Move Arm Up
        sleep(timeInMilliseconds);
        boxSlammer.setPower(drivePower); //Bring Box Up
        sleep(timeInMilliseconds / 2);
        strafeLeft(1f, 1000); //Strafe Out of the hanger/deployer
        boxArm.setPower(drivePower); //Arm goes down
        sleep(timeInMilliseconds);
        boxSlammer.setPower(-drivePower); //Box Down
        sleep(timeInMilliseconds / 2);
        boxArm.setPower(0);
    }

    /*public void dropMarker(float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        boxSlammer.setPower(drivePower);
        sleep(timeInMilliseconds);
        boxSlammer.setPower(-drivePower);
        sleep(timeInMilliseconds);
        boxSlammer.setPower(0);
    }*/


}
