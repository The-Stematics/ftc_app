package org.firstinspires.ftc.teamcode.Year_2018_19.Robot;

import android.content.Context;
import android.media.MediaPlayer;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.R;

import java.io.IOException;

public class TreadDriveRobot
{
    //The hardware components.
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor shoulderArm;
    public DcMotor racknPinion;
    public Servo claw;
    public float drivePower;

    public ColorSensor lineFollower;
    public IntegratingGyroscope gyro;
    public ModernRoboticsI2cGyro modernRoboticsI2cGyro;
//HAHAHA jake was here YEET
    private MediaPlayer mediaPlayer = null;

    //The hardware data
    public String alliance = null;

    public int rawX;
    public int rawY;
    public int rawZ;
    public int heading;
    public int integratedZ;
    public AngularVelocity rates;
    public float zAngle;
    public int zAxisOffset;
    public int zAxisScalingCoefficient;


    //Set up commands here.
    public void init(HardwareMap hwMap)    //Hardware and components initiates.
    {
        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");

        shoulderArm = hwMap.get (DcMotor.class, "shoulderArm");
        racknPinion = hwMap.get (DcMotor.class, "racknPinion");
        claw = hwMap.get (Servo.class, "claw");

        lineFollower = hwMap.get(ColorSensor.class, "lineFollower");
        modernRoboticsI2cGyro = hwMap.get(ModernRoboticsI2cGyro.class, "gyroSensor");
        gyro = (IntegratingGyroscope) modernRoboticsI2cGyro;

        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lineFollower.enableLed(true);

        modernRoboticsI2cGyro.calibrate();
        modernRoboticsI2cGyro.resetZAxisIntegrator();
    }

    public void safetyStop() //Safely stops all motors and other running components.
    {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        lineFollower.enableLed(false);
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

    public void driveForward(float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        leftDrive.setPower(drivePower);
        rightDrive.setPower(drivePower);
        wait(timeInMilliseconds);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void driveBackward(float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        leftDrive.setPower(-drivePower);
        rightDrive.setPower(-drivePower);
        wait(timeInMilliseconds);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void rotateLeft(float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        leftDrive.setPower(-drivePower);
        rightDrive.setPower(drivePower);
    }

    public void rotateRight(float drivePower, int timeInMilliseconds) throws InterruptedException
    {
        leftDrive.setPower(drivePower);
        rightDrive.setPower(-drivePower);
    }

    public void senseGyro()
    {
        rawX = modernRoboticsI2cGyro.rawX();
        rawY = modernRoboticsI2cGyro.rawY();
        rawZ = modernRoboticsI2cGyro.rawZ();
        heading = modernRoboticsI2cGyro.getHeading();
        integratedZ = modernRoboticsI2cGyro.getIntegratedZValue();

        rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
        zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

        zAxisOffset = modernRoboticsI2cGyro.getZAxisOffset();
        zAxisScalingCoefficient = modernRoboticsI2cGyro.getZAxisScalingCoefficient();
    }
}
