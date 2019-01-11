package org.firstinspires.ftc.teamcode.Year_2018_19.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Year_2018_19.Robot.MecanumDriveRobot;

@Autonomous(name = "MecanumDriveAutonomous", group = "AutonomousMode")
//@Disabled

public class MecanumDriveAutonomous extends LinearOpMode
{
    private MecanumDriveRobot robot = new MecanumDriveRobot();
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

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
        //robot.teamMarkerDropper.setPosition(1);
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
        robot.boxArm.setPower(-1);
        wait(1000);
        robot.boxArm.setPower(0);
        encoderDrive(1f, 2.5f*12, 2.5f*12, 2.5f*12, 2.5f*12, 1000); //Drive to crater
    }

    private void RightAlliance() throws InterruptedException
    {
        robot.driveForward(1.2f, 1500);
        robot.driveBackward(1f, 800);
        //robot.teamMarkerDropper.setPosition(0);
        wait(1000);
        //robot.teamMarkerDropper.setPosition(1);
        //wait(1000);
        robot.rotateRight(0.5f, 1800);
        robot.driveForward(1f, 4000);
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

    public void encoderDrive(double speed,
                             double frontLeftInches, double frontRightInches,
                             double backLeftInches, double backRightInches,
                             double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (frontLeftInches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.frontRightDrive.getCurrentPosition() + (int) (frontRightInches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.backLeftDrive.getCurrentPosition() + (int) (backLeftInches * COUNTS_PER_INCH);
            newBackRightTarget = robot.backRightDrive.getCurrentPosition() + (int) (backRightInches * COUNTS_PER_INCH);
            robot.frontLeftDrive.setTargetPosition(newFrontLeftTarget);
            robot.frontRightDrive.setTargetPosition(newFrontRightTarget);
            robot.backLeftDrive.setTargetPosition(newBackLeftTarget);
            robot.backRightDrive.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeftDrive.setPower(Math.abs(speed));
            robot.frontRightDrive.setPower(Math.abs(speed));
            robot.backLeftDrive.setPower(Math.abs(speed));
            robot.backRightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeftDrive.isBusy() && robot.frontRightDrive.isBusy() && robot.backLeftDrive.isBusy() && robot.backRightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d %7d %7d :%7d", newFrontLeftTarget, newFrontRightTarget, newBackLeftTarget, newBackRightTarget);
                telemetry.addData("Path2", "Running at %7d %7d %7d :%7d",
                        robot.frontLeftDrive.getCurrentPosition(),
                        robot.frontRightDrive.getCurrentPosition(),
                        robot.backLeftDrive.getCurrentPosition(),
                        robot.backRightDrive.getCurrentPosition());
                telemetry.update();
            }


            // Stop all motion;
            robot.frontLeftDrive.setPower(0);
            robot.frontRightDrive.setPower(0);
            robot.backLeftDrive.setPower(0);
            robot.backRightDrive.setPower(0);


            // Turn off RUN_TO_POSITION
            robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void encoderArm(double speed, double armInches, double secondaryArmInches, double timeoutS) {
        int newArmTarget;
        int newSecondaryArmTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newArmTarget = robot.frontLeftDrive.getCurrentPosition() + (int) (armInches * COUNTS_PER_INCH);
            newSecondaryArmTarget = robot.frontRightDrive.getCurrentPosition() + (int) (secondaryArmInches * COUNTS_PER_INCH);
            robot.boxArm.setTargetPosition(newArmTarget);
            robot.secondaryArmMotor.setTargetPosition(newSecondaryArmTarget);

            // Turn On RUN_TO_POSITION
            robot.boxArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.secondaryArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.boxArm.setPower(Math.abs(speed));
            robot.secondaryArmMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.boxArm.isBusy() && robot.secondaryArmMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d %7d %7d :%7d", newArmTarget, newSecondaryArmTarget);
                telemetry.addData("Path2", "Running at %7d %7d %7d :%7d",
                        robot.boxArm.getCurrentPosition(),
                        robot.secondaryArmMotor.getCurrentPosition());
                telemetry.update();
            }


            // Stop all motion;
            robot.boxArm.setPower(0);
            robot.secondaryArmMotor.setPower(0);


            // Turn off RUN_TO_POSITION
            robot.boxArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.secondaryArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
